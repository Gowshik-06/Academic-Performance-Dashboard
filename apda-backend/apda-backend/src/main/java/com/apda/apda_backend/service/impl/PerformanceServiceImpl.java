package com.apda.apda_backend.service.impl;

import com.apda.apda_backend.dto.PerformanceStatusDTO;
import com.apda.apda_backend.entity.Attendance;
import com.apda.apda_backend.entity.Marks;
import com.apda.apda_backend.entity.Student;
import com.apda.apda_backend.entity.User;
import com.apda.apda_backend.repository.AttendanceRepository;
import com.apda.apda_backend.repository.MarksRepository;
import com.apda.apda_backend.repository.StudentRepository;
import com.apda.apda_backend.repository.UserRepository;
import com.apda.apda_backend.service.PerformanceService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PerformanceServiceImpl implements PerformanceService {

    private final AttendanceRepository attendanceRepository;
    private final MarksRepository marksRepository;
    private final StudentRepository studentRepository;
    private final UserRepository userRepository;

    public PerformanceServiceImpl(AttendanceRepository attendanceRepository,
                                  MarksRepository marksRepository,
                                  StudentRepository studentRepository,
                                  UserRepository userRepository) {
        this.attendanceRepository = attendanceRepository;
        this.marksRepository = marksRepository;
        this.studentRepository = studentRepository;
        this.userRepository = userRepository;
    }

    @Override
    public PerformanceStatusDTO getMyPerformance() {

        // 🔐 get logged-in user
        String username = SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getName();

        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Student student = studentRepository.findByUser(user)
                .orElseThrow(() -> new RuntimeException("Student profile not found"));

        // 📊 GPA calculation
        List<Marks> marksList = marksRepository.findByStudent(student);

        double totalPoints = 0;
        int totalCredits = 0;

        for (Marks m : marksList) {
            int totalMarks = m.getInternalMarks() + m.getSemesterMarks();
            int gradePoint = totalMarks / 10;
            int credits = m.getSubject().getCredits();

            totalPoints += gradePoint * credits;
            totalCredits += credits;
        }

        double gpa = totalCredits == 0 ? 0 : totalPoints / totalCredits;

        // 📊 Attendance calculation
        List<Attendance> attendanceList =
                attendanceRepository.findByStudent(student);

        int totalClasses = 0;
        int attendedClasses = 0;

        for (Attendance a : attendanceList) {
            totalClasses += a.getTotalClasses();
            attendedClasses += a.getAttendedClasses();
        }

        double attendancePercentage =
                totalClasses == 0 ? 0 :
                        (attendedClasses * 100.0) / totalClasses;

        // 🚦 Performance status
        boolean lowAttendance = attendancePercentage < 75;
        boolean lowGpa = gpa < 5.0;

        String status;
        if (lowAttendance && lowGpa) {
            status = "CRITICAL";
        } else if (lowAttendance || lowGpa) {
            status = "WARNING";
        } else {
            status = "SAFE";
        }

        return new PerformanceStatusDTO(gpa, attendancePercentage, status);
    }
}

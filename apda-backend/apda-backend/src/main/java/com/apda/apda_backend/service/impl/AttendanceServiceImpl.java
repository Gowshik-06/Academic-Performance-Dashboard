package com.apda.apda_backend.service.impl;

import com.apda.apda_backend.entity.Attendance;
import com.apda.apda_backend.entity.Student;
import com.apda.apda_backend.entity.Subject;
import com.apda.apda_backend.entity.User;
import com.apda.apda_backend.repository.AttendanceRepository;
import com.apda.apda_backend.repository.StudentRepository;
import com.apda.apda_backend.repository.SubjectRepository;
import com.apda.apda_backend.repository.UserRepository;
import com.apda.apda_backend.service.AttendanceService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AttendanceServiceImpl implements AttendanceService {

    private final AttendanceRepository attendanceRepository;
    private final StudentRepository studentRepository;
    private final SubjectRepository subjectRepository;
    private final UserRepository userRepository;

    public AttendanceServiceImpl(AttendanceRepository attendanceRepository,
                                 StudentRepository studentRepository,
                                 SubjectRepository subjectRepository,
                                 UserRepository userRepository) {
        this.attendanceRepository = attendanceRepository;
        this.studentRepository = studentRepository;
        this.subjectRepository = subjectRepository;
        this.userRepository = userRepository;
    }

    // 🔐 FACULTY / ADMIN
    @Override
    public Attendance recordAttendance(Long studentId,
                                       Long subjectId,
                                       int totalClasses,
                                       int attendedClasses) {

        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Student not found"));

        Subject subject = subjectRepository.findById(subjectId)
                .orElseThrow(() -> new RuntimeException("Subject not found"));

        Attendance attendance = attendanceRepository
                .findByStudentAndSubject(student, subject)
                .orElse(new Attendance());

        attendance.setStudent(student);
        attendance.setSubject(subject);
        attendance.setTotalClasses(totalClasses);
        attendance.setAttendedClasses(attendedClasses);

        return attendanceRepository.save(attendance);
    }

    // 🔐 STUDENT – own attendance only
    @Override
    public List<Attendance> getMyAttendance() {

        String username = SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getName();

        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Student student = studentRepository.findByUser(user)
                .orElseThrow(() -> new RuntimeException("Student profile not found"));

        return attendanceRepository.findByStudent(student);
    }

    // 🔐 ADMIN / FACULTY
    @Override
    public List<Attendance> getAttendanceByStudent(Long studentId) {

        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Student not found"));

        return attendanceRepository.findByStudent(student);
    }
}

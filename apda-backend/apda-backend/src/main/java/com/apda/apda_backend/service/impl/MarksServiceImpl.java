package com.apda.apda_backend.service.impl;

import com.apda.apda_backend.entity.Marks;
import com.apda.apda_backend.entity.Student;
import com.apda.apda_backend.entity.Subject;
import com.apda.apda_backend.entity.User;
import com.apda.apda_backend.repository.MarksRepository;
import com.apda.apda_backend.repository.StudentRepository;
import com.apda.apda_backend.repository.SubjectRepository;
import com.apda.apda_backend.repository.UserRepository;
import com.apda.apda_backend.service.MarksService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MarksServiceImpl implements MarksService {

    private final MarksRepository marksRepository;
    private final StudentRepository studentRepository;
    private final SubjectRepository subjectRepository;
    private final UserRepository userRepository;

    public MarksServiceImpl(MarksRepository marksRepository,
                            StudentRepository studentRepository,
                            SubjectRepository subjectRepository,
                            UserRepository userRepository) {
        this.marksRepository = marksRepository;
        this.studentRepository = studentRepository;
        this.subjectRepository = subjectRepository;
        this.userRepository = userRepository;
    }

    // 🔐 FACULTY / ADMIN
    @Override
    public Marks recordMarks(Long studentId,
                             Long subjectId,
                             int internalMarks,
                             int semesterMarks) {

        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Student not found"));

        Subject subject = subjectRepository.findById(subjectId)
                .orElseThrow(() -> new RuntimeException("Subject not found"));

        Marks marks = marksRepository
                .findByStudentAndSubject(student, subject)
                .orElse(new Marks());

        marks.setStudent(student);
        marks.setSubject(subject);
        marks.setInternalMarks(internalMarks);
        marks.setSemesterMarks(semesterMarks);

        return marksRepository.save(marks);
    }

    // 🔐 STUDENT – own marks only
    @Override
    public List<Marks> getMyMarks() {

        String username = SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getName();

        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Student student = studentRepository.findByUser(user)
                .orElseThrow(() -> new RuntimeException("Student profile not found"));

        return marksRepository.findByStudent(student);
    }

    // 🔐 ADMIN / FACULTY
    @Override
    public List<Marks> getMarksByStudent(Long studentId) {

        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Student not found"));

        return marksRepository.findByStudent(student);
    }
}

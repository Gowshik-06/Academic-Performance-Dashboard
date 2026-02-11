package com.apda.apda_backend.service.impl;

import com.apda.apda_backend.dto.CreateStudentRequest;
import com.apda.apda_backend.entity.Student;
import com.apda.apda_backend.entity.User;
import com.apda.apda_backend.repository.StudentRepository;
import com.apda.apda_backend.repository.UserRepository;
import com.apda.apda_backend.service.StudentService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;
    private final UserRepository userRepository;

    public StudentServiceImpl(StudentRepository studentRepository,
                              UserRepository userRepository) {
        this.studentRepository = studentRepository;
        this.userRepository = userRepository;
    }

    // 🔐 ADMIN creates student
    @Override
    public Student createStudent(CreateStudentRequest request) {

        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        Student student = new Student();
        student.setRollNo(request.getRollNo());
        student.setDepartment(request.getDepartment());
        student.setYear(request.getYear());
        student.setSection(request.getSection());
        student.setUser(user);

        return studentRepository.save(student);
    }

    // 🔐 ADMIN access
    @Override
    public Student getStudentById(Long id) {
        return studentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found"));
    }

    // 🔐 STUDENT own profile
    @Override
    public Student getMyStudentProfile() {

        String username = SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getName();

        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return studentRepository.findByUser(user)
                .orElseThrow(() -> new RuntimeException("Student profile not found"));
    }
}

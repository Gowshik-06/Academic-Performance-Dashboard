package com.apda.apda_backend.controller;

import com.apda.apda_backend.dto.CreateStudentRequest;
import com.apda.apda_backend.dto.StudentResponseDTO;
import com.apda.apda_backend.entity.Student;
import com.apda.apda_backend.service.StudentService;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    // 🔐 ADMIN ONLY
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public StudentResponseDTO createStudent(
            @Valid @RequestBody CreateStudentRequest request) {

        Student student = studentService.createStudent(request);

        return mapToDTO(student);
    }

    @GetMapping("/{id}")
    public StudentResponseDTO getStudentById(@PathVariable Long id) {

        Student student = studentService.getStudentById(id);

        return mapToDTO(student);
    }

    @GetMapping("/test")
    public String test() {
        return "Student API is working";
    }

    // 🔁 ENTITY → DTO mapping
    private StudentResponseDTO mapToDTO(Student student) {
        return new StudentResponseDTO(
                student.getStudentId(),
                student.getRollNo(),
                student.getDepartment(),
                student.getYear(),
                student.getSection(),
                student.getUser().getUserId()
        );
    }
    @PreAuthorize("hasRole('STUDENT')")
    @GetMapping("/me")
    public StudentResponseDTO getMyProfile() {

        Student student = studentService.getMyStudentProfile();

        return mapToDTO(student);
    }

}

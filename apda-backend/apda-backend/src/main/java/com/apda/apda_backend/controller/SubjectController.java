package com.apda.apda_backend.controller;

import com.apda.apda_backend.entity.Subject;
import com.apda.apda_backend.service.SubjectService;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/subjects")
public class SubjectController {

    private final SubjectService subjectService;

    public SubjectController(SubjectService subjectService) {
        this.subjectService = subjectService;
    }

    // 🔐 ADMIN ONLY – create subject
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public Subject createSubject(@Valid @RequestBody Subject subject) {
        return subjectService.createSubject(subject);
    }

    // 🔐 ADMIN ONLY – get subject by id
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/{id}")
    public Subject getSubjectById(@PathVariable Long id) {
        return subjectService.getSubjectById(id);
    }

    // 🔐 ADMIN / FACULTY / STUDENT – view all subjects
    @PreAuthorize("hasAnyRole('ADMIN','FACULTY','STUDENT')")
    @GetMapping
    public List<Subject> getAllSubjects() {
        return subjectService.getAllSubjects();
    }
}

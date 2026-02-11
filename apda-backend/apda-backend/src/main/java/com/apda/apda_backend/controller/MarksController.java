package com.apda.apda_backend.controller;

import com.apda.apda_backend.entity.Marks;
import com.apda.apda_backend.service.MarksService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/marks")
public class MarksController {

    private final MarksService marksService;

    public MarksController(MarksService marksService) {
        this.marksService = marksService;
    }

    // 🔐 FACULTY / ADMIN – record or update marks
    @PreAuthorize("hasAnyRole('ADMIN','FACULTY')")
    @PostMapping
    public Marks recordMarks(@RequestParam Long studentId,
                             @RequestParam Long subjectId,
                             @RequestParam int internalMarks,
                             @RequestParam int semesterMarks) {

        return marksService.recordMarks(
                studentId, subjectId, internalMarks, semesterMarks);
    }

    // 🔐 STUDENT – view own marks
    @PreAuthorize("hasRole('STUDENT')")
    @GetMapping("/me")
    public List<Marks> getMyMarks() {
        return marksService.getMyMarks();
    }

    // 🔐 ADMIN / FACULTY – view marks by student
    @PreAuthorize("hasAnyRole('ADMIN','FACULTY')")
    @GetMapping("/student/{studentId}")
    public List<Marks> getMarksByStudent(@PathVariable Long studentId) {
        return marksService.getMarksByStudent(studentId);
    }
}
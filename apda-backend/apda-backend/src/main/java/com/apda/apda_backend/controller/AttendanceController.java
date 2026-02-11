package com.apda.apda_backend.controller;

import com.apda.apda_backend.entity.Attendance;
import com.apda.apda_backend.service.AttendanceService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/attendance")
public class AttendanceController {

    private final AttendanceService attendanceService;

    public AttendanceController(AttendanceService attendanceService) {
        this.attendanceService = attendanceService;
    }

    // 🔐 FACULTY / ADMIN – record or update attendance
    @PreAuthorize("hasAnyRole('ADMIN','FACULTY')")
    @PostMapping
    public Attendance recordAttendance(@RequestParam Long studentId,
                                       @RequestParam Long subjectId,
                                       @RequestParam int totalClasses,
                                       @RequestParam int attendedClasses) {

        return attendanceService.recordAttendance(
                studentId, subjectId, totalClasses, attendedClasses);
    }

    // 🔐 STUDENT – view own attendance
    @PreAuthorize("hasRole('STUDENT')")
    @GetMapping("/me")
    public List<Attendance> getMyAttendance() {
        return attendanceService.getMyAttendance();
    }

    // 🔐 ADMIN / FACULTY – view student attendance
    @PreAuthorize("hasAnyRole('ADMIN','FACULTY')")
    @GetMapping("/student/{studentId}")
    public List<Attendance> getAttendanceByStudent(@PathVariable Long studentId) {
        return attendanceService.getAttendanceByStudent(studentId);
    }
}

package com.apda.apda_backend.service;

import com.apda.apda_backend.entity.Attendance;

import java.util.List;

public interface AttendanceService {

    // FACULTY / ADMIN
    Attendance recordAttendance(Long studentId,
                                Long subjectId,
                                int totalClasses,
                                int attendedClasses);

    // STUDENT (own)
    List<Attendance> getMyAttendance();

    // ADMIN / FACULTY
    List<Attendance> getAttendanceByStudent(Long studentId);
}

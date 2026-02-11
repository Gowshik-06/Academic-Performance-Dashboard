package com.apda.apda_backend.service;

import com.apda.apda_backend.entity.Marks;

import java.util.List;

public interface MarksService {

    // 🔐 FACULTY / ADMIN
    Marks recordMarks(Long studentId,
                      Long subjectId,
                      int internalMarks,
                      int semesterMarks);

    // 🔐 STUDENT (own)
    List<Marks> getMyMarks();

    // 🔐 ADMIN / FACULTY
    List<Marks> getMarksByStudent(Long studentId);
}

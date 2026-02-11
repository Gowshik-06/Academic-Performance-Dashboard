package com.apda.apda_backend.service;

import com.apda.apda_backend.entity.Subject;

import java.util.List;

public interface SubjectService {

    Subject createSubject(Subject subject);

    Subject getSubjectById(Long id);

    List<Subject> getAllSubjects();
}

package com.apda.apda_backend.service.impl;

import com.apda.apda_backend.entity.Subject;
import com.apda.apda_backend.repository.SubjectRepository;
import com.apda.apda_backend.service.SubjectService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubjectServiceImpl implements SubjectService {

    private final SubjectRepository subjectRepository;

    public SubjectServiceImpl(SubjectRepository subjectRepository) {
        this.subjectRepository = subjectRepository;
    }

    @Override
    public Subject createSubject(Subject subject) {

        // Optional safety check (recommended)
        subjectRepository.findBySubjectCode(subject.getSubjectCode())
                .ifPresent(s -> {
                    throw new RuntimeException("Subject code already exists");
                });

        return subjectRepository.save(subject);
    }

    @Override
    public Subject getSubjectById(Long id) {
        return subjectRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Subject not found with id: " + id));
    }

    @Override
    public List<Subject> getAllSubjects() {
        return subjectRepository.findAll();
    }
}

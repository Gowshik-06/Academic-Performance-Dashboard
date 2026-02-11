package com.apda.apda_backend.repository;

import com.apda.apda_backend.entity.Subject;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import com.apda.apda_backend.entity.Subject;

public interface SubjectRepository extends JpaRepository<Subject, Long> {

    Optional<Subject> findBySubjectCode(String subjectCode);
}

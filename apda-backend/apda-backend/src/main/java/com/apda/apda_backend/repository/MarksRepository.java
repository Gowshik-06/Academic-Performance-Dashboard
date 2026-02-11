package com.apda.apda_backend.repository;

import com.apda.apda_backend.entity.Marks;
import com.apda.apda_backend.entity.Student;
import com.apda.apda_backend.entity.Subject;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MarksRepository extends JpaRepository<Marks, Long> {

    Optional<Marks> findByStudentAndSubject(Student student, Subject subject);

    List<Marks> findByStudent(Student student);
}

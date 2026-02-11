package com.apda.apda_backend.repository;

import com.apda.apda_backend.entity.Attendance;
import com.apda.apda_backend.entity.Student;
import com.apda.apda_backend.entity.Subject;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AttendanceRepository extends JpaRepository<Attendance, Long> {

    Optional<Attendance> findByStudentAndSubject(Student student, Subject subject);

    List<Attendance> findByStudent(Student student);
}

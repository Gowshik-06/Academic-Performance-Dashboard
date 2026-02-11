package com.apda.apda_backend.service;

import com.apda.apda_backend.dto.CreateStudentRequest;
import com.apda.apda_backend.entity.Student;

public interface StudentService {

    // 🔐 ADMIN
    Student createStudent(CreateStudentRequest request);

    // 🔐 ADMIN
    Student getStudentById(Long id);

    // 🔐 STUDENT (own profile only)
    Student getMyStudentProfile();
}

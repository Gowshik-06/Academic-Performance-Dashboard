package com.apda.apda_backend.dto;

public class StudentResponseDTO {

    private Long studentId;
    private String rollNo;
    private String department;
    private Integer year;
    private String section;
    private Long userId;

    public StudentResponseDTO(Long studentId, String rollNo, String department,
                              Integer year, String section, Long userId) {
        this.studentId = studentId;
        this.rollNo = rollNo;
        this.department = department;
        this.year = year;
        this.section = section;
        this.userId = userId;
    }

    // getters
    public Long getStudentId() {
        return studentId;
    }

    public String getRollNo() {
        return rollNo;
    }

    public String getDepartment() {
        return department;
    }

    public Integer getYear() {
        return year;
    }

    public String getSection() {
        return section;
    }

    public Long getUserId() {
        return userId;
    }
}

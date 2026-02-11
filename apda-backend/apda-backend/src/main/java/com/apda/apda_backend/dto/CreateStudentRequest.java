package com.apda.apda_backend.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Min;

public class CreateStudentRequest {

    @NotBlank(message = "Roll number is required")
    private String rollNo;

    @NotBlank(message = "Department is required")
    private String department;

    @NotNull(message = "Year is required")
    @Min(value = 1, message = "Year must be at least 1")
    private Integer year;

    @NotBlank(message = "Section is required")
    private String section;

    @NotNull(message = "User ID is required")
    private Long userId;

    // getters & setters
    public String getRollNo() {
        return rollNo;
    }

    public void setRollNo(String rollNo) {
        this.rollNo = rollNo;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}

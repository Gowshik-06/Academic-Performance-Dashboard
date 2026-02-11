package com.apda.apda_backend.dto;

public class PerformanceStatusDTO {

    private double gpa;
    private double attendancePercentage;
    private String status;

    public PerformanceStatusDTO(double gpa,
                                double attendancePercentage,
                                String status) {
        this.gpa = gpa;
        this.attendancePercentage = attendancePercentage;
        this.status = status;
    }

    public double getGpa() {
        return gpa;
    }

    public double getAttendancePercentage() {
        return attendancePercentage;
    }

    public String getStatus() {
        return status;
    }
}

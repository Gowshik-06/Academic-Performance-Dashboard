package com.apda.apda_backend.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.ManyToOne;

import com.apda.apda_backend.entity.Student;
import com.apda.apda_backend.entity.Subject;
import jakarta.persistence.*;

@Entity
@Table(name = "marks")
public class Marks {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 🔗 Student
    @ManyToOne
    @JoinColumn(name = "student_id", nullable = false)
    private Student student;

    // 🔗 Subject
    @ManyToOne
    @JoinColumn(name = "subject_id", nullable = false)
    private Subject subject;

    // 📊 Marks
    @Column(nullable = false)
    private int internalMarks;

    @Column(nullable = false)
    private int semesterMarks;

    // ✅ REQUIRED: no-args constructor
    public Marks() {}

    // ✅ Getters & Setters (THIS WAS MISSING)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public int getInternalMarks() {
        return internalMarks;
    }

    public void setInternalMarks(int internalMarks) {
        this.internalMarks = internalMarks;
    }

    public int getSemesterMarks() {
        return semesterMarks;
    }

    public void setSemesterMarks(int semesterMarks) {
        this.semesterMarks = semesterMarks;
    }
}

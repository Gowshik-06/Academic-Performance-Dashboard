package com.apda.apda_backend.service.impl;

import com.apda.apda_backend.entity.Marks;
import com.apda.apda_backend.entity.Student;
import com.apda.apda_backend.repository.MarksRepository;
import com.apda.apda_backend.service.GpaService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GpaServiceImpl implements GpaService {

    private final MarksRepository marksRepository;

    public GpaServiceImpl(MarksRepository marksRepository) {
        this.marksRepository = marksRepository;
    }

    @Override
    public double calculateGpa(Student student) {

        List<Marks> marksList = marksRepository.findByStudent(student);

        double totalPoints = 0;
        int totalCredits = 0;

        for (Marks m : marksList) {
            int totalMarks = m.getInternalMarks() + m.getSemesterMarks();
            int gradePoint = totalMarks / 10;   // simple logic
            int credits = m.getSubject().getCredits();

            totalPoints += gradePoint * credits;
            totalCredits += credits;
        }

        return totalCredits == 0 ? 0 : totalPoints / totalCredits;
    }
}

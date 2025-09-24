package edu.ccrm.domain;

import java.time.LocalDate;
import java.util.Optional;

public class Enrollment {
    private final String id;
    private final Student student;
    private final Course course;
    private final LocalDate enrolledOn;
    private Grade grade;

    public Enrollment(String id, Student student, Course course) {
        this.id = id;
        this.student = student;
        this.course = course;
        this.enrolledOn = LocalDate.now();
    }

    public String getId() { return id; }
    public Student getStudent() { return student; }
    public Course getCourse() { return course; }
    public LocalDate getEnrolledOn() { return enrolledOn; }

    public void recordGrade(Grade g) { this.grade = g; }
    public Optional<Grade> getGrade() { return Optional.ofNullable(grade); }

    @Override
    public String toString() {
        return String.format("Enrollment[id=%s, student=%s, course=%s, grade=%s]",
                id, student.getRegNo(), course.getCode(), grade == null ? "N/A" : grade);
    }
}
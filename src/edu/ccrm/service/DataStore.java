package edu.ccrm.service;

import edu.ccrm.domain.Student;
import edu.ccrm.domain.Course;
import edu.ccrm.domain.Enrollment;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class DataStore {
    // Singleton instance
    private static DataStore instance;

    // In-memory storage
    private final Map<String, Student> students = new ConcurrentHashMap<>();
    private final Map<String, Course> courses = new ConcurrentHashMap<>();
    private final Map<String, Enrollment> enrollments = new ConcurrentHashMap<>();

    // private constructor
    private DataStore() {}

    // get instance
    public static synchronized DataStore getInstance() {
        if (instance == null) {
            instance = new DataStore();
        }
        return instance;
    }

    // ====== Student Methods ======
    public void addStudent(Student s) {
        students.put(s.getRegNo(), s);
    }

    public Collection<Student> allStudents() {
        return students.values();
    }

    public Optional<Student> findByRegNo(String regNo) {
        return Optional.ofNullable(students.get(regNo));
    }

    // ====== Course Methods ======
    public void addCourse(Course c) {
        courses.put(c.getCode(), c);
    }

    public Collection<Course> allCourses() {
        return courses.values();
    }

    public Optional<Course> findCourse(String code) {
        return Optional.ofNullable(courses.get(code));
    }

    // ====== Enrollment Methods ======
    public void addEnrollment(Enrollment e) {
        enrollments.put(e.getId(), e);
    }

    public Collection<Enrollment> allEnrollments() {
        return enrollments.values();
    }

    public List<Enrollment> findEnrollmentsByStudent(String regNo) {
        List<Enrollment> list = new ArrayList<>();
        for (Enrollment e : enrollments.values()) {
            if (e.getStudent().getRegNo().equals(regNo)) {
                list.add(e);
            }
        }
        return list;
    }
}
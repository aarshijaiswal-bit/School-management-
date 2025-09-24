package edu.ccrm.domain;

public class Course {
    private final String code;      
    private final String title;     
    private final int credits;
    private String instructor;
    private String department;

    private Course(Builder b) {
        this.code = b.code;
        this.title = b.title;
        this.credits = b.credits;
        this.instructor = b.instructor;
        this.department = b.department;
    }
    public static class Builder {
        private String code;
        private String title;
        private int credits;
        private String instructor;
        private String department;

        public Builder code(String c) { this.code = c; return this; }
        public Builder title(String t) { this.title = t; return this; }
        public Builder credits(int c) { this.credits = c; return this; }
        public Builder instructor(String i) { this.instructor = i; return this; }
        public Builder department(String d) { this.department = d; return this; }

        public Course build() { return new Course(this); }
    }

    public String getCode() { return code; }
    public String getTitle() { return title; }
    public int getCredits() { return credits; }
    public String getInstructor() { return instructor; }
    public String getDepartment() { return department; }

    @Override
    public String toString() {
        return String.format("%s (%s) - %d credits, Instructor: %s, Dept: %s",
                title, code, credits, instructor, department);
    }
}
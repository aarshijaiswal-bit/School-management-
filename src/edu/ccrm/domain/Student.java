package edu.ccrm.domain;

public class Student extends Person {
    private String regNo;
    private boolean active = true;

    public Student(String id, String regNo, String fullName, String email) {
        super(id, fullName, email); // call Person constructor
        this.regNo = regNo;
    }

    @Override
    public String getRole() {
        return "STUDENT";
    }

    public String getRegNo() { return regNo; }
    public boolean isActive() { return active; }
    public void deactivate() { active = false; }

    @Override
    public String toString() {
        return String.format("%s, regNo=%s, active=%s", super.toString(), regNo, active);
    }
}
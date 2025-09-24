package edu.ccrm.domain;

public abstract class Person {
    protected String id;
    protected String fullName;
    protected String email;

    protected Person(String id, String fullName, String email) {
        this.id = id;
        this.fullName = fullName;
        this.email = email;
    }

    public String getId() { return id; }
    public String getFullName() { return fullName; }
    public String getEmail() { return email; }

    // Each subclass (like Student, Instructor) will define its role
    public abstract String getRole();

    @Override
    public String toString() {
        return String.format("%s[id=%s, name=%s, email=%s]", getRole(), id, fullName, email);
    }
}
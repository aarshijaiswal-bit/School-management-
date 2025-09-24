package edu.ccrm.cli;

import edu.ccrm.domain.Student;
import edu.ccrm.domain.Course;
import edu.ccrm.domain.Enrollment;
import edu.ccrm.domain.Grade;
import edu.ccrm.service.DataStore;
import edu.ccrm.io.ImportExportService;
import edu.ccrm.io.BackupService;

import java.util.Scanner;
import java.util.UUID;
import java.nio.file.Path;
import java.nio.file.Paths;

public class AppMain {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        DataStore ds = DataStore.getInstance();
        ImportExportService ioService = new ImportExportService();
        BackupService backupService = new BackupService();
        boolean running = true;

        while (running) {
            System.out.println("\n=== CCRM Menu ===");
            System.out.println("1. Add Student");
            System.out.println("2. List Students");
            System.out.println("3. Add Course");
            System.out.println("4. List Courses");
            System.out.println("5. Enroll Student in Course");
            System.out.println("6. List Enrollments");
            System.out.println("7. Record Grade");
            System.out.println("8. View Transcript");
            System.out.println("9. Import Students from CSV");
            System.out.println("10. Export Students to CSV");
            System.out.println("11. Import Courses from CSV");
            System.out.println("12. Export Courses to CSV");
            System.out.println("13. Backup Data Directory");
            System.out.println("14. Show Directory Size");
            System.out.println("0. Exit");
            System.out.print("Choose option: ");
            String opt = scanner.nextLine().trim();

            try {
                switch (opt) {
                    case "1" -> {
                        System.out.print("id: ");
                        String id = scanner.nextLine().trim();
                        System.out.print("regNo: ");
                        String regNo = scanner.nextLine().trim();
                        System.out.print("fullName: ");
                        String name = scanner.nextLine().trim();
                        System.out.print("email: ");
                        String email = scanner.nextLine().trim();

                        Student s = new Student(id, regNo, name, email);
                        ds.addStudent(s);
                        System.out.println("Student added!");
                    }
                    case "2" -> {
                        System.out.println("\n-- All Students --");
                        ds.allStudents().forEach(System.out::println);
                    }
                    case "3" -> {
                        System.out.print("code: ");
                        String code = scanner.nextLine().trim();
                        System.out.print("title: ");
                        String title = scanner.nextLine().trim();
                        System.out.print("credits: ");
                        int credits = Integer.parseInt(scanner.nextLine().trim());
                        System.out.print("instructor: ");
                        String instructor = scanner.nextLine().trim();
                        System.out.print("department: ");
                        String dept = scanner.nextLine().trim();

                        Course c = new Course.Builder()
                                .code(code)
                                .title(title)
                                .credits(credits)
                                .instructor(instructor)
                                .department(dept)
                                .build();

                        ds.addCourse(c);
                        System.out.println("Course added!");
                    }
                    case "4" -> {
                        System.out.println("\n-- All Courses --");
                        ds.allCourses().forEach(System.out::println);
                    }
                    case "5" -> {
                        System.out.print("Student regNo: ");
                        String regNo = scanner.nextLine().trim();
                        System.out.print("Course code: ");
                        String courseCode = scanner.nextLine().trim();

                        var studentOpt = ds.findByRegNo(regNo);
                        var courseOpt = ds.findCourse(courseCode);

                        if (studentOpt.isPresent() && courseOpt.isPresent()) {
                            String eid = UUID.randomUUID().toString();
                            Enrollment e = new Enrollment(eid, studentOpt.get(), courseOpt.get());
                            ds.addEnrollment(e);
                            System.out.println("Enrollment created: " + e);
                        } else {
                            System.out.println("Invalid student or course.");
                        }
                    }
                    case "6" -> {
                        System.out.println("\n-- All Enrollments --");
                        ds.allEnrollments().forEach(System.out::println);
                    }
                    case "7" -> {
                        System.out.print("Enrollment ID: ");
                        String eid = scanner.nextLine().trim();
                        System.out.print("Grade (S/A/B/C/D/E/F): ");
                        String gradeStr = scanner.nextLine().trim();

                        var eOpt = ds.allEnrollments().stream()
                                .filter(e -> e.getId().equals(eid))
                                .findFirst();

                        if (eOpt.isPresent()) {
                            try {
                                Grade g = Grade.valueOf(gradeStr);
                                eOpt.get().recordGrade(g);
                                System.out.println("Grade recorded.");
                            } catch (IllegalArgumentException ex) {
                                System.out.println("Invalid grade.");
                            }
                        } else {
                            System.out.println("Enrollment not found.");
                        }
                    }
                    case "8" -> {
                        System.out.print("Student regNo: ");
                        String regNo = scanner.nextLine().trim();
                        var enrollments = ds.findEnrollmentsByStudent(regNo);

                        if (enrollments.isEmpty()) {
                            System.out.println("No enrollments found.");
                        } else {
                            System.out.println("\n-- Transcript for " + regNo + " --");
                            int totalCredits = 0;
                            int totalPoints = 0;
                            for (Enrollment e : enrollments) {
                                var gOpt = e.getGrade();
                                System.out.println(e);
                                if (gOpt.isPresent()) {
                                    totalCredits += e.getCourse().getCredits();
                                    totalPoints += e.getCourse().getCredits() * gOpt.get().getPoints();
                                }
                            }
                            if (totalCredits > 0) {
                                double gpa = (double) totalPoints / totalCredits;
                                System.out.printf("GPA: %.2f\n", gpa);
                            } else {
                                System.out.println("No grades recorded yet.");
                            }
                        }
                    }
                    case "9" -> {
                        System.out.print("CSV path: ");
                        String path = scanner.nextLine().trim();
                        ioService.importStudentsCsv(Paths.get(path));
                        System.out.println("Students imported.");
                    }
                    case "10" -> {
                        System.out.print("CSV path: ");
                        String path = scanner.nextLine().trim();
                        ioService.exportStudentsCsv(Paths.get(path));
                        System.out.println("Students exported.");
                    }
                    case "11" -> {
                        System.out.print("CSV path: ");
                        String path = scanner.nextLine().trim();
                        ioService.importCoursesCsv(Paths.get(path));
                        System.out.println("Courses imported.");
                    }
                    case "12" -> {
                        System.out.print("CSV path: ");
                        String path = scanner.nextLine().trim();
                        ioService.exportCoursesCsv(Paths.get(path));
                        System.out.println("Courses exported.");
                    }
                    case "13" -> {
                        System.out.print("Source directory path: ");
                        String src = scanner.nextLine().trim();
                        System.out.print("Backup target root path: ");
                        String target = scanner.nextLine().trim();
                        Path backupDir = backupService.makeBackup(Paths.get(src), Paths.get(target));
                        System.out.println("Backup created at: " + backupDir);
                    }
                    case "14" -> {
                        System.out.print("Directory path: ");
                        String dir = scanner.nextLine().trim();
                        long size = backupService.computeDirectorySize(Paths.get(dir));
                        System.out.println("Directory size: " + size + " bytes");
                    }
                    case "0" -> running = false;
                    default -> System.out.println("Invalid option, try again.");
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }

        scanner.close();
        System.out.println("Goodbye!");
    }
}
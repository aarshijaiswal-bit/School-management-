package edu.ccrm.io;

import edu.ccrm.domain.Student;
import edu.ccrm.domain.Course;
import edu.ccrm.service.DataStore;

import java.io.IOException;
import java.nio.file.*;
import java.util.stream.Stream;

public class ImportExportService {

    public void importStudentsCsv(Path file) throws IOException {
        try (Stream<String> lines = Files.lines(file)) {
            lines.skip(1) // skip header
                 .forEach(line -> {
                     String[] parts = line.split(",");
                     if (parts.length >= 4) {
                         String id = parts[0].trim();
                         String regNo = parts[1].trim();
                         String fullName = parts[2].trim();
                         String email = parts[3].trim();
                         Student s = new Student(id, regNo, fullName, email);
                         DataStore.getInstance().addStudent(s);
                     }
                 });
        }
    }

    public void exportStudentsCsv(Path file) throws IOException {
        String header = "id,regNo,fullName,email\n";
        Files.writeString(file, header, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
        for (Student s : DataStore.getInstance().allStudents()) {
            String row = String.format("%s,%s,%s,%s\n",
                    s.getId(), s.getRegNo(), s.getFullName(), s.getEmail());
            Files.writeString(file, row, StandardOpenOption.APPEND);
        }
    }

    public void importCoursesCsv(Path file) throws IOException {
        try (Stream<String> lines = Files.lines(file)) {
            lines.skip(1)
                 .forEach(line -> {
                     String[] parts = line.split(",");
                     if (parts.length >= 5) {
                         String code = parts[0].trim();
                         String title = parts[1].trim();
                         int credits = Integer.parseInt(parts[2].trim());
                         String instructor = parts[3].trim();
                         String dept = parts[4].trim();
                         Course c = new Course.Builder()
                                 .code(code)
                                 .title(title)
                                 .credits(credits)
                                 .instructor(instructor)
                                 .department(dept)
                                 .build();
                         DataStore.getInstance().addCourse(c);
                     }
                 });
        }
    }

    public void exportCoursesCsv(Path file) throws IOException {
        String header = "code,title,credits,instructor,department\n";
        Files.writeString(file, header, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
        for (Course c : DataStore.getInstance().allCourses()) {
            String row = String.format("%s,%s,%d,%s,%s\n",
                    c.getCode(), c.getTitle(), c.getCredits(), c.getInstructor(), c.getDepartment());
            Files.writeString(file, row, StandardOpenOption.APPEND);
        }
    }
}

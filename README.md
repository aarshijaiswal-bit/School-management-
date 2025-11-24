# Course & Curriculum Resource Management System (CCRM)

## 📌 Project Overview
This is a simple Java-based **Course & Curriculum Resource Management System** (CCRM).  
The application is **console-based** and demonstrates:

- Object-Oriented Programming concepts  
- Collections Framework  
- Exception Handling  
- File Handling (CSV import/export)  
- Backup and NIO.2 features  
- Modular Java (module-info.java)  

It was built and tested using **Eclipse IDE for Java Developers (2025-09 release)**.

---

## ⚙️ Project Setup
1. Open **Eclipse IDE**.  
2. Create a new **Java Project** → Name it `firstproject`.  
3. Create these packages under `src`:  
   - `edu.ccrm.domain` → for entities (Student, Course, Enrollment, Grade)  
   - `edu.ccrm.service` → for `DataStore` (in-memory database)  
   - `edu.ccrm.io` → for `ImportExportService`, `BackupService`  
   - `edu.ccrm.cli` → for `AppMain` (menu interface)  
4. Run `AppMain` to start the menu-driven program.

---

## 🖥️ Application Menu
When you run `AppMain`, the following menu appears:
    1.   Add Student
	2.	List Students
	3.	Add Course
	4.	List Courses
	5.	Enroll Student in Course
	6.	List Enrollments
	7.	Record Grade
	8.	View Transcript
	9.	Import Students from CSV
	10.	Export Students to CSV
	11.	Import Courses from CSV
	12.	Export Courses to CSV
	13.	Backup Data Directory
	14.	Show Directory Size
	15.	Exit---

## 📂 CSV File Format

**students.csv**
id,regNo,fullName,email
s1,REG001,John Doe,john@example.com
s2,REG002,Maya Patel,maya@example.com

**courses.csv**
code,title,credits,instructor,department
CS101,Intro to CS,4,Dr. Smith,CS
MA101,Calculus,3,Dr. Johnson,Math

---

## 📚 Java Evolution (Short Notes)

- **JDK (Java Development Kit)** → contains compiler (`javac`), JRE, and development tools.  
- **JRE (Java Runtime Environment)** → only for running Java programs (no compiler).  
- **JVM (Java Virtual Machine)** → executes Java bytecode; platform-independent.  
- Java evolved from **JDK 1.0 (1996)** → to modern versions like **Java SE 21 (2023)**.  
- Features improved across versions: generics, streams, modules, records, pattern matching.  

---

## 🛠️ Mapping: Problem Statement → Implementation

| Requirement                          | Implemented In                          |
|--------------------------------------|------------------------------------------|
| Student management                   | `edu.ccrm.domain.Student` + `DataStore` |
| Course management                    | `edu.ccrm.domain.Course` + `DataStore`  |
| Enrollment system                    | `edu.ccrm.domain.Enrollment`            |
| Transcript with GPA                  | `AppMain` option 8                       |
| Collections usage                    | `HashMap`, `List`, `Optional` in `DataStore` |
| Exception handling                   | Try-catch in `AppMain`                   |
| File handling (import/export)        | `edu.ccrm.io.ImportExportService`       |
| Backup (NIO.2)                       | `edu.ccrm.io.BackupService`             |
| Modular programming                  | `module-info.java`                       |
| Menu-based CLI                       | `edu.ccrm.cli.AppMain`                   |

---

---

## Made by - Aarshi Jaiswal - 24BCE11393
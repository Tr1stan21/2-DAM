package main;

import dao.impl.AlumnoDaoImpl;
import model.Student;

import java.time.LocalDate;
import java.util.List;

public class MainApp {
    public static void main(String[] args) {

        AlumnoDaoImpl dao = new AlumnoDaoImpl();

        System.out.println("=== CREATE ===");
        Student s1 = new Student("Michael Brown", "m.brown@example.com", LocalDate.of(2025, 1, 12));
        s1 = dao.create(s1);
        System.out.println("Inserted: " + s1.toString());

        Student s2 = new Student("Sofia Castro", "sofia.castro@example.com", LocalDate.of(2025, 2, 3));
        s2 = dao.create(s2);
        System.out.println("Inserted: " + s2.toString());

        System.out.println("\n=== FIND BY ID ===");
        Student found = dao.findById(s1.getId());
        System.out.println("Found: " + found.toString());

        System.out.println("\n=== FIND ALL ===");
        List<Student> all = dao.findAll();
        all.forEach(System.out::println);

        System.out.println("\n=== UPDATE ===");
        s1.setName("Michael A. Brown");
        s1.setEmail("michael.brown@example.com");
        dao.update(s1);
        System.out.println("Updated: " + dao.findById(s1.getId()).toString());

        System.out.println("\n=== DELETE ===");
        dao.deleteById(s2.getId());
        System.out.println("Deleted student with ID " + s2.getId());

        System.out.println("\n=== FIND ALL AFTER DELETE ===");
        dao.findAll().forEach(System.out::println);

        // ==== BATCH INSERT ====
        System.out.println("\n=== BATCH INSERT ===");

        Student[] batchStudents = {
                new Student("Emily Johnson", "emily.johnson@example.com", LocalDate.of(2025, 3, 15)),
                new Student("Tomás Herrera", "tomas.herrera@example.com", LocalDate.of(2025, 4, 1)),
                new Student("Nadia Karim", "nadia.karim@example.com", LocalDate.of(2025, 4, 8)),
                new Student("Leo Schneider", "leo.schneider@example.com", LocalDate.of(2025, 5, 23))
        };

        dao.insertStudentsByBatches(batchStudents, 2);
        System.out.println("Batch inserted successfully");

        System.out.println("\n=== FINAL LIST ===");
        dao.findAll().forEach(System.out::println);
    }
}

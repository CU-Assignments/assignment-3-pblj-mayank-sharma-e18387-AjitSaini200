import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// Custom Exception for Course Full
class CourseFullException extends Exception {
    public CourseFullException(String message) {
        super(message);
    }
}

// Custom Exception for Prerequisite Not Met
class PrerequisiteNotMetException extends Exception {
    public PrerequisiteNotMetException(String message) {
        super(message);
    }
}

// Course class
class Course {
    private String name;
    private int capacity;
    private List<String> enrolledStudents;
    private String prerequisite;

    public Course(String name, int capacity, String prerequisite) {
        this.name = name;
        this.capacity = capacity;
        this.prerequisite = prerequisite;
        this.enrolledStudents = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public String getPrerequisite() {
        return prerequisite;
    }

    public boolean isFull() {
        return enrolledStudents.size() >= capacity;
    }

    public void enrollStudent(String studentName) throws CourseFullException {
        if (isFull()) {
            throw new CourseFullException("Error: Course " + name + " is full.");
        }
        enrolledStudents.add(studentName);
        System.out.println("Successfully enrolled in " + name);
    }
}

// Student class
class Student {
    private String name;
    private List<String> completedCourses;

    public Student(String name) {
        this.name = name;
        this.completedCourses = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void completeCourse(String courseName) {
        completedCourses.add(courseName);
    }

    public boolean hasCompleted(String courseName) {
        return completedCourses.contains(courseName);
    }
}

// Main class for the enrollment system
public class UniversityEnrollmentSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Create a course
        Course advancedJava = new Course("Advanced Java", 2, "Core Java");

        // Create a student
        System.out.print("Enter student name: ");
        String studentName = scanner.nextLine();
        Student student = new Student(studentName);

        // Simulate enrollment
        try {
            System.out.print("Enroll in Course: " + advancedJava.getName() + "\n");
            System.out.print("Prerequisite: " + advancedJava.getPrerequisite() + "\n");
            System.out.print("Status: ");
            String status = scanner.nextLine();

            // Check if prerequisite is met
            if (status.equalsIgnoreCase("Prerequisite not completed")) {
                throw new PrerequisiteNotMetException("Error: PrerequisiteNotMetException - Complete " + advancedJava.getPrerequisite() + " before enrolling in " + advancedJava.getName() + ".");
            }

            // Attempt to enroll the student
            advancedJava.enrollStudent(student.getName());

        } catch (PrerequisiteNotMetException e) {
            System.out.println(e.getMessage());
        } catch (CourseFullException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.out.println("An unexpected error occurred: " + e.getMessage());
        } finally {
            // Display remaining capacity
            System.out.println("Remaining capacity for " + advancedJava.getName() + ": " + (advancedJava.isFull() ? 0 : 2 - advancedJava.enrolledStudents.size()));
            // Close the scanner
            scanner.close();
        }
    }
}

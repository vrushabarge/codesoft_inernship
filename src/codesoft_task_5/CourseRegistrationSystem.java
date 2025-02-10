/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package codesoft_task_5;

/**
 *
 * @author Admin
 */
import java.util.*;

class Course {
    String courseCode;
    String title;
    String description;
    int capacity;
    String schedule;
    int enrolledStudents;

    public Course(String courseCode, String title, String description, int capacity, String schedule) {
        this.courseCode = courseCode;
        this.title = title;
        this.description = description;
        this.capacity = capacity;
        this.schedule = schedule;
        this.enrolledStudents = 0;
    }

    public boolean isFull() {
        return enrolledStudents >= capacity;
    }

    public void enrollStudent() {
        enrolledStudents++;
    }

    public void dropStudent() {
        enrolledStudents--;
    }

    @Override
    public String toString() {
        return String.format("%s: %s\nDescription: %s\nSchedule: %s\nAvailable Slots: %d/%d\n",
                courseCode, title, description, schedule, (capacity - enrolledStudents), capacity);
    }
}

class Student {
    List<Course> registeredCourses;

    public Student(String studentID, String name) {
        this.registeredCourses = new ArrayList<>();
    }

    public void registerCourse(Course course) {
        if (!course.isFull()) {
            registeredCourses.add(course);
            course.enrollStudent();
            System.out.println("Successfully registered for " + course.title);
        } else {
            System.out.println("Course is full. Cannot register for " + course.title);
        }
    }

    public void dropCourse(Course course) {
        if (registeredCourses.contains(course)) {
            registeredCourses.remove(course);
            course.dropStudent();
            System.out.println("Successfully dropped " + course.title);
        } else {
            System.out.println("You are not registered for " + course.title);
        }
    }

    public void displayRegisteredCourses() {
        if (registeredCourses.isEmpty()) {
            System.out.println("You are not registered for any courses.");
        } else {
            System.out.println("Registered Courses:");
            for (Course course : registeredCourses) {
                System.out.println(course);
            }
        }
    }
}

public class CourseRegistrationSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Course Database
        List<Course> courses = new ArrayList<>();
        courses.add(new Course("CSCI101", "Introduction to Programming", "Learn the basics of programming.", 30, "Mon/Wed 10:00 AM"));
        courses.add(new Course("MATH201", "Calculus I", "Introduction to differential and integral calculus.", 25, "Tue/Thu 2:00 PM"));
        courses.add(new Course("PHYS101", "Physics for Engineers", "Fundamentals of physics for engineering students.", 20, "Mon/Wed/Fri 1:00 PM"));

        // Student Database
        Map<String, Student> students = new HashMap<>();

        while (true) {
            System.out.println("\n1. Display Available Courses");
            System.out.println("2. Register as a Student");
            System.out.println("3. Register for a Course");
            System.out.println("4. Drop a Course");
            System.out.println("5. View Registered Courses");
            System.out.println("6. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1 -> {
                    System.out.println("\nAvailable Courses:");
                    for (Course course : courses) {
                        System.out.println(course);
                    }
                }

                case 2 -> {
                    System.out.print("Enter your student ID: ");
                    String studentID = scanner.nextLine();
                    System.out.print("Enter your name: ");
                    String name = scanner.nextLine();
                    students.put(studentID, new Student(studentID, name));
                    System.out.println("Student registered successfully!");
                }

                case 3 -> {
                    System.out.print("Enter your student ID: ");
                    String studentID = scanner.nextLine();
                    Student student = students.get(studentID);
                    if (student == null) {
                        System.out.println("Student not found. Please register first.");
                        break;
                    }
                    System.out.println("\nAvailable Courses:");
                    for (int i = 0; i < courses.size(); i++) {
                        System.out.println((i + 1) + ". " + courses.get(i).title);
                    }
                    System.out.print("Select a course number to register: ");
                    int courseIndex = scanner.nextInt() - 1;
                    scanner.nextLine(); // Consume newline
                    if (courseIndex >= 0 && courseIndex < courses.size()) {
                        student.registerCourse(courses.get(courseIndex));
                    } else {
                        System.out.println("Invalid course selection.");
                    }
                }

                case 4 -> {
                    System.out.print("Enter your student ID: ");
                    String studentID = scanner.nextLine();
                    Student student = students.get(studentID);
                    if (student == null) {
                        System.out.println("Student not found. Please register first.");
                        break;
                    }
                    student.displayRegisteredCourses();
                    System.out.print("Enter the course title to drop: ");
                    String courseTitle = scanner.nextLine();
                    Course courseToDrop = null;
                    for (Course course : student.registeredCourses) {
                        if (course.title.equalsIgnoreCase(courseTitle)) {
                            courseToDrop = course;
                            break;
                        }
                    }
                    if (courseToDrop != null) {
                        student.dropCourse(courseToDrop);
                    } else {
                        System.out.println("You are not registered for this course.");
                    }
                }

                case 5 -> {
                    System.out.print("Enter your student ID: ");
                    String studentID = scanner.nextLine();
                    Student student = students.get(studentID);
                    if (student == null) {
                        System.out.println("Student not found. Please register first.");
                        break;
                    }
                    student.displayRegisteredCourses();
                }

                case 6 -> {
                    System.out.println("Exiting...");
                    scanner.close();
                    return;
                }

                default -> System.out.println("Invalid option. Please try again.");
            }
        }
    }
}
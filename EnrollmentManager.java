import java.io.*;
import java.util.*;

public class EnrollmentManager {
    private List<Student> students = new ArrayList<>();
    private List<Course> courses = new ArrayList<>();
    private Map<String, List<String>> enrollments = new HashMap<>();

    public void loadData() throws IOException {
        loadStudents();
        loadCourses();
        loadEnrollments();
    }

    private void loadStudents() throws IOException {
        File file = new File("data/students.txt");
        if (!file.exists()) return;
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                students.add(Student.fromString(line));
            }
        }
    }

    private void loadCourses() throws IOException {
        File file = new File("data/courses.txt");
        if (!file.exists()) return;
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                courses.add(Course.fromString(line));
            }
        }
    }

    private void loadEnrollments() throws IOException {
        File file = new File("data/enrollments.txt");
        if (!file.exists()) return;
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                enrollments.computeIfAbsent(parts[0], k -> new ArrayList<>()).add(parts[1]);
            }
        }
    }

    public void saveData() throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("data/students.txt"))) {
            for (Student s : students) writer.write(s.toString() + "\n");
        }
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("data/courses.txt"))) {
            for (Course c : courses) writer.write(c.toString() + "\n");
        }
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("data/enrollments.txt"))) {
            for (Map.Entry<String, List<String>> entry : enrollments.entrySet()) {
                for (String course : entry.getValue()) {
                    writer.write(entry.getKey() + "," + course + "\n");
                }
            }
        }
    }

    public void addStudent(String id, String name) {
        students.add(new Student(id, name));
    }

    public void addCourse(String code, String title) {
        courses.add(new Course(code, title));
    }

    public void enrollStudent(String studentId, String courseCode) {
        enrollments.computeIfAbsent(studentId, k -> new ArrayList<>()).add(courseCode);
    }

    public void viewStudents() {
        for (Student s : students) {
            System.out.println("ID: " + s.getId() + ", Name: " + s.getName());
        }
    }

    public void viewCourses() {
        for (Course c : courses) {
            System.out.println("Code: " + c.getCode() + ", Title: " + c.getTitle());
        }
    }

    public void viewEnrollments() {
        for (String studentId : enrollments.keySet()) {
            System.out.print("Student ID: " + studentId + " -> Courses: ");
            for (String course : enrollments.get(studentId)) {
                System.out.print(course + " ");
            }
            System.out.println();
        }
    }
}

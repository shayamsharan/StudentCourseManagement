import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        EnrollmentManager manager = new EnrollmentManager();
        Scanner sc = new Scanner(System.in);
        try {
            manager.loadData();
        } catch (Exception e) {
            System.out.println("Error loading data: " + e.getMessage());
        }

        while (true) {
            System.out.println("\n--- Student Course Management ---");
            System.out.println("1. Add Student");
            System.out.println("2. Add Course");
            System.out.println("3. Enroll Student");
            System.out.println("4. View Students");
            System.out.println("5. View Courses");
            System.out.println("6. View Enrollments");
            System.out.println("0. Exit");
            System.out.print("Enter choice: ");
            int choice = sc.nextInt();
            sc.nextLine();

            try {
                switch (choice) {
                    case 1:
                        System.out.print("Enter student ID: ");
                        String sid = sc.nextLine();
                        System.out.print("Enter name: ");
                        String sname = sc.nextLine();
                        manager.addStudent(sid, sname);
                        break;
                    case 2:
                        System.out.print("Enter course code: ");
                        String ccode = sc.nextLine();
                        System.out.print("Enter title: ");
                        String ctitle = sc.nextLine();
                        manager.addCourse(ccode, ctitle);
                        break;
                    case 3:
                        System.out.print("Enter student ID: ");
                        String stid = sc.nextLine();
                        System.out.print("Enter course code: ");
                        String corcode = sc.nextLine();
                        manager.enrollStudent(stid, corcode);
                        break;
                    case 4:
                        manager.viewStudents();
                        break;
                    case 5:
                        manager.viewCourses();
                        break;
                    case 6:
                        manager.viewEnrollments();
                        break;
                    case 0:
                        manager.saveData();
                        System.out.println("Exiting...");
                        return;
                    default:
                        System.out.println("Invalid choice.");
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }
}

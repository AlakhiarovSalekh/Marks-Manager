import java.util.Scanner;
import java.util.List;

public class ConsoleApp {

    public static void main(String[] args) {

        StudentManager manager = new StudentManager();
        Scanner sc = new Scanner(System.in);
        String defaultFile = "students.csv";

        while (true) {
            System.out.println("\n--- Marks Manager (Console) ---");
            System.out.println("1. Add Student Marks");
            System.out.println("2. View All Students");
            System.out.println("3. Search Student");
            System.out.println("4. Delete Student");
            System.out.println("5. Show Class Average Percentage");
            System.out.println("6. Show Topper");
            System.out.println("7. Show Pass/Fail Count");
            System.out.println("8. Save to File");
            System.out.println("9. Load from File");
            System.out.println("10. Exit");
            System.out.print("Enter option: ");

            int choice;
            try {
                choice = sc.nextInt();
            } catch (Exception e) {
                System.out.println("Please enter a valid number.");
                sc.nextLine(); // clear invalid input
                continue;
            }
            sc.nextLine(); // clear buffer

            switch (choice) {

                case 1:
                    System.out.print("Name: ");
                    String name = sc.nextLine();

                    System.out.print("Marks in Subject 1: ");
                    int m1 = sc.nextInt();
                    System.out.print("Marks in Subject 2: ");
                    int m2 = sc.nextInt();
                    System.out.print("Marks in Subject 3: ");
                    int m3 = sc.nextInt();
                    sc.nextLine(); // clear buffer

                    Student s = new Student(name, m1, m2, m3);
                    manager.addStudent(s);
                    System.out.println("Student marks added!");
                    break;

                case 2:
                    if (manager.getStudents().isEmpty()) {
                        System.out.println("No records available.");
                    } else {
                        System.out.println("--- All Students ---");
                        for (Student st : manager.getStudents()) {
                            System.out.println(st);
                        }
                    }
                    break;

                case 3:
                    System.out.print("Enter name to search: ");
                    String sname = sc.nextLine();
                    Student found = manager.searchStudent(sname);
                    if (found != null) {
                        System.out.println("Found: " + found);
                    } else {
                        System.out.println("Student not found.");
                    }
                    break;

                case 4:
                    System.out.print("Enter name to delete: ");
                    String dname = sc.nextLine();
                    if (manager.deleteStudent(dname)) {
                        System.out.println("Deleted successfully.");
                    } else {
                        System.out.println("Not found.");
                    }
                    break;

                case 5:
                    double avg = manager.getClassAveragePercentage();
                    System.out.println("Class Average Percentage: " + String.format("%.2f", avg));
                    break;

                case 6:
                    Student topper = manager.getTopper();
                    if (topper == null) {
                        System.out.println("No records to find topper.");
                    } else {
                        System.out.println("Topper:");
                        System.out.println(topper);
                    }
                    break;

                case 7:
                    int pass = manager.getPassCount();
                    int fail = manager.getFailCount();
                    System.out.println("Pass count : " + pass);
                    System.out.println("Fail count : " + fail);
                    break;

                case 8:
                    try {
                        MarksStorage.saveToFile(manager.getStudents(), defaultFile);
                        System.out.println("Saved to file: " + defaultFile);
                    } catch (Exception ex) {
                        System.out.println("Error saving file: " + ex.getMessage());
                    }
                    break;

                case 9:
                    try {
                        List<Student> list = MarksStorage.loadFromFile(defaultFile);
                        manager.setStudents(list);
                        System.out.println("Loaded " + list.size() + " records from " + defaultFile);
                    } catch (Exception ex) {
                        System.out.println("Error loading file: " + ex.getMessage());
                    }
                    break;

                case 10:
                    System.out.println("Exiting...");
                    sc.close();
                    System.exit(0);

                default:
                    System.out.println("Invalid option.");
            }
        }
    }
}

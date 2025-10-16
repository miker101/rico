package mine;

import java.util.*;

public class Performance {

    private students studentModule; // shared student data
    private Map<String, double[]> performanceData = new LinkedHashMap<>();
    private final String[] subjects = {"Java", "Data Structures", "Databases", "Networks", "Web Development"};

    // Constructor
    public Performance(students studentModule) {
        this.studentModule = studentModule;
    }

    // Default constructor (for testing)
    public Performance() {}

    public void run() {
        Scanner input = new Scanner(System.in);
        int choice = 0;

        do {
            System.out.println("\n--- PERFORMANCE ANALYTICS ---");
            System.out.println("1. Record Student Marks");
            System.out.println("2. View Individual Performance");
            System.out.println("3. View All Student Performance");
            System.out.println("4. Generate Performance Report");
            System.out.println("5. Back to Main Menu");
            System.out.print("Enter choice: ");

            if (!input.hasNextInt()) {
                System.out.println(" Please enter a number.");
                input.nextLine();
                continue;
            }

            choice = input.nextInt();
            input.nextLine();

            switch (choice) {
                case 1:
                    recordMarks(input);
                    break;
                case 2:
                    viewStudentPerformance(input);
                    break;
                case 3:
                    displayAllPerformance();
                    break;
                case 4:
                    generateReport();
                    break;
                case 5:
                    System.out.println("Returning to Main Menu...");
                    break;
                default:
                    System.out.println(" Invalid choice!");
            }

        } while (choice != 5);
    }

    // Record marks for a student
    private void recordMarks(Scanner input) {
        System.out.print("Enter Student ID: ");
        String studentId = input.nextLine().trim();

        if (!isStudentRegistered(studentId)) {
            System.out.println("❌ Student not found. Register first in Student Module.");
            return;
        }

        double[] marks = new double[subjects.length];

        for (int i = 0; i < subjects.length; i++) {
            System.out.print("Enter marks for " + subjects[i] + ": ");
            while (!input.hasNextDouble()) {
                System.out.println("⚠️ Invalid input. Enter numeric marks.");
                input.nextLine();
            }
            marks[i] = input.nextDouble();
            input.nextLine();
        }

        performanceData.put(studentId, marks);
        System.out.println("Marks recorded successfully for " + studentId);
    }

    // View one student's marks
    private void viewStudentPerformance(Scanner input) {
        System.out.print("Enter Student ID: ");
        String id = input.nextLine().trim();

        if (!performanceData.containsKey(id)) {
            System.out.println("No performance data found for this student.");
            return;
        }

        double[] marks = performanceData.get(id);
        System.out.println("\n Performance for " + id + ":");
        double total = 0;
        for (int i = 0; i < subjects.length; i++) {
            System.out.println(subjects[i] + ": " + marks[i]);
            total += marks[i];
        }
        double avg = total / subjects.length;
        System.out.println("Average: " + avg);
        System.out.println("Grade: " + getGrade(avg));
        System.out.println("Status: " + (avg >= 50 ? "✅ Pass" : "❌ Fail"));
    }

    // Display all student performances
    private void displayAllPerformance() {
        if (performanceData.isEmpty()) {
            System.out.println(" No performance data recorded yet.");
            return;
        }

        System.out.println("\n📊 ALL STUDENT PERFORMANCE:");
        for (Map.Entry<String, double[]> entry : performanceData.entrySet()) {
            String id = entry.getKey();
            double[] marks = entry.getValue();
            double avg = Arrays.stream(marks).average().orElse(0);
            System.out.println(id + " | Avg: " + avg + " | Grade: " + getGrade(avg));
        }
    }

    // Generate performance report
    private void generateReport() {
        if (performanceData.isEmpty()) {
            System.out.println("No data to generate report.");
            return;
        }

        double classTotal = 0;
        double topAvg = 0;
        String topStudent = "";

        for (Map.Entry<String, double[]> entry : performanceData.entrySet()) {
            double avg = Arrays.stream(entry.getValue()).average().orElse(0);
            classTotal += avg;
            if (avg > topAvg) {
                topAvg = avg;
                topStudent = entry.getKey();
            }
        }

        double classAverage = classTotal / performanceData.size();

        System.out.println("\n📈 PERFORMANCE REPORT");
        System.out.println("Total Students with Records: " + performanceData.size());
        System.out.println("Class Average: " + String.format("%.2f", classAverage));
        System.out.println("Top Student: " + topStudent + " (Avg: " + String.format("%.2f", topAvg) + ")");
    }

    // Check if a student is registered
    private boolean isStudentRegistered(String id) {
        if (studentModule == null) return false;
        List<Map<String, Object>> data = studentModule.getFeeData();
        for (Map<String, Object> entry : data) {
            if (entry.get("id").equals(id)) return true;
        }
        return false;
    }

    // Determine grade
    private String getGrade(double avg) {
        if (avg >= 80) return "A";
        else if (avg >= 70) return "B";
        else if (avg >= 60) return "C";
        else if (avg >= 50) return "D";
        else return "E";
    }
}

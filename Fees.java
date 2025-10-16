package mine;

import java.util.*;

public class Fees {

    private static final double TOTAL_FEES = 70000;
    private students studentModule;

    public Fees() {
        // default, will be set by MainMenu later if needed
    }

    // If you want to share data directly like courses do:
    public Fees(students studentModule) {
        this.studentModule = studentModule;
    }

    public void run() {
        Scanner input = new Scanner(System.in);
        int choice = 0;

        do {
            System.out.println("\n--- FEE TRACKING ---");
            System.out.println("1. Display Fee Status for All Students");
            System.out.println("2. Generate Fee Report");
            System.out.println("3. Back to Main Menu");
            System.out.print("Enter choice: ");

            if (!input.hasNextInt()) {
                System.out.println("⚠️ Please enter a valid number.");
                input.nextLine();
                continue;
            }

            choice = input.nextInt();
            input.nextLine();

            switch (choice) {
                case 1:
                    displayFeeStatus();
                    break;
                case 2:
                    generateFeeReport();
                    break;
                case 3:
                    System.out.println("Returning to Main Menu...");
                    break;
                default:
                    System.out.println("⚠️ Invalid choice!");
            }

        } while (choice != 3);
    }

    public void displayFeeStatus() {
        if (studentModule == null) {
            System.out.println("⚠️ Student data not linked.");
            return;
        }

        List<Map<String, Object>> data = studentModule.getFeeData();

        if (data.isEmpty()) {
            System.out.println("⚠️ No students found.");
            return;
        }

        System.out.println("\n📊 Student Fee Status:");
        for (Map<String, Object> entry : data) {
            String id = (String) entry.get("id");
            String name = (String) entry.get("name");
            double paid = (double) entry.get("amountPaid");
            String status = paid >= TOTAL_FEES ? "✅ Finished" : "❌ Has Arrears (" + (TOTAL_FEES - paid) + ")";
            System.out.println(id + " | " + name + " | Paid: " + paid + " | " + status);
        }
    }

    public void generateFeeReport() {
        if (studentModule == null) {
            System.out.println("⚠️ Student data not linked.");
            return;
        }

        List<Map<String, Object>> data = studentModule.getFeeData();
        if (data.isEmpty()) {
            System.out.println("⚠️ No student data to report.");
            return;
        }

        double totalCollected = 0;
        int cleared = 0;
        int arrears = 0;

        for (Map<String, Object> entry : data) {
            double paid = (double) entry.get("amountPaid");
            totalCollected += paid;
            if (paid >= TOTAL_FEES) cleared++;
            else arrears++;
        }

        System.out.println("\n📘 FEE REPORT");
        System.out.println("Total Students: " + data.size());
        System.out.println("Students Cleared: " + cleared);
        System.out.println("Students with Arrears: " + arrears);
        System.out.println("Total Collected: " + totalCollected);
        System.out.println("Outstanding: " + ((TOTAL_FEES * data.size()) - totalCollected));
    }
}

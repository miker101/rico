package mine;

import java.util.*;

public class Courses {

    private String[] availableCourses = {
        "Mathematics", "Java Programming", "Python", "Networks", "Artificial Intelligence"
    };

    private final int MAX_CAPACITY = 3; // ✅ maximum students per course

    // Each course name → list of assigned student IDs
    private Map<String, List<String>> courseAllocations = new LinkedHashMap<>();

    private students studentModule; // shared reference

    public Courses(students studentModule) {
        this.studentModule = studentModule;
        // Initialize course map with empty lists
        for (String course : availableCourses) {
            courseAllocations.put(course, new ArrayList<>());
        }
    }

    public void run() {
        Scanner input = new Scanner(System.in);
        int choice = 0;

        do {
            System.out.println("\n--- COURSE SCHEDULING ---");
            System.out.println("1. Auto Assign Courses to Students");
            System.out.println("2. Display Course Allocations");
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
                    autoAssignCourses();
                    break;

                case 2:
                    displayAllocations();
                    break;

                case 3:
                    System.out.println("Returning to Main Menu...");
                    break;

                default:
                    System.out.println("⚠️ Invalid choice! Try again.");
            }

        } while (choice != 3);
    }

    // Auto assign courses considering max capacity
    public void autoAssignCourses() {
        List<String> allStudents = studentModule.getAllStudentIDs();

        if (allStudents == null || allStudents.isEmpty()) {
            System.out.println("⚠️ No students registered yet.");
            return;
        }

        // Reset old allocations
        for (String course : availableCourses) {
            courseAllocations.get(course).clear();
        }

        int assignedCount = 0;
        int courseIndex = 0;

        for (String id : allStudents) {
            boolean assigned = false;

            // loop until we find a course that has a free slot
            int attempts = 0;
            while (!assigned && attempts < availableCourses.length) {
                String currentCourse = availableCourses[courseIndex];
                List<String> currentList = courseAllocations.get(currentCourse);

                if (currentList.size() < MAX_CAPACITY) {
                    currentList.add(id);
                    assigned = true;
                    assignedCount++;
                } else {
                    // course full → move to next
                    courseIndex = (courseIndex + 1) % availableCourses.length;
                    attempts++;
                }
            }

            // move to next course after assigning
            courseIndex = (courseIndex + 1) % availableCourses.length;

            if (!assigned) {
                System.out.println("⚠️ Could not assign course for student " + id + " (all full)");
            }
        }

        System.out.println("✅ Auto assignment complete. " + assignedCount + " students assigned to courses.");
    }

    // Display course allocations
    public void displayAllocations() {
        System.out.println("\n📋 COURSE ALLOCATIONS (Max per course: " + MAX_CAPACITY + ")");
        for (String course : availableCourses) {
            List<String> students = courseAllocations.get(course);
            System.out.println("\n" + course + " (" + students.size() + "/" + MAX_CAPACITY + "):");
            if (students.isEmpty()) {
                System.out.println("  - No students assigned");
            } else {
                for (String s : students) {
                    System.out.println("  • Student ID: " + s);
                }
            }
        }
    }
}

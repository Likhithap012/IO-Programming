package csv;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class SearchRecord {
    public static void main(String[] args) {
        String filePath = "employee.csv";
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter employee name to search: ");
        String searchName = scanner.nextLine().trim().toLowerCase(); // Case-insensitive

        boolean found = false;

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line = br.readLine(); // Read header

            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");

                if (parts.length >= 4) {
                    String name = parts[1].trim().toLowerCase();

                    if (name.equals(searchName)) {
                        String department = parts[2].trim();
                        String salary = parts[3].trim();

                        System.out.println("Department: " + department);
                        System.out.println("Salary: " + salary);
                        found = true;
                        break;
                    }
                }
            }

            if (!found) {
                System.out.println("Employee with name '" + searchName + "' not found.");
            }

        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }
}

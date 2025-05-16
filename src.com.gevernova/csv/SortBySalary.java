package csv;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SortBySalary {
    public static void main(String[] args) {
        String file = "employee.csv";
        List<String[]> records = new ArrayList<>();

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
            String header = bufferedReader.readLine(); // read header line
            String line = bufferedReader.readLine();

            while (line != null) {
                String[] parts = line.split(",");
                if (parts.length >= 4) {
                    records.add(parts);
                }
                line = bufferedReader.readLine();
            }

            // Sort by salary in descending order
            records.sort((a, b) -> {
                int salaryA = Integer.parseInt(a[3].trim());
                int salaryB = Integer.parseInt(b[3].trim());
                return Integer.compare(salaryB, salaryA); // descending
            });

            System.out.println("Top 5 highest-paid employees:");
            System.out.println(header); // Print header

            for (int i = 0; i < Math.min(5, records.size()); i++) {
                String[] r = records.get(i);
                System.out.println(String.join(", ", r));
            }

        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}

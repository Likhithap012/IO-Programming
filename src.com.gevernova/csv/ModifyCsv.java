package csv;

import java.io.*;

public class ModifyCsv {
    public static void main(String[] args) {
        String inputFile = "employee.csv";
        String outputFile = "updated_employee.csv";

        try (
                BufferedReader bufferedReader = new BufferedReader(new FileReader(inputFile));
                BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(outputFile))
        ) {
            String line = bufferedReader.readLine(); // Read header
            bufferedWriter.write(line); // Write header to new file
            bufferedWriter.newLine();

            line = bufferedReader.readLine(); // Move to next line (first record)
            while (line != null) {
                String[] parts = line.split(",");

                if (parts.length >= 4) {
                    String department = parts[2].trim();
                    int salary = Integer.parseInt(parts[3].trim());

                    if (department.equalsIgnoreCase("IT")) {
                        salary = (int) (salary * 1.10); // Increase by 10%
                    }

                    // Write updated line
                    bufferedWriter.write(parts[0].trim() + "," + parts[1].trim() + "," + department + "," + salary);
                    bufferedWriter.newLine();
                }

                line = bufferedReader.readLine(); // Read next line
            }

            System.out.println("Updated CSV saved as " + outputFile);
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}

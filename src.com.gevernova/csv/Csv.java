package csv;
import java.io.*;
import java.util.*;

public class Csv{

    // 1: Read a CSV and print data
    public static void readAndPrintCSV(String filePath) {
        System.out.println("Reading and printing CSV data:");
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            // Read header
            String header = br.readLine();
            System.out.println("Header: " + header);
            // Read and print each record
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                System.out.printf("ID: %s, Name: %s, Age: %s, Marks: %s%n",
                        parts[0], parts[1], parts[2], parts[3]);
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }

    // 2: Write employee data to CSV
    public static void writeEmployeesCSV(String filePath) {
        System.out.println("\nWriting employee data to CSV:");
        String[] employees = {
                "101,John Doe,IT,55000",
                "102,Jane Smith,HR,48000",
                "103,Bob Johnson,Finance,60000",
                "104,Alice Williams,IT,62000",
                "105,Chris Evans,Marketing,50000"
        };

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath))) {
            // Write header
            bw.write("ID,Name,Department,Salary");
            bw.newLine();
            // Write records
            for (String emp : employees) {
                bw.write(emp);
                bw.newLine();
            }
            System.out.println("Employees written successfully.");
        } catch (IOException e) {
            System.out.println("Error writing file: " + e.getMessage());
        }
    }

    // 3: Count rows excluding header
    public static void countRows(String filePath) {
        System.out.println("\nCounting rows in CSV (excluding header):");
        int count = 0;
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            br.readLine(); // skip header
            while (br.readLine() != null) {
                count++;
            }
            System.out.println("Total records: " + count);
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }

    // 4: Filter students with marks > 80
    public static void filterStudentsByMarks(String filePath) {
        System.out.println("\nStudents scoring more than 80:");
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String header = br.readLine(); // header
            System.out.println(header);
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                int marks = Integer.parseInt(parts[3]);
                if (marks > 80) {
                    System.out.println(line);
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }

    // 5: Search employee by name and print dept & salary
    public static void searchEmployee(String filePath, String name) {
        System.out.println("\nSearching for employee: " + name);
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            br.readLine(); // skip header
            String line;
            boolean found = false;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts[1].equalsIgnoreCase(name)) {
                    System.out.printf("Department: %s, Salary: %s%n", parts[2], parts[3]);
                    found = true;
                    break;
                }
            }
            if (!found) {
                System.out.println("Employee not found.");
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }

    // 6: Increase IT salary by 10% and save to new file
    public static void increaseITSalary(String inputFilePath, String outputFilePath) {
        System.out.println("\nIncreasing IT department salaries by 10% and saving:");
        try (BufferedReader br = new BufferedReader(new FileReader(inputFilePath));
             BufferedWriter bw = new BufferedWriter(new FileWriter(outputFilePath))) {

            String header = br.readLine();
            bw.write(header);
            bw.newLine();

            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts[2].equalsIgnoreCase("IT")) {
                    double salary = Double.parseDouble(parts[3]);
                    salary *= 1.1; // increase by 10%
                    parts[3] = String.format("%.2f", salary);
                }
                bw.write(String.join(",", parts));
                bw.newLine();
            }
            System.out.println("Updated CSV saved to " + outputFilePath);
        } catch (IOException e) {
            System.out.println("Error processing files: " + e.getMessage());
        }
    }

    // 7: Sort by salary desc and print top 5
    public static void top5Salaries(String filePath) {
        System.out.println("\nTop 5 highest-paid employees:");
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String header = br.readLine();
            List<String[]> employees = new ArrayList<>();

            String line;
            while ((line = br.readLine()) != null) {
                employees.add(line.split(","));
            }

            // Sort by salary descending
            employees.sort((a, b) -> {
                double salaryA = Double.parseDouble(a[3]);
                double salaryB = Double.parseDouble(b[3]);
                return Double.compare(salaryB, salaryA);
            });

            System.out.println(header);
            for (int i = 0; i < Math.min(5, employees.size()); i++) {
                System.out.println(String.join(",", employees.get(i)));
            }

        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }
}

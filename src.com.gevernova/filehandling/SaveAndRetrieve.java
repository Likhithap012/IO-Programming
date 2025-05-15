package filehandling;

import java.io.*;

public class SaveAndRetrieve {
    public static void main(String[] args) {
        Employee employee = new Employee();
        employee.id = 1;
        employee.name = "likhitha";
        employee.department = "cse";
        employee.salary = 2000000;

        // Serialize the employee to a file
        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(
                new FileOutputStream("employee_data.dat"))) {
            objectOutputStream.writeObject(employee);
            System.out.println("Employee saved successfully.");
        } catch (IOException e) {
            System.out.println("IOException: " + e.getMessage());
        }

        // Deserialize the employee from the file
        try (ObjectInputStream objectInputStream = new ObjectInputStream(
                new FileInputStream("employee_data.dat"))) {
            Employee savedEmployee = (Employee) objectInputStream.readObject();
            System.out.println("Employee retrieved:");
            System.out.println("ID: " + savedEmployee.id);
            System.out.println("Name: " + savedEmployee.name);
            System.out.println("Department: " + savedEmployee.department);
            System.out.println("Salary: " + savedEmployee.salary);
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error during retrieval: " + e.getMessage());
        }
    }
}

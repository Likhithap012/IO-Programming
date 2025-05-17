package filehandling;

import java.io.*;

public class StudentDataStream {
    public static void main(String[] args) {
        String fileName = "students.dat";

        // Write data to binary file
        try (DataOutputStream dataOutputStream = new DataOutputStream(new FileOutputStream(fileName))) {
            // Sample student data
            dataOutputStream.writeInt(101);                 // Roll Number
            dataOutputStream.writeUTF("Likhitha");          // Name
            dataOutputStream.writeDouble(9.2);              // GPA

            dataOutputStream.writeInt(102);
            dataOutputStream.writeUTF("Vyshnavi");
            dataOutputStream.writeDouble(8.7);

            System.out.println("Student data written successfully.");
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }

        // Read data from binary file
        try (DataInputStream dataInputStream = new DataInputStream(new FileInputStream(fileName))) {
            while (dataInputStream.available() > 0) { // while there's still data to read
                int roll = dataInputStream.readInt();
                String name = dataInputStream.readUTF();
                double gpa = dataInputStream.readDouble();

                System.out.println("Roll No: " + roll + ", Name: " + name + ", GPA: " + gpa);
            }
        } catch (IOException e) {
            System.out.println("Error reading from file: " + e.getMessage());
        }
    }
}

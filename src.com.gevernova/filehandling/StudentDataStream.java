package filehandling;

import java.io.*;

public class StudentDataStream {
    public static void main(String[] args) {
        String fileName = "students.dat";

        // Write data to binary file
        try (DataOutputStream dos = new DataOutputStream(new FileOutputStream(fileName))) {
            // Sample student data
            dos.writeInt(101);                 // Roll Number
            dos.writeUTF("Likhitha");          // Name
            dos.writeDouble(9.2);              // GPA

            dos.writeInt(102);
            dos.writeUTF("Vyshnavi");
            dos.writeDouble(8.7);

            System.out.println("Student data written successfully.");
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }

        // Read data from binary file
        try (DataInputStream dis = new DataInputStream(new FileInputStream(fileName))) {
            while (dis.available() > 0) { // while there's still data to read
                int roll = dis.readInt();
                String name = dis.readUTF();
                double gpa = dis.readDouble();

                System.out.println("Roll No: " + roll + ", Name: " + name + ", GPA: " + gpa);
            }
        } catch (IOException e) {
            System.out.println("Error reading from file: " + e.getMessage());
        }
    }
}

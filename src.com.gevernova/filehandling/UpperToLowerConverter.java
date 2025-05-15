package filehandling;

import java.io.*;

public class UpperToLowerConverter {
    public static void main(String[] args) {
        // Change these paths as per your system
        String inputFilePath = "input.txt";
        String outputFilePath = "output.txt";

        // Use try-with-resources to ensure streams are closed automatically
        try (
                // FileReader and FileWriter use default encoding (can be overridden with InputStreamReader/Writer)
                BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(inputFilePath), "UTF-8"));
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outputFilePath), "UTF-8"))
        ) {
            String line;
            while ((line = reader.readLine()) != null) {
                // Convert to lowercase and write to output
                writer.write(line.toLowerCase());
                writer.newLine(); // Preserve line breaks
            }

            System.out.println("Conversion completed. Check output.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


package filehandling;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
public class ImageByteArray {
    public static void main(String[] args) {
        int width = 200;
        int height = 200;

        // Create a buffered image
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        // Draw on the image
        Graphics2D g2d = image.createGraphics();
        g2d.setPaint(Color.BLUE);
        g2d.fillRect(0, 0, width, height);
        g2d.setPaint(Color.YELLOW);
        g2d.setFont(new Font("Arial", Font.BOLD, 20));
        g2d.drawString("Sample Image", 30, 100);
        g2d.dispose();

        // Save the image to a file
        try {
            File output = new File("original_image.jpg");
            ImageIO.write(image, "jpg", output);
            System.out.println("Sample image created: original_image.jpg");
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
        String originalImagePath = "original_image.jpg";
        String copiedImagePath = "copied_image.jpg";

        try {
            // 1. Read the image file into a byte array
            FileInputStream fileInputStream = new FileInputStream(originalImagePath);
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

            byte[] buffer = new byte[4096];
            int bytesRead;

            while ((bytesRead = fileInputStream.read(buffer)) != -1) {
                byteArrayOutputStream.write(buffer, 0, bytesRead);
            }

            fileInputStream.close();

            // Convert ByteArrayOutputStream to byte array
            byte[] imageBytes = byteArrayOutputStream.toByteArray();

            // 2. Read the byte array using ByteArrayInputStream
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(imageBytes);
            FileOutputStream fileOutputStream = new FileOutputStream(copiedImagePath);

            // 3. Write the byte array to a new image file
            while ((bytesRead = byteArrayInputStream.read(buffer)) != -1) {
                fileOutputStream.write(buffer, 0, bytesRead);
            }

            fileOutputStream.close();
            byteArrayInputStream.close();

            System.out.println("Image copied successfully.");

        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}

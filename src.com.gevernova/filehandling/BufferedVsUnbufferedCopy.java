package filehandling;

import java.io.*;

public class BufferedVsUnbufferedCopy {
    public static void main(String[] args) {
        String sourceFile = "largefile.txt";
        String destUnbuffered = "copy_unbuffered.txt";
        String destBuffered = "copy_buffered.txt";

        final int BUFFER_SIZE = 4096; // 4KB

        // Step 1: Create a large file (~100MB)
        File file = new File(sourceFile);
        if (!file.exists()) {
            System.out.println("Creating large file...");
            try (FileOutputStream fileOutputStream = new FileOutputStream(file)) {
                byte[] data = "SampleText1234567890\n".getBytes();
                for (int i = 0; i < (100 * 1024 * 1024) / data.length; i++) {
                    fileOutputStream.write(data);
                }
                System.out.println("Large file created: " + sourceFile);
            } catch (IOException e) {
                System.out.println("Error creating large file: " + e.getMessage());
                return;
            }
        }

        // Step 2: Unbuffered copy
        try (
                FileInputStream fileInputStream = new FileInputStream(sourceFile);
                FileOutputStream fileOutputStream = new FileOutputStream(destUnbuffered)
        ) {
            byte[] buffer = new byte[BUFFER_SIZE];
            int bytesRead;
            long start = System.nanoTime();

            while ((bytesRead = fileInputStream.read(buffer)) != -1) {
                fileOutputStream.write(buffer, 0, bytesRead);
            }

            long end = System.nanoTime();
            System.out.println("Unbuffered copy time: " + (end - start) / 1_000_000 + " ms");

        } catch (IOException e) {
            System.out.println("Unbuffered Error: " + e.getMessage());
        }

        // Step 3: Buffered copy
        try (
                BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(sourceFile));
                BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(destBuffered))
        ) {
            byte[] buffer = new byte[BUFFER_SIZE];
            int bytesRead;
            long start = System.nanoTime();

            while ((bytesRead = bufferedInputStream.read(buffer)) != -1) {
                bufferedOutputStream.write(buffer, 0, bytesRead);
            }

            long end = System.nanoTime();
            System.out.println("Buffered copy time: " + (end - start) / 1_000_000 + " ms");

        } catch (IOException e) {
            System.out.println("Buffered Error: " + e.getMessage());
        }
    }
}


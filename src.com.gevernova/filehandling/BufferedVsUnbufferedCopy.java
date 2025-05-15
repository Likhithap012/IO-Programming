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
            try (FileOutputStream fos = new FileOutputStream(file)) {
                byte[] data = "SampleText1234567890\n".getBytes();
                for (int i = 0; i < (100 * 1024 * 1024) / data.length; i++) {
                    fos.write(data);
                }
                System.out.println("Large file created: " + sourceFile);
            } catch (IOException e) {
                System.out.println("Error creating large file: " + e.getMessage());
                return;
            }
        }

        // Step 2: Unbuffered copy
        try (
                FileInputStream fis = new FileInputStream(sourceFile);
                FileOutputStream fos = new FileOutputStream(destUnbuffered)
        ) {
            byte[] buffer = new byte[BUFFER_SIZE];
            int bytesRead;
            long start = System.nanoTime();

            while ((bytesRead = fis.read(buffer)) != -1) {
                fos.write(buffer, 0, bytesRead);
            }

            long end = System.nanoTime();
            System.out.println("Unbuffered copy time: " + (end - start) / 1_000_000 + " ms");

        } catch (IOException e) {
            System.out.println("Unbuffered Error: " + e.getMessage());
        }

        // Step 3: Buffered copy
        try (
                BufferedInputStream bis = new BufferedInputStream(new FileInputStream(sourceFile));
                BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(destBuffered))
        ) {
            byte[] buffer = new byte[BUFFER_SIZE];
            int bytesRead;
            long start = System.nanoTime();

            while ((bytesRead = bis.read(buffer)) != -1) {
                bos.write(buffer, 0, bytesRead);
            }

            long end = System.nanoTime();
            System.out.println("Buffered copy time: " + (end - start) / 1_000_000 + " ms");

        } catch (IOException e) {
            System.out.println("Buffered Error: " + e.getMessage());
        }
    }
}


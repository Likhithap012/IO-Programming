package filehandling;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class ReadAndWriteaTextFile {
    public static void main(String[] args) throws IOException {
        String sourceFile="source.txt";
        String destFile="destination.txt";
        try{
            File file=new File(sourceFile);
            if(!file.exists()){
                file.createNewFile();
                FileOutputStream fileOutputStream=new FileOutputStream(file);
                String sampleText="This is text.";
//                sampleText.getBytes() converts that String into an array of bytes (byte[]).
//                fileOutputStream.write(byte[]) writes those bytes to the file.
                fileOutputStream.write(sampleText.getBytes());
            }
            FileInputStream fileInputStream=new FileInputStream(sourceFile);
            FileOutputStream fileOutputStream=new FileOutputStream(destFile);
            int data=fileInputStream.read();
            while(data!=-1){
                fileOutputStream.write(data);
                data=fileInputStream.read();
            }
        }catch (IOException e){
            System.out.println(e.getMessage());
        }
    }
}

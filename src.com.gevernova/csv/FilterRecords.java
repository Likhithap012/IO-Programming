package csv;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class FilterRecords {
    public static void main(String[] args) {
        String file="employee.csv";
        try(BufferedReader bufferedReader=new BufferedReader(new FileReader(file))) {
            String line=bufferedReader.readLine();
            line= bufferedReader.readLine();
            while(line!=null){
                String[] parts=line.split(",");
                if(parts.length>=4){
                    int marks=Integer.parseInt(parts[3].trim());
                    if(marks>80){
                        System.out.println(line);
                    }
                }
                line=bufferedReader.readLine();
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}

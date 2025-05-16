package csv;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class WriteCSVFile {
    public static void main(String[] args) {
        String path="employee.csv";
        try(BufferedWriter bufferedWriter=new BufferedWriter(new FileWriter(path))){
            bufferedWriter.write("ID, Name, Department, Salary");
            bufferedWriter.newLine();
            bufferedWriter.write("1, likhitha, nwc,2000000");
            bufferedWriter.close();
        }catch (IOException e){
            System.out.println(e.getMessage());
        }
    }
}

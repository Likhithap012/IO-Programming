package csv;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class ReadAndCountRows {
    public static void main(String[] args) {
        String file="employee.csv";
        try(BufferedReader bufferedReader=new BufferedReader(new FileReader(file))){
            int count=0;
            String line=bufferedReader.readLine();
            while(line!=null){
                count++;
                line=bufferedReader.readLine();
            }
            System.out.println(count);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

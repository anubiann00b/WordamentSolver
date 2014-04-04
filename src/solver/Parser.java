package solver;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;


public class Parser {
    
    public static String[] readWordList(String filename) {
        ArrayList<String> words = new ArrayList<String>();
        String line;
        
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(filename)));
            while ((line = reader.readLine())!=null) {
                words.add(line);
            }
            reader.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + filename);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        
        return words.toArray(new String[0]);
    }
    
    public static char[][] parseGrid(String grid) {
        int length = (int)(Math.sqrt(grid.length())+0.5);
        
        char[][] chars = new char[length][length];
        char[] gridChars = grid.toCharArray();
        
        for(int i=0;i<length;i++) {
            chars[i] = Arrays.copyOfRange(gridChars,i*length,(i+1)*length);
        }
        return chars;
    }
}

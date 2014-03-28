package solver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    
    public static void main(String[] args) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Enter a grid.\n");
        try {
            String grid = reader.readLine();
            reader.close();
        } catch (IOException e) {
            System.out.println("IO Error: " + e);
        }
    }
}
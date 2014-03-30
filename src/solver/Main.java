package solver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    
    public static void main(String[] args) {        
        new Thread(new Runnable(){
            public void run() {
                Solver.setWords("wordlist_big.txt");
            }
        }).start();
        
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Enter a grid.\n");
        String grid = null;
        try {
            grid = reader.readLine();
            reader.close();
        } catch (IOException e) {
            System.out.println("IO Error: " + e);
        }
        Solver.setGrid(Parser.parseGrid(grid));
        Solver.solve();
    }
}
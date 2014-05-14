package solver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main {
    
    public static void main(String[] args) {
        
        Solver.setWords("wordlist.txt");
        
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
        
        long startTime = System.currentTimeMillis();
        
        String[] rawSolutions = Solver.solve();
        
        System.out.println("\nTime: " + (System.currentTimeMillis()-startTime));
        
        ArrayList<String> solutions = new ArrayList<String>();
        
        for (String s : rawSolutions)
            if (!solutions.contains(s))
                solutions.add(s);
        
        System.out.println("Words: " + solutions.size() + "\n");
        
        for (String s : solutions)
            System.out.println(s);
    }
}
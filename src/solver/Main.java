package solver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main {
    
    public static void main(String[] args) {        
        new Thread(new Runnable(){
            @Override
            public void run() {
                Solver.setWords("wordlist.txt");
                Solver.initialize();
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
        
        System.out.println("Started.");
        long startTime = System.currentTimeMillis();
        
        String[] rawSolutions = Solver.solve();
        
        System.out.println("Time: " + (System.currentTimeMillis()-startTime));
        
        ArrayList<String> solutions = new ArrayList<String>();
        
        for (String s : rawSolutions)
            if (!solutions.contains(s))
                solutions.add(s);
        
        for (String s : solutions)
            System.out.println(s);
    }
}
package solver;

public class Solver {
    
    private static String[] words;
    private static char[][] grid;
    
    public static void setWords(String file) {
        words = Parser.readWordList(file);
    }

    public static void setGrid(char[][] newGrid) {
        grid = newGrid;
    }

    public static void solve() {
        
    }
}
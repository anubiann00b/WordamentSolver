package solver;

import java.util.ArrayList;

public class Solver {
    
    private static String[] words;
    private static char[][] grid;
    
    public static void setWords(String file) {
        words = Parser.readWordList(file);
    }
    
    public static void setGrid(char[][] newGrid) {
        grid = newGrid;
        System.out.println("\nGrid:");
        
        for (char[] arr : newGrid) {
            for (char c : arr) {
                System.out.print(c + " ");
            }
            System.out.println();
        }
    }
    public static String[] solve() {
        boolean[][] visited = new boolean[grid.length][grid.length];
        ArrayList<String> strings = new ArrayList<String>();
        for (int i=0;i<grid.length;i++) {
            for (int j=0;j<grid[0].length;j++) {
                recursiveSolve(i,j,"",strings,visited);
            }
        }
        return strings.toArray(new String[]{});
    }
    
    private static void recursiveSolve(int cx, int cy, String str, ArrayList<String> strings,
            boolean[][] visited) {
        
        if (!isPrefix(str))
            return;
        
        if (isWord(str))
            strings.add(str);
        
        for (int i=-1;i<=1;i++) {
            for (int j=-1;j<=1;j++) {
                if (i==0 && j==0)
                    continue;
                
                int x = cx + i;
                int y = cy + j;
                
                if (x<0 || y<0 || x>grid.length-1 || y>grid[0].length-1)
                    continue;
                
                if (visited[x][y])
                    continue;
                
                String newStr = str + grid[x][y];
                
                visited[x][y] = true;
                recursiveSolve(x,y,newStr,strings,visited);
                visited[x][y] = false;
            }
        }
    }
    
    public static boolean isWord(String prefix) {
        int low = 0;
        int high = words.length;
        int middle;
        while (low < high-1) {
            middle = (low+high)/2;
            int pos = prefix.compareTo(words[middle]);
            if (pos == 0)
                return true;
            if (pos < 0)
                high = middle;
            if (pos > 0)
                low = middle;
        }
        return false;
    }
    
    public static boolean isPrefix(String prefix) {
        int low = 0;
        int high = words.length;
        int middle = (low+high)/2;
        while (low < high-1) {
            if (words[middle].contains(prefix))
                return true;
            middle = (low+high)/2;
            int pos = prefix.compareTo(words[middle]);
            if (pos < 0)
                high = middle;
            if (pos > 0)
                low = middle;
        }
        return false;
    }
}
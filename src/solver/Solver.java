package solver;

import java.util.ArrayList;
import java.util.Arrays;

public class Solver {
    
    private static String[] words;
    private static char[][] grid;
    
    public static void setWords(String file) {
        words = Parser.readWordList(file);
    }
    
    public static void setGrid(char[][] newGrid) {
        grid = newGrid;
    }
    
    public static String[] solve() {
        ArrayList<String> strings = new ArrayList<String>();
        for (int i=0;i<grid.length;i++) {
            for (int j=0;j<grid[0].length;j++) {
                strings.addAll(recursiveSolve(i,j,new char[]{grid[i][j]},
                        WordTree.getNode(grid[i][j]),new ArrayList<Location>()));
            }
        }
        return strings.toArray(new String[]{});
    }
    
    private static ArrayList<String> recursiveSolve(int cx, int cy, char[] chars,
            Node node, ArrayList<Location> locs) {
        ArrayList<String> strings = new ArrayList<String>();
        if (!node.hasNodes()) {
            strings.add(new String(chars));
            return strings;
        }
        for (int i=-1;i<=1;i++) {
            for (int j=-1;j<=1;j++) {
                int x = cx + i;
                int y = cy + j;
                
                if (x<0 || y<0 || x>grid.length-1 || y>grid[0].length-1)
                    continue;
                
                Location newLoc = new Location(x,y);
                
                if (locs.contains(newLoc))
                    continue;
                
                char[] newArr = Arrays.copyOf(chars,chars.length+1);
                newArr[chars.length] = grid[x][y];
                
                Node newNode = node.getNodeWithChar(grid[x][y]);
                if (newNode == null) {
                    continue;
                }
                ArrayList<Location> newLocs = new ArrayList<Location>(locs);
                newLocs.add(newLoc);
                strings.addAll(recursiveSolve(x,y,newArr,newNode,newLocs));
            }
        }
        return strings;
    }
    
    public static void initialize() {
        WordTree.initialize(words);
    }
}

class Location {
    private int x;
    private int y;

    public int getX() { return x; }
    public int getY() { return y; }

    public Location(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
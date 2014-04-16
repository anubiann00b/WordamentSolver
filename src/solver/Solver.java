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
        System.out.println("Grid:");
        
        for (char[] arr : newGrid) {
            for (char c : arr) {
                System.out.print(c + " ");
            }
            System.out.println();
        }
        Location.init(newGrid.length);
    }
    public static String[] solve() {
        ArrayList<String> strings = new ArrayList<String>(2500);
        for (int i=0;i<grid.length;i++) {
            for (int j=0;j<grid[0].length;j++) {
                recursiveSolve(i,j,"",strings,new ArrayList<Location>());
            }
        }
        System.out.println(strings.size());
        return strings.toArray(new String[]{});
    }
    
    private static void recursiveSolve(int cx, int cy, String str, ArrayList<String> strings,
            ArrayList<Location> locs) {
        
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
                
                Location newLoc = Location.getLoc(x,y);
                
                if (locs.contains(newLoc))
                    continue;
                
                String newStr = str + grid[x][y];
                
                ArrayList<Location> newLocs = new ArrayList<Location>(locs.size()+1);
                newLocs.addAll(locs);
                newLocs.add(newLoc);
                recursiveSolve(x,y,newStr,strings,newLocs);
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

class Location {
    
    private static Location[][] locs;
    
    static void init(int len) {
        locs = new Location[len][len];
        
        for (int i=0;i<len;i++)
            for (int j=0;j<len;j++)
                locs[i][j] = new Location(i,j);
    }
    
    static Location getLoc(int x, int y) {
        return locs[x][y];
    }
    
    private int x;
    private int y;

    int getX() { return x; }
    int getY() { return y; }

    private Location(int x, int y) {
        this.x = x;
        this.y = y;
    }
    
    @Override
    public boolean equals(Object o) {
        if (o == null || !(o instanceof Location))
            return false;
        Location l = (Location) o;
        return x==l.x && y==l.y;
    }
    
    @Override
    public String toString() {
        return x + " " + y;
    }
    
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 17*hash+this.x;
        hash = 17*hash+this.y;
        return hash;
    }
}
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
        ArrayList<String> strings = new ArrayList<String>(300);
        for (int i=0;i<grid.length;i++) {
            for (int j=0;j<grid[0].length;j++) {
                recursiveSolve(i,j,"",strings,new ArrayList<Location>(20));
            }
        }
        return strings.toArray(new String[]{});
    }
    
    private static void recursiveSolve(int cx, int cy, String str, ArrayList<String> strings,
            ArrayList<Location> locs) {
        
        int word = isWord(str);
        
        if (word == 3 && str.length() >= 3)
            strings.add(str);
        
        if (word == 0 || word == 2)
            return;
        
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
    
    /**
     * Tests if a string is in the wordlist.
     * 
     * @param prefix The string to test.
     * @return 3 if word and prefix, 2 if word, 1 if prefix, 0 if neither.
     */
    public static int isWord(String prefix) {
        int low = 0;
        int high = words.length;
        int middle = (low+high)/2;
        while (low < high-1) {
            middle = (low+high)/2;
            //System.out.println(words[middle] + " " + low + " " + high);
            int pos = prefix.compareTo(words[middle]);
            if (pos == 0) {
                if (words[middle+1].contains(prefix))
                    return 3;
                else
                    return 2;
            }
            if (pos < 0)
                high = middle;
            if (pos > 0)
                low = middle;
        }
        //System.out.println(words[middle]);
        if (words[middle+1].contains(prefix))
            return 1;
        return 0;
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
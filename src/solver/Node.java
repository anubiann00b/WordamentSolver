package solver;

import java.util.ArrayList;

public class Node {
    
    private char myChar;
    private Node[] nodes;
    private int depth;
    private boolean isWord;
    
    public char getChar() { return myChar; }
    public boolean hasNodes() { return nodes!=null; }
    public boolean isWord() { return isWord; }
    
    public Node(char c, int depth, String s, int arrStart) {
        this.myChar = c;
        this.depth = depth;
        this.isWord = WordTree.isWord(s);
        setupNodes(arrStart);
    }
    
    public Node getNodeWithChar(char c) {
        for (Node n : nodes)
            if(n.getChar() == c)
                return n;
        return null;
    }
    
    private void setupNodes(int arrStart) {
        String[] words = WordTree.words;
        
        ArrayList<Node> nodeList = new ArrayList<Node>();
        
        int seenCount = 0;
        char[] seen = new char[26];
        
        for (int i=arrStart;i<words.length;i++) {
            //System.out.println(words[i]);
            if (words[i].length()<=depth)
                continue;
            if (words[i].charAt(depth-1) != myChar)
                break;
            char currentChar = words[i].charAt(depth);
            boolean charSeen = false;
            for (int j=0;j<seenCount;j++) {
                if (seen[j] == currentChar) {
                    charSeen=true;
                    break;
                }
            }
            if (charSeen)
                continue;
            seen[seenCount] = currentChar;
            seenCount++;
            nodeList.add(new Node(currentChar,depth+1,words[i].substring(0,depth),i));
        }
        if (!nodeList.isEmpty())
            nodes = nodeList.toArray(new Node[]{});
        else
            nodes = null;
    }
    
    @Override
    public String toString() {
        return Character.toString(myChar);
    }
}

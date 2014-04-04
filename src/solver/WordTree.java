package solver;

public class WordTree {
    
    private static Node[] letters = new Node[26];
    public static String[] words;
    
    public static void initialize(String[] newWords) {
        words = newWords;
        
        int pointer = 0;
        
        for (int i=0;i<26;i++) {
            int start = 0;
            for (;pointer<words.length;pointer++) {
                if (words[pointer].charAt(0) == i+'a') {
                    start = pointer;
                    break;
                }
            }
            for (;pointer<words.length;pointer++) {
                if (words[pointer].charAt(0) == i+'a'+1) {
                    break;
                }
            }
            letters[i] = new Node((char)(i+'a'),1,"",start);
        }
        System.out.println("Initialized.");
    }
    
    public static boolean isWord(String str) {
        for (String s : words)
            if (s.equals(str))
                return true;
        return false;
    }
    
    public static boolean isWordMatch(char[] chars) {
        Node currentNode;
        int currentDepth = 0;
        
        for (Node n : letters) {
            if (n.getChar() == chars[0]) {
                currentNode = n;
                while (currentDepth < chars.length-1) {
                    if (currentNode == null)
                        return false;
                    currentDepth++;
                    currentNode = currentNode.getNodeWithChar(chars[currentDepth]);
                }
                return currentNode != null;
            }
        }
        return false;
    }

    public static Node getNode(char c) {
        for (Node n : letters) {
            if (n.getChar() == c) {
                return n;
            }
        }
        return null;
    }
}

import java.util.LinkedList;
import java.util.List;

public class MyTrieSet implements TrieSet61B{

    private static final int R = 128;
    private Node root;
    //private int n;

    private class Node{
        private boolean isKey;
        private char ch;
        private Node[] next;

        public Node(char ch, boolean isKey, int R){
            this.ch = ch;
            this.isKey = isKey;
            this.next = new Node[R];
        }
    }
    public MyTrieSet(){
        root = new Node('R', false, R);
    }

    /** Clears all items out of Trie */
    public void clear(){
        this.root = new Node('R', false, R);
    }

    /** Returns true if the Trie contains KEY, false otherwise */
    public boolean contains(String key){
        return contains(root, key, 0);
    }

    private boolean contains(Node n, String key, int index){
        char ch = key.charAt(index);
        if ((n.next[ch] == null) || (ch != n.next[ch].ch))
            return false;
        else{
            if(index == key.length() - 1){ // if this key has been traversed to the end
                if(n.next[ch].isKey)
                    return true;
                else
                    return false;
            }
            else
                return contains(n.next[ch], key, index + 1);
        }
    }

    /** Inserts string KEY into Trie */
    public void add(String key){
        if (key == null || key.length() < 1)
            return;
        add(root, key, 0);
    }

    private void add(Node n, String key, int index){
        if(key.length() <= index){ // if this is the last char, mark it blue
            n.isKey = true;
            return;
        }
        char ch = key.charAt(index);

        if ((n.next[ch] == null) || (ch != n.next[ch].ch)){
            Node temp = new Node(ch, false, R);
            n.next[ch] = temp;
            add(temp, key, index + 1);
        }
        else if (ch == n.next[ch].ch)
            add(n.next[ch], key, index + 1);
    }

    /** Returns a list of all words that start with PREFIX */
    public List<String> keysWithPrefix(String prefix){
        Node start = findNodeWithPrefix(root, prefix, 0);
        if (start == null)
            return null;
        List<String> result;
        result = colHelper(prefix, new LinkedList<String>(), start);
        return result;
    }
    public Node findNodeWithPrefix(Node n, String prefix, int index){
        char ch = prefix.charAt(index);
        if ((n.next[ch] == null) || (ch != n.next[ch].ch))
            return null;
        else{
            if(index == prefix.length() - 1){ // prefix search ended
                return n.next[ch];
            }
            return findNodeWithPrefix(n.next[ch], prefix, index + 1);
        }
    }
    public List<String> collect(){ // return all keys in the Trie
        return colHelper("", new LinkedList<String>(), root);
    }

    private List<String> colHelper(String s, List<String> result, Node n){
        if (n.isKey)
            result.add(s);
        for(Node node : n.next){
            if (node != null)
                colHelper(s + node.ch, result, node);
        }
        return result;
    }

    /** Returns the longest prefix of KEY that exists in the Trie
     * Not required for Lab 9. If you don't implement this, throw an
     * UnsupportedOperationException.
     */
    public String longestPrefixOf(String key){
        throw new UnsupportedOperationException();
    }

    public static void main(String[] args) {
        MyTrieSet t = new MyTrieSet();
        t.add("hello");
        t.add("hi");
        t.add("help");
        t.add("zebra");
//        System.out.println(t.contains("hi"));
//        System.out.println(t.contains("hello"));
//        System.out.println(t.contains("help"));
//        System.out.println(t.contains("zebra"));
//        System.out.println(t.contains("sdf"));
        //System.out.println(t.keysWithPrefix("he"));
        System.out.println(t.collect());





        //System.out.println((int) 'z');
    }


}

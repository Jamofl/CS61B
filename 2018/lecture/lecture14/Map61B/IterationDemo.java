package Map61B;

import java.util.Iterator;

public class IterationDemo {
    public static void main(String[] args){
        ArrayMap<String, Integer> m = new ArrayMap<String, Integer>();
        m.put("hello", 1);
        m.put("how", 2);
        m.put("what", 3);

        ArrayMap.KeyIterator kIter = m.new KeyIterator();
        Iterator<String> iter = m.iterator();
        // syntax of instantiate a nested class (not static), add a instance name before "new"
        // because the KeyIterator class need to be associated with a specific array map(not the array class)

        while(iter.hasNext()){
            System.out.println(iter.next());
        }

        for(String s : m){
            System.out.println(s);
        }
    }
}

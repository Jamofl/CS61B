import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class PriorityQueueTest {
    /*Find the k most common words in a document. Assume that you can represent
    this as an array of Strings, where each word is an element in the array. You
    might find using multiple data structures useful.

     */


    public static void topKWord(String[] sentence, int k){
        Map<String, Integer> map = new HashMap<>();
        for (String s : sentence){
            if (map.keySet().contains(s))
                map.put(s,  map.get(s) + 1);
            else
                map.put(s, 1);
        }

        // decreasing order
        Comparator<String> cmp = new Comparator<>()
        {
            @Override
            public int compare(String t1, String t2) {
                return map.get(t2) - map.get(t1);
            }
        };

        // by default: increasing order, but here given a decreasing comparator
        PriorityQueue<String> priorityQueue = new PriorityQueue<>(cmp);

        for(String s : map.keySet()){
            priorityQueue.add(s);
        }

        for(int i = 0; i < k ; i ++){
            priorityQueue.poll();
        }

    }
}

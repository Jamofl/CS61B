package hw3.hash;

import org.w3c.dom.DOMError;

import javax.swing.plaf.basic.BasicScrollPaneUI;
import java.util.HashMap;
import java.util.List;

public class OomageTestUtility {
    public static boolean haveNiceHashCodeSpread(List<Oomage> oomages, int M) {
        /* TODO:
         * Write a utility function that returns true if the given oomages
         * have hashCodes that would distribute them fairly evenly across
         * M buckets. To do this, convert each oomage's hashcode in the
         * same way as in the visualizer, i.e. (& 0x7FFFFFFF) % M.
         * and ensure that no bucket has fewer than N / 50
         * Oomages and no bucket has more than N / 2.5 Oomages.
         */
        HashMap<Integer, Integer> map = new HashMap<>();
        int N = oomages.size();
        for (int i = 0; i < N; i ++){
            Oomage o = oomages.get(i);
            int bucketNum = (o.hashCode() & 0x7FFFFFFF) % M;
            if (map.get(bucketNum) == null)
                map.put(bucketNum, 1);
            else
                map.put(bucketNum, map.get(bucketNum) + 1);
        }
        for(int i = 0; i < M; i ++){
            if (map.get(i) == null)
                return false;
            else if ((map.get(i) < N / 50) || (map.get(i) > N / 2.5))
                return false;
        }
        return true;


    }
}

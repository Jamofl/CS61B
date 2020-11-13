package Map61B;

import Map61B.ArrayMap;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;



public class MapHelper {

    // create a method that operates on generic types(specify this before return type)
    // return the item in the map if it exists, otherwise null
    public static <K, V> V get(Map61B<K, V> m, K key){
        if(m.containsKey(key))
            return m.get(key);
        return null;
    }

    // return the maximum of all keys, works only if keys can be compared
    // (this was guaranteed by K extends Compareble<K>)!!!!!!
    // extends here has different meaning, it means K is a sub type of Comparable,
    // this ability is not given by this extends, K is capable of Compare itself
    public static <K extends Comparable<K>, V> K maxKey(Map61B<K, V> m){
        List<K> keys = m.keys();
        K max = keys.get(0);
        for(K key : keys){
            if (key.compareTo(max) > 0 )
                max = key;
        }
        return max;

    }

    @Test
    public void testGet(){
        Map61B<String, Integer> m = new ArrayMap<String, Integer>();
        m.put("aam",1);
        m.put("bom",2);
        m.put("cim",3);
        Integer result = MapHelper.get(m, "aam");
        Integer exp = 1;
        assertEquals(exp, result);

    }

    @Test
    public void testGetMax(){
        Map61B<String, Integer> m = new ArrayMap<String, Integer>();
        m.put("aam",1);
        m.put("bom",2);
        m.put("cim",3);
        String exp2 = "cim";
        assertEquals(exp2, MapHelper.maxKey(m));
    }
}


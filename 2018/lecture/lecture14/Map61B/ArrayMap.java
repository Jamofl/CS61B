package Map61B;
import org.junit.Test;

import java.security.Key;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class ArrayMap<K, V> implements Map61B<K, V>, Iterable<K>{ // an iterable must have an iterator method
    private K[] keys;
    private V[] values;
    int size; // non-static member

    @Override //Override iterator method in Interface Iterable
    public Iterator<K> iterator(){
        return new KeyIterator();
    }
    // define a nested class: KeyIterator(not static)
    class KeyIterator implements Iterator<K>{
        private int position;

        public KeyIterator(){ // instantiation
            position = 0;
        }

        @Override // Override hasnext and next method in Interface Iterator
        public boolean hasNext(){
            return position < size;
        }

        @Override
        public K next(){
            K toReturn = keys[position];
            position ++;
            return toReturn;
        }
    }

    public ArrayMap(){
        keys = (K[]) new Object[100];
        values= (V[]) new Object[100];
        size = 0;
    }


    // return the index of the given key if ti exists, -1 otherwise
    private int keyIndex(K key){
        for( int i = 0; i < size; i ++){
            //if (keys[i] == key) this means these two object point at same address, which is not what we want
            if (keys[i].equals(key)) // we want compare whether these two have same contents
                return i;
        }
        return -1;
    }

    public void put(K key, V value){
        int index = keyIndex(key);
        if (index == -1){ // if it was not there, go to the end of the array and put it there
            keys[size] = key;
            values[size] = value;
            size ++;
        }
        else{
            values[index] = value;
        }

    }

    public boolean containsKey(K key){
        return keyIndex(key) != -1;
    }

    public V get(K key){
        int index = keyIndex(key);
//        if (index == -1){
//            throw new IllegalArgumentException("the key " + key + " does not exist in the map");
//        }
        return values[index];
    }

    public List<K> keys(){
        List<K> keyList = new ArrayList<K>();
        for( int i = 0; i < size; i ++){
            keyList.add(keys[i]);
        }
        return keyList;
    }

    public int size(){
        return size;
    }

    @Test
    public void test(){
        Map61B<Integer, Integer> t = new ArrayMap<Integer, Integer>();
        t.put(1, 3);
        int exp = 3;
        assertEquals(exp, (int)t.get(1)); // long long compare
        assertEquals((Integer)exp, t.get(1));// object object compare
    }

    public static void main(String[] args){
        Map61B<String, Integer> map = new ArrayMap<String, Integer>();
        map.put("a", 1);
        map.put("b", 2);
        map.put("c", 3);
        List<String> k = map.keys();
    }


}
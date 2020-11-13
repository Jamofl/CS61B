import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Set;

public class MyHashMap<K, V> implements Map61B<K, V> {
    public int initialSize = 16;
    public double loadFactor = 10;
    MyHashTable<K, V> hashtable;
    //public int load = 0;
    //private ArrayList<V> buckets;


    public MyHashMap(){
        this(16, 10);
    }
    public MyHashMap(int initialSize){
        this(initialSize, 10);
    }

    public MyHashMap(int initialSize, double loadFactor){
        this.initialSize = initialSize;
        this.loadFactor = loadFactor;
        this.hashtable = new MyHashTable<>(this.initialSize, this.loadFactor);
        //this.buckets = new ArrayList<V>(this.initialSize);

    }

    /** Removes all of the mappings from this map. */
    public void clear(){
        this.hashtable.clear();
    }

    /** Returns true if this map contains a mapping for the specified key. */
    public boolean containsKey(K key) {
        return this.hashtable.contains(key);
    }
    /**
     * Returns the value to which the specified key is mapped, or null if this
     * map contains no mapping for the key.
     */
    public V get(K key){
        return this.hashtable.get(key);
    }

    /** Returns the number of key-value mappings in this map. */
    public int size(){
        return this.hashtable.size();
    }

    /**
     * Associates the specified value with the specified key in this map.
     * If the map previously contained a mapping for the key,
     * the old value is replaced.
     */
    public void put(K key, V value){
        this.hashtable.put(key, value);
    }

    /** Returns a Set view of the keys contained in this map. */
    public Set<K> keySet(){
        return this.hashtable.keySet();
    }

    /**
     * Removes the mapping for the specified key from this map if present.
     * Not required for Lab 8. If you don't implement this, throw an
     * UnsupportedOperationException.
     */
    public V remove(K key){
        //throw new UnsupportedOperationException();
        return this.hashtable.remove(key);
    }

    /**
     * Removes the entry for the specified key only if it is currently mapped to
     * the specified value. Not required for Lab 8. If you don't implement this,
     * throw an UnsupportedOperationException.
     */
    public V remove(K key, V value){
        //throw new UnsupportedOperationException();
        return this.hashtable.remove(key, value);
    }

    private class HashMapIter implements Iterator<K>{

        @Override
        public boolean hasNext() {
            return keySet().size() != 0;
        }

        @Override
        public K next() {
            Iterator<K> it = keySet().iterator();
            return (K)it.next();
        }
    }

    @Override
    public Iterator<K> iterator() {
        return keySet().iterator();
        //return new HashMapIter();
    }
}

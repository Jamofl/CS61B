import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

public class BSTMap<K extends Comparable<K>, V> implements Map61B<K, V>{

    private BST bstmap;
    public BSTMap(){
        bstmap = new BST();
    }

    /** Removes all of the mappings from this map. */
    public void clear(){
        bstmap.clear();
    }

    /* Returns true if this map contains a mapping for the specified key. */
    public boolean containsKey(K key){
        return bstmap.get(key) != null;
    }

    /* Returns the value to which the specified key is mapped, or null if this
     * map contains no mapping for the key.
     */
    public V get(K key){
        if (containsKey(key))
            return (V) bstmap.get(key);
        else
            return null;
    }

    /* Returns the number of key-value mappings in this map. */
    public int size(){
        return bstmap.size();
    }

    /* Associates the specified value with the specified key in this map. */
    public void put(K key, V value){
        bstmap.insert(key, value);
    }

    /* Returns a Set view of the keys contained in this map. */
    public Set<K> keySet(){
        return bstmap.getKeys();
    }


    public void printInOrder(){
        Set set = bstmap.getKeys();
        for(Object key : set){
            System.out.println(key);
        }
    }

    /* Removes the mapping for the specified key from this map if present.
     * Not required for Lab 8. If you don't implement this, throw an
     * UnsupportedOperationException. */
    @Override
    public V remove(K key){
        V valueToReturn = this.get(key);
        if(valueToReturn != null){
            bstmap.delete(key);
            return valueToReturn;
        }
        else{
            return null;
        }
    }

    /* Removes the entry for the specified key only if it is currently mapped to
     * the specified value. Not required for Lab 8. If you don't implement this,
     * throw an UnsupportedOperationException.*/
    @Override
    public V remove(K key, V value){
        V valueToReturn = this.get(key);
        if (valueToReturn == value){
            bstmap.delete(key);
            return valueToReturn;
        }
        else{
            return null;
        }
    }

    @Override
    public Iterator<K> iterator() {
        return keySet().iterator();
    }


}

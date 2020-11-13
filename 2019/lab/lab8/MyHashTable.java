import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class MyHashTable<K, V> {

    private class Entry{
        private K key;
        private V val;
        private Entry next;
        private Entry pre;

        public Entry(K key, V val, Entry pre, Entry next){
            this.key = key;
            this.val = val;
            this.pre = pre;
            this.next = next;
        }

        public Entry find(K key){ // return Entry who has K key
            if (this.key.equals(key))
                return this;
            else if(this.next != null)
                return this.next.find(key);
            else
                return null;
        }

        /*
        public Entry getLast(){ // return the last Entry
            if (this.next == null)
                return this;
            return this.next.getLast();
        }
        */
    }

    private ArrayList<Entry> buckets;
    private int n; // count of pairs
    private int m; // count of buckets
    private double loadFac;

    public MyHashTable(int iniSize, double loadFac){
        buckets = new ArrayList<>(iniSize);
        m = iniSize;
        n = 0;
        this.loadFac = loadFac;
        for(int i = 0 ; i < iniSize ; i ++){
            buckets.add(null);
        }
    }
    public Set keySet(){
        HashSet<K> keyset = new HashSet<>();
        for(Entry front : buckets) {
            if (front == null)
                continue;
            else {
                while (front != null) {
                    keyset.add(front.key);
                    front = front.next;
                }
            }
        }
        return keyset;
    }

    public void clear(){
        for(int i = 0 ; i < m ; i ++){
            buckets.set(i, null);
        }
        this.n = 0;
        this.loadFac = 0;
    }

    public int size(){ // get key, val pairs
        return n;
    }
    private int getIndex(K key){
        int hashcode = key.hashCode();
        int index = (hashcode & 0x7fffffff) % m;
        return index;
    }

    private void checkUpdate(int n , int m){
        double fac = (n / m);
        if(fac >= loadFac)
            resize();
    }

    private void resize(){
        MyHashTable newTable = new MyHashTable(m * 2, loadFac);
        for(int i = 0 ; i < m * 2 ; i ++){
            newTable.buckets.add(null);
        }
        for(Entry front : buckets){
            if (front == null)
                continue;
            else{
                while(front != null){
                    newTable.put(front.key, front.val);
                    front = front.next;
                }
            }
        }
        this.m = newTable.m;
        this.n = newTable.n;
        this.buckets = newTable.buckets;
        this.loadFac = newTable.loadFac;

    }

    public void put(K key, V val){
        int index = getIndex(key);
        Entry e = new Entry(key, val, null, null);
        Entry front = this.buckets.get(index);
        if (this.contains(key)){  // if this key already exist, update val
            Entry temp = front.find(key);
            temp.val = val;
        }
        else{
            if (front != null){
                e.next = new Entry(front.key, front.val, e, front.next);
            }
            else{

            }
            this.buckets.set(index, e);
            n++;
            checkUpdate(n, m);
        }
    }

    public V get(K key){
        Entry e;
        for(Entry front : buckets){
            if (front == null)
                continue;
            else{
                e = front.find(key);
                if (e != null)
                    return e.val;
            }
        }
        return null;
    }

    public boolean contains(K key){
        return (this.get(key)!= null);
    }

    /**
     * Removes the mapping for the specified key from this map if present.
     * Not required for Lab 8. If you don't implement this, throw an
     * UnsupportedOperationException.
     */
    public V remove(K key){
        V val = get(key);
        return remove(key, val);
    }

    /**
     * Removes the entry for the specified key only if it is currently mapped to
     * the specified value. Not required for Lab 8. If you don't implement this,
     * throw an UnsupportedOperationException.
     */
    public V remove(K key, V value){
        if (!contains(key))
            return null;
        else{
            Entry e = null;
            for(Entry front : buckets){
                if (front == null)
                    continue;
                else{
                    e = front.find(key);
                    if(e != null)
                        break;
                }
            }
            V toReturn = e.val;
            if (toReturn != value)
                return null;
            else{
                if (e.pre == null)
                    this.buckets.set(getIndex(key), e.next);
                else
                    e.pre.next = e.next;
                return toReturn;
            }
        }
    }

    public static void main(String[] args){

        MyHashTable t = new MyHashTable(4, 1);
        t.put("a", 1);
        t.put("b", 2);
        t.put("cc", 3);
        t.put("d", 4);
        t.put("e", 5);
        t.put("f", 6);
        t.put("f", 11);
        t.put("g", 7);
        t.put("h", 8);
        t.put("i", 9);
        t.put(1, 10);
        System.out.println(t.get("cc"));
        System.out.println(t.get("g"));
        System.out.println(t.get("f"));
        System.out.println(t.get(1));
        System.out.println(t.contains("i"));
        System.out.println(t.contains("z"));
        Set keyset = t.keySet();
        t.clear();
        System.out.println(t.contains("z"));
        /*
        String s1 = "123";
        System.out.println(s1 == "123");
        System.out.println(Integer.valueOf(123).equals(123));
        */


    }

}

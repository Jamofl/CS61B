package Map61B;
import java.util.List;

import static org.junit.Assert.assertEquals;

public interface Map61B<K, V> {

    public void put(K key, V value);

    public boolean containsKey(K key);

    public V get(K key);

    public List<K> keys();

    public int size();
}

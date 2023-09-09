package my.map;

public interface MyMap<K, V> {

    V get(K key);

    void add(K key, V value);

    void delete(K key);

    int size();
}

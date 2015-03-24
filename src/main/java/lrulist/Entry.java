package lrulist;

public class Entry<K, V> {
    K key;
    V value;
    Node node;

    public Entry() {
    }

    public Entry(K key, V value, Node node) {
        this.key = key;
        this.value = value;
        this.node = node;
    }
}

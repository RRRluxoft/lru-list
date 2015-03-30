package lrulist;

public class EntryMap<K, V> implements LRUList.Entry<K, V> {
    final K key;
    final V value;
    private Node<?> node;

    public EntryMap(K key, V value) {
        this.key = key;
        this.value = value;
    }

    public Node<?> getNode() {
        return node;
    }

    public void setNode(Node<?> node) {
        this.node = node;
    }

    @Override
    public K getKey() {
        return key;
    }

    @Override
    public V getValue() {
        return value;
    }
}

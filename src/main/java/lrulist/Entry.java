package lrulist;

public class Entry<K, V, E> {
    final K key;
    final V value;
    Node<E> node;

    public Entry(K key, V value, Node<E> node) {
        this.key = key;
        this.value = value;
        this.node = node;
    }

    public Node<E> getNode() {
        return node;
    }

    public void setNode(Node<E> node) {
        this.node = node;
    }
}

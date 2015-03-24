package lrulist;


import java.util.concurrent.atomic.AtomicReference;

public class Node<E> {

    private Entry entry;
    final AtomicReference<Node<E>> next;
    final AtomicReference<Node<E>> prev;

    public Node(AtomicReference<Node<E>> prev, Entry entry, AtomicReference<Node<E>> next) {
        this.prev = prev;
        this.next = next;
        this.entry = entry;
    }

    public Entry getEntry() {
        return entry;
    }

    public void setEntry(Entry entry) {
        this.entry = entry;
    }
}

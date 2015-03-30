package lrulist;


import java.util.concurrent.atomic.AtomicReference;

public class Node<E> {

    private EntryMap entry;
    final AtomicReference<Node<E>> next;
    final AtomicReference<Node<E>> prev;

    public Node(AtomicReference<Node<E>> prev, EntryMap entry, AtomicReference<Node<E>> next) {
        this.prev = prev;
        this.next = next;
        this.entry = entry;
    }

    public EntryMap getEntry() {
        return entry;
    }

    public void setEntry(EntryMap entry) {
        this.entry = entry;
    }
}

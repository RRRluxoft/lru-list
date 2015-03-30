package lrulist;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;
import lrulist.EntryMap;

/**
 * Simple LRU list.
 *
 * @param <K>
 * @param <V>
 */
public class SimpleLRUList<K, V> implements LRUList<K, V> {
    AtomicBoolean flag = new AtomicBoolean(false);
    private final Object lock = new Object();
    private final LinkedList<Node> nodeList = new LinkedList<Node>();
    private final ConcurrentHashMap<K, V> map = new ConcurrentHashMap<K, V>();

    private AtomicReference<Node> head = new AtomicReference<Node>(new Node(null, null, null));
    private AtomicReference<Node> tail = head;

    private final int capacity;

    public SimpleLRUList(int capacity) {
        this.capacity = capacity;
    }

    @Override
    public V get(K key) {
        synchronized (lock) {
            final V value = map.remove(key);

            if (value == null)
                return null;

            map.put(key, value);

            return value;
        }
    }

    @Override
    public V put(K key, V value) {
        V oldValue = map.get(key);
        Node newNode = new Node(tail, null , null);
        nodeList.offerLast(newNode);

        if (oldValue != null)
            return oldValue;

        map.putIfAbsent(key, value);

        return value;
    }

    private  boolean offer(EntryMap<K, V> entry) {
        if (entry != tail.get().getEntry()) {
            Node curNodeList = nodeList.getLast();
            Node newNode = new Node(null, entry, null);
            Node currNode = entry.getNode();

            if (entry.getNode().compareAndSet(currNode, newNode)) {
                tail.lazySet(newNode);
                return true;
                if (currNode != null) {
                   // TODO:  cleanup()
                }
            }
            return true;
        }
        return false;
    }

    private  void purge() {

    }

    private void evict(Node node) {

    }

    @Override
    public Iterator<Entry<K, V>> iterator() {
        final List<Entry<K, V>> entries = new ArrayList<Entry<K, V>>();

        synchronized (lock) {
            for (final Map.Entry<K, V> entry : map.entrySet()) {
                entries.add(new Entry<K, V>() {
                    @Override
                    public K getKey() {
                        return entry.getKey();
                    }

                    @Override
                    public V getValue() {
                        return entry.getValue();
                    }
                });
            }
        }
        return entries.iterator();
    }

}

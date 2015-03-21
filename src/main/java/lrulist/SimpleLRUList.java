package lrulist;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedDeque;

/**
 * Simple LRU list.
 *
 * @param <K>
 * @param <V>
 */
public class SimpleLRUList<K, V> implements LRUList<K, V> {
    private final Object lock = new Object();
    private final ConcurrentHashMap<K, V> map = new ConcurrentHashMap<K, V>() {
//        @Override
        protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
            return capacity < size();
        }
    };
    private final Deque<Node> nodeList = new ConcurrentLinkedDeque<Node>();

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
        synchronized (lock) {
            V oldValue = map.get(key);

            if (oldValue != null)
                return oldValue;

            map.putIfAbsent(key, value);

            return value;
        }
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

    class Node {

    }
}

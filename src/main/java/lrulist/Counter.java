package lrulist;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Roman Litvishko
 */
public class Counter {
    private AtomicInteger val;

    public Counter(int start) {
        this.val = new AtomicInteger(start);
    }

    public int getVal() {
        return val.get();
    }

    public int increment() {
        int v;
        do {
            v = val.get();
        }  while (!val.compareAndSet(v, v + 1));
        return v + 1;
    }
}

package lrulist;

import junit.framework.TestCase;
import org.junit.Test;

import static org.junit.Assert.*;

public class CounterTest extends TestCase {

    @Test
    public void testIncrement() throws Exception {
        Counter counter = new Counter(2);
        assertEquals(3, counter.increment());
        System.out.println(counter.getVal());
    }
}
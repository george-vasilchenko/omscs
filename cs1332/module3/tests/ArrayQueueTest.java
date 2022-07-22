import java.lang.reflect.Array;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ArrayQueueTest {

    @org.junit.jupiter.api.Test
    void enqueue_dataNull_throws() {
        ArrayQueue<Integer> queue = new ArrayQueue<>();
        assertThrowsExactly(IllegalArgumentException.class, () -> {
            queue.enqueue(null);
        });
    }

    @org.junit.jupiter.api.Test
    void enqueue_firstElement_adds() {
        ArrayQueue<Integer> queue = new ArrayQueue<>();

        int data0 = 1;
        queue.enqueue(data0);

        assertEquals(queue.size(), 1);
        Object valueAt0 = Array.get(queue.getBackingArray(), 0);
        assertEquals(data0, valueAt0);
    }

    @org.junit.jupiter.api.Test
    void enqueue_secondElement_adds() {
        ArrayQueue<Integer> queue = new ArrayQueue<>();

        int data0 = 1;
        queue.enqueue(data0);

        int data1 = 2;
        queue.enqueue(data1);

        assertEquals(queue.size(), 2);
        Object valueAt0 = Array.get(queue.getBackingArray(), 0);
        assertEquals(data0, valueAt0);
        Object valueAt1 = Array.get(queue.getBackingArray(), 1);
        assertEquals(data1, valueAt1);
    }

    @org.junit.jupiter.api.Test
    void enqueue_overCapacity_resizesAndAdds() {
        ArrayQueue<Integer> queue = new ArrayQueue<>();

        int startCapacity = 9;
        Integer initialLength = Array.getLength(queue.getBackingArray());
        assertEquals(initialLength, startCapacity);
        for (int i = 0; i < initialLength; i++) {
            queue.enqueue(i);
        }

        int data = 9;
        queue.enqueue(data);

        assertEquals(Array.getLength(queue.getBackingArray()), initialLength * 2);
        assertEquals(queue.size(), initialLength + 1);

        for (int i = 0; i < queue.size(); i++) {
            Object value = Array.get(queue.getBackingArray(), i);
            assertEquals(i, value);
        }
    }

    @org.junit.jupiter.api.Test
    void dequeue_empty_throws() {
        ArrayQueue<Integer> queue = new ArrayQueue<>();
        assertThrowsExactly(NoSuchElementException.class, () -> {
            queue.dequeue();
        });
    }

    @org.junit.jupiter.api.Test
    void dequeue_singleElement_removes() {
        ArrayQueue<Integer> queue = new ArrayQueue<>();

        int data0 = 1;
        queue.enqueue(data0);

        int result = queue.dequeue();

        assertEquals(queue.size(), 0);
        assertEquals(data0, result);

        for (int i = 0; i < Array.getLength(queue.getBackingArray()); i++) {
            Object value = Array.get(queue.getBackingArray(), i);
            assertEquals(null, value);
        }
    }

    @org.junit.jupiter.api.Test
    void dequeue_twoElements_removes() {
        ArrayQueue<Integer> queue = new ArrayQueue<>();

        int data0 = 1;
        queue.enqueue(data0);

        int data1 = 2;
        queue.enqueue(data1);

        int result = queue.dequeue();

        assertEquals(queue.size(), 1);
        assertEquals(data0, result);

        Object value = Array.get(queue.getBackingArray(), 1);
        assertEquals(data1, value);
    }

    @org.junit.jupiter.api.Test
    void dequeue_withResize_removes() {
        ArrayQueue<Integer> queue = new ArrayQueue<>();

        for (int i = 0; i < 40; i++) {
            int data = i;
            queue.enqueue(data);
        }
        assertEquals(queue.size(), 40);

        for (int i = 0; i < 40; i++) {
            int result = queue.dequeue();
            assertEquals(result, i);
        }
        assertEquals(queue.size(), 0);
    }

    @org.junit.jupiter.api.Test
    void dequeue_circular_removes() {
        ArrayQueue<Integer> queue = new ArrayQueue<>();
        final int capacity = 9;
        for (int i = 0; i < capacity * 2; i++) {
            int data = i;
            queue.enqueue(data);

            Object value = Array.get(queue.getBackingArray(), i % capacity);
            assertEquals(value, data);

            int result = queue.dequeue();
            assertEquals(result, data);
        }
    }

    @org.junit.jupiter.api.Test
    void combined_random_addsAndRemoves() {
        ArrayQueue<Integer> queue = new ArrayQueue<>();

        // enqueue 3 elements
        queue.enqueue(0);
        queue.enqueue(1);
        queue.enqueue(2);
        assertEquals(Array.get(queue.getBackingArray(), 0), 0);
        assertEquals(Array.get(queue.getBackingArray(), 1), 1);
        assertEquals(Array.get(queue.getBackingArray(), 2), 2);
        assertEquals(Array.get(queue.getBackingArray(), 3), null);
        assertEquals(Array.get(queue.getBackingArray(), 4), null);
        assertEquals(Array.get(queue.getBackingArray(), 5), null);
        assertEquals(Array.get(queue.getBackingArray(), 6), null);
        assertEquals(Array.get(queue.getBackingArray(), 7), null);
        assertEquals(Array.get(queue.getBackingArray(), 8), null);
        assertEquals(queue.size(), 3);


        // dequeue 2
        int res0 = queue.dequeue();
        int res1 = queue.dequeue();
        assertEquals(res0, 0);
        assertEquals(res1, 1);

        assertEquals(Array.get(queue.getBackingArray(), 0), null);
        assertEquals(Array.get(queue.getBackingArray(), 1), null);
        assertEquals(Array.get(queue.getBackingArray(), 2), 2);
        assertEquals(Array.get(queue.getBackingArray(), 3), null);
        assertEquals(Array.get(queue.getBackingArray(), 4), null);
        assertEquals(Array.get(queue.getBackingArray(), 5), null);
        assertEquals(Array.get(queue.getBackingArray(), 6), null);
        assertEquals(Array.get(queue.getBackingArray(), 7), null);
        assertEquals(Array.get(queue.getBackingArray(), 8), null);
        assertEquals(queue.size(), 1);

        // enqueue 5 more
        queue.enqueue(5);
        queue.enqueue(11);
        queue.enqueue(-2);
        queue.enqueue(102);
        queue.enqueue(19);

        assertEquals(Array.get(queue.getBackingArray(), 0), null);
        assertEquals(Array.get(queue.getBackingArray(), 1), null);
        assertEquals(Array.get(queue.getBackingArray(), 2), 2);
        assertEquals(Array.get(queue.getBackingArray(), 3), 5);
        assertEquals(Array.get(queue.getBackingArray(), 4), 11);
        assertEquals(Array.get(queue.getBackingArray(), 5), -2);
        assertEquals(Array.get(queue.getBackingArray(), 6), 102);
        assertEquals(Array.get(queue.getBackingArray(), 7), 19);
        assertEquals(Array.get(queue.getBackingArray(), 8), null);
        assertEquals(queue.size(), 6);

        // dequeue 3
        int res2 = queue.dequeue();
        int res3 = queue.dequeue();
        int res4 = queue.dequeue();
        assertEquals(res2, 2);
        assertEquals(res3, 5);
        assertEquals(res4, 11);

        assertEquals(Array.get(queue.getBackingArray(), 0), null);
        assertEquals(Array.get(queue.getBackingArray(), 1), null);
        assertEquals(Array.get(queue.getBackingArray(), 2), null);
        assertEquals(Array.get(queue.getBackingArray(), 3), null);
        assertEquals(Array.get(queue.getBackingArray(), 4), null);
        assertEquals(Array.get(queue.getBackingArray(), 5), -2);
        assertEquals(Array.get(queue.getBackingArray(), 6), 102);
        assertEquals(Array.get(queue.getBackingArray(), 7), 19);
        assertEquals(Array.get(queue.getBackingArray(), 8), null);
        assertEquals(queue.size(), 3);

        // enqueue 3
        queue.enqueue(4);
        queue.enqueue(44);
        queue.enqueue(444);
        assertEquals(Array.get(queue.getBackingArray(), 0), 44);
        assertEquals(Array.get(queue.getBackingArray(), 1), 444);
        assertEquals(Array.get(queue.getBackingArray(), 2), null);
        assertEquals(Array.get(queue.getBackingArray(), 3), null);
        assertEquals(Array.get(queue.getBackingArray(), 4), null);
        assertEquals(Array.get(queue.getBackingArray(), 5), -2);
        assertEquals(Array.get(queue.getBackingArray(), 6), 102);
        assertEquals(Array.get(queue.getBackingArray(), 7), 19);
        assertEquals(Array.get(queue.getBackingArray(), 8), 4);
        assertEquals(queue.size(), 6);

        // add 3 more to make full
        queue.enqueue(5);
        queue.enqueue(55);
        queue.enqueue(555);
        assertEquals(Array.get(queue.getBackingArray(), 0), 44);
        assertEquals(Array.get(queue.getBackingArray(), 1), 444);
        assertEquals(Array.get(queue.getBackingArray(), 2), 5);
        assertEquals(Array.get(queue.getBackingArray(), 3), 55);
        assertEquals(Array.get(queue.getBackingArray(), 4), 555);
        assertEquals(Array.get(queue.getBackingArray(), 5), -2);
        assertEquals(Array.get(queue.getBackingArray(), 6), 102);
        assertEquals(Array.get(queue.getBackingArray(), 7), 19);
        assertEquals(Array.get(queue.getBackingArray(), 8), 4);
        assertEquals(queue.size(), 9);

        // add 1 more to resize and realign
        queue.enqueue(0);
        assertEquals(Array.get(queue.getBackingArray(), 0), -2);
        assertEquals(Array.get(queue.getBackingArray(), 1), 102);
        assertEquals(Array.get(queue.getBackingArray(), 2), 19);
        assertEquals(Array.get(queue.getBackingArray(), 3), 4);
        assertEquals(Array.get(queue.getBackingArray(), 4), 44);
        assertEquals(Array.get(queue.getBackingArray(), 5), 444);
        assertEquals(Array.get(queue.getBackingArray(), 6), 5);
        assertEquals(Array.get(queue.getBackingArray(), 7), 55);
        assertEquals(Array.get(queue.getBackingArray(), 8), 555);
        assertEquals(Array.get(queue.getBackingArray(), 9), 0);
        assertEquals(Array.get(queue.getBackingArray(), 10), null);
        assertEquals(Array.get(queue.getBackingArray(), 11), null);
        assertEquals(Array.get(queue.getBackingArray(), 12), null);
        assertEquals(Array.get(queue.getBackingArray(), 13), null);
        assertEquals(Array.get(queue.getBackingArray(), 14), null);
        assertEquals(Array.get(queue.getBackingArray(), 15), null);
        assertEquals(Array.get(queue.getBackingArray(), 16), null);
        assertEquals(Array.get(queue.getBackingArray(), 17), null);
        assertEquals(queue.size(), 10);

        // cleanup
        assertEquals(queue.dequeue(), -2);
        assertEquals(queue.dequeue(), 102);
        assertEquals(queue.dequeue(), 19);
        assertEquals(queue.dequeue(), 4);
        assertEquals(queue.dequeue(), 44);
        assertEquals(queue.dequeue(), 444);
        assertEquals(queue.dequeue(), 5);
        assertEquals(queue.dequeue(), 55);
        assertEquals(queue.dequeue(), 555);
        assertEquals(queue.dequeue(), 0);
        assertEquals(queue.size(), 0);

        for (int i = 0; i < 18; i++) {
            assertEquals(Array.get(queue.getBackingArray(), i), null);
        }
    }
}
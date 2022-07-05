import java.lang.reflect.Array;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

class ArrayListTest {

    @org.junit.jupiter.api.Test
    void addToFront_dataNull_throws() {
        ArrayList<Integer> arrayList = new ArrayList<>();

        assertThrowsExactly(IllegalArgumentException.class, () -> {
            arrayList.addToFront(null);
        });
    }

    @org.junit.jupiter.api.Test
    void addToFront_1_noResize() {
        ArrayList<Integer> arrayList = new ArrayList<>();
        Integer initialSize = arrayList.size();

        arrayList.addToFront(0);
        Integer currentSize = arrayList.size();

        assertNotEquals(initialSize, currentSize);
        Object valueAt0 = Array.get(arrayList.getBackingArray(), 0);
        assertEquals(0, valueAt0);
    }

    @org.junit.jupiter.api.Test
    void addToFront_2_noResize() {
        ArrayList<Integer> arrayList = new ArrayList<Integer>();
        Integer initialSize = arrayList.size();

        arrayList.addToFront(1);
        arrayList.addToFront(0);
        Integer currentSize = arrayList.size();

        assertNotEquals(initialSize, currentSize);
        Object valueAt0 = Array.get(arrayList.getBackingArray(), 0);
        assertEquals(0, valueAt0);

        Object valueAt1 = Array.get(arrayList.getBackingArray(), 1);
        assertEquals(1, valueAt1);
    }

    @org.junit.jupiter.api.Test
    void addToFront_3_noResize() {
        ArrayList<Integer> arrayList = new ArrayList<Integer>();
        Integer initialSize = arrayList.size();

        arrayList.addToFront(2);
        arrayList.addToFront(1);
        arrayList.addToFront(0);

        Integer currentSize = arrayList.size();
        assertNotEquals(initialSize, currentSize);

        Object valueAt0 = Array.get(arrayList.getBackingArray(), 0);
        assertEquals(0, valueAt0);

        Object valueAt1 = Array.get(arrayList.getBackingArray(), 1);
        assertEquals(1, valueAt1);

        Object valueAt2 = Array.get(arrayList.getBackingArray(), 2);
        assertEquals(2, valueAt2);
    }

    @org.junit.jupiter.api.Test
    void addToFront_4_noResize() {
        ArrayList<Integer> arrayList = new ArrayList<Integer>();
        Integer initialSize = arrayList.size();

        arrayList.addToFront(3);
        arrayList.addToFront(2);
        arrayList.addToFront(1);
        arrayList.addToFront(0);

        Integer currentSize = arrayList.size();
        assertNotEquals(initialSize, currentSize);

        Object valueAt0 = Array.get(arrayList.getBackingArray(), 0);
        assertEquals(0, valueAt0);

        Object valueAt1 = Array.get(arrayList.getBackingArray(), 1);
        assertEquals(1, valueAt1);

        Object valueAt2 = Array.get(arrayList.getBackingArray(), 2);
        assertEquals(2, valueAt2);

        Object valueAt3 = Array.get(arrayList.getBackingArray(), 3);
        assertEquals(3, valueAt3);
    }

    @org.junit.jupiter.api.Test
    void addToFront_resizes(){
        ArrayList<Integer> arrayList = new ArrayList<Integer>();
        Object initialArray = arrayList.getBackingArray();
        Integer initialCapacity = Array.getLength(arrayList.getBackingArray());

        for (int i = 0; i <= 9; i++){
            arrayList.addToFront(9 - i);
        }

        Object currentArray = arrayList.getBackingArray();
        assertNotEquals(initialArray, currentArray);

        Integer currentCapacity = Array.getLength(arrayList.getBackingArray());
        assertNotEquals(initialCapacity, currentCapacity);
        assertEquals(currentCapacity, initialCapacity * 2);
    }

    @org.junit.jupiter.api.Test
    void addToBack_dataNull_throws() {
        ArrayList<Integer> arrayList = new ArrayList<>();

        assertThrowsExactly(IllegalArgumentException.class, () -> {
            arrayList.addToBack(null);
        });
    }

    @org.junit.jupiter.api.Test
    void addToBack_1_noResize() {
        ArrayList<Integer> arrayList = new ArrayList<>();
        Integer initialSize = arrayList.size();

        arrayList.addToBack(0);

        Integer currentSize = arrayList.size();
        assertNotEquals(initialSize, currentSize);

        Object valueAt0 = Array.get(arrayList.getBackingArray(), 0);
        assertEquals(0, valueAt0);
    }

    @org.junit.jupiter.api.Test
    void addToBack_2_noResize() {
        ArrayList<Integer> arrayList = new ArrayList<>();
        Integer initialSize = arrayList.size();

        arrayList.addToBack(0);
        arrayList.addToBack(1);

        Integer currentSize = arrayList.size();
        assertNotEquals(initialSize, currentSize);
        Object valueAt0 = Array.get(arrayList.getBackingArray(), 0);
        assertEquals(0, valueAt0);
        Object valueAt1 = Array.get(arrayList.getBackingArray(), 1);
        assertEquals(1, valueAt1);
    }

    @org.junit.jupiter.api.Test
    void addToBack_3_noResize() {
        ArrayList<Integer> arrayList = new ArrayList<>();
        Integer initialSize = arrayList.size();

        arrayList.addToBack(0);
        arrayList.addToBack(1);
        arrayList.addToBack(2);

        Integer currentSize = arrayList.size();
        assertNotEquals(initialSize, currentSize);
        Object valueAt0 = Array.get(arrayList.getBackingArray(), 0);
        assertEquals(0, valueAt0);
        Object valueAt1 = Array.get(arrayList.getBackingArray(), 1);
        assertEquals(1, valueAt1);
        Object valueAt2 = Array.get(arrayList.getBackingArray(), 2);
        assertEquals(2, valueAt2);
    }

    @org.junit.jupiter.api.Test
    void addToBack_4_noResize() {
        ArrayList<Integer> arrayList = new ArrayList<>();
        Integer initialSize = arrayList.size();

        arrayList.addToBack(0);
        arrayList.addToBack(1);
        arrayList.addToBack(2);
        arrayList.addToBack(3);

        Integer currentSize = arrayList.size();
        assertNotEquals(initialSize, currentSize);
        Object valueAt0 = Array.get(arrayList.getBackingArray(), 0);
        assertEquals(0, valueAt0);
        Object valueAt1 = Array.get(arrayList.getBackingArray(), 1);
        assertEquals(1, valueAt1);
        Object valueAt2 = Array.get(arrayList.getBackingArray(), 2);
        assertEquals(2, valueAt2);
        Object valueAt3 = Array.get(arrayList.getBackingArray(), 3);
        assertEquals(3, valueAt3);
    }

    @org.junit.jupiter.api.Test
    void addToBack_resizes(){
        ArrayList<Integer> arrayList = new ArrayList<Integer>();
        Object initialArray = arrayList.getBackingArray();
        Integer initialCapacity = Array.getLength(arrayList.getBackingArray());

        for (int i = 0; i <= 9; i++){
            arrayList.addToBack(i);
        }

        Object currentArray = arrayList.getBackingArray();
        assertNotEquals(initialArray, currentArray);

        Integer currentCapacity = Array.getLength(arrayList.getBackingArray());
        assertNotEquals(initialCapacity, currentCapacity);
        assertEquals(currentCapacity, initialCapacity * 2);
    }

    @org.junit.jupiter.api.Test
    void removeFromFront() {
        ArrayList<Integer> arrayList = new ArrayList<>();

        assertThrowsExactly(NoSuchElementException.class, () -> {
            arrayList.removeFromFront();
        });
    }

    @org.junit.jupiter.api.Test
    void removeFromFront_1() {
        ArrayList<Integer> arrayList = new ArrayList<>();
        Integer addedValue = 0;
        arrayList.addToBack(addedValue);
        Integer initialSize = arrayList.size();

        Integer removedValue = arrayList.removeFromFront();

        Integer currentSize = arrayList.size();
        assertNotEquals(initialSize, currentSize);
        assertEquals(addedValue, removedValue);
    }

    @org.junit.jupiter.api.Test
    void removeFromFront_2() {
        ArrayList<Integer> arrayList = new ArrayList<>();
        Integer addedVal0 = 0;
        arrayList.addToBack(addedVal0);
        Integer addedVal1 = 1;
        arrayList.addToBack(addedVal1);
        Integer initialSize = arrayList.size();

        Integer removedValue = arrayList.removeFromFront();

        Integer currentSize = arrayList.size();
        assertNotEquals(initialSize, currentSize);
        assertEquals(addedVal0, removedValue);

        Object valueAt0 = Array.get(arrayList.getBackingArray(), 0);
        assertEquals(addedVal1, valueAt0);
    }

    @org.junit.jupiter.api.Test
    void removeFromFront_3() {
        ArrayList<Integer> arrayList = new ArrayList<>();
        Integer addedVal0 = 0;
        arrayList.addToBack(addedVal0);
        Integer addedVal1 = 1;
        arrayList.addToBack(addedVal1);
        Integer addedVal2 = 2;
        arrayList.addToBack(addedVal2);
        Integer initialSize = arrayList.size();

        Integer removedValue = arrayList.removeFromFront();

        Integer currentSize = arrayList.size();
        assertNotEquals(initialSize, currentSize);
        assertEquals(addedVal0, removedValue);

        Object valueAt0 = Array.get(arrayList.getBackingArray(), 0);
        assertEquals(addedVal1, valueAt0);

        Object valueAt1 = Array.get(arrayList.getBackingArray(), 1);
        assertEquals(addedVal2, valueAt1);
    }

    @org.junit.jupiter.api.Test
    void removeFromFront_n() {
        ArrayList<Integer> arrayList = new ArrayList<>();

        Integer n = 16;
        int[] inputs = new int[n];
        for (int i = 0; i < n; i++) {
            inputs[i] = i;
            arrayList.addToBack(i);
        }
        Integer initialSize = arrayList.size();

        Integer removedValue = arrayList.removeFromFront();

        Integer currentSize = arrayList.size();
        assertNotEquals(initialSize, currentSize);
        assertEquals(inputs[0], removedValue);

        for (int i = 0; i < arrayList.size(); i++) {
            int inputValue = inputs[i];
            Object arrayValue = Array.get(arrayList.getBackingArray(), i);
            assertEquals(inputValue + 1, arrayValue);
        }
    }

    @org.junit.jupiter.api.Test
    void removeFromBack_empty_throws() {
        ArrayList<Integer> arrayList = new ArrayList<>();

        assertThrowsExactly(NoSuchElementException.class, () -> {
            arrayList.removeFromBack();
        });
    }

    @org.junit.jupiter.api.Test
    void removeFromBack_removesOne() {
        ArrayList<Integer> arrayList = new ArrayList<>();
        Integer addedValue = 0;
        arrayList.addToBack(addedValue);
        Integer initialSize = arrayList.size();

        Integer removedValue = arrayList.removeFromBack();

        Integer currentSize = arrayList.size();
        assertNotEquals(initialSize, currentSize);
        assertEquals(addedValue, removedValue);
    }
}
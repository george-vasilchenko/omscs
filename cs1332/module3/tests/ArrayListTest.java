import org.junit.jupiter.api.Test;

import java.lang.reflect.Array;

import static org.junit.jupiter.api.Assertions.*;

class ArrayListTest {

    @Test
    void addAtIndex_first_adds() {
        ArrayList<Integer> al = new ArrayList<>();
        al.addAtIndex(0, -1);

        Object value = Array.get(al.getBackingArray(), 0);
        assertEquals(value, -1);
    }

    @Test
    void addAtIndex_multipleRandom_adds() {
        ArrayList<Integer> al = new ArrayList<>();
        al.addAtIndex(0, 12);
        al.addAtIndex(1, 87);
        al.addAtIndex(2, 12);
        al.addAtIndex(3, 5);

        assertEquals(Array.get(al.getBackingArray(), 0), 12);
        assertEquals(Array.get(al.getBackingArray(), 1), 87);
        assertEquals(Array.get(al.getBackingArray(), 2), 12);
        assertEquals(Array.get(al.getBackingArray(), 3), 5);

        al.addAtIndex(0, 100);

        assertEquals(Array.get(al.getBackingArray(), 0), 100);
        assertEquals(Array.get(al.getBackingArray(), 1), 12);
        assertEquals(Array.get(al.getBackingArray(), 2), 87);
        assertEquals(Array.get(al.getBackingArray(), 3), 12);
        assertEquals(Array.get(al.getBackingArray(), 4), 5);
    }
}
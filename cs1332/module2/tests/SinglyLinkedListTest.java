import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class SinglyLinkedListTest {

    @Test
    void addToFront_dataNull_throws() {
        assertThrowsExactly(
                IllegalArgumentException.class,
                () -> new SinglyLinkedList<>().addToFront(null));
    }

    @Test
    void addToFront_addFirst_adds() {
        SinglyLinkedList<Integer> ll = new SinglyLinkedList<>();
        int data = 10;

        assertNull(ll.getHead());
        assertNull(ll.getTail());
        assertEquals(ll.size(), 0);

        ll.addToFront(data);

        assertEquals(ll.getHead().getData(), data);
        assertEquals(ll.getTail().getData(), data);
        assertEquals(ll.size(), 1);
    }

    @Test
    void addToFront_addSecond_adds() {
        SinglyLinkedList<Integer> ll = new SinglyLinkedList<>();
        int dataFirst = 2;
        int dataSecond = 1;

        ll.addToFront(dataFirst);
        ll.addToFront(dataSecond);

        assertEquals(ll.getHead().getData(), dataSecond);
        assertEquals(ll.getTail().getData(), dataFirst);
        assertEquals(ll.getHead().getNext().getData(), dataFirst);
        assertEquals(ll.size(), 2);
    }

    @Test
    void addToFront_addThird_adds() {
        SinglyLinkedList<Integer> ll = new SinglyLinkedList<>();
        int dataFirst = 3;
        int dataSecond = 2;
        int dataThird = 1;

        ll.addToFront(dataFirst);
        ll.addToFront(dataSecond);
        ll.addToFront(dataThird);

        assertEquals(ll.getHead().getData(), dataThird);
        assertEquals(ll.getTail().getData(), dataFirst);
        assertEquals(ll.getHead().getNext().getData(), dataSecond);
        assertEquals(ll.size(), 3);
    }

    @Test
    void addToFront_addNth_adds() {
        SinglyLinkedList<Integer> ll = new SinglyLinkedList<>();
        int maxCount = (int) Math.floor(Math.random()*(100-1+1)+1);
        int[] dataInputs = new int[maxCount];

        for (int i = dataInputs.length - 1; i >= 0; i--) {
            dataInputs[i] = i;
            ll.addToFront(i);
        }

        assertEquals(ll.getHead().getData(), dataInputs[0]);
        assertEquals(ll.getTail().getData(), dataInputs[maxCount - 1]);
        assertEquals(ll.size(), maxCount);

        assertNodes(ll.getHead(), dataInputs, 0);
    }

    @Test
    void addToBack_dataNull_throws() {
        assertThrowsExactly(
                IllegalArgumentException.class,
                () -> new SinglyLinkedList<>().addToBack(null));
    }

    @Test
    void addToBack_addFirst_adds() {
        SinglyLinkedList<Integer> ll = new SinglyLinkedList<>();
        int data = 10;

        assertNull(ll.getHead());
        assertNull(ll.getTail());
        assertEquals(ll.size(), 0);

        ll.addToBack(data);

        assertEquals(ll.getHead().getData(), data);
        assertEquals(ll.getTail().getData(), data);
        assertEquals(ll.size(), 1);
    }

    @Test
    void addToBack_addSecond_adds() {
        SinglyLinkedList<Integer> ll = new SinglyLinkedList<>();
        int dataFirst = 1;
        int dataSecond = 2;

        ll.addToBack(dataFirst);
        ll.addToBack(dataSecond);

        assertEquals(ll.getHead().getData(), dataFirst);
        assertEquals(ll.getTail().getData(), dataSecond);
        assertEquals(ll.getHead().getNext().getData(), dataSecond);
        assertEquals(ll.size(), 2);
    }

    @Test
    void addToBack_addThird_adds() {
        SinglyLinkedList<Integer> ll = new SinglyLinkedList<>();
        int dataFirst = 1;
        int dataSecond = 2;
        int dataThird = 3;

        ll.addToBack(dataFirst);
        ll.addToBack(dataSecond);
        ll.addToBack(dataThird);

        assertEquals(ll.getHead().getData(), dataFirst);
        assertEquals(ll.getTail().getData(), dataThird);
        assertEquals(ll.getHead().getNext().getData(), dataSecond);
        assertEquals(ll.size(), 3);
    }

    @Test
    void addToBack_addNth_adds() {
        SinglyLinkedList<Integer> ll = new SinglyLinkedList<>();
        int maxCount = (int) Math.floor(Math.random()*(100-1+1)+1);
        int[] dataInputs = new int[maxCount];

        for (int i = 0; i < dataInputs.length; i++) {
            dataInputs[i] = i;
            ll.addToBack(i);
        }

        assertEquals(ll.getHead().getData(), dataInputs[0]);
        assertEquals(ll.getTail().getData(), dataInputs[maxCount - 1]);
        assertEquals(ll.size(), maxCount);

        assertNodes(ll.getHead(), dataInputs, 0);
    }

    @Test
    void removeFromFront_empty_throws() {
        assertThrowsExactly(
                NoSuchElementException.class,
                () -> new SinglyLinkedList<>().removeFromFront());
    }

    @Test
    void removeFromFront_hasOne_removes() {
        SinglyLinkedList<Integer> ll = new SinglyLinkedList<>();
        int data = 1;
        ll.addToFront(data);
        assertEquals(ll.size(), 1);

        int nodeData = ll.removeFromFront();

        assertEquals(nodeData, data);
        assertEquals(ll.size(), 0);
        assertEquals(ll.getHead(), null);
        assertEquals(ll.getTail(), null);
    }

    @Test
    void removeFromFront_hasTwo_removes() {
        SinglyLinkedList<Integer> ll = new SinglyLinkedList<>();
        int dataFirst = 2;
        int dataSecond = 1;
        ll.addToFront(dataFirst);
        ll.addToFront(dataSecond);
        assertEquals(ll.size(), 2);

        int nodeData = ll.removeFromFront();

        assertEquals(nodeData, dataSecond);
        assertEquals(ll.size(), 1);
        assertEquals(ll.getHead().getData(), dataFirst);
        assertEquals(ll.getTail().getData(), dataFirst);
    }

    @Test
    void removeFromFront_hasThree_removes() {
        SinglyLinkedList<Integer> ll = new SinglyLinkedList<>();
        int dataFirst = 3;
        int dataSecond = 2;
        int dataThird = 1;
        ll.addToFront(dataFirst);
        ll.addToFront(dataSecond);
        ll.addToFront(dataThird);
        assertEquals(ll.size(), 3);

        int nodeData = ll.removeFromFront();

        assertEquals(nodeData, dataThird);
        assertEquals(ll.size(), 2);
        assertEquals(ll.getHead().getData(), dataSecond);
        assertEquals(ll.getTail().getData(), dataFirst);
    }

    @Test
    void removeFromFront_hasN_removes() {
        SinglyLinkedList<Integer> ll = new SinglyLinkedList<>();
        int inputsCount = ThreadLocalRandom.current().nextInt(32, 128 + 1);
        ArrayList<Integer> dataInputs = new ArrayList<>();
        for (int i = 0; i < inputsCount; i++) {
            dataInputs.add(i);
            ll.addToBack(i);
        }

        int toRemoveCount = ThreadLocalRandom.current().nextInt(2,  16 + 1);
        for (int i = 0; i < toRemoveCount; i++) {
            dataInputs.remove(0);
            ll.removeFromFront();
        }

        assertEquals(ll.getHead().getData(), dataInputs.get(0));
        assertEquals(ll.getTail().getData(), dataInputs.get(inputsCount - toRemoveCount - 1));

        int[] dataInputsArray = dataInputs.stream().mapToInt(i -> i).toArray();
        assertNodes(ll.getHead(), dataInputsArray, 0);
    }

    @Test
    void removeFromBack_empty_throws() {
        assertThrowsExactly(
                NoSuchElementException.class,
                () -> new SinglyLinkedList<>().removeFromBack());
    }

    @Test
    void removeFromBack_hasOne_removes() {
        SinglyLinkedList<Integer> ll = new SinglyLinkedList<>();
        int data = 1;
        ll.addToFront(data);
        assertEquals(ll.size(), 1);

        int nodeData = ll.removeFromBack();

        assertEquals(nodeData, data);
        assertEquals(ll.size(), 0);
        assertEquals(ll.getHead(), null);
        assertEquals(ll.getTail(), null);
    }

    @Test
    void removeFromBack_hasTwo_removes() {
        SinglyLinkedList<Integer> ll = new SinglyLinkedList<>();
        int dataFirst = 2;
        int dataSecond = 1;
        ll.addToFront(dataFirst);
        ll.addToFront(dataSecond);
        assertEquals(ll.size(), 2);

        int nodeData = ll.removeFromBack();

        assertEquals(nodeData, dataFirst);
        assertEquals(ll.size(), 1);
        assertEquals(ll.getHead().getData(), dataSecond);
        assertEquals(ll.getTail().getData(), dataSecond);
    }

    @Test
    void removeFromBack_hasThree_removes() {
        SinglyLinkedList<Integer> ll = new SinglyLinkedList<>();
        int dataFirst = 3;
        int dataSecond = 2;
        int dataThird = 1;
        ll.addToFront(dataFirst);
        ll.addToFront(dataSecond);
        ll.addToFront(dataThird);
        assertEquals(ll.size(), 3);

        int nodeData = ll.removeFromBack();

        assertEquals(nodeData, dataFirst);
        assertEquals(ll.size(), 2);
        assertEquals(ll.getHead().getData(), dataThird);
        assertEquals(ll.getTail().getData(), dataSecond);
    }

    @Test
    void removeFromBack_hasN_removes() {
        SinglyLinkedList<Integer> ll = new SinglyLinkedList<>();
        int inputsCount = ThreadLocalRandom.current().nextInt(32, 128 + 1);
        ArrayList<Integer> dataInputs = new ArrayList<>();
        for (int i = 0; i < inputsCount; i++) {
            dataInputs.add(i);
            ll.addToBack(i);
        }

        int toRemoveCount = ThreadLocalRandom.current().nextInt(2,  16 + 1);
        for (int i = 0; i < toRemoveCount; i++) {
            int indexToRemove = dataInputs.size() - 1;
            dataInputs.remove(indexToRemove);
            ll.removeFromBack();
        }

        assertEquals(ll.getHead().getData(), dataInputs.get(0));
        assertEquals(ll.getTail().getData(), dataInputs.get(inputsCount - toRemoveCount - 1));

        int[] dataInputsArray = dataInputs.stream().mapToInt(i -> i).toArray();
        assertNodes(ll.getHead(), dataInputsArray, 0);
    }

    void assertNodes(SinglyLinkedListNode<Integer> node, int[] data, int index){
        assertEquals(node.getData(), data[index]);

        if(node.getNext() != null){
            assertNodes(node.getNext(), data, ++index);
        }
    }
}
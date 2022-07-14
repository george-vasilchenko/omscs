import java.util.NoSuchElementException;

/**
 * Your implementation of a Singly-Linked List.
 */
public class SinglyLinkedList<T> {

    /*
     * Do not add new instance variables or modify existing ones.
     */
    private SinglyLinkedListNode<T> head;
    private SinglyLinkedListNode<T> tail;
    private int size;

    /*
     * Do not add a constructor.
     */

    /**
     * Adds the element to the front of the list.
     *
     * Method should run in O(1) time.
     *
     * @param data the data to add to the front of the list
     * @throws java.lang.IllegalArgumentException if data is null
     */
    public void addToFront(T data) {
        validateData(data);

        if(size == 0){
            head = new SinglyLinkedListNode<>(data);
            tail = head;
            size++;
            return;
        }

        SinglyLinkedListNode<T> prevHead = head;
        SinglyLinkedListNode<T> newNode = new SinglyLinkedListNode<>(data);

        newNode.setNext(prevHead);
        head = newNode;
        size++;
    }

    /**
     * Adds the element to the back of the list.
     *
     * Method should run in O(1) time.
     *
     * @param data the data to add to the back of the list
     * @throws java.lang.IllegalArgumentException if data is null
     */
    public void addToBack(T data) {
        validateData(data);

        if(size == 0){
            head = new SinglyLinkedListNode<>(data);
            tail = head;
            size++;
            return;
        }

        SinglyLinkedListNode<T> prevTail = tail;
        SinglyLinkedListNode<T> newNode = new SinglyLinkedListNode<>(data);

        prevTail.setNext(newNode);
        tail = newNode;
        size++;
    }

    /**
     * Removes and returns the first data of the list.
     *
     * Method should run in O(1) time.
     *
     * @return the data formerly located at the front of the list
     * @throws java.util.NoSuchElementException if the list is empty
     */
    public T removeFromFront() {
        validateNotEmpty();

        if(size == 1){
            T data = head.getData();
            head = null;
            tail = null;
            size = 0;
            return data;
        }

        T data = head.getData();
        head = head.getNext();
        size--;
        return data;
    }

    /**
     * Removes and returns the last data of the list.
     *
     * Method should run in O(n) time.
     *
     * @return the data formerly located at the back of the list
     * @throws java.util.NoSuchElementException if the list is empty
     */
    public T removeFromBack() {
        validateNotEmpty();

        if(size == 1){
            T data = head.getData();
            head = null;
            tail = null;
            size = 0;
            return data;
        }

        SinglyLinkedListNode<T> current = head;
        while(current.getNext().getNext() != null) {
            current = current.getNext();
        }

        SinglyLinkedListNode<T> newTail = current;
        T data = current.getNext().getData();
        tail = newTail;
        tail.setNext(null);

        size--;
        return data;
    }

    /**
     * Returns the head node of the list.
     *
     * For grading purposes only. You shouldn't need to use this method since
     * you have direct access to the variable.
     *
     * @return the node at the head of the list
     */
    public SinglyLinkedListNode<T> getHead() {
        // DO NOT MODIFY THIS METHOD!
        return head;
    }

    /**
     * Returns the tail node of the list.
     *
     * For grading purposes only. You shouldn't need to use this method since
     * you have direct access to the variable.
     *
     * @return the node at the tail of the list
     */
    public SinglyLinkedListNode<T> getTail() {
        // DO NOT MODIFY THIS METHOD!
        return tail;
    }

    /**
     * Returns the size of the list.
     *
     * For grading purposes only. You shouldn't need to use this method since
     * you have direct access to the variable.
     *
     * @return the size of the list
     */
    public int size() {
        // DO NOT MODIFY THIS METHOD!
        return size;
    }

    private void validateNotEmpty(){
        if(size == 0){
            throw new NoSuchElementException("SLL is empty.");
        }
    }

    private void validateData(T data) {
        if(data == null) {
            throw new IllegalArgumentException("Parameter 'data' must not be null.");
        }
    }
}
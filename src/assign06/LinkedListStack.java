package assign06;

import java.util.NoSuchElementException;

/**
 * Class to create a stack using a singly linked list.
 * Has LIHO behavior.
 * @author Dominik Jamrich, Ausitn Corbett
 * @version February 29, 2024
 */
public class LinkedListStack<E> implements Stack<E>{
    private SinglyLinkedList<E> SLL;

    /**
     * Default contructor for the LinkedListStack.
     * simply creates a new SLL.
     */
    public LinkedListStack(){
        SLL = new SinglyLinkedList<>();
    }
    /**
     * Removes all of the elements from the stack.
     */
    @Override
    public void clear() {
        SLL.clear();
    }

    /**
     * @return true if the stack contains no elements; false, otherwise.
     */
    @Override
    public boolean isEmpty() {
        return SLL.isEmpty();
    }

    /**
     * Returns, but does not remove, the element at the top of the stack.
     *
     * @return the element at the top of the stack
     * @throws NoSuchElementException if the stack is empty
     */
    @Override
    public E peek() throws NoSuchElementException {
        if(isEmpty()) throw new NoSuchElementException();
        return SLL.getFirst();
    }
    /**
     * Returns and removes the item at the top of the stack.
     *
     * @return the element at the top of the stack
     * @throws NoSuchElementException if the stack is empty
     */
    @Override
    public E pop() throws NoSuchElementException {
        if(isEmpty()) throw new NoSuchElementException();
        return SLL.deleteFirst();
    }


    /**
     * Adds a given element to the stack, putting it at the top of the stack.
     *
     * @param element - the element to be added
     */
    @Override
    public void push(E element) {
        SLL.insertFirst(element);
    }

    /**
     * @return the number of elements in the stack
     */
    @Override
    public int size() {
        return SLL.size();
    }


}

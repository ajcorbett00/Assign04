package assign06;

import java.util.Iterator;
import java.util.NoSuchElementException;
/**
 * Class to create and modify a singly linked list.
 * Contains a iterator constructor to iterate through the list
 *
 * @author Dominik Jamrich, Ausitn Corbett
 * @version February 29, 2024
 */
public class SinglyLinkedList<E> implements List<E>{
    private Node<E> head;
    private int size;

    /**
     * Generic nested Node class for helping create the linked list elements.
     */
    public class Node<E> {
        protected E data;
        protected Node<E> next;
        protected Node(E data){
            this.data = data;
            this.next = null;
        }

        /**
         * Constructor for a node
         * @param data to be stored in the node
         * @param next pointer to the next node in the SLL.
         */
        public Node(E data, Node<E> next) {

            this.data = data;
            this.next = next;
        }
    }

    /**
     * Default contructor for a SLL.
     */
    public SinglyLinkedList(){
        this.head = new Node<>(null);
        this.size = 0;

    }

    /**
     * Inserts an element at the beginning of the list.
     * O(1) for a singly-linked list.
     *
     * @param element - the element to add
     */
    @Override
    public void insertFirst(E element){
        insert(0,element);
    }

    /**
     * Inserts an element at a specific position in the list.
     * O(N) for a singly-linked list.
     *
     * @param index - the specified position
     * @param element - the element to add
     * @throws IndexOutOfBoundsException if index is out of range (index < 0 || index > size())
     */
    public void insert(int index, E element) throws IndexOutOfBoundsException{
        if(index < 0 || index > size()) throw new IndexOutOfBoundsException();
        Node<E> change = parse(index);
        change.next = new Node<>(element,change.next);
        size++;
    }

    /**
     * Gets the first element in the list.
     * O(1) for a singly-linked list.
     *
     * @return the first element in the list
     * @throws NoSuchElementException if the list is empty
     */
    public E getFirst() throws NoSuchElementException{
        if(size == 0){
            throw new NoSuchElementException();
        }
        return head.next.data;
    }

    /**
     * Gets the element at a specific position in the list.
     * O(N) for a singly-linked list.
     *
     * @param index - the specified position
     * @return the element at the position
     * @throws IndexOutOfBoundsException if index is out of range (index < 0 || index >= size())
     */
    public E get(int index) throws IndexOutOfBoundsException{
        if(index < 0 || index >= size()) throw new IndexOutOfBoundsException();
        Node<E> seek = parse(index);
        return seek.next.data;
    }

    /**
     * Deletes and returns the first element from the list.
     * O(1) for a singly-linked list.
     *
     * @return the first element
     * @throws NoSuchElementException if the list is empty
     */
    public E deleteFirst() throws NoSuchElementException{
        if(size == 0){
            throw new NoSuchElementException();
        }
      return delete(0);
    }

    /**
     * Deletes and returns the element at a specific position in the list.
     * O(N) for a singly-linked list.
     *
     * @param index - the specified position
     * @return the element at the position
     * @throws IndexOutOfBoundsException if index is out of range (index < 0 || index >= size())
     */
    public E delete(int index) throws IndexOutOfBoundsException{
        if(index < 0 || index >= size()){
            throw new IndexOutOfBoundsException();
        }
        Node<E> beforeDelete = parse(index);
        E ret = beforeDelete.next.data;
        beforeDelete.next = beforeDelete.next.next;
        size--;
        return ret;
    }

    /**
     * Determines the index of the first occurrence of the specified element in the list,
     * or -1 if this list does not contain the element.
     * O(N) for a singly-linked list.
     *
     * @param element - the element to search for
     * @return the index of the first occurrence; -1 if the element is not found
     */
    public int indexOf(E element){
        Iterator<E> iter = iterator();
        int index = 0;
        while(iter.hasNext()){
            if(iter.next().equals(element)){
                return index;
            }
            index++;
        }
        return -1;
    }

    /**
     * O(1) for a singly-linked list.
     *
     * @return the number of elements in this list
     */
    public int size(){
return this.size;
    }

    /**
     * O(1) for a singly-linked list.
     *
     * @return true if this collection contains no elements; false, otherwise
     */
    public boolean isEmpty(){
        return size==0;

    }

    /**
     * Removes all of the elements from this list.
     * O(1) for a singly-linked list.
     */
    public void clear(){
      head.next = null;
      size = 0;

    }

    /**
     * Generates an array containing all of the elements in this list in proper sequence
     * (from first element to last element).
     * O(N) for a singly-linked list.
     *
     * @return an array containing all of the elements in this list, in order
     */
    public Object[] toArray(){
        Object[] arrayFormed = new Object[size];
        Node<E> temp = head;
        for(int i = 0; i < size; i++){
            arrayFormed[i] = temp.next.data;
            temp = temp.next;
        }
        return arrayFormed;
    }

    /**
     * @return an iterator over the elements in this list in proper sequence (from first
     * element to last element)
     */
    public Iterator<E> iterator(){
        return new SLLIterator();
    }

    /**
     * Nested Iterator class to help iterate through the SLL.
     * All method should be O(1) behavior.
     */
    public class SLLIterator implements Iterator<E>{
        private boolean canRemove ;
        private int nextIndex;
        private Node<E> thisNode;
        private Node<E> previousNode;
        public SLLIterator(){
            canRemove = false;
            nextIndex = 0;
            thisNode = head;
        }
        @Override
        public boolean hasNext() {
            return nextIndex < size;
        }
        @Override
        public E next() throws NoSuchElementException{
            if(!hasNext()){
                throw new NoSuchElementException();
            }
            canRemove = true;
            nextIndex++;
            previousNode = thisNode;
            thisNode = thisNode.next;
            return thisNode.data;
        }
        public void remove() throws IllegalStateException{
            if(!canRemove){
                throw new IllegalStateException();
            }
            canRemove = false;
            previousNode.next = thisNode.next;
            thisNode = previousNode;
            nextIndex--;
            size--;
        }
    }

    //---Helper Methods ---

    /**
     * @return the Node before the element at the specified index
     * @param index this is the index of the element after the Node returned
     */
    private Node<E> parse(int index){
        Node<E> temp = head;
        for(int i = 0; i<index;i++){
            temp = temp.next;
        }
        return temp;
    }
}

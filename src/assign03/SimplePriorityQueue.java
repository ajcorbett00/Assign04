package assign03;
/**
 *
 */

import java.util.Collection;
import java.util.Comparator;
import java.util.NoSuchElementException;


/**
 * A Class that implements the PriorityQueue interface. It incorporates
 * a binary search helper method to facilitate the insert and contains method;
 * The priority queue optimizes accessing the maximum element in the queue. .
 *
 * @authorf Dominik Jamrich, Ausitn Corbett
 * @param <E> - the class of elements contained in this priority queue
 */
public class SimplePriorityQueue<E> implements PriorityQueue<E> {

    private E[] queue;
    private Comparator<? super E> cmp;
    private int size;
    private int arrLength;

    /**
     * Constructor of a priority queue for types that implement a comparable interface.
     * Queue assumes natural ordering of class.
     */
    public SimplePriorityQueue() {

        this(null);

    }

    /**
     *Constructor of a priority queue ordered using the comparator cmp
     * that the user passes for comparison.
     *
     * @param cmp Comparator for the given class
     */
    @SuppressWarnings("unchecked")
    public SimplePriorityQueue(Comparator<? super E> cmp) {
        queue = (E[]) new Object[50];
        arrLength = 50;
        this.cmp = cmp;
        size = 0;
    }

    /**
     * Retrieves, but does not remove, the maximum element in this priority
     * queue.
     *
     * @return the maximum element
     * @throws NoSuchElementException if the priority queue is empty
     */
    @Override
    public E findMax() throws NoSuchElementException{
        if(isEmpty()) throw new NoSuchElementException();
        return queue[size - 1];

    }
    /**
     * Retrieves and removes the maximum element in this priority queue.
     *
     * @return the maximum element
     * @throws NoSuchElementException if the priority queue is empty
     */
    @Override
    public E deleteMax() throws NoSuchElementException {
        if(isEmpty()) throw new NoSuchElementException();
        size--;
        var lastElement = queue[size];
        queue[size] = null;
        return lastElement;

    }
    /**
     * Inserts the specified element into this priority queue.
     *
     * @param item - the element to insert
     */
    @Override
    public void insert(E item) {
if(size == arrLength){
    doubleBackingArray();
}
int insertIndex = binarySearch(item);
for (int i = size;i > insertIndex;i--){
            queue[i] = queue[i-1];
        }
queue[insertIndex] = item;
size++;
    }
    /**
     * Inserts the specified elements into this priority queue.
     *
     * @param coll - the collection of elements to insert
     */
    @Override
    public void insertAll(Collection<? extends E> coll) {
for(E item: coll){
    insert(item);
}
    }
    /**
     * Indicates whether this priority queue contains the specified element.
     *
     * @param item - the element to be checked for containment in this priority queue
     */
    @Override
    public boolean contains(E item) {
        if(isEmpty()) return false;
        int index = binarySearch(item);
        if(queue[index] != null) return compare(item, queue[binarySearch(item)]) == 0;
        return false;
    }
    /**
     * @return the number of elements in this priority queue
     */
    @Override
    public int size() {
        return size;
    }
    /**
     * @return true if this priority queue contains no elements, false otherwise
     */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }
    /**
     * Removes all the elements from this priority queue.
     * The queue will be empty when this call returns.
     */
    @Override
    public void clear() {
size = 0;
    }


    //--Private Helper Methods--

    /**
     * Helper method to find a given element's index or
     * the index just greater than the target. The Binary
     * search algorithm is used to search through the queue.
     *
     * @param target
     * @return index or the index of the element
     * just greater than the target.
     */
    private int binarySearch(E target) {
        int max = size;
        int min = 0;

            while (min < max) {
                int mid = ((max + min) / 2);
                int comp = compare(target, queue[mid]);
                if (comp == 0) {
                    return mid;
                } else if (comp > 0) {
                    min = mid + 1;
                } else {
                    max = mid;
                }
            }
            return max;
            }

    /**
     * Doubles the backing array.
     */
    @SuppressWarnings("unchecked")
    private void doubleBackingArray() {
        E[] largerArray = (E[]) new Object[size*2];
        arrLength *= 2;
        System.arraycopy(queue, 0, largerArray, 0, queue.length);
        this.queue = largerArray;
    }

    /**
     * Checks to see if the queue object is implementing comparator or not
     * and then preforms the correct comparison.
     *
     * @param lhs to be compared.
     * @param rhs to be compared
     * @return < 0 when lhs is less than rhs, >0 when lhs is greater than rhs,
     *  0 when lhs and rhs are equal.
     */
    @SuppressWarnings("unchecked")
    private int compare(E lhs, E rhs){
        if(cmp != null){
           return cmp.compare(lhs,rhs);
        }
        return ((Comparable<? super E>)lhs).compareTo(rhs);
    }
}



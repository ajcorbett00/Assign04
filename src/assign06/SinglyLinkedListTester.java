package assign06;

import org.junit.jupiter.api.*;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Tester class for SinglyLinkedList.
 *
 * @author Dominik Jamrich, Austin Corbett
 * @version February.
 */
public class SinglyLinkedListTester {
    private SinglyLinkedList<String> smallSSLL;
    private SinglyLinkedList<Integer> smallIntSLL;
    private SinglyLinkedList<String> largerSSLL;
    private Iterator<String> SSSLLIterator;

    @BeforeEach
    public void setUp(){
        smallSSLL = new SinglyLinkedList<String>();
        smallIntSLL = new SinglyLinkedList<Integer>();
        largerSSLL = new SinglyLinkedList<String>();
        SSSLLIterator = smallSSLL.iterator();

        //**initial insertions test some insert() and insertFirst() functionality

        //smallSSLL
        smallSSLL.insertFirst("Apple");
        smallSSLL.insert(1,"Baby");
        smallSSLL.insert(2,"Cat");
        smallSSLL.insert(3,"Dog");

        //smallIntSLL
        smallIntSLL.insertFirst(0);
        smallIntSLL.insert(1,1);
        smallIntSLL.insert(2,2);
        smallIntSLL.insert(3,3);
        smallIntSLL.insert(4,4);
        smallIntSLL.insert(5,5);
        smallIntSLL.insert(6,6);
        smallIntSLL.insert(7,7);
        smallIntSLL.insert(8,8);
        smallIntSLL.insert(9,9);

        //largerSSLL
        int lSSLLSize = 20;
        largerSSLL.insertFirst("A");
        for(Integer i = 1; i < lSSLLSize; i++){
            largerSSLL.insert(i,i.toString());
        }


    }

    @Test
    public void getFirstTest(){
       assertTrue(smallSSLL.getFirst().equals("Apple"));
       smallSSLL.insertFirst("Awe");
        assertTrue(smallSSLL.getFirst().equals("Awe"));
       assertTrue(largerSSLL.getFirst().equals("A"));
       assertTrue(smallIntSLL.getFirst() == 0);

    }

    @Test
    public void emptyGetFirstAndGet(){
        SinglyLinkedList<String> empty = new SinglyLinkedList<>();
        assertThrows(NoSuchElementException.class,() -> empty.getFirst());
        assertThrows(IndexOutOfBoundsException.class, () -> empty.get(10));
    }

    @Test
    public void insertThrows(){
        assertThrows(IndexOutOfBoundsException.class, () -> largerSSLL.insert(largerSSLL.size()+1,""));
    }

    @Test
    public void deletionsThrow(){
        SinglyLinkedList<String> empty = new SinglyLinkedList<>();
        assertThrows(NoSuchElementException.class,() -> empty.deleteFirst());
        assertThrows(IndexOutOfBoundsException.class, () -> empty.delete(10));
    }

    @Test
    public void getTest(){
        assertTrue(smallSSLL.get(2).equals("Cat"));
        for(int i = 0; i < smallIntSLL.size(); i++){
            assertEquals(smallIntSLL.get(i), i);
        }
    }

    @Test
    public void deleteFirstAndDelete(){
        int size = smallIntSLL.size();
        for(int i = 0; i < size; i++){
            assertEquals(smallIntSLL.deleteFirst(),i);
        }
        assertTrue(smallIntSLL.isEmpty());
        int sizeLarger = largerSSLL.size();
        System.out.println("-------deleteFirstAndDelete print out of largerSSLL backward------");
        for(int i = sizeLarger-1; i >= 0; i--){
            System.out.println(largerSSLL.delete(i));
        }
        assertTrue(largerSSLL.isEmpty());
    }

    @Test
    public void indexOfTest(){
        assertEquals(largerSSLL.indexOf("A"),0);
        for(int i = 1; i < smallIntSLL.size(); i++){
            assertEquals(smallIntSLL.indexOf(i),i);
        }
        assertEquals(largerSSLL.indexOf("B"),-1);
    }

    @Test
    public void toObjectArray(){
        Object[] array = smallIntSLL.toArray();
        for(int i = 0; i < array.length; i++){
            assertEquals(array[i],smallIntSLL.get(i));
        }
    }

    @Test
    public void IsEmptyAndClear(){
        largerSSLL.clear();
        assertTrue(largerSSLL.isEmpty());
        smallIntSLL.clear();
        assertTrue(smallIntSLL.isEmpty());
        assertTrue(!smallSSLL.isEmpty());
    }



    @Test
    public void IterateThrough(){
        System.out.println("------IterateThrough smallSSLL Before Removal-----");
        for(String item : smallSSLL){
            System.out.println(item);
        }
        while(SSSLLIterator.hasNext()){
            if(!SSSLLIterator.next().equals("Apple")){
                SSSLLIterator.remove();
                assertThrows(IllegalStateException.class, () -> {SSSLLIterator.remove();});
            }
        }
        System.out.println("-------IterateThrough smallSSLL After Removal-----");
        for(String item : smallSSLL){
            System.out.println(item);
        }

    }
    @Test
    public void iterateRemoveTest(){
        var iterator = smallIntSLL.iterator();
        iterator.next();
        iterator.next();
        iterator.remove();
        iterator.next();
        iterator.remove();
        assertEquals(8,smallIntSLL.size());
        assertEquals(0,smallIntSLL.getFirst());
        assertEquals(4,smallIntSLL.get(2));
    }
    // remove every element
    // remove and make sure get and delete form SLL work.
    //next next remove next remove (alternate next and remove)
    // one element in SLL throws exception


}


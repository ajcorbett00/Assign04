package assign03;

import org.junit.jupiter.api.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.NoSuchElementException;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for SimplePriorityQueue
 *
 * @Authors Dominik Jamrich, Austin Corbett
 */
public class SimplePriorityQueueTester {

    private SimplePriorityQueue<String> emptyQueue;
    private SimplePriorityQueue<Integer> numberQueue;

    @BeforeEach
    public void TestSetUp(){
 emptyQueue = new SimplePriorityQueue<>();
 numberQueue = new SimplePriorityQueue<>((Integer lhs,Integer rhs) -> (-1*(lhs.compareTo(rhs))));
 for(int i = 0; i < 30; i++){
     if(i != 13) numberQueue.insert(i);
 }
    }

    //-----Empty Queue Tests ----

    @Test
    public void emptyDeleteTest() {
        assertThrows(NoSuchElementException.class, () ->
        {
            emptyQueue.deleteMax();
        });
    }

    @Test public void emptySizeTest(){
        while(!numberQueue.isEmpty()){
            numberQueue.deleteMax();
        }
        assertEquals(numberQueue.size(),0);

    }

    @Test
    public void emptyFindMaxTest(){
        assertThrows(NoSuchElementException.class, () ->
        {emptyQueue.findMax();});
        assertFalse(emptyQueue.contains("S"));
    }

    //---Small String Queues ---

    @Test
    public void clearSmall(){
        emptyQueue.insert("Z");
        emptyQueue.clear();
        assertTrue(emptyQueue.isEmpty());
        emptyQueue.insert("X");
        assertEquals(emptyQueue.findMax(),"X");
    }

    @Test
    public void smallQueues(){
        emptyQueue.insert("A");
        assertFalse(emptyQueue.contains("S"));
        emptyQueue.insert("S");
        assertTrue(emptyQueue.contains("S"));
    }

    @Test
    public void addingTwoMax(){
        emptyQueue.insert("A");
        emptyQueue.insert("B");
        emptyQueue.insert("Z");
        emptyQueue.insert("Z");
        assertEquals(emptyQueue.findMax(),"Z");
        emptyQueue.deleteMax();
        assertEquals(emptyQueue.findMax(),"Z");
    }


    //--Backwards Int Queue Functionality---

    @Test
    public void backwardInts(){
        assertEquals(numberQueue.findMax(),0);
    }

    @Test
    public void backwardIntsCorrectOrdering(){
        Integer[] backwards = new Integer[29];
        for(int i =0; i < 30; i++){
            if(i != 13) {assertEquals(numberQueue.deleteMax(), i);}
        }
    }

    @Test
    public void backwardsIntsAdd13(){
        numberQueue.insert(13);
        numberQueue.contains(13);
    }

    @Test
    public void backwardsIntAddMax(){
        numberQueue.insert(-200);
        assertEquals(numberQueue.deleteMax(),-200);
    }

    @Test
    public void deleteMaxTestBasic(){
        assertEquals(numberQueue.deleteMax(),0);
        assertFalse(numberQueue.contains(0));
    }

    @Test public void numberQueueSizeTest(){
        assertEquals(numberQueue.size(),29);
    }

//---Queue Tests with Custom Student Class --

    @Test
    public void comparatorTest(){
         var studentArray = new Student[15];
         for(int i=0;i<15;i++){studentArray[i] = new Student("John" + i, i);}
         SimplePriorityQueue<Student> largeTest = new SimplePriorityQueue<Student>((lhs,rhs)-> (lhs.getID()-rhs.getID()));
         for(int i=14;i>=0;i--)largeTest.insert(studentArray[i]);
         for( int i=0; i<15;i++) {assertTrue(largeTest.contains(new Student("John" + i, i)));}
    }

    @Test
    public void insertAllLargeTest(){
        var studentCollection = new ArrayList<Student>();
        for(int i=0;i<15;i++){studentCollection.add(new Student("John" + i, i)) ;}
        SimplePriorityQueue<Student> largeTest = new SimplePriorityQueue<Student>((lhs,rhs)-> (lhs.getID()-rhs.getID()));
        largeTest.insertAll(studentCollection);
        for( int i=0; i<15;i++) {assertTrue(largeTest.contains(new Student("John" + i, i)));}
    }

    @Test
    public void takesAnySize() {
        var studentCollection = new ArrayList<Student>();
        for(int i=0;i<55;i++){studentCollection.add(new Student("John" + i, i)) ;}
        SimplePriorityQueue<Student> largeTest = new SimplePriorityQueue<Student>((lhs,rhs)-> (lhs.getID()-rhs.getID()));
        largeTest.insertAll(studentCollection);
        assertEquals(largeTest.size(),55);
    }


}

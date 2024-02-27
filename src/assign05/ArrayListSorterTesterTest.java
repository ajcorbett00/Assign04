package assign05;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;

import static assign05.ArrayListSorter.*;
import static org.junit.jupiter.api.Assertions.*;

class ArrayListSorterTesterTest {
private ArrayList<Integer> randomIntList;
private ArrayList<Double> doubleList = new ArrayList<Double>();
private ArrayList<Integer> smallIntList;
private ArrayList<Integer> largeIntList;

   @BeforeEach
   public void beforeEachSetup() {
       randomIntList = new ArrayList<Integer>();
       randomIntList = generatePermuted(200);
       for(int i = 0;i<40000;i++) {
           doubleList.add((double)i/7);
       }

   }

    @Test
   public void ascendingTest(){
       smallIntList = generateAscending(10);
       assertEquals(1,smallIntList.get(0));
       assertEquals(10,smallIntList.get(9));
       assertEquals(5,smallIntList.get(4));

   }
   @Test
   public void ascendingZeroTest(){
       smallIntList = generateAscending(0);
       assertEquals(new ArrayList<Integer>(),smallIntList);
   }
    @Test
    public void ascendingLargeTest(){
        largeIntList = generateAscending(2000);
        assertEquals(1, largeIntList.get(0));
        assertEquals(2000, largeIntList.get(1999));
        assertEquals( 495,largeIntList.get(494));

    }
   @Test
   public void permutedTest(){
       largeIntList = generateAscending(200);
       assertEquals(largeIntList.size(),randomIntList.size());
       assertNotEquals(randomIntList.get(0),largeIntList);
   }
   @Test
   public void descendingTest(){
       smallIntList = generateDescending(20);
       assertEquals(1,smallIntList.get(19));
       assertEquals(10,smallIntList.get(10));
       assertEquals(20,smallIntList.get(0));
   }
   @Test
   public void descendingZeroTest(){
       smallIntList = generateDescending(0);
       assertEquals(new ArrayList<Integer>(), smallIntList);
   }

   @Test
    public void mergesortTester(){
       assertNotEquals(1,randomIntList.get(0));
       assertNotEquals(200,randomIntList.get(199));
       mergesort(randomIntList);
       assertEquals(1,randomIntList.get(0));
       assertEquals(200,randomIntList.get(199));
   }
   @Test
   public void mergesortLargeTest(){
    var randArr = generatePermuted(20000);
    mergesort(randArr);
    assertEquals(generateAscending(20000),randArr);
   }
   @Test
   public void mergesortDoubleTest(){
       ArrayList<Double> doubleListShuffled = new ArrayList<Double>();
       for(int i = 0; i<doubleList.size();i++){
           doubleListShuffled.add(doubleList.get(i));
       }
       Collections.shuffle(doubleListShuffled);
       mergesort(doubleListShuffled);
       assertEquals(doubleList,doubleListShuffled);
   }
    @Test
    public void mergesortEmptyTest(){
        smallIntList = new ArrayList<Integer>();
        mergesort(smallIntList);
    }
   @Test
   public void quickSortTest(){
       quicksort(randomIntList);
       assertEquals(1,randomIntList.get(0));
       assertEquals(14,randomIntList.get(13));
       assertEquals(132,randomIntList.get(131));
   }
   @Test
   public void quicksortLargeTest(){
       var randArr = generatePermuted(20000);
       quicksort(randArr);
       assertEquals(generateAscending(20000),randArr);
   }
   @Test
   public void quicksortEmptyTest(){
       smallIntList = new ArrayList<Integer>();
       quicksort(smallIntList);
   }
   @Test
   public void quicksortDoubleTest() {
       ArrayList<Double> doubleListShuffled = new ArrayList<Double>();
       for (int i = 0; i < doubleList.size(); i++) {
           doubleListShuffled.add(doubleList.get(i));
       }
       Collections.shuffle(doubleListShuffled);
       quicksort(doubleListShuffled);
       assertEquals(doubleList, doubleListShuffled);
   }
}
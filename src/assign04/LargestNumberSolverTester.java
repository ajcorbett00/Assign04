package assign04;

import org.junit.jupiter.api.*;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;

import static assign04.LargestNumberSolver.readFile;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 *
 */
public class LargestNumberSolverTester {

    private Integer[] mediumIntArray;

    private Integer[] multiDigitArray;
    private ArrayList<Integer[]> smallIntList = new ArrayList<>();

    private ArrayList<Integer[]> biggerIntList = new ArrayList<>();
    private Integer[] length0 = new Integer[0];
    private Integer[] length1 = new Integer[]{1};

    @BeforeEach
    public void setup(){
    mediumIntArray = new Integer[]{9,5,6,7,8};
    multiDigitArray = new Integer[]{99, 100,5};
    smallIntList.add(mediumIntArray);
    smallIntList.add(multiDigitArray);
    biggerIntList.add(new Integer[]{1,2,3});
    biggerIntList.add(new Integer[]{99,0});
    for(int i = 0; i < 5; i++){
    biggerIntList.add(new Integer[]{1,4,990});
    }
    }


    //---InsertionSort Tests--
    @Test
    public void insertionSortTestNaturalOrdering() {
        LargestNumberSolver.insertionSort(mediumIntArray,(lhs,rhs) ->
                lhs.compareTo(rhs));
        var expected = new Integer[]{5,6,7,8,9};
        for(int i = 0; i < expected.length; i++){
            assertEquals(expected[i], mediumIntArray[i]);
        }
    }

    @Test
    public void insertionSortTestComparatorBackward(){
        LargestNumberSolver.insertionSort(mediumIntArray, (lhs,rhs) ->
                -1*lhs.compareTo(rhs));
        var expected = new Integer[]{9,8,7,6,5};
        for(int i = 0; i < expected.length; i++){
            assertEquals(expected[i], mediumIntArray[i]);
        }
    }

    @Test
    public void insertionSortEdgeCases(){
        LargestNumberSolver.insertionSort(length0, (lhs,rhs) -> lhs.compareTo(rhs));
        LargestNumberSolver.insertionSort(length1, (lhs,rhs) -> lhs.compareTo(rhs));
        assertEquals(1, length1[0]);
    }


    //---FindLargest (Big Integer) Tests ---

    @Test
    public void findLargestEdgeCases(){
        assertEquals(new BigInteger("0"),LargestNumberSolver.findLargestNumber(length0));
        assertEquals(new BigInteger("1"),LargestNumberSolver.findLargestNumber(length1));
    }

    @Test
    public void findLargestOneDigitFunctionality(){
        assertEquals(new BigInteger("98765"), LargestNumberSolver.findLargestNumber(mediumIntArray));
    }

    @Test
    public void findLargestMultiDigitFunctionality(){
        assertEquals(new BigInteger("995100"),
                LargestNumberSolver.findLargestNumber(multiDigitArray));
    }

//---FindLargest (int) Tests --

    @Test
    public void findLargestIntEdgeCases(){
        assertEquals(0,LargestNumberSolver.findLargestInt(length0));
        assertEquals(1,LargestNumberSolver.findLargestInt(length1));
    }

    @Test
    public void findLargestIntOneDigitFunctionality(){
        assertEquals(98765, LargestNumberSolver.findLargestInt(mediumIntArray));
    }

    @Test
    public void findLargestIntVariableDigitFunctionality(){
        assertEquals(995100,
                LargestNumberSolver.findLargestInt(multiDigitArray));
    }

    @Test
    public void findLargestIntThrows(){
        Integer[] testParam1 = new Integer[]{Integer.MAX_VALUE,0};
        OutOfRangeException testException =
                assertThrows(OutOfRangeException.class, () -> LargestNumberSolver.findLargestInt(testParam1));
        assertEquals("The value is too large for the int data type.", testException.getMessage());
        Integer[] testParam2 = new Integer[]{0,1,2,3,4,5,6,7,8,9,9};
        testException = assertThrows(OutOfRangeException.class, () -> LargestNumberSolver.findLargestInt(testParam2));
        assertEquals("The value is too large for the int data type.", testException.getMessage());
    }

    //---FindLargest (long) Tests ---

    @Test
    public void findLargestLongEdgeCases(){
        assertEquals(0,LargestNumberSolver.findLargestLong(length0));
        assertEquals(1,LargestNumberSolver.findLargestLong(length1));
    }

    @Test
    public void findLargestLongOneDigitFunctionality(){
        assertEquals(98765, LargestNumberSolver.findLargestLong(mediumIntArray));
    }

    @Test
    public void findLargestLongVariableDigitFunctionality(){
        assertEquals(995100,
                LargestNumberSolver.findLargestLong(multiDigitArray));
    }

    @Test
    public void findLargestLongThrows(){
        Integer[] testParam1 = new Integer[]{Integer.MAX_VALUE, Integer.MAX_VALUE};
        OutOfRangeException testException =
                assertThrows(OutOfRangeException.class, () -> LargestNumberSolver.findLargestLong(testParam1));
        assertEquals("The value is too large for the long data type.", testException.getMessage());
        Integer[] testParam2 = new Integer[]{10000,10000,10000,10000,10000};
        testException = assertThrows(OutOfRangeException.class, () -> LargestNumberSolver.findLargestLong(testParam2));
        assertEquals("The value is too large for the long data type.", testException.getMessage());
    }

//---Sum and KthLargest Tests --

    @Test
    public void sumEdgeCase(){
        ArrayList<Integer[]> emptyList = new ArrayList<>();
        assertEquals(new BigInteger("0"), LargestNumberSolver.sum(emptyList));
    }

    @Test
    public void sumFunctionality(){
        BigInteger sum = new BigInteger("98765");
        sum = sum.add(new BigInteger("995100"));
        assertEquals(0,sum.compareTo(LargestNumberSolver.sum(smallIntList)));
    }


    @Test
    public void findKthLargestFunctionality(){
        assertEquals(multiDigitArray, LargestNumberSolver.findKthLargest(smallIntList,0));
        assertEquals(mediumIntArray, LargestNumberSolver.findKthLargest(smallIntList,1));
        Integer[] returned = LargestNumberSolver.findKthLargest(biggerIntList, biggerIntList.size()-2);
        assertEquals(99,returned[0]);
    }

    @Test
    public void findKthLargestThrows(){
        IllegalArgumentException testException =
                assertThrows(IllegalArgumentException.class, () ->
                        LargestNumberSolver.findKthLargest(smallIntList,smallIntList.size()));
        assertEquals("The value of k that you provided is out of bounds of the " +
                "List that you provided", testException.getMessage());
    }


    //---readFile Tests---
    @Test
    public void fileReaderFoundFile(){
       var arr = readFile("\\Users\\HP\\Documents\\College Spring 24'\\CS 2420\\Assingments\\assignment04\\src\\assign04\\integers.txt");
        assertEquals(Arrays.toString(arr.get(7)), Arrays.toString(new Integer[]{88,51}));
        assertEquals(Arrays.toString(arr.get(902)), Arrays.toString(new Integer[]{85,35,34,52,14,92,9,79,82,83}));
    }

    @Test
    public void fileReaderFileNotFound(){
        var arr = new ArrayList<Integer[]>();
        var check = LargestNumberSolver.readFile("doesNotExist");
        assertEquals(arr, check);
    }


}

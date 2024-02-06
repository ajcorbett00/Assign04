package assign04;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigInteger;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LargeNumberSolverTester {

    @BeforeEach
    public void setup(){

    }
    @Test
    public void insertionSortTest() {
    }
    @Test
    public void findLargestNumber(){
BigInteger largest;
Integer[] list = new Integer[3];
list[0] = 11;
list[1] = 2;
list[2] = 100;
largest = LargestNumberSolver.findLargestNumber(list);
assertEquals(largest,new BigInteger("211100"));
list[0] = 1;
list[1] = 45;
list[2] = 9;
largest = LargestNumberSolver.findLargestNumber(list);
assertEquals(largest,new BigInteger("9451"));




    }
    @Test
    public void findLargestIntTest(){

    }
    @Test
    public void findLargestLong(){

    }
    @Test
    public void sum(){

    }
    @Test public void findKthLargest(){
        ArrayList<Integer[]> mainList = new ArrayList<Integer[]>();
        Integer[] subList1 = new Integer[3];
        subList1[0] = 11;
        subList1[1] = 2;
        subList1[2] = 100;
        Integer[] subList2 = new Integer[3];
        subList2[0] = 1;
        subList2[1] = 45;
        subList2[2] = 9;

        mainList.add(subList1);
        mainList.add(subList2);
        assertEquals(LargestNumberSolver.findKthLargest(mainList,0),subList1);
        assertEquals(LargestNumberSolver.findKthLargest(mainList,1),subList2);
        //assertThrows() check to see if throws Illegal Argument


    }


}

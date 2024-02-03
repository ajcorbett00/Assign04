package lab01;

import org.junit.jupiter.api.Test;

import static lab01.DiffUtil.findSmallestDiff;
import static org.junit.jupiter.api.Assertions.*;

class DiffUtilTest {

    @Test
    public void testAllSameNum() {
        int arr[] = new int[] { 3, 3, 3 };
        assertEquals(0, findSmallestDiff(arr));
    }

    @Test
    public void testNegAndPos() {
        int arr[] = new int[] { 52, 4, -8, 0, -17 };
        assertEquals(4, findSmallestDiff(arr));
    }
    @Test
    public void testTooSmallArray() {
        int arr[] = new int[0];
        assertThrows(IllegalArgumentException.class, () -> { findSmallestDiff(arr); });
    }

    @Test
    public void testLowestIsFirstComparison(){
        int arr[] = new int[]  {-3, 9, 100, 45, 99, 105};
        assertEquals( findSmallestDiff(arr),1);
    }
    @Test
    public void testAllNegative(){
        int arr[] = new int[]  {-3, -9, -100, -45, -99, -105};
        assertEquals( findSmallestDiff(arr),1);
    }
}
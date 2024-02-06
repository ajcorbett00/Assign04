package assign04;

import java.lang.*;
import java.math.*;
import java.util.*;

public class LargestNumberSolver {


    public static <T> void insertionSort(T[] arr, Comparator<? super T> cmp) {
        for (int i = 1; i < arr.length; i++) {
            T val = arr[i];
            int j;
            for (j = i; (j > 0) && (cmp.compare(val, arr[j - 1]) < 0); j--) {
                arr[j] = arr[j - 1];
            }
            arr[j] = val;
        }
    }

    public static BigInteger findLargestNumber(Integer[] arr) {
        Comparator<Integer> cmp = new Comparator<Integer>() {
            @Override
            public int compare(Integer x, Integer y) {
                String forward = x.toString() + y.toString();
                String reverse = y.toString() + x.toString();
                BigInteger front = new BigInteger(forward);
                BigInteger back = new BigInteger(reverse);
                if (front.compareTo(back) > 0) {
                    return -1;
                } else {
                    return 1;
                }
            }
        };
        Integer[] arrayToSort = new Integer[arr.length];
        System.arraycopy(arr, 0, arrayToSort, 0, arr.length);
        insertionSort(arrayToSort, cmp);
        var numberAsString = new StringBuilder();
        for (Integer x : arrayToSort) {
            numberAsString.append(x);
        }
        return new BigInteger(numberAsString.toString());
    }

    public static int findLargestInt(Integer[] arr) throws OutOfRangeException {
        var max_val = Integer.MAX_VALUE;
        var max = new BigInteger("" + max_val);
        Comparator<Integer> cmp = new Comparator<Integer>() {
            @Override
            public int compare(Integer x, Integer y) {
                String forward = x.toString() + y.toString();
                String reverse = y.toString() + x.toString();
                var front = new BigInteger(forward);
                var back = new BigInteger(reverse);
                if (front.compareTo(max) >= 0 || back.compareTo(max) >= 0) {
                    throw new OutOfRangeException("int");
                }
                if (front.compareTo(back) > 0) {
                    return -1;
                } else {
                    return 1;
                }
            }
        };
        Integer[] arrayToSort = new Integer[arr.length];
        System.arraycopy(arr, 0, arrayToSort, 0, arr.length);
        insertionSort(arrayToSort, cmp);
        var numberAsString = new StringBuilder();
        for (Integer x : arrayToSort) {
            numberAsString.append(x);
        }
        var largestNum = new BigInteger(numberAsString.toString());
        if (largestNum.compareTo(max) >= 0) {
            throw new OutOfRangeException("int");
        }
        return largestNum.intValue();
    }

    public static long findLargestLong(Integer[] arr) throws OutOfRangeException {
        var max_val = Long.MAX_VALUE;
        var max = new BigInteger("" + max_val);
        Comparator<Integer> cmp = new Comparator<Integer>() {
            @Override
            public int compare(Integer x, Integer y) {
                String forward = x.toString() + y.toString();
                String reverse = y.toString() + x.toString();
                var front = new BigInteger(forward);
                var back = new BigInteger(reverse);
                if (front.compareTo(max) >= 0 || back.compareTo(max) >= 0) {
                    throw new OutOfRangeException("long");
                }
                if (front.compareTo(back) > 0) {
                    return -1;
                } else {
                    return 1;
                }
            }
        };
        Integer[] arrayToSort = new Integer[arr.length];
        System.arraycopy(arr, 0, arrayToSort, 0, arr.length);
        insertionSort(arrayToSort, cmp);
        var numberAsString = new StringBuilder();
        for (Integer x : arrayToSort) {
            numberAsString.append(x);
        }
        var largestNum = new BigInteger(numberAsString.toString());
        if (largestNum.compareTo(max) >= 0) {
            throw new OutOfRangeException("long");
        }
        return largestNum.longValue();
    }

    public static BigInteger sum(List<Integer[]> list) {
        BigInteger sum = new BigInteger("0");
        for (Integer[] subList : list) {
            sum.add(findLargestNumber(subList));
        }
        return sum;
    }

    public static Integer[] findKthLargest(List<Integer[]> list, int k) throws IllegalArgumentException {
        if(k >= list.size()){throw new IllegalArgumentException("The value of k that you provided is out of bounds of the " +
                    "List that you provided");}
        Comparator<Integer[]> cmp = new Comparator<Integer[]>(){
            @Override
           public int compare(Integer[] lhs, Integer[] rhs){
                BigInteger left = LargestNumberSolver.findLargestNumber(lhs);
                return (-1)*left.compareTo(LargestNumberSolver.findLargestNumber(rhs));
            }
        };
        insertionSortForKthLargest(list,cmp);
        return list.get(k);
    }

    public static List<Integer[]> readFile(String fileName){
        return new ArrayList<>();
    }

    //-----Private Helper Methods----

    private static void insertionSortForKthLargest(List<Integer[]> list, Comparator<Integer[]> cmp) {
        for (int i = 1; i < list.size(); i++) {
            var val = list.get(i);
            int j;
            for (j = i; (j > 0) && (cmp.compare(val, list.get(j - 1)) < 0); j--) {
                list.set(j,list.get(j-1));
            }
            list.set(j,val);
        }
    }



    
}

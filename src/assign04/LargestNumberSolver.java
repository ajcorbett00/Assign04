package assign04;

import java.io.File;
import java.io.FileNotFoundException;
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
        if (arr.length == 0) {
            return new BigInteger("0");
        }
        if (arr.length == 1) {
            return new BigInteger("" + arr[0]);
        }
        Integer[] arrayToSort = new Integer[arr.length];
        System.arraycopy(arr, 0, arrayToSort, 0, arr.length);
        insertionSort(arrayToSort, new CompareByString());
        var numberAsString = new StringBuilder();
        for (Integer x : arrayToSort) {
            numberAsString.append(x);
        }
        return new BigInteger(numberAsString.toString());
    }

    public static int findLargestInt(Integer[] arr) throws OutOfRangeException {
        if (arr.length == 0) {
            return 0;
        }
        if (arr.length == 1) {
            return arr[0];
        }
        var max_val = Integer.MAX_VALUE;
        var max = new BigInteger("" + max_val);
        Integer[] arrayToSort = new Integer[arr.length];
        System.arraycopy(arr, 0, arrayToSort, 0, arr.length);
        insertionSort(arrayToSort, new CompareByString());
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
        if (arr.length == 0) {
            return 0;
        }
        if (arr.length == 1) {
            return arr[0];
        }
        var max_val = Long.MAX_VALUE;
        var max = new BigInteger("" + max_val);
        Integer[] arrayToSort = new Integer[arr.length];
        System.arraycopy(arr, 0, arrayToSort, 0, arr.length);
        insertionSort(arrayToSort, new CompareByString());
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
            sum = sum.add(findLargestNumber(subList));
        }
        return sum;
    }

    public static Integer[] findKthLargest(List<Integer[]> list, int k) throws IllegalArgumentException {
        if (k >= list.size()) {
            throw new IllegalArgumentException("The value of k that you provided is out of bounds of the " +
                    "List that you provided");
        }
        Integer[][] copy = list.toArray(new Integer[list.size()][]);

        //insertionSort(copy, new CompareByArr());
        Arrays.sort(copy,new CompareByArr());
        return copy[k];

    }

    public static List<Integer[]> readFile(String fileName) {

        var intList = new ArrayList<Integer[]>();
        try {
            File file = new File(fileName);
            Scanner scanner = new Scanner(file);

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] numbersAsString = line.trim().split("\\s+");
                var arr = new Integer[numbersAsString.length];
                for (int i = 0; i < numbersAsString.length; i++) {
                    arr[i] = Integer.parseInt(numbersAsString[i]);
                }
                intList.add(arr);
            }
        } catch (FileNotFoundException ignored) {}
        return intList;
    }
}

    class CompareByString implements Comparator<Integer> {

        @Override
        public int compare(Integer o1, Integer o2) {
            String s1 = o1.toString();
            String s2 = o2.toString();

            String left = s1 + s2;
            String right = s2 + s1;

            return right.compareTo(left);
        }
    }

    class CompareByArr implements Comparator<Integer[]> {

        @Override
        public int compare(Integer[] o1, Integer[] o2) {
            BigInteger s1 = LargestNumberSolver.findLargestNumber(o1);
            BigInteger s2 = LargestNumberSolver.findLargestNumber(o2);

            return s2.compareTo(s1);
        }
    }



    


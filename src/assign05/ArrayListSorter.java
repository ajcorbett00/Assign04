package assign05;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Class to sort ArrayLists of any type that
 * implements comparable.
 * @Author Austin Corbett
 * @version febuary 23, 2023
 */
public class ArrayListSorter {
private static int mergesortThreshold=200 ;
private static int quicksortThreshold=0;


    /**
     * Sorts any given generic array using the mergesort algorithm.
     * Driver of the mergesort() recursive method.
     * @param array to be sorted
     */
    public static <T extends Comparable<? super T>> void mergesort(ArrayList<T> array){
        if(array.size()<=1|| array==null)
            return;
        mergesort(array,0,array.size()-1);

    }

    /**
     * Sorts any given generic array using the quicksort algorithm.
     * Driver method of the quicksort() recursive method.
     * @param array to be sorted
     */
    public static <T extends Comparable<? super T>> void quicksort(ArrayList<T> array){
        if(array.size()==0 || array==null)
            return;
        quicksort(array,0,array.size()-1);
    }

    /**
     * Method to generate an acending array.
     * @param size of array
     * @return accending order array
     */
    public static ArrayList<Integer> generateAscending(int size){
        var arr = new ArrayList<Integer>();
        for(int i=0;i<size;i++){
            arr.add(i+1);
        }
        return arr;
    }

    /**
     * Method to generate a random int array.
     * @param size of the random array.
     * @return the randomized array
     */
    public static ArrayList<Integer> generatePermuted(int size){
        var randArr = generateAscending(size);
        Collections.shuffle(randArr);
        return randArr;
    }

    /**
     * Method to generate a decending ordered array.
     * @param size of int array.
     * @return the decendign ordered array
     */
    public static ArrayList<Integer> generateDescending(int size){
        var arr = new ArrayList<Integer>();
        for(int i =size;i>0;i--){
            arr.add(i);
        }
        return arr;
    }

    /**
     * Recursive method of the mergesort method.
     * @param arr to be merge sorted
     * @param left index of the start of sub array
     * @param right index of the sub array
     */
    private static <T extends Comparable<? super T>> void mergesort(ArrayList<T> arr, int left, int right){

        if (right-left>mergesortThreshold) {
            int middle = (left+right ) / 2;
            mergesort(arr, left, middle);
            mergesort(arr, middle + 1, right);
            merge(arr,left,middle,right );
        }else{
            insertionSort(arr.subList(left,right+1));
        }
    }

    /**
     *
     * @param arr to merge
     * @param left start of left array to merge
     * @param mid breaking point of the two sub arrays to merge
     * @param right the end of the second array
     * @param <T> the generic array list type
     */
    private static<T extends Comparable<? super T>> void merge(ArrayList<T> arr, int left, int mid, int right){
        int leftSize = mid-left+1;
        int rightSize = right-mid;

        var leftArr = new ArrayList<T>(leftSize);
        var rightArr = new ArrayList<T>(rightSize);

        for(int i = 0; i<leftSize;i++)
            leftArr.add(i,arr.get(left+i));
        for(int i=0; i<rightSize; i++)
            rightArr.add(i,arr.get(mid+1+i));

        int i=0;
        int j=0;
        int cursor = left;
        while(i<leftArr.size() && j< rightArr.size()){
            if (leftArr.get(i).compareTo(rightArr.get(j))<=0) {
                arr.set(cursor,leftArr.get(i));
                i++;
            }else{
                arr.set(cursor,rightArr.get(j));
                j++;
            }
            cursor++;
        }
        while(i<leftSize) {
            arr.set(cursor,leftArr.get(i));
            i++;
            cursor++;
        }
        while(j < rightSize) {
            arr.set(cursor, rightArr.get(j));
            j++;
            cursor++;
        }

    }
    /**
     * Insertion sorting method to help at the threshold of the merge and quicksort methods
     * @param arr to use insertion sort method on.
     */
    public static <T extends Comparable<? super T>> void insertionSort(List<T> arr) {
        for (int i = 1; i < arr.size(); i++) {
            T val = arr.get(i);
            int j;
            for (j = i; (j > 0) && (val.compareTo(arr.get(j - 1)) < 0); j--) {
                arr.set(j,arr.get(j - 1));
            }
            arr.set(j,val);
        }
    }

    /**
     * Helper method to swap elements in an array list.
     * @param arr of elements in which the swap will happen
     * @param first element to be swapped
     * @param second element to be swapped
     */
    private static <T extends Comparable<? super T>> void swap(ArrayList<T> arr, int first,int second){
        T temp = arr.get(first);
        arr.set(first,arr.get(second));
        arr.set(second,temp);
    }

    /**
     * Helper method to partition the array in to the sub arrays
     * to be sorted for the quick sort method.
     * @param arr to be partitoned
     * @param beg start of subarray
     * @param end of sub array to partition
     * @return the partition location
     */
    private static <T extends Comparable<? super T>> int partition(ArrayList<T> arr,int beg, int end){
        T pivot = arr.get(end);
        //the position before where the pivot should swap with.
        int j = beg-1;
        for( int i=beg; i<end;i++){
            if(arr.get(i).compareTo(pivot)<=0) {
                j++;
                swap(arr, j, i);

            }
        }
        swap(arr,j+1,end);
        return j+1;
    }

    /**
     * Quicksort recursive method. Once a threshold is hit the recursive method will switch
     * to insertion sort.
     * @param arr to be sorted
     * @param beg of first array
     * @param end end of second sub array
     */
    private static <T extends Comparable<? super T>> void quicksort(ArrayList<T> arr, int beg, int end){
        if((end- beg)> quicksortThreshold|| (end-beg)>1){
            int pivotIndex = partition(arr,beg,end);
            quicksort(arr,beg,pivotIndex-1);
            quicksort(arr,pivotIndex+1,end);
        }else{
            insertionSort(arr.subList(beg,end+1));
        }

    }

}

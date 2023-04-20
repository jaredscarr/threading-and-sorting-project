package sort;

import java.util.List;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveAction;

/**
 * Quicksort implementation that uses the median-of-three strategy to select
 * as the pivot for partitioning then sorts the List in place.
 * Passed List must contain elements that implement Comparable.
 */
public class QuickSort<T extends Comparable<? super T>> extends RecursiveAction {

    private final List<T> theList;
    private final int threshold;

    /**
     * Default constructor.
     *
     * @param list - List to be sorted
     */
    QuickSort(List<T> list) {
        theList = list;
        threshold = 100;
    }

    /**
     * Constructor that takes a list and specified threshold.
     *
     * @param list          - list to be sorted
     * @param passed_thresh - threshold for when to use threads
     */
    QuickSort(List<T> list, int passed_thresh) {
        theList = list;
        int MIN_THRESHOLD = 2;
        threshold = Math.max(passed_thresh, MIN_THRESHOLD);
    }

    /**
     * Sort the given List with either quick sort or insertion sort strategies.
     * Insertion sort for List sizes < threshold.
     * Quick sort for List sizes >= threshold.
     */
    @Override
    protected void compute() {
        int high = theList.size() - 1;
        int low = 0;
        // base case
        if (high - low < threshold) {
            InsertionSort<T> supplSort = new InsertionSort<>();
            supplSort.sort(theList);
        } else {
            // Use Recursive Action
            if (high > low) {
                int pivotIndex = partition(low, high);
                // Recursively sort below the partition with threads
                ForkJoinTask.invokeAll(
                        new QuickSort<>(theList.subList(low, pivotIndex)),
                        new QuickSort<>(theList.subList(pivotIndex + 1, high + 1)
                        ));
            }
        }
    }

    /**
     * Partition the List around the pivot.
     *
     * @param low  - lower bound index.
     * @param high - upper bound index.
     * @return - pivot index as an integer.
     */
    private int partition(int low, int high) {
        medianOfThree(low, high);
        return getPartitionIndex(low, high);
    }

    /**
     * Given a List, a lower bound, and an upper bound of the List. Find the median
     * value between the three and swamp it with the first position of the passed List.
     *
     * @param low  - lower bound index.
     * @param high - upper bound index.
     */
    private void medianOfThree(int low, int high) {
        int mid = (low + high) / 2;
        // assign the values to first, second, and third from the indices of low, mid, and high
        T first = theList.get(low);
        T second = theList.get(mid);
        T third = theList.get(high);
        // if the median is the middle value swap with low
        if ((first.compareTo(second) <= 0 && second.compareTo(third) <= 0)
                || (third.compareTo(second) <= 0 && second.compareTo(first) <= 0)) {
            swap(mid, low);
        }

        // if the median is the last value swap with low
        if ((second.compareTo(third) <= 0 && third.compareTo(first) <= 0)
                || (first.compareTo(third) <= 0 && third.compareTo(second) <= 0)) {
            swap(high, low);
            // the median is the last value
        }
        // otherwise the median is the low so no swap necessary
    }

    /**
     * Swap the values at the passed indices with each other.
     *
     * @param pivotIndex - the pivot index to swap.
     * @param swapIndex  - the index to swap with the pivot.
     */
    private void swap(int pivotIndex, int swapIndex) {
        // swap the values at the pivot and low indices
        T temp = theList.get(pivotIndex);
        theList.set(pivotIndex, theList.get(swapIndex));
        theList.set(swapIndex, temp);
    }

    /**
     * Partitions the List based on the value at the first position of the given List.
     *
     * @param low  - start index of the arrayList to be partitioned.
     * @param high - end index of the arrayList to be partitioned.
     * @return the pivot index integer.
     */
    private int getPartitionIndex(int low, int high) {
        T pivotValue = theList.get(low);
        int front = low + 1;
        int back = high;
        // while the tracking indexes have not crossed
        while (back > front) {
            // Iterate forward until a value greater than the pivot value is found
            while (front <= back && theList.get(front).compareTo(pivotValue) <= 0) {
                front++;
            }
            // Iterate backward until a value less than or equal to the pivot value is found
            while (front <= back && theList.get(back).compareTo(pivotValue) > 0) {
                back--;
            }
            // if the indexes have not crossed swap the values
            if (back > front) {
                T temp = theList.get(back);
                theList.set(back, theList.get(front));
                theList.set(front, temp);
            }
        }

        while (back > low && theList.get(back).compareTo(pivotValue) >= 0) {
            back--;
        }
        // if the pivot was moved then return the new pivot index
        if (pivotValue.compareTo(theList.get(back)) > 0) {
            theList.set(low, theList.get(back));
            theList.set(back, pivotValue);
            return back;
        } else {
            return low;
        }
    }
}

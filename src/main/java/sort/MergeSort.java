package sort;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveAction;

/**
 * MergeSort class. Passed List must contain elements that implement Comparable.
 *
 * @param <T>
 */
public class MergeSort<T extends Comparable<? super T>> extends RecursiveAction {
    private final List<T> theList;
    private final int threshold;

    /**
     * Default constructor.
     *
     * @param list - List to be sorted
     */
    MergeSort(List<T> list) {
        theList = list;
        threshold = 100;
    }

    /**
     * Constructor that takes a list and specified threshold.
     * Must have a minimum of 2 for recursion to work properly so does not allow
     * threshold to be set lower than 2.
     *
     * @param list          - list to be sorted
     * @param passed_thresh - threshold for when to use threads
     */
    MergeSort(List<T> list, int passed_thresh) {
        theList = list;
        int MIN_THRESHOLD = 2;
        threshold = Math.max(passed_thresh, MIN_THRESHOLD);
    }

    /**
     * Sort the given List with either merge sort or insertion sort strategies.
     * Insertion sort for List sizes < threshold.
     * Merge sort for List sizes >= threshold.
     */
    @Override
    protected void compute() {
        // if the size of the list is less than the threshold do Selection Sort
        int listSize = theList.size();
        // base case
        if (listSize < threshold) {
            InsertionSort<T> supplSort = new InsertionSort<>();
            supplSort.sort(theList);
        } else {
            // Use Recursive Action
            int mid = listSize / 2;

            List<T> leftArr = new ArrayList<>(mid);
            List<T> rightArr = new ArrayList<>(listSize - mid);

            for (int i = 0; i < mid; i++) {
                leftArr.add(i, theList.get(i));
            }

            for (int i = mid; i < listSize; i++) {
                rightArr.add(i - mid, theList.get(i));
            }

            ForkJoinTask.invokeAll(new MergeSort<T>(leftArr), new MergeSort<T>(rightArr));
            merge(leftArr, rightArr);
        }
    }

    /**
     * Merge two arrayLists together.
     *
     * @param leftList  - List to merge.
     * @param rightList - List to merge.
     */
    private void merge(List<T> leftList, List<T> rightList) {
        int i = 0, j = 0, k = 0;
        while (i < leftList.size() && j < rightList.size()) {
            if (leftList.get(i).compareTo(rightList.get(j)) < 1) {
                theList.set(k++, leftList.get(i++));
            } else {
                theList.set(k++, rightList.get(j++));
            }
        }
        while (i < leftList.size()) {
            theList.set(k++, leftList.get(i++));
        }
        while (j < rightList.size()) {
            theList.set(k++, rightList.get(j++));
        }
    }
}

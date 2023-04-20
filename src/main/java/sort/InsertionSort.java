package sort;


import java.util.List;

/**
 * Insertion sort class.
 *
 * @param <T> must implement Comparable
 */
public class InsertionSort<T extends Comparable<? super T>> {

    /**
     * Sort the list by inserting each element into the
     * correct place. This divides the list into a sorted part
     * and an unsorted part. When complete the list is completely
     * sorted.
     *
     * @param theList - list to sort.
     */
    public void sort(List<T> theList) {
        for (int i = 1; i < theList.size(); ++i) {
            T key = theList.get(i);
            int insertIndex = i - 1;

            while (insertIndex >= 0 && theList.get(insertIndex).compareTo(key) >= 0) {
                theList.set(insertIndex + 1, theList.get(insertIndex));
                insertIndex--;
            }
            theList.set(insertIndex + 1, key);
        }
    }
}

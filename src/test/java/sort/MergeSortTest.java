package sort;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ForkJoinPool;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Tests MergeSort class. Only tests the threaded part of the class.
 * This is where the threshold has been surpassed and the use of
 * threads seem necessary as dictated by the default implementation
 * or the programmer if passed as a parameter to the constructor.
 * Tests for under threshold are found in:
 * test/java/sort/InsertionSortTest.java
 */

class MergeSortTest {

    private final ForkJoinPool fjp = ForkJoinPool.commonPool();
    private final TestUtility testUtil = new TestUtility();

    @Test
    void sortLargeList() {
        List<Integer> actual = testUtil.createRandomIntegerList(1000, 100);
        List<Integer> expected = testUtil.copyIntegerList(actual);
        Collections.sort(expected);
        MergeSort<Integer> task = new MergeSort<>(actual, 100);
        fjp.invoke(task);
        assertEquals(expected, actual);
    }

    @Test
    void sortIntegers() {
        List<Integer> actual = Arrays.asList(4, 9, 5, 2, 56, 34, 12);
        List<Integer> expected = Arrays.asList(2, 4, 5, 9, 12, 34, 56);
        MergeSort<Integer> task = new MergeSort<>(actual, 2);
        fjp.invoke(task);
        assertEquals(expected, actual);
    }

    @Test
    void sortBasic() {
        List<Integer> actual = Arrays.asList(4, 9, 5, 2, 56, 34, 12);
        List<Integer> expected = Arrays.asList(2, 4, 5, 9, 12, 34, 56);
        MergeSort<Integer> task = new MergeSort<>(actual, 2);
        fjp.invoke(task);
        assertEquals(expected, actual);
    }

    @Test
    void sortReverseOrder() {
        List<Integer> actual = Arrays.asList(12, 11, 10, 9, 8, 7, 6, 5, 4, 3, 2, 1);
        List<Integer> expected = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12);
        MergeSort<Integer> task = new MergeSort<>(actual, 2);
        fjp.invoke(task);
        assertEquals(expected, actual);
    }

    @Test
    void sortInOrder() {
        List<Integer> actual = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12);
        List<Integer> expected = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12);
        MergeSort<Integer> task = new MergeSort<>(actual, 2);
        fjp.invoke(task);
        assertEquals(expected, actual);
    }

    @Test
    void charSort() {
        List<Character> actual = Arrays.asList('d', 'z', 'p', 'e');
        List<Character> expected = Arrays.asList('d', 'e', 'p', 'z');
        MergeSort<Character> task = new MergeSort<>(actual, 2);
        fjp.invoke(task);
        assertEquals(expected, actual);
    }

    @Test
    void stringSort() {
        List<String> actual = Arrays.asList("put", "me", "in", "order");
        List<String> expected = Arrays.asList("in", "me", "order", "put");
        MergeSort<String> task = new MergeSort<>(actual, 2);
        fjp.invoke(task);
        assertEquals(expected, actual);
    }
}
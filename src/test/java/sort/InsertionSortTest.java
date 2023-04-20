package sort;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class InsertionSortTest {

    private final InsertionSort<Integer> inSort = new InsertionSort<>();
    private final TestUtility testUtil = new TestUtility();

    @Test
    void sortLargeList() {
        List<Integer> actual = testUtil.createRandomIntegerList(100, 1000);
        List<Integer> expected = testUtil.copyIntegerList(actual);
        Collections.sort(expected);
        inSort.sort(actual);
        assertEquals(expected, actual);
    }

    @Test
    void sortIntegers() {
        List<Integer> actual = Arrays.asList(4, 9, 5, 2, 56, 34, 12);
        List<Integer> expected = Arrays.asList(2, 4, 5, 9, 12, 34, 56);
        inSort.sort(actual);
        assertEquals(expected, actual);
    }

    @Test
    void sortBasic() {
        List<Integer> actual = Arrays.asList(4, 9, 5, 2, 56, 34, 12);
        List<Integer> expected = Arrays.asList(2, 4, 5, 9, 12, 34, 56);
        inSort.sort(actual);
        assertEquals(expected, actual);
    }

    @Test
    void sortReverseOrder() {
        List<Integer> actual = Arrays.asList(12, 11, 10, 9, 8, 7, 6, 5, 4, 3, 2, 1);
        List<Integer> expected = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12);
        inSort.sort(actual);
        assertEquals(expected, actual);
    }

    @Test
    void sortInOrder() {
        List<Integer> actual = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12);
        List<Integer> expected = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12);
        inSort.sort(actual);
        assertEquals(expected, actual);
    }

    @Test
    void charSort() {
        InsertionSort<Character> inSort = new InsertionSort<>();
        List<Character> actual = Arrays.asList('d', 'z', 'p', 'e');
        List<Character> expected = Arrays.asList('d', 'e', 'p', 'z');
        inSort.sort(actual);
        assertEquals(expected, actual);
    }

    @Test
    void stringSort() {
        InsertionSort<String> inSort = new InsertionSort<>();
        List<String> actual = Arrays.asList("put", "me", "in", "order");
        List<String> expected = Arrays.asList("in", "me", "order", "put");
        inSort.sort(actual);
        assertEquals(expected, actual);
    }
}

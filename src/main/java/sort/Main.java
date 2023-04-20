package sort;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ForkJoinPool;

/**
 * Main class for Threading and Sorting Project.
 * Runs MergeSort and QuickSort on elements of 100 with threshold 100 (which is just Insertion Sort under the hood).
 * Runs MergeSort and QuickSort with 1000 elements and threshold of 100.
 * <p>
 * Run the tests located at src/tests to further seed the code works.
 * <p>
 * Note: These classes work with any object that implements Comparable
 * <p>
 * fjp.invoke(new MergeSort<String>(listOfStrings)); will work for example.
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("All lists generated using Random class with bound 100 and 1000.");
        System.out.println("-------------------------------------------------------------------------------------");
        List<Integer> intList100ForMergeSort = createRandomIntegerList(100, 100);
        List<Integer> intList100ForQuickSort = createRandomIntegerList(100, 100);
        List<Integer> intList1000ForMergeSort = createRandomIntegerList(1000, 1000);
        List<Integer> intList1000ForQuickSort = createRandomIntegerList(1000, 1000);
        System.out.println("First unsorted list of size 100 for MergeSort.");
        System.out.println();
        System.out.println(intList100ForMergeSort);
        System.out.println("-------------------------------------------------------------------------------------");
        System.out.println("Second unsorted list of size 100 for QuickSort.");
        System.out.println();
        System.out.println(intList100ForQuickSort);
        System.out.println("-------------------------------------------------------------------------------------");
        System.out.println("Third unsorted list of size 1000 for MergeSort.");
        System.out.println();
        System.out.println(intList1000ForMergeSort);
        System.out.println();
        System.out.println("Fourth unsorted list of size 1000 for QuickSort.");
        System.out.println();
        System.out.println(intList1000ForQuickSort);
        System.out.println();

        try (ForkJoinPool fjp = new ForkJoinPool()) {
            System.out.println("-------------------------------------------------------------------------------------");
            System.out.print("MergeSort Results from the list of 100 (InsertionSort): ");
            fjp.invoke(new MergeSort<Integer>(intList100ForMergeSort));
            System.out.println(intList100ForMergeSort);
            System.out.println();
            System.out.println("-------------------------------------------------------------------------------------");
            System.out.print("QuickSort Results from the list of 100 (InsertionSort): ");
            fjp.invoke(new MergeSort<Integer>(intList100ForQuickSort));
            System.out.println(intList100ForQuickSort);
            System.out.println();
            System.out.println("-------------------------------------------------------------------------------------");
            System.out.print("MergeSort Results from the list of 1000:");
            fjp.invoke(new MergeSort<Integer>(intList1000ForMergeSort));
            System.out.println(intList1000ForMergeSort);
            System.out.println();
            System.out.println("-------------------------------------------------------------------------------------");
            System.out.print("QuickSort Results from the list of 1000:");
            fjp.invoke(new QuickSort<Integer>(intList1000ForQuickSort));
            System.out.println(intList1000ForQuickSort);
            System.out.println();
        }
    }

    public static List<Integer> createRandomIntegerList(int size, int bound) {
        Random r = new Random();
        List<Integer> intList = new ArrayList<>(size);
        int i = 0;
        while (i < size) {
            intList.add(r.nextInt(bound));
            i++;
        }
        return intList;
    }
}
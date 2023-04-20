# Threading and Sorting Project

In this project, you will work individually on a project around threading and concurrency in Java by implementing sorting algorithms.

Write a multithreaded sorting program that works as follows: A list of integers is divided into two smaller lists of equal size. Two separate threads (which we will term sorting threads) sort each sublist using a sorting algorithm of your choice. The two sublists are then merged by a third thread—a merging thread—which merges the two sublists into a single sorted list.

Implement the project using Java's fork-join parallelism API. This project will be developed in two different versions.
Each version will implement a different divide-and-conquer sorting algorithm:

1. MergeSort
2. QuickSort

The Quicksort implementation will use the Quicksort algorithm for dividing the list of elements to be sorted into a left
half and a right half based on the position of the pivot value. The Mergesort algorithm will divide the list into two
evenly sized halves. For both the Quicksort and Mergesort algorithms, when the list to be sorted falls within some
threshold value (for example, the list is size 100 or fewer), directly apply a simple algorithm such as the Selection
or Insertion sort. Most data structures texts describe these two well-known, divide-and-conquer sorting algorithms.

The Java class SumTask extends RecursiveTask, which is a result-bearing ForkJoinTask. As this assignment will involve
sorting the array that is passed to the task, but not returning any values, you will instead create a class that extends
RecursiveAction, a non result-bearing ForkJoinTask.

The objects passed to each sorting algorithm are required to implement Java's Comparable interface, and this will need
to be reflected in the class definition for each sorting algorithm.

Test this program by creating an array of 1000 random elements and another array with 100 elements (which will use your
Selection or Insertion sort).

Be sure your output tests if the final results is sorted and prints out both the unsorted and then sorted versions.
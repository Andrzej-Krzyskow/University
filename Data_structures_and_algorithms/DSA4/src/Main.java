import advancedsorts.IterativeMerge;
import advancedsorts.MaxHeapSort;
import advancedsorts.test;
import simplesorts.*;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {

        //Task 1
        System.out.println("Task 1");
        int[] data1 = {76, 71, 5, 57, 12, 50, 20, 93, 20, 55, 62, 3};

        System.out.println("Before: " + Arrays.toString(data1));
        InsertionSort.insertionSort(data1);
        System.out.println("After: "+Arrays.toString(data1));

        //Task 2
        System.out.println("\nTask 2");
        int[] data2 = {76, 71, 5, 57, 12, 50, 20, 93, 20, 55, 62, 3};

        System.out.println("Before: " + Arrays.toString(data2));
        SelectionSort.selectionSort(data2);
        System.out.println("After: "+Arrays.toString(data2));

        //Task 3
        System.out.println("\nTask 3");
        int[] data3 = {76, 71, 5, 57, 12, 50, 20, 93, 20, 55, 62, 3};

        System.out.println("Before: " + Arrays.toString(data3));
        BubbleSort.bubbleSort(data3);
        System.out.println("After: "+Arrays.toString(data3));

        //Task 4
        System.out.println("\nTask 4");
        int[] data4 = {76, 71, 5, 57, 12, 50, 20, 93, 20, 55, 62, 3};

        System.out.println("Before: " + Arrays.toString(data4));
        ImprovedShakerSort.shakerSort(data4);
        System.out.println("After: "+Arrays.toString(data4));

        //Task 5
        System.out.println("\nTask 5 - that's just showing process of simplesorts.ShakerSort");

        //Task 6
        System.out.println("\nTask 6");
        int[] data6 = {76, 71, 5, 57, 12, 50, 20, 93, 20, 55, 62, 3};

        System.out.println("Before: " + Arrays.toString(data6));
        IterativeMerge.mergeSort(data6);
        System.out.println("After: "+Arrays.toString(data6));

        //Task 7
        System.out.println("\nTask 7");
        int[] data7 = {76, 71, 5, 57, 12, 50, 20, 93, 20, 55, 62, 3};

        System.out.println("Before: " + Arrays.toString(data7));
        MaxHeapSort.heapSort(data7);
        System.out.println("After: "+Arrays.toString(data7));

        //Task 8
        System.out.println("\nTask 8");
        int[] data8 = {0, 2, 1, 0, 4, 4, 2, 1, 1, 1};

        System.out.println("Before: " + Arrays.toString(data8));
        CountingSort.countSort(data8, 4);
        System.out.println("After: "+Arrays.toString(data8));
    }
}

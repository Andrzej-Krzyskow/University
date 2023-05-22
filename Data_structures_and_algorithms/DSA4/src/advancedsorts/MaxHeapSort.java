package advancedsorts;

import java.util.Arrays;

public class MaxHeapSort {

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    private static void heapAdjust(int[] arr, int arrSize) {
        for (int indexOfParent = (arrSize - 1) / 2; indexOfParent >= 0; indexOfParent--) {
            sink(arr, indexOfParent, arrSize);
        }
    }

    private static void sink(int[] arr, int indexOfParent, int arrSize) {
        int indexOfGreaterChild=2*indexOfParent+1;

        if (indexOfGreaterChild < arrSize) {

            if (indexOfGreaterChild + 1 < arrSize && arr[indexOfGreaterChild]<arr[indexOfGreaterChild+1]) {
                indexOfGreaterChild++;
            }

            if(arr[indexOfGreaterChild]>arr[indexOfParent]){
                swap(arr,indexOfGreaterChild,indexOfParent);
                sink(arr,indexOfGreaterChild,arrSize);
            }
        }
    }


    public static void heapSort(int[] arr) {
        int arrSize = arr.length;

        for (int i = arrSize / 2 - 1; i >= 0; i--) {
            heapAdjust(arr, arrSize);
        }

        for (int i = arrSize - 1; i > 0; i--) {
            swap(arr, 0, i);
            sink(arr, 0, i);
            //System.out.println(Arrays.toString(arr));
        }
    }


}

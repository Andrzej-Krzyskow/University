package simplesorts;

import java.util.Arrays;

public class ImprovedShakerSort {

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void shakerSort(int[] arr) {
        boolean swapped = true;
        int start = 0;
        int end = arr.length;

        while (swapped) {

            swapped = false;

            for (int i = start; i < end - 1; i++) {
                if (arr[i] > arr[i + 1]) {
                    swap(arr, i, i+1);
                    swapped = true;
                }
            }
            //System.out.println(Arrays.toString(arr));
            if (!swapped) {
                break;
            }

            swapped = false;
            end = end - 1;

            for (int i = end - 1; i >= start; i--) {
                if (arr[i] > arr[i + 1]) {
                    swap(arr, i, i+1);
                    swapped = true;
                }
            }

            start = start + 1;
            //System.out.println(Arrays.toString(arr));

        }
    }

}

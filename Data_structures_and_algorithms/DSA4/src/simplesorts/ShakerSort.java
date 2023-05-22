package simplesorts;

import java.util.Arrays;

public class ShakerSort {

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void shakerSort(int[] arr) {
        int start = 0;
        int end = arr.length;

        while (start < end) {

            for (int i = start; i < end - 1; i++) {
                if (arr[i] > arr[i + 1]) {
                    swap(arr, i, i + 1);
                }
            }
            //System.out.println(Arrays.toString(arr));
            end = end - 1;

            for (int i = end - 1; i >= start; i--) {
                if (arr[i] > arr[i + 1]) {
                    swap(arr, i, i + 1);
                }
            }

            start = start + 1;
            //System.out.println(Arrays.toString(arr));

        }
    }

}

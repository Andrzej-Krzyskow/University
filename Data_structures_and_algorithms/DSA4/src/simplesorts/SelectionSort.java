package simplesorts;

import java.util.Arrays;

public class SelectionSort {

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void selectionSort(int[] arr) {

        int n = arr.length;

        for (int i = 1; i < n; i++) {
            int indexOfCurrentMinValue = 0;

            for (int j = 0; j <= n - i; j++) {
                if (arr[j] < arr[indexOfCurrentMinValue]) {
                    indexOfCurrentMinValue = j;
                }
            }
            swap(arr, indexOfCurrentMinValue, n - i);
            //System.out.println(Arrays.toString(arr));
        }
    }
}

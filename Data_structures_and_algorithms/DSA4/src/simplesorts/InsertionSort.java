package simplesorts;

import java.util.Arrays;

public class InsertionSort {

    public static void insertionSort(int[] arr) {
        int n = arr.length;

        for (int i = 1; i < n; i++) {
            int j = n - 1 - i;
            int temp = arr[j];

            for (; j < n - 1 && temp < arr[j + 1]; j++) {
                arr[j] = arr[j + 1];
            }
            arr[j] = temp;

            //System.out.println(Arrays.toString(arr));
        }

    }
}

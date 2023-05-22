package simplesorts;

import java.util.Arrays;

public class BubbleSort {
    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void bubbleSort(int[] arr) {
        int n = arr.length;

        for (int i = 1; i < n; i++) {

            for (int j = n - 1; j >= i; j--) {

                if (arr[j - 1] < arr[j]) {
                    swap(arr, j - 1, j);
                }
            }

            //System.out.println(Arrays.toString(arr));
        }
    }
}

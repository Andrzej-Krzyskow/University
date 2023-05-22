import java.util.Random;

class QuickSort {

    private static final Random random = new Random();

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    private static void choosePivotIndex(int[] arr, int left, int right) {

        right++;
        if (right - left + 1 > 100) {

            int a = (random.nextInt(left, right));
            int b = (random.nextInt(left, right));
            int c = (random.nextInt(left, right));

            if (arr[a] > arr[b]) {
                if (arr[b] > arr[c]) {
                    swap(arr, right - 1, b);
                } else if (arr[a] > arr[c]) {
                    swap(arr, right - 1, c);
                } else {
                    swap(arr, right - 1, a);
                }
            } else {
                if (arr[a] > arr[c]) {
                    swap(arr, right - 1, a);
                } else if (arr[b] > arr[c]) {
                    swap(arr, right - 1, c);
                } else {
                    swap(arr, right - 1, b);
                }
            }
        }

        swap(arr, random.nextInt(left, right), right - 1);

    }

    private static int partition(int[] arr, int left, int right) {

        choosePivotIndex(arr, left, right);
        int pivot = arr[right];

        int i = (left - 1);

        for (int j = left; j <= right - 1; j++) {

            if (arr[j] < pivot) {
                i++;
                swap(arr, i, j);
            }
        }

        swap(arr, i + 1, right);
        return (i + 1);
    }


    private static void recursiveQuickSort(int[] arr, int left, int right) {

        if (left < right) {
            int done = partition(arr, left, right);

            recursiveQuickSort(arr, left, done - 1);
            recursiveQuickSort(arr, done + 1, right);
        }
    }

    static void quickSort(int[] arr) {
        recursiveQuickSort(arr, 0, arr.length - 1);
    }
}
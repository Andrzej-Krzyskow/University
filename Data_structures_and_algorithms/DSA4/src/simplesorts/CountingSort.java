package simplesorts;

public class CountingSort {

    public static void countSort(int[] data, int range) {
        int[] result = new int[data.length];
        int[] pos = new int[range+1];

        for (int integer : data) {
            pos[integer]++;
        }

        pos[0]--;
        for (int i = 1; i <= range; i++) {
            pos[i]+=pos[i-1];
        }

        for (int i = data.length - 1; i >= 0; i--) {
            result[pos[data[i]]] = data[i];
            pos[data[i]]--;
        }

        System.arraycopy(result, 0, data, 0, data.length);
    }

}

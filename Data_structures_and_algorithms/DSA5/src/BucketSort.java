import java.util.ArrayList;
import java.util.Comparator;

public class BucketSort {

    public static void bucketSort(int[] data, int numberOfBuckets, int maxRange) {

        @SuppressWarnings("unchecked")
        ArrayList<Integer>[] buckets = new ArrayList[numberOfBuckets];


        for (int i = 0; i < numberOfBuckets; i++) {
            buckets[i] = new ArrayList<>();
        }

        for (int integer : data) {
            int bucketIdx = integer * numberOfBuckets / maxRange;
            buckets[bucketIdx].add(integer);
        }

        for (int i = 0; i < numberOfBuckets; i++) {
            buckets[i].sort(Comparator.naturalOrder());
        }

        int index = 0;
        for (int i = 0; i < numberOfBuckets; i++) {
            for (int j = 0; j < buckets[i].size(); j++) {
                data[index++] = buckets[i].get(j);
            }
        }

    }
}

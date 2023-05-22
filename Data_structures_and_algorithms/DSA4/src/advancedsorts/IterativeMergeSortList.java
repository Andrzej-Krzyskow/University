package advancedsorts;
import structures.TwoWayCycledOrderedListWithSentinel;
import structures.EfficientQueue;
import structures.TwoWayUnorderedListWithHeadAndTail;

public class IterativeMergeSortList {

    public static void mergeSort(int[] arr)
    {
        EfficientQueue<TwoWayCycledOrderedListWithSentinel<Integer>> queue = new EfficientQueue<>();

        for (int j : arr) {
            TwoWayCycledOrderedListWithSentinel<Integer> tempList = new TwoWayCycledOrderedListWithSentinel<>();

            tempList.add(j);
            queue.enqueue(tempList);
        }

        while (!queue.isEmpty()) {
            TwoWayCycledOrderedListWithSentinel<Integer> tempList = new TwoWayCycledOrderedListWithSentinel<>();

            tempList.add(queue.dequeue());
            tempList.add(queue.dequeue());

            if (queue.isEmpty()) {
                int i = 0;

                for (Integer integer : tempList) {
                    arr[i++] = integer;
                }
                break;
            }

            queue.enqueue(tempList);
        }

    }
}

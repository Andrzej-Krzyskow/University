import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;


public class FIFO {

    public static int calculatePageFaults(int[] pages, int framesNum) {
        HashSet<Integer> memory = new HashSet<>(framesNum);
        Queue<Integer> loadOrder = new LinkedList<>();
        int pageFaults = 0;

        for (Integer page : pages) {

            if (!memory.contains(page)) {

                if (memory.size() == framesNum && !loadOrder.isEmpty()) {
                    memory.remove(loadOrder.poll());
                }

                memory.add(page);
                loadOrder.add(page);
                pageFaults++;
            }
        }
        return pageFaults;
    }
}
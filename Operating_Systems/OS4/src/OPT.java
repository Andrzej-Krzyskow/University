import java.util.HashSet;
public class OPT {

    public static int calculatePageFaults(int[] pages, int framesNum) {
        HashSet<Integer> memory = new HashSet<>(framesNum);
        int pageFaults = 0;

        for (int i = 0; i < pages.length; i++) {

            if (!memory.contains(pages[i])) {

                if (memory.size() == framesNum) {
                    memory.remove(findBestPage(pages, memory, i + 1));
                }

                memory.add(pages[i]);
                pageFaults++;
            }
        }
        return pageFaults;
    }
    private static int findBestPage(int[] pages, HashSet<Integer> memory, int currentPageIndex) {
        int bestPage = memory.iterator().next();
        int distance = 0;
        for (int frame : memory) {
            int i;
            for (i = currentPageIndex; i < pages.length; i++) {
                if (frame == pages[i]) {
                    if (i > distance) {
                        distance = i;
                        bestPage = frame;
                    }
                    break;
                }
            }

            if (i == pages.length) {
                return frame;
            }
        }
        return bestPage;
    }
}
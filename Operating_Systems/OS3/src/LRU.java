import java.util.*;

public class LRU {


    public static int calculatePageFaults(int[] pages, int framesNum) {
        HashMap<Integer, Integer> loadedPages = new HashMap<>();
        int pageFaults = 0;

        for (int i = 0; i < pages.length; i++) {

            if (!loadedPages.containsKey(pages[i])) {

                if (loadedPages.size() == framesNum && !loadedPages.isEmpty()) {
                    int min = Collections.min(loadedPages.values());
                    loadedPages.values().remove(min);
                }

                pageFaults++;
            }

            loadedPages.put(pages[i], i);
        }


        return pageFaults;
    }



}

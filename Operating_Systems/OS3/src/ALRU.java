import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class ALRU {
    public static int calculatePageFaults(int[] pages, int framesNum) {
        HashMap<Integer, Boolean> loadedPages = new HashMap<>();
        int pageFaults = 0;

        for (int page : pages) {

            if (!loadedPages.containsKey(page)) {

                if (loadedPages.size() == framesNum && !loadedPages.isEmpty()) {
                    if (!loadedPages.values().remove(false)) {
                        Map.Entry<Integer, Boolean> entry = loadedPages.entrySet().iterator().next();
                        loadedPages.remove(entry.getKey());
                    }

                }

                for (Map.Entry<Integer, Boolean> entry : loadedPages.entrySet()) {
                    entry.setValue(false);
                }

                pageFaults++;
            }

            loadedPages.put(page, true);
        }


        return pageFaults;
    }
}

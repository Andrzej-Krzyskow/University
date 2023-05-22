import java.util.HashSet;
import java.util.Random;

public class RAND {

    public static int calculatePageFaults(int[] pages, int framesNum) {
        HashSet<Integer> memory = new HashSet<>(framesNum);
        Random rand = new Random(framesNum);
        int pageFaults = 0;

       for (int page : pages) {

            if (!memory.contains(page)) {

                if (memory.size() == framesNum) {
                    Integer rndInt = memory.stream().skip(rand.nextInt(memory.size())).findFirst().orElse(null);
                    memory.remove(rndInt);

/*                    Integer[] arrayNumbers = memory.toArray(new Integer[memory.size()]);
                    memory.remove(arrayNumbers[rand.nextInt(framesNum)]);*/
                }

                memory.add(page);
                pageFaults++;
            }
        }
        return pageFaults;
    }
}

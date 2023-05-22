import java.util.Arrays;

public class Main {
    public static void main(String[] args) {

        int NUMBER_OF_FRAMES = 20;
        int NUMBER_OF_CALLS = 10000;
        int NUMBER_OF_PAGES = 100;
        int ITERATION_NUMBER = 30;
        int[] averages = new int[5];

        for (int i = 0; i < ITERATION_NUMBER; i++) {
            int[] calls = CallGenerator.generateCalls(NUMBER_OF_CALLS,NUMBER_OF_PAGES, i);

/*            if (i == 0) {
                System.out.println(Arrays.toString(calls));
            }*/

           averages[0] += FIFO.calculatePageFaults(calls, NUMBER_OF_FRAMES);
            averages[1] += RAND.calculatePageFaults(calls, NUMBER_OF_FRAMES);
            averages[2] += OPT.calculatePageFaults(calls, NUMBER_OF_FRAMES);
            averages[3] += LRU.calculatePageFaults(calls, NUMBER_OF_FRAMES);
            averages[4] += ALRU.calculatePageFaults(calls, NUMBER_OF_FRAMES);

        }

        for(int i=0; i<5; i++){
            averages[i]/=ITERATION_NUMBER;
        }




        System.out.println("Average values");
        System.out.println("Number of page faults FIFO: " + averages[0]);
        System.out.println("Number of page faults RAND: " + averages[1]);
        System.out.println("Number of page faults OPT: " + averages[2]);
        System.out.println("Number of page faults LRU: " + averages[3]); //it's not as good as it supposed to be, because in real scenario we have locality of references (we refer many times during short period of time to the same address)
        System.out.println("Number of page faults ALRU: " + averages[4]);

    }
}


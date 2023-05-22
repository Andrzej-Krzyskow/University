import java.util.Random;

public class processGenerator {

    private static int[] generateCalls(int callNum, int seed, int startPage, int endPage) {
        int[] calls = new int[callNum];
        Random rand = new Random(seed);

        calls[0] = rand.nextInt(startPage, endPage);

        for (int i = 1; i < callNum / 2; i++) {
            int page = calls[i - 1];

            //locality principle
            if (rand.nextInt(100) >= 75) {
                int temp = rand.nextInt(startPage, endPage);

                while (temp == page) {
                    temp = rand.nextInt(startPage, endPage);
                }
                page = temp;

                calls[callNum - i - 2] = calls[i - 1];
                calls[i + 1] = calls[i - 1];
                i++;
                calls[callNum - i] = page;
                calls[i - 1] = page;
                continue;
            }


            if (rand.nextInt(100) >= 99) {
                int temp = rand.nextInt(startPage, endPage);
                page = calls[i - 1];

                while (temp == page) {
                    temp = rand.nextInt(startPage, endPage);
                }
                page = temp;
            }

            //symmetry principle
            calls[callNum - i - 1] = page;
            calls[i] = page;
        }

        calls[callNum / 2] = rand.nextInt(startPage, endPage + 1);

        //change symmetry not to be 100%
        for (int i = 0; i < rand.nextInt(10, 21) * callNum / 100; i++) {
            calls[rand.nextInt(callNum)] = rand.nextInt(startPage, endPage + 1);
        }

        return calls;
    }

    public static Process[] generateProcesses(int processNum, int callNum, int pageNum, int processSize, int seed) {

        Process[] processes = new Process[processNum];
        int interval = pageNum / processNum;
        Random random = new Random();
        for (int i = 0; i < processNum; i++) {
            processes[i] = new Process(processGenerator.generateCalls(callNum , i + seed, interval * i, interval * i + interval), random.nextInt(processSize));
        }

        return processes;
    }


}

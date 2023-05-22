import java.util.Random;

public class CallGenerator {

    public static int[] generateCalls(int callNum, int pageNum, int seed){
        int[] calls = new int[callNum];
        Random rand = new Random(seed);

        calls[0] = rand.nextInt(pageNum);

        for(int i = 1; i<callNum/2; i++){
            int page = calls[i - 1];

            //locality principle
            if (rand.nextInt(100) >=75) {
                int temp= rand.nextInt(pageNum);

                while (temp == page) {
                    temp= rand.nextInt(pageNum);
                }
                page  = temp;

                calls[callNum-i-2] = calls[i-1];
                calls[i+1] = calls[i-1];
                i++;
                calls[callNum-i] = page;
                calls[i-1] = page;
                continue;
            }


            if (rand.nextInt(100) >=99) {
                int temp= rand.nextInt(pageNum);
                page = calls[i - 1];

                while (temp == page) {
                    temp= rand.nextInt(pageNum);
                }
                page  = temp;
            }

            //symmetry principle
            calls[callNum-i-1] = page;
            calls[i] = page;
        }

        calls[callNum / 2] = rand.nextInt(pageNum + 1);

        //change symmetry not to be 100%
        for (int i = 0; i < rand.nextInt(10, 21)*pageNum/100; i++) {
            calls[rand.nextInt(callNum)] = rand.nextInt(pageNum + 1);
        }

        return calls;
    }


    public static int[] generateCalls1(int callNum, int pageNum, int seed){
        int[] calls = new int[callNum];
        Random rand = new Random(seed);

        for(int i = 0; i<callNum/2; i++){
            int page = rand.nextInt(pageNum);
            calls[callNum-i-1] = page;
            calls[i] = page;
        }

        calls[callNum / 2] = rand.nextInt(pageNum + 1);

        //symmetry principle
        for (int i = 0; i < rand.nextInt(10, 21)*pageNum/100; i++) {
            calls[rand.nextInt(callNum)] = rand.nextInt(pageNum + 1);
        }

        return calls;

    }

}

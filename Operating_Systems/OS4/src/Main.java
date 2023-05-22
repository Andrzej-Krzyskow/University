public class Main {
    public static void main(String[] args) {

        int NUMBER_OF_FRAMES = 75;
        int CALL_NUM_PER_PROCESS = 3000;
        int RANGE_OF_PAGES = 100;
        int PROCESS_NUMBER = 10;

        //proportional
        int MAX_PROCESS_SIZE = 10;
        //page fault frequency
        int MIN_PERCENTAGE = 10;
        int MAX_PERCENTAGE = 90;
        int LOOK_BACK_NUMBER = 100;
        //Working Set
        int TIME_WINDOW = 100;


        int ITERATION_NUMBER = 10;
        int[] averages = new int[5];


        for (int i = 0; i < ITERATION_NUMBER; i++) {
            Process[] processes = processGenerator.generateProcesses(PROCESS_NUMBER, CALL_NUM_PER_PROCESS, RANGE_OF_PAGES, MAX_PROCESS_SIZE, i);
            averages[0] += Equal.calculatePageFaults(processes, NUMBER_OF_FRAMES);
            averages[1] += Rand.calculatePageFaults(processes, NUMBER_OF_FRAMES);
            averages[2] += Proportional.calculatePageFaults(processes, NUMBER_OF_FRAMES);
            averages[3] += PageFaultFrequency.calculatePageFaults(processes, NUMBER_OF_FRAMES, LOOK_BACK_NUMBER, MAX_PERCENTAGE, MIN_PERCENTAGE);
            averages[4] += WorkingSet.calculatePageFaults(processes, NUMBER_OF_FRAMES, TIME_WINDOW);

        }


        //System.out.println(Arrays.toString(processes));

        System.out.println("EQL page faults: " + averages[0] / ITERATION_NUMBER);
        System.out.println("RAND page faults: " + averages[1] / ITERATION_NUMBER);
        System.out.println("PROP page faults: " + averages[2] / ITERATION_NUMBER);
        System.out.println("PFF page faults: " + averages[3] / ITERATION_NUMBER);
        System.out.println("WS page faults: " + averages[4] / ITERATION_NUMBER);


    }
}



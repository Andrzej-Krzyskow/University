import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {

        int SEED = 1;
        int NO_CPU = 75;
        int NO_PROCESSES = 15000;
        int EXECUTION_TIME = 50;
        int TIME_BOUND = 2000;    //for generating processes
        int LOAD_BOUND = 40;    //for generating processes

        int MAX_REQUESTS = 20;  //for RAND
        int MAX_THRESHOLD = 90;
        int MIN_THRESHOLD = 10;

        int[] averageCPULoad = new int[3];
        int[] averageRequests = new int[3];
        int[] averageMigrations = new int[3];
        int[] executionTime = new int[3];

        int NO_ITERATIONS = 3;

        for (int i = 0; i < NO_ITERATIONS; i++) {
            ArrayList<CPU> CPUsOriginal = Generator.generateCPUs(NO_CPU, LOAD_BOUND, TIME_BOUND, NO_PROCESSES, SEED + i);
            ArrayList<CPU> CPUs = cloneCPUs(CPUsOriginal);

            int[] RANDdata = RAND.getData(CPUs, MAX_REQUESTS, MAX_THRESHOLD, EXECUTION_TIME, SEED + i);
            CPUs = cloneCPUs(CPUsOriginal);
            int[] MAXThresholdData = MaxThreshold.getData(CPUs, MAX_THRESHOLD, EXECUTION_TIME, SEED + i);
            CPUs = cloneCPUs(CPUsOriginal);
            int[] MINThresholdData = MinThreshold.getData(CPUs, MAX_THRESHOLD, MIN_THRESHOLD, EXECUTION_TIME, SEED + i);

            averageCPULoad[0] += RANDdata[0];
            averageCPULoad[1] += MAXThresholdData[0];
            averageCPULoad[2] += MINThresholdData[0];


            averageRequests[0] += RANDdata[1];
            averageRequests[1] += MAXThresholdData[1];
            averageRequests[2] += MINThresholdData[1];

            averageMigrations[0] += RANDdata[2];
            averageMigrations[1] += MAXThresholdData[2];
            averageMigrations[2] += MINThresholdData[2];

            executionTime[0] += RANDdata[3];
            executionTime[1] += MAXThresholdData[3];
            executionTime[2] += MINThresholdData[3];
        }

        System.out.println("Random data:\n\tCPU load: " + averageCPULoad[0] / NO_ITERATIONS +
                "\n\tRequests: " + averageRequests[0] / NO_ITERATIONS +
                "\n\tMigrations: " + averageMigrations[0] / NO_ITERATIONS +
                "\n\tTime taken to execute: " + executionTime[0] / NO_ITERATIONS);

        System.out.println("MAX Threshold data:\n\tCPU load: " + averageCPULoad[1] / NO_ITERATIONS +
                "\n\tRequests: " + averageRequests[1] / NO_ITERATIONS +
                "\n\tMigrations: " + averageMigrations[1] / NO_ITERATIONS +
                "\n\tTime taken to execute: " + executionTime[1] / NO_ITERATIONS);

        System.out.println("MIN threshold data:\n\tCPU load: " + averageCPULoad[2] / NO_ITERATIONS +
                "\n\tRequests: " + averageRequests[2] / NO_ITERATIONS +
                "\n\tMigrations: " + averageMigrations[2] / NO_ITERATIONS +
                "\n\tTime taken to execute: " + executionTime[2] / NO_ITERATIONS);


    }

    public static ArrayList<CPU> cloneCPUs(ArrayList<CPU> CPUs) {
        ArrayList<CPU> cpus = new ArrayList<>();

        for (CPU cpu : CPUs) {
            cpus.add(cpu.clone());
        }

        return cpus;
    }

}

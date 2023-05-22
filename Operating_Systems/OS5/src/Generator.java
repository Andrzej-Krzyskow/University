import java.util.*;

public class Generator {

    private static Queue<Process> generateProcesses(int numberOfProcesses, int loadBound, int timeBound, int seed) {

        LinkedList<Process> processes = new LinkedList<>();
        Random random = new Random(seed);

        for (int i = 0; i < numberOfProcesses; i++) {
            processes.add(new Process(random.nextInt(1, loadBound+1), random.nextInt(timeBound + 1)));
            processes.sort(Comparator.comparingInt(a -> a.arrivalTime));
        }
        return processes;
    }

    public static ArrayList<CPU> generateCPUs(int numberOfCPUs, int loadBound, int timeBound, int numberOfProcesses, int seed) {
        int numberOfProcessesPerCPU = numberOfProcesses/numberOfCPUs + 1;
        ArrayList<CPU> CPUs = new ArrayList<>();

        for (int i = 0; i < numberOfCPUs; i++) {
            CPUs.add(new CPU(generateProcesses(numberOfProcessesPerCPU, loadBound, timeBound, seed + i)));
        }

        return CPUs;
    }

}

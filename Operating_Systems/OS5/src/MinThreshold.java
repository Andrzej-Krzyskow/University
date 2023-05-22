import java.util.*;

public class MinThreshold {
    public static int[] getData(ArrayList<CPU> CPUs, int maxThreshold,int minThreshold, int executionTime, int seed) {
        Random random = new Random(seed);
        int[] data = new int[4];
        int CPULoad = 0;
        int requests = 0;
        int migrations = 0;
        int time = 0;


        while (run(CPUs)) {

            for (CPU cpu : CPUs) {
                while (!cpu.processesToCome.isEmpty() && cpu.processesToCome.peek().getArrivalTime() == time) {
                    Process process = cpu.processesToCome.peek();

                    assert process != null;
                    if (cpu.getLoad() + process.getLoad() <= 100) {
                        cpu.increaseLoadBy(process.getLoad());
                        cpu.loadedProcesses.add(process);
                        cpu.processesToCome.remove();
                    } else {

                        int chosenCPUIndex = random.nextInt(CPUs.size());
                        requests++;

                        for(int i = 0; CPUs.get(chosenCPUIndex).getLoad() + process.load > maxThreshold && i < CPUs.size()*0.2; i++) {
                            chosenCPUIndex = random.nextInt(CPUs.size());
                            requests++;
                        }
                        CPU chosenCpu = CPUs.get(chosenCPUIndex);

                        if (chosenCpu!=cpu && chosenCpu.getLoad() <= maxThreshold && chosenCpu.getLoad() + process.getLoad() <= 100) {
                            chosenCpu.increaseLoadBy(process.getLoad());
                            chosenCpu.loadedProcesses.add(process);
                            cpu.processesToCome.remove();
                            migrations++;
                        } else{
                            for (Process process1 : cpu.processesToCome) {
                                process1.increaseArrivalTimeBy(1);
                            }
                        }
                    }
                }

                if (cpu.getLoad() < minThreshold) {
                    int chosenCPUIndex = random.nextInt(CPUs.size());
                    requests++;

                    for(int i = 0; CPUs.get(chosenCPUIndex).getLoad() <= maxThreshold && i < CPUs.size()*0.2; i++) {
                        chosenCPUIndex = random.nextInt(CPUs.size());
                        requests++;
                    }
                    CPU chosenCpu = CPUs.get(chosenCPUIndex);

                    while (chosenCpu.getLoad() > maxThreshold) {
                        Process process = chosenCpu.loadedProcesses.remove();
                        chosenCpu.decreaseLoadBy(process.getLoad());
                        cpu.increaseLoadBy(process.getLoad());
                        cpu.loadedProcesses.add(process);

                        LinkedList<Process> processes = new LinkedList<>();

                        while (!cpu.loadedProcesses.isEmpty()) {
                            processes.add(cpu.loadedProcesses.remove());
                        }

                        processes.sort(Comparator.comparingInt(a -> a.arrivalTime));

                        cpu.loadedProcesses.addAll(processes);
                        migrations++;
                    }
                }


                while (!cpu.loadedProcesses.isEmpty() && cpu.loadedProcesses.peek().getArrivalTime() + executionTime == time) {
                    cpu.decreaseLoadBy(cpu.loadedProcesses.remove().getLoad());
                }
            }

            CPULoad += calcAverage(CPUs);
            time++;
        }


        data[0] = CPULoad / time;
        data[1] = requests;
        data[2] = migrations;
        data[3] = time;
        return data;
    }


    private static boolean run(ArrayList<CPU> CPUs) {

        boolean run = false;

        for (CPU cpu : CPUs) {
            if (!cpu.processesToCome.isEmpty() || !cpu.loadedProcesses.isEmpty()) {
                run = true;
                break;
            }
        }

        return run;
    }

    private static int calcAverage(ArrayList<CPU> CPUs) {
        int average = 0;
        for (CPU cpu : CPUs) {
            average += cpu.getLoad();
        }
        average /= CPUs.size();
        return average;
    }
}

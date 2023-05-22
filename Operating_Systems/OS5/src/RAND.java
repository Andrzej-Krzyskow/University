import java.util.ArrayList;
import java.util.Random;

public class RAND {
    public static int[] getData(ArrayList<CPU> CPUs, int maxRequestNumber, int maxThreshold, int executionTime, int seed) {
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
                    int chosenCPUIndex = random.nextInt(CPUs.size());
                    requests++;

                    for (int i = 1; i < maxRequestNumber && CPUs.get(chosenCPUIndex).getLoad() + process.getLoad() > maxThreshold; i++) {
                        chosenCPUIndex = random.nextInt(CPUs.size());
                        requests++;
                    }
                    CPU chosenCpu = CPUs.get(chosenCPUIndex);

                    if (chosenCpu!=cpu && chosenCpu.getLoad() <= maxThreshold && chosenCpu.getLoad() + process.getLoad() <= 100) {
                        chosenCpu.increaseLoadBy(process.getLoad());
                        chosenCpu.loadedProcesses.add(process);
                        cpu.processesToCome.remove();
                        migrations++;
                    } else {

                        if (cpu.getLoad() + process.getLoad() <= 100) {
                            cpu.increaseLoadBy(process.getLoad());
                            cpu.loadedProcesses.add(process);
                            cpu.processesToCome.remove();

                        } else {
                            for (Process process1 : cpu.processesToCome) {
                                process1.increaseArrivalTimeBy(1);
                            }
                        }

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

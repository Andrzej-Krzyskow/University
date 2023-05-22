import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class CPU {
    public Queue<Process> processesToCome;
    public Queue<Process> loadedProcesses;
    int load;

    public CPU(Queue<Process> processes) {
        this.processesToCome = processes;
        loadedProcesses = new LinkedList<>();
        load = 0;
    }

    public void increaseLoadBy(int load) {
        this.load += load;
    }

    public void decreaseLoadBy(int load) {
        this.load -= load;
    }

    public int getLoad() {
        return load;
    }

    @Override
    public String toString() {
        return "CPU{" +
                "load=" + load +
                '}';
    }

    @Override
    public CPU clone() {
        CPU cpu = new CPU(this.processesToCome);
        cpu.processesToCome = new LinkedList<>();
        for (Process process : this.processesToCome) {
            cpu.processesToCome.add(process.clone());
        }
        return cpu;
    }
}

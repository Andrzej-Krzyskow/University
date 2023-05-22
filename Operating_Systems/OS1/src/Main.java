import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {

        int tasksNumber = 10;
        int iterationsNumber = 1;
        int seed = 2;
        int quantumTime = 20;

        double averageFifo = 0;
        double averageSJF = 0;
        double averagePSJF = 0;
        double averageRR = 0;

        double maxFifo = 0;
        double maxSJF = 0;
        double maxPSJF = 0;
        double maxRR = 0;

        for (int i = 0; i < iterationsNumber; i++) {
            ArrayList<Task> tasks = TaskGenerator.generateTasks(tasksNumber, seed);


            FIFO fifo = new FIFO(tasks);
            averageFifo += fifo.getAverageWaitingTime();
            maxFifo = fifo.getMaxWaitingTime();


            tasks = TaskGenerator.generateTasks(tasksNumber, seed);
            SJF sjf = new SJF(tasks);
            averageSJF += sjf.getAverageWaitingTime();
            maxSJF = sjf.getMaxWaitingTime();


            tasks = TaskGenerator.generateTasks(tasksNumber, seed);
            PSJF psjf = new PSJF(tasks);
            averagePSJF += psjf.getAverageWaitingTime();
            maxPSJF = psjf.getMaxWaitingTime();


            tasks = TaskGenerator.generateTasks(tasksNumber, seed);
            RoundRobin rr = new RoundRobin(tasks, quantumTime);
            averageRR += rr.getAverageWaitingTime();
            maxRR = rr.getMaxWaitingTime();
        }

        System.out.println("FIFO: " + averageFifo / iterationsNumber + "   max waiting time: " + maxFifo);
        System.out.println("SJF:  " + averageSJF / iterationsNumber + "   max waiting time: " + maxSJF);
        System.out.println("PSJF: " + averagePSJF / iterationsNumber + "   max waiting time: " + maxPSJF);
        System.out.println("RR  : " + averageRR / iterationsNumber + "   max waiting time: " + maxRR);
    }
}

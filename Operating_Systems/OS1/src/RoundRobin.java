import java.util.ArrayList;

public class RoundRobin {
    ArrayList<Task> tasks;
    ArrayList<Task> processingQueue = new ArrayList<>();
    int globalTime = 0;
    int tasksNumber;
    int tasksQueued = 0;
    int tasksDone = 0;
    int currentTaskIndex = 0;
    int maxWaitingTime = 0;
    final int QUANTUM_TIME;

    public RoundRobin(ArrayList<Task> tasks, int timeInterval) {
        this.tasks = tasks;
        tasksNumber = tasks.size();
        this.QUANTUM_TIME = timeInterval;
        tasks.sort(new ArrivalTimeComparator());
        this.updateQueue();
    }

    private void updateQueue() {

        while (tasksQueued < tasksNumber && tasks.get(tasksQueued).getArrivalTime() == globalTime) {
            processingQueue.add(tasks.get(tasksQueued));
            tasksQueued++;
        }

    }


    public double getAverageWaitingTime() {
        int processingTime = QUANTUM_TIME;
        Task currentTask;
        if (processingQueue.size() != 0) {
            currentTask = processingQueue.get(0);
        } else {
            currentTask = new Task(-1, 0, 0);
        }

        while (tasksDone != tasksNumber) {
            this.updateQueue();
            if (processingQueue.size() == 0) {
                globalTime++;
                continue;
            }

            if (currentTask.getProcessingTime() > 0 && processingTime > 0) {

                currentTask.decreaseProcessingTime(1);
                processingTime--;

                for (Task task : processingQueue) {
                    if (!task.equals(currentTask)) {
                        task.increaseWaitingTime(1);
                    }
                }

            } else if (currentTask.getProcessingTime() == 0) {
                processingQueue.remove(currentTask);
                processingTime = QUANTUM_TIME;
                tasksDone++;
                globalTime--;

                if (currentTaskIndex < processingQueue.size()) {
                    currentTask = processingQueue.get(currentTaskIndex);
                } else {
                    currentTaskIndex = 0;
                }
            } else {
                processingTime = QUANTUM_TIME;
                processingQueue.remove(currentTask);
                processingQueue.add(currentTask);
                globalTime--;
                if (currentTaskIndex == processingQueue.size()) {
                    currentTaskIndex = 0;
                }
                currentTask = processingQueue.get(currentTaskIndex);
            }

            globalTime++;
        }

        return calculateAverageWaitingTime();
    }


    private double calculateAverageWaitingTime() {
        double sumOfWaitingTimes = 0;

        for (Task task : tasks) {
            sumOfWaitingTimes += task.getWaitingTime();
        }

        return sumOfWaitingTimes / tasksNumber;
    }

    public double getMaxWaitingTime() {

        for (Task task : tasks) {
            if (task.getWaitingTime() > maxWaitingTime) {
                maxWaitingTime = task.getWaitingTime();
            }
        }

        return maxWaitingTime;
    }


}

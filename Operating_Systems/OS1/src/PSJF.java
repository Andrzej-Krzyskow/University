import java.util.ArrayList;

public class PSJF {

    ArrayList<Task> tasks;
    ArrayList<Task> processingQueue = new ArrayList<>();
    int globalTime = 0;
    int tasksNumber;
    int tasksQueued = 0;
    int tasksDone = 0;
    int maxWaitingTime = 0;

    public PSJF(ArrayList<Task> tasks) {
        this.tasks = tasks;
        tasksNumber = tasks.size();
        tasks.sort(new ArrivalAndProcessingTimeComparator());
        this.updateQueue();
    }

    private void updateQueue() {

        while (tasksQueued < tasksNumber && tasks.get(tasksQueued).getArrivalTime() == globalTime) {
            processingQueue.add(tasks.get(tasksQueued));
            tasksQueued++;
        }
        processingQueue.sort(new ProcessingTimeComparator());

    }

    public double getAverageWaitingTime() {
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

            if (currentTask.getProcessingTime() > 0) {
                if (!processingQueue.get(0).equals(currentTask)) {
                    currentTask = processingQueue.get(0);
                }


                currentTask.decreaseProcessingTime(1);
                for (Task task : processingQueue) {
                    if (!task.equals(currentTask)) {
                        task.increaseWaitingTime(1);
                    }
                }

            } else {
                processingQueue.remove(currentTask);
                tasksDone++;
                globalTime--;

                if (processingQueue.size() > 0) {
                    currentTask = processingQueue.get(0);
                }
            }
            globalTime++;
        }

        return calculateAverageWaitingTime(tasksNumber);
    }


    private double calculateAverageWaitingTime(int tasksNumber) {
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

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Random;

public class ScheduleAlg {
    private final int[][] times;
    private int headMovements = 0;
    private int maxTrackPosition = 0;
    int greatestStarvationTime = 0;


    public ScheduleAlg(int tasksNum, int maxTrackPosition, int maxTaskArrivalTime, int seed) {
        Random rand = new Random(seed);
        times = new int[tasksNum][2];
        this.maxTrackPosition=maxTrackPosition;

        for (int i = 0; i < times.length; i++) {
            times[i][1] = rand.nextInt(maxTrackPosition) + 1;
            times[i][0] = rand.nextInt(maxTaskArrivalTime) + 1;
        }

    }

    public int getMaxTrackPosition() {
        return maxTrackPosition;
    }

    public ScheduleAlg(int[][] times) {
        this.times = times;
    }

    void addTasksToQueue(ArrayList<Task> tasks, ArrayList<Task> queue, int time) {
        for (Task task : tasks) {
            if (task.getArrivalTime() == time) {
                queue.add(task);
            }
        }
    }

    void addEdfTasksToQueue(ArrayList<Task> queue, ArrayList<Task> edfTasks) {
        for (Task task : queue) {
            if (task.getDeadline() != 0) {
                edfTasks.add(task);
            }
        }
        edfTasks.sort(Comparator.comparingInt(Task::getDeadline));
    }

    boolean run(ArrayList<Task> tasks, ArrayList<Task> queue, int time) {
        return tasks.get(tasks.size()-1).getArrivalTime()>=time ||queue.size()>0;
    }

    ArrayList<Task> generateTasksFrom() {
        ArrayList<Task> tasks = new ArrayList<>();
        for (int i = 0; i < times.length; i++) {

            if (i < times.length / 4) {
                tasks.add(new Task(i,0, times[i][1]));
            } else {
                tasks.add(new Task(i,times[i][0], times[i][1]));
            }
        }
        return tasks;
    }

    ArrayList<Task> generateTasksFrom(int realTimeTasksPercentage, int maxRealTime) {
        ArrayList<Task> tasks = this.generateTasksFrom();
        int realTimeTasks = tasks.size() * realTimeTasksPercentage / 100;
        Random random = new Random(realTimeTasks);

        while (realTimeTasks > 0) {
            Task tempTask = tasks.get(random.nextInt(0,tasks.size()));
            if (tempTask.getDeadline() == 0) {
                tempTask.setDeadline(random.nextInt(1, maxRealTime+1));
                realTimeTasks--;
            }
        }
        return tasks;
    }


    boolean removeFromQueueIfReached(Task activeTask, ArrayList<Task> queue, int headPosition) {
        if (activeTask.getTrack() == headPosition) {
            queue.remove(activeTask);
            return true;
        }
        return false;
    }

    void removeFromQueueIfReached( ArrayList<Task> queue, int headPosition) {
        queue.removeIf(task -> task.getTrack() == headPosition);
    }

    public void setHeadMovements(int headMovements) {
        this.headMovements = headMovements;
    }

    public int getHeadMovements() {
        return headMovements;
    }

    public void increaseWaitingTime(ArrayList<Task> queue, int headPosition) {
        for (Task task : queue) {
            if (task.getTrack() != headPosition) {
                task.increaseWaitingTime(1);
            }
        }
    }

    public void increaseWaitingTime(ArrayList<Task> queue, Task nextTask) {
        for (Task task : queue) {
            if (!task.equals(nextTask)) {
                task.increaseWaitingTime(1);
            }
        }
    }

    public void setGreatestStarvationTime(ArrayList<Task> queue) {
        for (Task task : queue) {
            if (task.getWaitingTime() > greatestStarvationTime) {
                greatestStarvationTime = task.getWaitingTime();
            }
        }
    }

    public int getGreatestStarvationTime() {
        return greatestStarvationTime;
    }
}
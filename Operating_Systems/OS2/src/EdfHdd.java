import java.util.ArrayList;
import java.util.Comparator;
import java.util.Random;

public class EdfHdd extends ScheduleAlg{
    private final int realTimeTaskPercentage;
    private int maxRealTime;

    public EdfHdd(int tasksNum, int maxTrackPosition, int maxTaskArrivalTime, int realTimeTaskPercentage, int maxRealTime, int seed) {
        super(tasksNum, maxTrackPosition, maxTaskArrivalTime, seed);
        this.realTimeTaskPercentage = realTimeTaskPercentage;
        this.maxRealTime = maxRealTime;
    }

    public EdfHdd(int[][] times) {
        super(times);
        this.realTimeTaskPercentage = new Random().nextInt(0, times.length);
    }

    public void run() {
        ArrayList<Task> tasks = generateTasksFrom(realTimeTaskPercentage, maxRealTime);
        ArrayList<Task> queue = new ArrayList<>(), edfTasks = new ArrayList<>();
        tasks.sort(Comparator.comparingInt(Task::getArrivalTime));

        int time = 0;
        int headMovements = 0;
        int headPosition = 0;
        addTasksToQueue(tasks, queue, time);
        addEdfTasksToQueue(queue,edfTasks);

        while (run(tasks, queue,time)) {
            if (queue.size() != 0) {
                headPosition++;
                headMovements++;

                while (edfTasks.size() > 0) {
                    Task nextEdfTask = edfTasks.get(0);

                    if (headPosition > nextEdfTask.getTrack()) {
                        headPosition--;
                        headMovements++;
                    } else if (headPosition < nextEdfTask.getTrack()) {
                        headPosition++;
                        headMovements++;
                    }

                    if (removeFromQueueIfReached(nextEdfTask, queue, headPosition)) {
                        edfTasks.remove(nextEdfTask);
                    }

                    increaseWaitingTime(queue, headPosition);
                    setGreatestStarvationTime(queue);

                }
                removeFromQueueIfReached(queue,headPosition);

                increaseWaitingTime(queue, headPosition);
                setGreatestStarvationTime(queue);

                if(headPosition == getMaxTrackPosition()){
                    headPosition=0;
                }

            }
            time++;
            addTasksToQueue(tasks, queue, time);
            addEdfTasksToQueue(queue, edfTasks);
        }

/*        while (run(tasks, queue, time)) {
            if (queue.size() != 0) {

                int shortestDeadline = Integer.MAX_VALUE;

                for (Task task : queue) {
                    if (task.getRealTime() != 0 && task.getRealTime() < shortestDeadline) {
                        nextEdfTask = task;
                        timeForEdf = true;
                    }
                }


                if (timeForEdf) {
                    while (!removeFromQueueIfReached(nextEdfTask, queue, headPosition)) {
                        if (headPosition > nextEdfTask.getTrack()) {
                            headPosition--;
                            headMovements++;
                        } else if (headPosition < nextEdfTask.getTrack()) {
                            headPosition++;
                            headMovements++;
                        }
                    }
                    timeForEdf = false;
                    nextEdfTask = null;

                } else {
                    headPosition++;
                    headMovements++;
                    removeFromQueueIfReached(queue, headPosition);
                }

                increaseWaitingTime(queue, headPosition);
                setGreatestStarvationTime(queue);

                if (headPosition-1 == getMaxTrackPosition()) {
                    headPosition = 0;
                }

            }
            time++;
            addTasksToQueue(tasks, queue, time);
        }*/

        setHeadMovements(headMovements);

    }
}

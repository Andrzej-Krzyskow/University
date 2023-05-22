import java.util.ArrayList;
import java.util.Comparator;

public class SstfHdd extends ScheduleAlg {

    public SstfHdd(int tasksNum, int maxTrackPosition, int maxTaskArrivalTime, int seed) {
        super(tasksNum, maxTrackPosition, maxTaskArrivalTime, seed);
    }

    public SstfHdd(int[][] times) {
        super(times);
    }

    public void run() {
        ArrayList<Task> tasks = generateTasksFrom(), queue = new ArrayList<>();
        tasks.sort(Comparator.comparingInt(Task::getArrivalTime));
        int time = 0;
        int headMovements = 0;
        int headPosition = 0;
        addTasksToQueue(tasks, queue, time);
        queue.sort(Comparator.comparingInt(Task::getTrack));
        Task nextTask = queue.get(0);


        while (run(tasks, queue, time)) {
            if (queue.size() != 0) {

                assert nextTask != null;
                if (headPosition > nextTask.getTrack()) {
                    headPosition--;
                    headMovements++;
                } else if (headPosition < nextTask.getTrack()) {
                    headPosition++;
                    headMovements++;
                }
                removeFromQueueIfReached(nextTask, queue, headPosition);

                increaseWaitingTime(queue, headPosition);
                setGreatestStarvationTime(queue);

                nextTask = getClosestToPrevTask(nextTask, queue);


            }
            time++;
            addTasksToQueue(tasks, queue, time);
            if (nextTask == null && queue.size() != 0) {
                nextTask = queue.get(0);
            }
        }
        setHeadMovements(headMovements);
    }

    private Task getClosestToPrevTask(Task prevTask, ArrayList<Task> queue) {
        if(queue.size() == 0){
            return null;
        }
        int prevTrack = prevTask.getTrack();
        Task closestTask = queue.get(0);
        for (Task task : queue) {
            if (Math.abs(prevTrack - task.getTrack()) < Math.abs(prevTrack - closestTask.getTrack())) {
                closestTask = task;
            }
        }
        return closestTask;
    }


}

import java.util.ArrayList;
import java.util.Comparator;

public class FifoHdd extends ScheduleAlg{


    public FifoHdd(int tasksNum, int maxTrackPosition, int maxTaskArrivalTime, int seed) {
        super(tasksNum, maxTrackPosition, maxTaskArrivalTime, seed);
    }
    public FifoHdd(int[][] times) {
        super(times);
    }

    public void run() {
        ArrayList<Task> tasks = generateTasksFrom(), queue = new ArrayList<>();
        tasks.sort(Comparator.comparingInt(Task::getArrivalTime));
        int time = 0;
        int headMovements= 0;
        int headPosition = 0;
        addTasksToQueue(tasks, queue, time);

        while (run(tasks, queue,time)) {
            if (queue.size() != 0) {
                Task nextTask = queue.get(0);
                if(headPosition> nextTask.getTrack()){
                    headPosition--;
                }else if(headPosition< nextTask.getTrack()){
                    headPosition++;
                }

                removeFromQueueIfReached(nextTask, queue,headPosition);

                increaseWaitingTime(queue, nextTask);
                setGreatestStarvationTime(queue);

                headMovements++;

            }
            time++;
            addTasksToQueue(tasks, queue, time);
        }
        setHeadMovements(headMovements);
    }
}
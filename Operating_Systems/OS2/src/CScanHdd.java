import java.util.ArrayList;
import java.util.Comparator;

public class CScanHdd extends ScheduleAlg{

    public CScanHdd(int tasksNum, int maxTrackPosition, int maxTaskArrivalTime, int seed) {
        super(tasksNum, maxTrackPosition, maxTaskArrivalTime, seed);
    }
    public CScanHdd(int[][] times) {
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
                headPosition++;
                headMovements++;

                removeFromQueueIfReached(queue,headPosition);

                increaseWaitingTime(queue, headPosition);
                setGreatestStarvationTime(queue);

                if(headPosition == getMaxTrackPosition()){
                    headPosition=0;
                }

            }
            time++;
            addTasksToQueue(tasks, queue, time);
        }

        setHeadMovements(headMovements);
    }
}

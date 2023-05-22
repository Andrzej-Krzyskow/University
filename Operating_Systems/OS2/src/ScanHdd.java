import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class ScanHdd extends ScheduleAlg{

    public ScanHdd(int tasksNum, int maxTrackPosition, int maxTaskArrivalTime, int seed) {
        super(tasksNum, maxTrackPosition, maxTaskArrivalTime, seed);
    }
    public ScanHdd(int[][] times) {
        super(times);
    }

    public void run() {
        ArrayList<Task> tasks = generateTasksFrom(), queue = new ArrayList<>();
        tasks.sort(Comparator.comparingInt(Task::getArrivalTime));
        int time = 0;
        int headMovements= 0;
        int headPosition = 0;
        Direction dir = Direction.RIGHT;
        addTasksToQueue(tasks, queue, time);

        while (run(tasks, queue,time)) {
            if (queue.size() != 0) {
                switch(dir){
                    case LEFT -> {headPosition--; }
                    case RIGHT -> {headPosition++;}
                }

                removeFromQueueIfReached(queue,headPosition);

                increaseWaitingTime(queue, headPosition);
                setGreatestStarvationTime(queue);

                headMovements++;
                if(headPosition == 0 ){
                    dir = Direction.RIGHT;
                }else if(headPosition == getMaxTrackPosition()){
                    dir = Direction.LEFT;
                }

            }
            time++;
            addTasksToQueue(tasks, queue, time);
        }
        setHeadMovements(headMovements);
    }
}

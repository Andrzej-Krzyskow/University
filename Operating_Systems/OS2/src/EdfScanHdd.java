import java.util.ArrayList;
import java.util.Comparator;
import java.util.Random;

public class EdfScanHdd extends ScheduleAlg {
    private final int realTimeTaskPercentage;
    private int maxRealTime;

    public EdfScanHdd(int tasksNum, int maxTrackPosition, int maxTaskArrivalTime, int realTimeTaskPercentage, int maxRealTime, int seed) {
        super(tasksNum, maxTrackPosition, maxTaskArrivalTime, seed);
        this.realTimeTaskPercentage = realTimeTaskPercentage;
        this.maxRealTime = maxRealTime;
    }

    public EdfScanHdd(int[][] times) {
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
        Task nextEdfTask;
        addTasksToQueue(tasks, queue, time);
        addEdfTasksToQueue(queue, edfTasks);

        while (run(tasks, queue, time)) {
            if (queue.size() != 0) {
                headPosition++;
                headMovements++;

                if (edfTasks.size() > 0) {
                    nextEdfTask = edfTasks.get(0);
                    Direction dir;
                    if (headPosition < nextEdfTask.getTrack()) {
                        dir = Direction.RIGHT;
                    } else {
                        dir = Direction.LEFT;
                    }

                    while (edfTasks.size() > 0) {

                        switch (dir) {
                            case LEFT -> headPosition--;
                            case RIGHT -> headPosition++;
                        }

                        if (removeFromQueueIfReached(nextEdfTask, edfTasks, headPosition)) {
                            int minimumDeadline = Integer.MAX_VALUE;

                            for (Task task : edfTasks) {
                                if (dir == Direction.RIGHT) {
                                    if (task.getTrack() > headPosition && task.getDeadline() < minimumDeadline) {
                                        minimumDeadline = task.getDeadline();
                                        nextEdfTask = task;
                                    }
                                } else {
                                    if (task.getTrack() < headPosition && task.getDeadline() < minimumDeadline) {
                                        minimumDeadline = task.getDeadline();
                                        nextEdfTask = task;
                                    }
                                }
                            }
                        }

                        increaseWaitingTime(edfTasks, headPosition);
                        setGreatestStarvationTime(edfTasks);

                        headMovements++;
                        if (headPosition == 0) {
                            dir = Direction.RIGHT;
                        } else if (headPosition == getMaxTrackPosition()) {
                            dir = Direction.LEFT;
                        }
                    }}


                    removeFromQueueIfReached(queue, headPosition);

                    increaseWaitingTime(queue, headPosition);
                    setGreatestStarvationTime(queue);

                    if (headPosition == getMaxTrackPosition()) {
                        headPosition = 0;
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



/*    private final int realTimeTaskPercentage;
    private int maxRealTime;

    public EdfScanHdd(int tasksNum, int maxTrackPosition, int maxTaskArrivalTime, int realTimeTaskPercentage, int maxRealTime, int seed) {
        super(tasksNum, maxTrackPosition, maxTaskArrivalTime, seed);
        this.realTimeTaskPercentage = realTimeTaskPercentage;
        this.maxRealTime = maxRealTime;
    }

    public EdfScanHdd(int[][] times) {
        super(times);
        this.realTimeTaskPercentage = new Random().nextInt(0, times.length);
    }

    public void run() {
        ArrayList<Task> tasks = generateTasksFrom(realTimeTaskPercentage, maxRealTime), queue = new ArrayList<>();
        tasks.sort(Comparator.comparingInt(Task::getArrivalTime));
        boolean timeForEdf = false;

        int time = 0;
        int headMovements = 0;
        int headPosition = 0;
        Direction dir = Direction.RIGHT;
        Task edfTask = null;
        addTasksToQueue(tasks, queue, time);


        while (run(tasks, queue, time)) {
            if (queue.size() != 0) {

                int shortestDeadline = Integer.MAX_VALUE;

                for (Task task : queue) {
                    if (task.getRealTime() != 0 && task.getRealTime() < shortestDeadline) {
                        edfTask = task;
                        timeForEdf = true;
                    }
                }


                if (timeForEdf) {

                    while (!removeFromQueueIfReached(edfTask, queue, headPosition)) {
                        switch(dir){
                            case LEFT -> {headPosition--; queue.sort(Comparator.comparingInt(Task::getTrack).reversed());}
                            case RIGHT -> {headPosition++; queue.sort(Comparator.comparingInt(Task::getTrack));}
                        }

                        if(headPosition == 0 ){
                            dir = Direction.RIGHT;
                        }else if(headPosition-1 == getMaxTrackPosition()){
                            dir = Direction.LEFT;
                        }
                    }
                    timeForEdf = false;
                    edfTask = null;

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
        }
        setHeadMovements(headMovements);

    }*/
}

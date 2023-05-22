import java.util.Objects;

public class Task{

    private final int id;
    private final int arrivalTime;

    private int processingTime;
    private int waitingTime=0;

    public Task(int id, int arrivalTime, int processingTime) {
        this.id = id;
        this.arrivalTime = arrivalTime;
        this.processingTime = processingTime;
    }

    public void increaseWaitingTime(int n) {
        waitingTime += n;
    }

    public void decreaseProcessingTime(int n) {
        processingTime-= n;
    }

    public boolean isDone() {

        return waitingTime >= processingTime;
    }


    public int getId() {
        return id;
    }

    public int getArrivalTime() {
        return arrivalTime;
    }

    public int getProcessingTime() {
        return processingTime;
    }

    public int getWaitingTime() {
        return waitingTime;
    }

    public void setProcessingTime(int processingTime) {
        this.processingTime = processingTime;
    }

    public void setWaitingTime(int waitingTime) {
        this.waitingTime = waitingTime;
    }

    @Override
    public String toString() {
        //return  "{"+arrivalTime+"," + processingTime+"},";
        return "Task{" +
                "id=" + id +
                ", arrivalTime=" + arrivalTime +
                ", processingTime=" + processingTime +
                ", waitingTime=" + waitingTime +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return id == task.id && arrivalTime == task.arrivalTime && processingTime == task.processingTime && waitingTime == task.waitingTime;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, arrivalTime, processingTime, waitingTime);
    }



}

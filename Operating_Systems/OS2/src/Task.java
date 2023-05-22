import java.util.Objects;

public class Task {
    private final int id;
    private final int arrivalTime;
    private final int track;
    private  int deadline = 0;
    private int waitingTime=0;

    public Task(int id, int arrivalTime, int track, int deadline) {
        this.arrivalTime = arrivalTime;
        this.track = track;
        this.id = id;
        this.deadline = deadline;
    }

    public Task(int id, int arrivalTime, int track) {
        this.arrivalTime = arrivalTime;
        this.track = track;
        this.id = id;
    }

    public void increaseWaitingTime(int n) {
        waitingTime += n;
    }

    public int getArrivalTime() {
        return arrivalTime;
    }

    public int getWaitingTime() {
        return waitingTime;
    }

    public int getDeadline() {
        return deadline;
    }

    public void setDeadline(int deadline) {
        this.deadline = deadline;
    }

    public int getTrack() {
        return track;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Task task = (Task) o;
        return id == task.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Task{" +
                "arrivalTime=" + arrivalTime +
                ", Track=" + track +
                '}';
    }

}
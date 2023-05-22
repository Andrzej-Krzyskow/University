import java.util.Comparator;

public class ArrivalAndProcessingTimeComparator implements Comparator<Task> {

    public ArrivalAndProcessingTimeComparator() {
    }

    @Override
    public int compare(Task o1, Task o2) {

        int arrivalTime = Integer.compare(o1.getArrivalTime(), o2.getArrivalTime());

        if (arrivalTime == 0) {
            return Integer.compare(o1.getProcessingTime(), o2.getProcessingTime());
        }

        return arrivalTime;

    }
}

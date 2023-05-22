import java.util.Comparator;

public class ArrivalTimeComparator implements Comparator<Task> {

    public ArrivalTimeComparator() {
    }

    @Override
    public int compare(Task o1, Task o2) {
        return Integer.compare(o1.getArrivalTime(), o2.getArrivalTime());
    }
}

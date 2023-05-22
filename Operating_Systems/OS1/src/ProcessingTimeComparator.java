import java.util.Comparator;

public class ProcessingTimeComparator implements Comparator<Task> {

    public ProcessingTimeComparator() {
    }

    @Override
    public int compare(Task o1, Task o2) {

        return Integer.compare(o1.getProcessingTime(), o2.getProcessingTime());
    }
}

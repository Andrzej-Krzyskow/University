import java.util.Iterator;
import java.util.NoSuchElementException;

public class FibbonaciGenerator implements Iterator<java.lang.Integer> {
    private int previous = 0, current = 1;


    @Override
    public boolean hasNext() {
        return previous<=current;
    }

    @Override
    public Integer next() {

        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        int next = previous + current;

        previous = current;
        current = next;

        return previous;
    }
}

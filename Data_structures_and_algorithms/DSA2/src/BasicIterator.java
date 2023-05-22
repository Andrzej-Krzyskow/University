import java.util.Iterator;

public class BasicIterator<T> implements Iterator<T> {
    private final Iterator<T> iterator;

    public BasicIterator(Iterator<T> iterator) {
        this.iterator = iterator;
    }

    @Override
    public boolean hasNext() {
        return iterator.hasNext();
    }

    @Override
    public T next() {
        return iterator.next();
    }
}

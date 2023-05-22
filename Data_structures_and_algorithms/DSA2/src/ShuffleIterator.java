import java.util.Iterator;

public class ShuffleIterator<T> implements Iterator<T> {
    private final Iterator<T> iterator1;
    private final Iterator<T> iterator2;
    boolean isFirstIteratorNow = true;

    public ShuffleIterator(Iterator<T> iterator1, Iterator<T> iterator2) {
        this.iterator1 = iterator1;
        this.iterator2 = iterator2;
    }

    @Override
    public boolean hasNext() {
        return iterator1.hasNext() || iterator2.hasNext();
    }

    @Override
    public T next() {

        if (isFirstIteratorNow && iterator1.hasNext()) {
            if (iterator2.hasNext()) {
                isFirstIteratorNow = false;
            }
            return iterator1.next();

        } else {
            isFirstIteratorNow = true;
            return iterator2.next();
        }
    }
}

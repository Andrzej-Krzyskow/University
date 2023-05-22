import java.util.Iterator;
import java.util.NoSuchElementException;

public class ArrayIterator<T> implements Iterator<T> {
    private final T[] array;
    private int pos = 0;
    boolean readyToRemove = false;

    public ArrayIterator(T[] anArray) {

        array = anArray;
    }

    public boolean hasNext() {

        return pos < array.length;
    }

    public T next() throws NoSuchElementException {


        if (hasNext()) {
            readyToRemove = true;
            return array[pos++];
        } else {
            throw new NoSuchElementException();
        }
    }

    public void remove() {

        if (readyToRemove) {
            array[pos - 1] = null;
            readyToRemove = false;
        } else {
            throw new IllegalStateException();
        }
    }
}
import java.util.Arrays;

public class SinkingStackEfficient<T> implements IStack<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private T[] data;
    private int beginIndex;
    private int endIndex;

    @SuppressWarnings("unchecked")
    public SinkingStackEfficient(int initialSize) {
        data = (T[]) new Object[initialSize + 1];
        beginIndex = 0;
        endIndex = 0;
    }

    public SinkingStackEfficient() {
        this(DEFAULT_CAPACITY);
    }

    @Override
    public boolean isEmpty() {
        return beginIndex == endIndex;
    }

    @Override
    public boolean isFull() {
        return beginIndex == (endIndex + 1) % data.length;
    }

    @Override
    public T pop() throws EmptyStackException {

        if (isEmpty()) {
            throw new EmptyStackException();
        }
        T returnedObject = data[(--endIndex + data.length) % data.length];
        endIndex = (endIndex + data.length) % data.length;

        return returnedObject;
    }

    @Override
    public void push(T elem) {

        if (isFull()) {
            beginIndex = (beginIndex + 1) % data.length;
        }

        data[endIndex++] = elem;
        endIndex %= data.length;
    }

    @Override
    public int size() {
        return (endIndex + data.length - beginIndex) % data.length;
    }

    @Override
    public T top() throws EmptyStackException {

        if (isEmpty()) {
            throw new EmptyStackException();
        }

        return data[(endIndex - 1)% data.length];
    }

    @Override
    public String toString() {
        return "SinkingStackEfficient{" +
                "data=" + Arrays.toString(data) +
                ", beginIndex=" + beginIndex +
                ", endIndex=" + endIndex +
                '}';
    }
}

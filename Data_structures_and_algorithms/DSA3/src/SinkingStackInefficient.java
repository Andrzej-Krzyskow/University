import java.util.Arrays;

public class SinkingStackInefficient<T> implements IStack<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private T[] data;
    private int topIndex;

    @SuppressWarnings("unchecked")
    public SinkingStackInefficient(int initialSize) {
        data = (T[]) new Object[initialSize];
        topIndex = 0;
    }

    public SinkingStackInefficient() {
        this(DEFAULT_CAPACITY);
    }

    @Override
    public boolean isEmpty() {
        return topIndex == 0;
    }

    @Override
    public boolean isFull() {
        return topIndex == data.length;
    }

    @Override
    public T pop() throws EmptyStackException {

        if (isEmpty()) {
            throw new EmptyStackException();
        }

        return data[--topIndex];
    }

    @Override
    public void push(T elem) {

        if (isFull()) {
            System.arraycopy(data, 1, data, 0, data.length - 1);
            topIndex--;
        }

        data[topIndex++] = elem;

    }

    @Override
    public int size() {
        return topIndex;
    }

    @Override
    public T top() throws EmptyStackException {

        if (isEmpty()) {
            throw new EmptyStackException();
        }

        return data[topIndex - 1];
    }

    @Override
    public String toString() {
        return "SinkingStackInefficient{" +
                "data=" + Arrays.toString(data) +
                ", topIndex=" + topIndex +
                '}';
    }

}

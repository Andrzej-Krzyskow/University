import java.util.Arrays;

public class VTS_Stack<T> implements IStack<T>{
    private static final int DEFAULT_CAPACITY = 10;
    private T[] data;
    private int topIndex;
    private int pointer;

    @SuppressWarnings("unchecked")
    public VTS_Stack(int initialSize) {
        data = (T[]) new Object[initialSize];
        topIndex = 0;
        pointer = 0;
    }

    public VTS_Stack() {
        this(DEFAULT_CAPACITY);
    }

    @Override
    public boolean isEmpty() {
        return topIndex == 0;
    }

    @Override
    public boolean isFull() {
        return topIndex==data.length;
    }

    @Override
    public T pop() throws EmptyStackException {

        if (isEmpty()) {
            throw new EmptyStackException();
        }

        pointer = --topIndex;
        return data[topIndex];
    }

    @Override
    public void push(T elem) throws FullStackException {

        if (isFull()) {
            throw new FullStackException();
        }

        data[topIndex] = elem;
        pointer = ++topIndex;
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

        pointer = topIndex - 1;
        return data[topIndex - 1];
    }

    public T peek() throws EmptyStackException {

        if (isEmpty()) {
            throw new EmptyStackException();
        }

        return data[pointer-1];
    }

    public boolean down() {

        if (pointer > 1) {
            pointer--;
            return true;
        }

        return false;
    }

    public void reverse() {
        VTS_Stack<T> reversed = new VTS_Stack<>();
        int tempTopIndex = topIndex, tempPointer = pointer;

        while (!this.isEmpty()) {
            reversed.push(this.pop());
        }

        topIndex = tempTopIndex;
        pointer = tempPointer;
        this.data = reversed.data;
    }

    @Override
    public String toString() {
        return "VTS_Stack{" +
                "data=" + Arrays.toString(data) +
                ", topIndex=" + topIndex +
                ", pointer=" + pointer +
                '}';
    }
}

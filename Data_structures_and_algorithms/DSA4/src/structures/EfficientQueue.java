package structures;

import java.util.Stack;

public class EfficientQueue<T> {
    Stack<T> firstStack = new Stack<>();
    Stack<T> secondStack = new Stack<>();

    public void enqueue(T e) {
        firstStack.push(e);
    }

    public T dequeue() {

        if (this.isEmpty()) {
            throw new RuntimeException("EMPTY QUEUE");
        }


        if (!secondStack.empty()) {
            return secondStack.pop();
        }

        while (!firstStack.empty()) {
            secondStack.push(firstStack.pop());
        }

        return secondStack.pop();
    }

    public boolean isEmpty() {

        return firstStack.isEmpty() && secondStack.isEmpty();
    }



}

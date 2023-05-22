import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;


public class TwoWayUnorderedListWithHeadAndTail<E> implements IList<E> {

    private Element head;
    private Element tail;
    private int size = 0;

    private class Element {
        E object;
        Element next = null;
        Element prev = null;

        public Element(E e) {
            this.object = e;
        }

    }

    public TwoWayUnorderedListWithHeadAndTail() {
        head = null;
        tail = null;
    }

    @Override
    public boolean add(E e) {
        Element newElement = new Element(e);

        if (head == null) {
            head = newElement;
        } else {
            tail.next = newElement;
            newElement.prev = tail;
        }
        tail = newElement;

        this.size++;
        return true;
    }

    @Override
    public void add(int index, E element) throws NoSuchElementException {

        if (index < 0 || index > size) {
            throw new NoSuchElementException();
        }

        Element newElement = new Element(element);


        if (index == 0 && size > 0) {
            head.prev = newElement;
            newElement.next = head;
            head = newElement;
        } else if (index == size) {
            this.add(element);
            return;
        } else {
            Element prevElement = getElement(index - 1);

            newElement.prev = prevElement;
            newElement.next = prevElement.next;
            prevElement.next.prev = newElement;
            prevElement.next = newElement;

        }

        size++;
    }

    @Override
    public void clear() {
        head = null;
        tail = null;
        size = 0;
    }

    @Override
    public boolean contains(E element) {

        return indexOf(element) >= 0;
    }

    @Override
    public int indexOf(E element) {
        int position = 0;
        Element currentElement = head;

        while (currentElement != null) {

            if (currentElement.object.equals(element)) {
                return position;
            }

            currentElement = currentElement.next;
            position++;
        }

        return -1;
    }

    @Override
    public E get(int index) {

        return getElement(index).object;
    }

    @Override
    public E set(int index, E element) {

        if (index < 0 || index >= size) {
            throw new NoSuchElementException();
        }

        Element currentElement = getElement(index);
        E returnedObject = currentElement.object;
        currentElement.object = element;

        return returnedObject;
    }

    @Override
    public boolean isEmpty() {

        return size == 0;
    }

    private void removeElement(Element currentElement) {
        if (currentElement == head) {
            if (size == 1) {
                this.clear();
                return;
            }
            head = head.next;
            head.prev = null;
        } else if (currentElement == tail) {
            tail = tail.prev;
            tail.next = null;
        } else {
            currentElement.next.prev = currentElement.prev;
            currentElement.prev.next = currentElement.next;
        }

        size--;
    }

    @Override
    public E remove(int index) throws NoSuchElementException {

        if (index < 0 || index >= size) {
            throw new NoSuchElementException();
        }

        Element currentElement = this.getElement(index);
        E returnedObject = currentElement.object;

        removeElement(currentElement);

        return returnedObject;
    }

    @Override
    public boolean remove(E e) {

        Element currentElement = head;

        while (currentElement != null) {

            if (currentElement.object.equals(e)) {

                removeElement(currentElement);
                return true;
            }

            currentElement = currentElement.next;
        }

        return false;
    }

    @Override
    public int size() {

        return size;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();

        for (E e : this) {
            stringBuilder.append("\n").append(e.toString());
        }

        return stringBuilder.toString();

    }

    public String toStringReverse() {
        ListIterator<E> iter = new InnerListIterator();

        while (iter.hasNext()) {
            iter.next();
        }
        StringBuilder stringBuilder = new StringBuilder();

        while (iter.hasPrevious()) {
            stringBuilder.append("\n").append(iter.previous().toString());
        }

        return stringBuilder.toString();
    }

    @Override
    public Iterator<E> iterator() {
        return new InnerIterator();
    }

    @Override
    public ListIterator<E> listIterator() {
        throw new UnsupportedOperationException();
    }

    private class InnerIterator implements Iterator<E> {

        Element pos;


        public InnerIterator() {
            pos = head;
        }

        @Override
        public boolean hasNext() {
            return pos != null;
        }

        @Override
        public E next() throws NoSuchElementException {

            Element currentObject = pos;

            if (currentObject == null || currentObject.object == null) {
                throw new NoSuchElementException();
            }

            pos = pos.next;


            return currentObject.object;
        }

    }

    private class InnerListIterator implements ListIterator<E> {
        Element current;
        Element previous;
        boolean wasNext;
        boolean wasPrevious;


        public InnerListIterator() {
            current = head;
            previous = head;
        }

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public boolean hasPrevious() {
            return previous != null;
        }

        @Override
        public E next() throws NoSuchElementException {

            if (!hasNext()) {
                throw new NoSuchElementException();
            }

            E returnedObject = current.object;
            previous = current;
            current = current.next;

            wasNext = true;
            wasPrevious = false;


            return returnedObject;
        }

        @Override
        public E previous() throws NoSuchElementException {

            if (!hasPrevious()) {
                throw new NoSuchElementException();
            }

            E returnedObject = previous.object;
            current = previous;
            previous = previous.prev;

            wasNext = false;
            wasPrevious = true;

            return returnedObject;
        }

        @Override
        public void set(E e) {
            if (wasNext) {
                previous.object = e;
                wasNext = false;
            }

            if (wasPrevious) {
                current.object = e;
                wasPrevious = false;
            }
        }

        @Override
        public void add(E e) {
            throw new UnsupportedOperationException();
        }

        @Override
        public int nextIndex() {
            throw new UnsupportedOperationException();
        }

        @Override
        public int previousIndex() {
            throw new UnsupportedOperationException();
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }

    }

    public void add(TwoWayUnorderedListWithHeadAndTail<E> other) {

        if (other == null || this == other || other.size == 0) {
            return;
        }

        other.head.prev = this.tail;
        if (this.tail != null) {
            this.tail.next = other.head;
        } else {
            this.head = other.head;
        }

        this.tail = other.tail;
        this.size += other.size;
        other.clear();
    }

    private Element getElement(int index) throws NoSuchElementException {
        Element currentElement = head;

        if (index >= this.size || index < 0) {
            throw new NoSuchElementException();
        }

        int pos = 0;
        while (pos != index) {
            currentElement = currentElement.next;
            pos++;
        }

        return currentElement;
    }

    public void removeDuplicates() {
        Element currentElement = head;

        while (currentElement != null && currentElement.next != null) {

            if (currentElement.object.equals(currentElement.next.object)) {

                if (currentElement.next.next == null) {
                    currentElement.next = null;
                    tail = currentElement;
                    break;
                }
                currentElement.next.next.prev = currentElement;
                currentElement.next = currentElement.next.next;

            } else {
                currentElement = currentElement.next;
            }

        }
    }
}

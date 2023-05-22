package structures;

import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

public class TwoWayCycledOrderedListWithSentinel<E extends Comparable<E>>  implements IList<E> {
    Element sentinel;
    int size;

    private class Element {
        E object;
        Element next = null;
        Element prev = null;

        public Element(E e) {
            object = e;
        }

        public Element(E e, Element next, Element prev) {
            object = e;
            this.next = next;
            this.prev = prev;
        }

        public void addAfter(Element elem) {
            elem.next = this.next;
            elem.prev = this;

            this.next.prev = elem;
            this.next = elem;
        }

        public void addBefore(Element element) {
            element.next = this;
            element.prev = this.prev;

            this.prev.next = element;
            this.prev = element;
        }

        public void remove() {

            if (this == sentinel) {
                return;
            }

            this.prev.next = this.next;
            this.next.prev = this.prev;

        }

    }

    private class InnerIterator implements Iterator<E> {
        Element currentElement;

        public InnerIterator() {
            currentElement = sentinel.next;
        }

        @Override
        public boolean hasNext() {
            return currentElement != sentinel;
        }

        @Override
        public E next() {

            if (!hasNext()) {
                throw new NoSuchElementException();
            }

            currentElement = currentElement.next;

            return currentElement.prev.object;
        }
    }

    private class InnerListIterator implements ListIterator<E> {
        Element nextElement;

        public InnerListIterator() {
            nextElement = sentinel.next;
        }

        @Override
        public boolean hasNext() {

            return nextElement != sentinel;
        }

        @Override
        public E next() {

            if (!hasNext()) {
                throw new NoSuchElementException();
            }

            E returnedValue = nextElement.object;
            nextElement = nextElement.next;

            return returnedValue;
        }

        @Override
        public void add(E arg0) {
            throw new UnsupportedOperationException();
        }

        @Override
        public boolean hasPrevious() {

            return nextElement.prev != sentinel;
        }

        @Override
        public E previous() {

            if (!hasPrevious()) {
                throw new NoSuchElementException();
            }

            nextElement = nextElement.prev;

            return nextElement.object;
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

        @Override
        public void set(E arg0) {
            throw new UnsupportedOperationException();
        }
    }

    public TwoWayCycledOrderedListWithSentinel() {
        sentinel = new Element(null);
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
        size = 0;
    }

    //@SuppressWarnings("unchecked")
    @Override
    public boolean add(E e) {
        Element currentElement = sentinel.next;

        if (size == 0) {
            sentinel.addAfter(new Element(e));
            size++;
            return true;
        }

        while (currentElement != sentinel) {
            if (e.compareTo(currentElement.object)<0) {
                currentElement.prev.addAfter(new Element(e));
                size++;
                return true;
            }
            currentElement = currentElement.next;
        }

        sentinel.prev.addAfter(new Element(e));
        size++;
        return true;
    }

    private Element getElement(int index) {

        if (index < 0 || index >= size) {
            throw new NoSuchElementException();
        }

        Element currentElement = sentinel.next;

        while (index > 0) {
            currentElement = currentElement.next;
            index--;
        }

        return currentElement;
    }

    private Element getElement(E obj) {

        Element currentElement = sentinel.next;

        while (currentElement != sentinel) {
            if (currentElement.object.equals(obj)) {
                return currentElement;
            }
            currentElement = currentElement.next;
        }

        return sentinel;
    }

    @Override
    public void add(int index, E element) {
        throw new UnsupportedOperationException();

    }

    @Override
    public void clear() {
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
        size = 0;
    }

    @Override
    public boolean contains(E element) {

        for (E e : this) {
            if (e.equals(element)) {
                return true;
            }
        }

        return false;
    }

    @Override
    public E get(int index) {

        return getElement(index).object;
    }

    @Override
    public E set(int index, E element) {
        throw new UnsupportedOperationException();
    }

    @Override
    public int indexOf(E element) {
        int index = 0;

        for (E e : this) {
            if (e.equals(element)) {
                return index;
            }
            index++;
        }

        return -1;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public Iterator<E> iterator() {
        return new InnerIterator();
    }

    @Override
    public ListIterator<E> listIterator() {
        return new InnerListIterator();
    }

    @Override
    public E remove(int index) {
        Element currentElement = getElement(index);
        currentElement.remove();
        size--;

        return currentElement.object;
    }

    @Override
    public boolean remove(E e) {
        Element currentElement = getElement(e);

        if (currentElement != sentinel) {
            currentElement.remove();
            size--;
            return true;
        }

        return false;
    }

    @Override
    public int size() {
        return size;
    }

    //@SuppressWarnings("unchecked")
    public void add(TwoWayCycledOrderedListWithSentinel<E> other) {
        if (this == other || other.size==0) {
            return;
        }

        Element thisCurrentElement = sentinel.next;
        Element otherCurrentElement = other.sentinel.next;

        while (thisCurrentElement!=sentinel && otherCurrentElement != other.sentinel) {

            if (thisCurrentElement.object.compareTo(otherCurrentElement.object) > 0) {

                thisCurrentElement.addBefore(new Element(otherCurrentElement.object));
                otherCurrentElement = otherCurrentElement.next;
            } else {
                thisCurrentElement =  thisCurrentElement.next;
            }
        }

        if (thisCurrentElement == sentinel) {
            while (otherCurrentElement != other.sentinel) {
                sentinel.addBefore(new Element(otherCurrentElement.object));
                otherCurrentElement = otherCurrentElement.next;
            }
        }

        other.clear();

/*
        Iterator<E> thisIterator = this.iterator();
        Iterator<E> otherIterator = other.iterator();
        boolean continueThis = thisIterator.hasNext();
        boolean continueOther= otherIterator.hasNext();
        E thisCurrentObject = null;
        E thisPreviousObject = null;
        E otherObject=null;

        if (continueThis) {
            thisCurrentObject = thisIterator.next();
            thisPreviousObject = thisCurrentObject;
        }

        if (continueOther) {
            otherObject = otherIterator.next();
        }

        while (continueThis && continueOther) {
            if (thisCurrentObject.compareTo(otherObject) < 0) {
                thisPreviousObject = thisCurrentObject;
                continueThis = thisIterator.hasNext();

                if (continueThis) {
                    thisCurrentObject = thisIterator.next();
                }
            } else {
                thisPreviousObject.
            }
        }
*/

    }

    //@SuppressWarnings({ "unchecked", "types" })
    public void removeAll(E e) {
        Element firstElement = getElement(e);

        while (firstElement != sentinel) {

            if (firstElement.object.equals(e)) {
                firstElement.remove();
                size--;
            }
            firstElement = firstElement.next;
        }
    }

    @Override
    public String toString() {

        StringBuilder stringBuilder = new StringBuilder();

        for (E e : this) {
            stringBuilder.append("\n").append(e.toString());
        }

        return stringBuilder.toString();
    }
}


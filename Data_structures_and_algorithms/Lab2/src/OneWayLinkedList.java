import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

class OneWayLinkedList<E> implements IList<E> {
    Element sentinel;

    private class Element {
        E object;
        Element nextElement = null;

        public Element(E e) {
            this.object = e;
        }

        public E getObject() {
            return object;
        }

        public void setObject(E object) {
            this.object = object;
        }

        public Element getNextElement() {
            return nextElement;
        }

        public void setNextElement(Element nextElement) {
            this.nextElement = nextElement;
        }
    }

    public OneWayLinkedList() {
        this.sentinel = new Element(null);
    }


    private Element getElement(int index) throws NoSuchElementException {

        if (index < 0) {
            throw new NoSuchElementException();
        }

        Element currentElement = sentinel.getNextElement();
        while (index > 0 && currentElement != null) {
            index--;
            currentElement = currentElement.getNextElement();
        }

        if (currentElement == null) {
            throw new NoSuchElementException();
        }

        return currentElement;
    }


    @Override
    public boolean add(E e) {
        Element newElement = new Element(e);


        Element lastElement = sentinel;
        while (lastElement.getNextElement() != null) {
            lastElement = lastElement.getNextElement();
        }

        lastElement.setNextElement(newElement);
        return true;
    }


    @Override
    public void add(int index, E element) throws NoSuchElementException {

        if (index < 0) {
            throw new NoSuchElementException();
        }

        Element previousElement = sentinel;
        Element newElement = new Element(element);

        if (index != 0) {
            previousElement = getElement(index - 1);
        }

        newElement.setNextElement(previousElement.getNextElement());
        previousElement.setNextElement(newElement);
    }


    @Override
    public void clear() {
        sentinel.setNextElement(null);
    }

    @Override
    public boolean contains(E element) {

        return indexOf(element) >= 0;
    }


    @Override
    public E get(int index) throws NoSuchElementException {

        return getElement(index).getObject();
    }


    @Override
    public E set(int index, E element) throws NoSuchElementException {

        if (index < 0 || sentinel.getNextElement()==null) {
            throw new NoSuchElementException();
        }

        E currentObject;
        if (index == 0) {
            currentObject = sentinel.getNextElement().getObject();
            sentinel.getNextElement().setObject(element);
        } else {
            Element previousElement = this.getElement(index - 1);
            currentObject = previousElement.getNextElement().getObject();

            previousElement.getNextElement().setObject(element);
        }


        return currentObject;
    }


    @Override
    public int indexOf(E element) {
        int position = 0;
        Element currentElement = sentinel.getNextElement();

        while (currentElement != null) {

            if (currentElement.getObject().equals(element)) {
                return position;
            }

            currentElement = currentElement.getNextElement();
            position++;
        }

        return -1;
    }


    @Override
    public boolean isEmpty() {
        return this.size() == 0;
    }


    @Override
    public E remove(int index) throws NoSuchElementException {

        if (index < 0 || sentinel.getNextElement() == null) {
            throw new NoSuchElementException();
        }

        E removedObject;
        if (index == 0) {
            removedObject = sentinel.getNextElement().getObject();
            sentinel.setNextElement(sentinel.getNextElement().getNextElement());
        } else {
            Element currentElement = getElement(index - 1);

            if (currentElement.getNextElement() == null) {
                throw new NoSuchElementException();
            }

            removedObject = currentElement.getNextElement().getObject();
            currentElement.setNextElement(currentElement.getNextElement().getNextElement());
        }


        return removedObject;
    }


    @Override
    public boolean remove(E e) {

        if (sentinel.getNextElement() == null) {
            return false;
        }

        if (sentinel.getNextElement().getObject().equals(e)) {
            sentinel.setNextElement(sentinel.getNextElement().getNextElement());
            return true;
        }

        Element currentElement = sentinel.getNextElement();
        while (currentElement.getNextElement() != null) {

            if (currentElement.getNextElement().getObject().equals(e)) {
                currentElement.setNextElement(currentElement.getNextElement().getNextElement());
                return true;
            }
            currentElement = currentElement.getNextElement();
        }

        return false;
    }

    public void removeeven() {

        int counter = 0;
        Element previousElement = sentinel;
        Element currentElement = sentinel.getNextElement();

        while (currentElement != null) {

            if (counter % 2 == 0) {

                if (currentElement.getNextElement() != null) {
                    previousElement.setNextElement(currentElement.getNextElement());
                } else {
                    break;
                }

            } else {
                previousElement = previousElement.getNextElement();
            }

            currentElement = currentElement.getNextElement();
            counter++;
        }
    }

    @Override
    public int size() {
        int counter = -1;
        Element currentElement = sentinel;

        while (currentElement != null) {
            counter++;
            currentElement = currentElement.getNextElement();
        }

        return counter;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();

        for (E e : this) {
            stringBuilder.append("\n").append(e.toString());
        }

        return stringBuilder.toString();
    }

    @Override
    public Iterator<E> iterator() {
        return new InnerIterator();
    }

    private class InnerIterator implements Iterator<E> {
        Element currentElement;

        public InnerIterator() {
            currentElement = sentinel.getNextElement();
        }

        @Override
        public boolean hasNext() {

            return currentElement != null;
        }

        @Override
        public E next() throws NoSuchElementException {

            if (currentElement == null) {
                throw new NoSuchElementException();
            }

            E object = currentElement.getObject();
            currentElement = currentElement.getNextElement();

            return object;
        }
    }

    @Override
    public ListIterator<E> listIterator() {
        throw new UnsupportedOperationException();
    }

}

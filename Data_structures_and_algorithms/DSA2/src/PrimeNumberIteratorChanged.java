import java.util.Iterator;
import java.util.NoSuchElementException;

public class PrimeNumberIteratorChanged implements Iterator<Integer> {
    private final int upperLimit;
    private PrimeNumberFilterIterator currentfilterIterator;
    int returnedValue;
    boolean bHasNext;

    public PrimeNumberIteratorChanged(int n) {
        upperLimit = n;
        currentfilterIterator = new PrimeNumberFilterIterator();
        returnedValue = currentfilterIterator.next();
        bHasNext = upperLimit > 1;
    }

    @Override
    public boolean hasNext() {

        return bHasNext;
    }

    @Override
    public Integer next() throws NoSuchElementException {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }

        int temp = returnedValue;

        currentfilterIterator = new PrimeNumberFilterIterator(currentfilterIterator, returnedValue);
        returnedValue = currentfilterIterator.next();

        if (returnedValue > upperLimit) {
            bHasNext = false;
        }

        return temp;
    }

}

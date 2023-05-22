import java.util.Iterator;

public class PrimeNumberFilterIterator implements Iterator<Integer> {
    private Iterator<Integer> iterator;
    private int valueToOmit;
    private int currentValue = 1;

    public PrimeNumberFilterIterator() {}

    public PrimeNumberFilterIterator(PrimeNumberFilterIterator filterIterator, int predicate) {
        iterator = filterIterator;
        valueToOmit = predicate;
    }


    @Override
    public boolean hasNext() {
        return true;
    }

    @Override
    public Integer next() {

        if (iterator == null) {
            return ++currentValue;

        } else {
            int temp = iterator.next();

            while (temp % valueToOmit == 0 ) {
                temp = iterator.next();
            }

            return temp;
        }

    }
}

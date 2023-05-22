import java.util.Iterator;
import java.util.NoSuchElementException;

public class PrimeNumberIteratorDecreasing<T> implements Iterator<java.lang.Integer> {
    private int upperLimit;

    public PrimeNumberIteratorDecreasing(int upperLimit) {
        this.upperLimit = upperLimit;
    }

    private boolean isPrime(int n) {

        for (int i = 2; i <= Math.sqrt(n); i++) {
            if (n % i == 0) {
                return false;
            }
        }

        return true;
    }

    @Override
    public boolean hasNext() {
        return upperLimit > 1;
    }

    @Override
    public java.lang.Integer next()throws NoSuchElementException {

        if (!hasNext()) {
            throw new NoSuchElementException();
        }

        while (!isPrime(upperLimit)) {
            upperLimit--;
        }

        return upperLimit--;
    }
}

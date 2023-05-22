import java.util.Iterator;
import java.util.NoSuchElementException;

public class PrimeNumberIteratorIncreasing<T> implements Iterator<Integer> {
    private final int upperLimit;
    private int currentPrime=2;

    public PrimeNumberIteratorIncreasing(int upperLimit) {
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

        for (int i = currentPrime; i <= upperLimit; i++) {
            if (isPrime(i)) {
                return true;
            }
        }

        return false;
    }

    @Override
    public Integer next() throws NoSuchElementException {

        if (!hasNext()) {
            throw new NoSuchElementException();
        }

        int returnedPrime = currentPrime;
        int i = currentPrime+1;

        while (!isPrime(i)) {
            i++;
        }
        currentPrime = i;

        return returnedPrime;
    }
}

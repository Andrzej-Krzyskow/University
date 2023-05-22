import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;

public class MainClass {

    public static void main(String[] args) {
        ArrayList<Integer> arrayList1 = new ArrayList<>(1);
        ArrayList<Integer> arrayList2 = new ArrayList<>(1);
        for (int i = 1; i < 6; i++) {
            arrayList1.add(i);
            arrayList2.add(i + 10);
            arrayList1.add(i + 20);
        }
        arrayList1.sort(Comparator.naturalOrder());
        arrayList2.sort(Comparator.naturalOrder());

        //task 1
        System.out.println("TASK 1");
        Iterator<Integer> iterator0 = arrayList1.iterator();
        BasicIterator<Integer> iterator1 = new BasicIterator<>(iterator0);
        while (iterator1.hasNext()) {
            System.out.println(iterator1.next());
        }

        //task 2
        System.out.println("TASK 2");
        FibbonaciGenerator fibbonaciGenerator = new FibbonaciGenerator();
        int k = 0;

        while (fibbonaciGenerator.hasNext() && k < 200) {
            System.out.println(fibbonaciGenerator.next());
            k++;
        }


        //task 3
        System.out.println("TASK 3");

        System.out.println(arrayList1);
        System.out.println(arrayList2);
        Iterator<Integer> iterator2 = arrayList1.iterator();
        Iterator<Integer> iterator3 = arrayList2.iterator();

        ShuffleIterator<Integer> shuffleIterator = new ShuffleIterator<>(iterator2, iterator3);

        while (shuffleIterator.hasNext()) {
            System.out.println(shuffleIterator.next());
        }

        //task 4
        System.out.println("TASK 4");
        int p =10;
        PrimeNumberIteratorDecreasing<Integer> primeNumberIteratorDecreasing = new PrimeNumberIteratorDecreasing<>(p);
        PrimeNumberIteratorIncreasing<Integer> primeNumberIteratorIncreasing = new PrimeNumberIteratorIncreasing<>(p);

        System.out.println("Decreasing:");
        while (primeNumberIteratorDecreasing.hasNext()) {
            System.out.println(primeNumberIteratorDecreasing.next());
        }

        System.out.println("Increasing:");
        while (primeNumberIteratorIncreasing.hasNext()) {
            System.out.println(primeNumberIteratorIncreasing.next());
        }

        //task 5
        System.out.println("TASK 5");
        Integer[] array = new Integer[]{1, 2, 3, 4, 5};

        ArrayIterator<Integer> arrayIterator1 = new ArrayIterator<>(array);
        ArrayIterator<Integer> arrayIterator2 = new ArrayIterator<>(array);

        while (arrayIterator1.hasNext()) {
            System.out.println(arrayIterator1.next());
            System.out.println(arrayIterator2.next());
            arrayIterator1.remove();
            arrayIterator2.remove();
        }

        System.out.println(Arrays.toString(array));


        //task 4 changed
        System.out.println("TASK 4 CHANGED");
        p=49;

        Iterator<Integer> primeNumberIteratorChanged = new PrimeNumberIteratorChanged(p);

        while (primeNumberIteratorChanged.hasNext()) {
            System.out.println(primeNumberIteratorChanged.next());
        }

    }
}

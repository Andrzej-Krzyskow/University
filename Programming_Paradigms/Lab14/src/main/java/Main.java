import io.reactivex.rxjava3.core.*;
import io.reactivex.rxjava3.schedulers.Schedulers;

import java.math.BigInteger;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Main {

    public static Flowable<Integer> createAccount() {

        Random random = new Random();

        return Flowable.range(1, 5)
                .map(v -> random.nextInt(1000) - 500)    // no. between -500 and 500
                .startWithItem(0);
    }

    public static void factorial(Observable<Long> observable){
        observable.map(BigInteger::valueOf)
                .scan((x,y)->x.multiply(y))
                .subscribe(System.out::println);
    }

    public static void main(String[] args) throws InterruptedException {


        // In ex. 1 and 2 I suppose that bank accounts can have deficit/debt so negative values are possible
        // ex. 1.

        Integer[] arr1 = {0, 150, 200, -300, 400, -500};
        Integer[] arr2 = {0, -100, -250, 100, 800, -150};

        Flowable<Integer> account1 = Flowable.fromArray(arr1);
        Flowable<Integer> account2 = Flowable.fromArray(arr2);

        // test from arrays
        System.out.println("-------------First arrays-------------------");
        account1.scan((x, y) -> x + y)
                .zipWith(account2.scan((x, y) -> x + y), (x, y) -> x + y)
                .subscribe(System.out::println);

        // test from random Flowable
        System.out.println("-------------First random-------------------");
        account1 = createAccount();
        account2 = createAccount();

        account1.scan((x, y) -> x + y)
                .zipWith(account2.scan((x, y) -> x + y), (x, y) -> x + y)
                .subscribe(System.out::println);


        // ex. 2

        // test from random Flowable
        System.out.println("-------------Second random-------------------");
        account1.zipWith(account2, (x, y) -> x + y)
                .subscribe(System.out::println);


        // test from arrays
        System.out.println("-------------Second arrays-------------------");
        account1 = Flowable.fromArray(arr1);
        account2 = Flowable.fromArray(arr2);
        account1.zipWith(account2, (x, y) -> x + y)
                .subscribe(System.out::println);


        // ex. 3
        System.out.println("-------------Third-------------------");

        Observable<Long> naturalNumbers = Observable.interval(20, TimeUnit.SECONDS).skip(1);

        factorial(naturalNumbers);

        // test case
        Observable<Long> naturalNumbersTest = Observable.interval(1, TimeUnit.MILLISECONDS).skip(1);

        factorial(naturalNumbersTest);

        TimeUnit.MILLISECONDS.sleep(25);
    }


}
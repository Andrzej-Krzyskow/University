import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class TEst {
    public static void main(String[] args) {
        Flowable.just("Hello world").subscribe(System.out::println);

        Flowable flow = Flowable.range(1, 6)
                .map(v -> v * v)
                .filter(v -> v % 3 == 0);

        Observable<Integer> flowa = Observable.range(1, 5)
                .map(v -> v * v)
                .filter(v -> v % 3 == 0);

        flow.subscribe(s -> System.out.print(s + " "));
        System.out.println();
        flowa.subscribe(System.out::println);

        Flowable.range(1, 100)
                .concatMapEager(v ->
                        Flowable.just(v)
                                .subscribeOn(Schedulers.computation())
                                .map(w -> w * w)
                )
                .blockingSubscribe(System.out::println);
    }
}

import java.util.concurrent.*;

public class Testing {

    public static Runnable task = () -> {
        String threadName = Thread.currentThread().getName();
        System.out.println("Hello " + threadName);
    };


    public static Callable<Integer> task2 = () -> {
        try {
            TimeUnit.SECONDS.sleep(1);
            return 123;
        }
        catch (InterruptedException e) {
            throw new IllegalStateException("task interrupted", e);
        }
    };

    public static ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(5);


    public static void main(String[] args) throws ExecutionException, InterruptedException {

        task.run();
        Thread thread = new Thread(task);
        thread.start();
        System.out.println("Done!");

        executor.setCorePoolSize(3);

        executor.submit(() -> {
            String threadName = Thread.currentThread().getName();
            System.out.println("Hello " + threadName);
        });
        System.out.println(executor.getPoolSize());
        executor.submit(() -> {
            String threadName = Thread.currentThread().getName();
            System.out.println("Hello " + threadName);
        });
        System.out.println(executor.getPoolSize());
        executor.submit(() -> {
            String threadName = Thread.currentThread().getName();
            System.out.println("Hello " + threadName);
        });
        System.out.println(executor.getPoolSize());
        executor.submit(() -> {
            String threadName = Thread.currentThread().getName();
            System.out.println("Hello " + threadName);
        });
        System.out.println(executor.getPoolSize());

        Future<Integer> future = executor.submit(task2);
        System.out.println("future done? " + future.isDone());
        Integer result = future.get();
        System.out.println("future done? " + future.isDone());
        System.out.print("result: " + result);


        executor.shutdown();

        ScheduledExecutorService executorsch = Executors.newScheduledThreadPool(1);
        Runnable task = () -> System.out.println("Scheduling: " + 420);
        ScheduledFuture<?> future1 = executorsch.schedule(task, 3, TimeUnit.SECONDS);
        TimeUnit.MILLISECONDS.sleep(1000);
        long remainingDelay = future1.getDelay(TimeUnit.MILLISECONDS);
        System.out.printf("Remaining Delay: %s \n", remainingDelay);
        remainingDelay = future1.getDelay(TimeUnit.MILLISECONDS);
        System.out.printf("Remaining Delay: %s", remainingDelay);
        executorsch.shutdown();
    }
}
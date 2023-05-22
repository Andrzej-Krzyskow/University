import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadLocalRandom;

public class Main {

    static Integer accountBalance = 100;
    static int amountOfMoney = 10;
    static int noOfOperations = 5;

    public static void main(String[] args) throws InterruptedException {

        /*
        ex. 1
        Code will produce:
            Hello main
            Done!
            Hello Thread-0

       Each program has one main thread which is provided by default by JVM for program execution,
       thus the name of the first thread is "main" and it's printed first as it is firstly created and doesn't take much time to execute.

       Basically, the order of execution of the thread is unpredictable, as JVM manages them.
       However, in this simple example, we always get the same output, as the main thread has "simpler" task to execute, which won't take
       much time. That's why after "Hello main" we get "Done!" as it is faster to process than earlier created "thread". If the main thread
       took more time (here we could add "Thread.sleep(2000)" after "thread.start();"), then we could observe different order, that is:
            Hello main
            Hello Thread-0
            Done!
       The above output can be also observed in a debugger.
     */


        // ex. 2
        class Outcome extends Thread {

            @Override
            public void run() {
                for (int i = 0; i < noOfOperations; i++) {
                    int afterOutcome = accountBalance - amountOfMoney;

                    try {
                        Thread.sleep(60);   // simulating time
                    } catch (InterruptedException e) {
                        System.err.println(e.getMessage());
                    }

                    accountBalance = afterOutcome;
                    System.out.println("after outcome = " + accountBalance);
                }

            }
        }

        class Income extends Thread {

            @Override
            public void run() {
                for (int i = 0; i < noOfOperations; i++) {
                    int afterIncome = accountBalance + amountOfMoney;
                    try {
                        Thread.sleep(100);  // simulating time
                    } catch (InterruptedException e) {
                        System.err.println(e.getMessage());
                    }
                    accountBalance = afterIncome;
                    System.out.println("after income = " + accountBalance);
                }
            }
        }

        Income income = new Income();
        Outcome outcome = new Outcome();
        int initialBalance = accountBalance;

        System.out.println("Before operations: " + initialBalance);

        income.start();
        outcome.start();


        try {
            Thread.sleep(1500);
            income.join();
            outcome.join();
        } catch (InterruptedException e) {
            System.err.println(e.getMessage());
        }

        System.out.println("Should be: " + initialBalance);
        System.out.println("In real is: " + accountBalance);

        /*

        In theory, after adding +10 funds to the account and removing -10 , the balance should be the same
        as at the beginning that is 100. However, the actual balance differ almost every time. During execution of the program,
        "outcome" and "income" have different execution times, which results that during evaluation of one task, the second one could
        already finish and save the result, but that result will be wrongly overridden. As the outcome, we get most often incorrect value.
        To prevent this from happening, we should synchronize accountBalance or desirably instance of class e.g. AccountBalance.

        */

        // ex. 3
        class OutcomeSynchronous extends Thread {

            @Override
            public void run() {
                synchronized (accountBalance) {     // <----------- we block the access to accountBalance by other threads for execution time, desirably we shouldn't lock value-based class
                    for (int i = 0; i < noOfOperations; i++) {
                        int afterOutcome = accountBalance - amountOfMoney;
                        try {
                            Thread.sleep(60);   // simulating time
                        } catch (InterruptedException e) {
                            System.err.println(e.getMessage());
                        }
                        accountBalance = afterOutcome;
                        System.out.println("after outcome = " + accountBalance);
                    }
                }
            }
        }

        class IncomeSynchronous extends Thread {

            @Override
            public void run() {
                synchronized (accountBalance) {// <----------- we block the access to accountBalance by other threads for execution time, desirably we shouldn't lock value-based class
                    for (int i = 0; i < noOfOperations; i++) {
                        int afterIncome = accountBalance + amountOfMoney;
                        try {
                            Thread.sleep(100);  // simulating time
                        } catch (InterruptedException e) {
                            System.err.println(e.getMessage());
                        }
                        accountBalance = afterIncome;
                        System.out.println("after income = " + accountBalance);
                    }
                }
            }
        }

        // ex. 4
        class Fork{}

        class Philosopher extends Thread {

            private final Fork leftFork;
            private final Fork rightFork;
            private final int id;

            public Philosopher(int id, Fork leftFork, Fork rightFork) {
                this.id = id;
                this.leftFork = leftFork;
                this.rightFork = rightFork;
            }

            @Override
            public void run() {
                try {
                    while (true) {
                        printState("is thinking");

                        synchronized (leftFork) {
                            printState("took the left fork");
                            synchronized (rightFork) {
                                printState("took the right fork and is eating");
                                printState("put down the right fork");
                            }

                            printState("put down the left fork and is thinking again");
                        }
                    }
                } catch (InterruptedException e) {
                    System.err.println(e.getMessage());
                }
            }

            private void printState(String state) throws InterruptedException {
                System.out.println("Philosopher " + id + " " + state);
                Thread.sleep(ThreadLocalRandom.current().nextInt(0, 2000));
            }

        }

        int noOfPhilosophers = 5;
        ExecutorService executorService = Executors.newFixedThreadPool(noOfPhilosophers);
        Fork[] forks = new Fork[noOfPhilosophers];

        for (int i = 0; i < noOfPhilosophers; i++) {
            forks[i] = new Fork();
        }

        for (int i = 0; i < noOfPhilosophers; i++) {
            Fork leftFork = forks[i];
            Fork rightFork;
            if (i < noOfPhilosophers - 1) {
                rightFork = forks[i + 1];
            } else {
                rightFork = forks[0];
            }

            // In order to prevent deadlock, when all philosophers take their left fork,
            // we order the last philosopher to take the right fork first
            if (i != noOfPhilosophers - 1) {
                executorService.submit(new Philosopher(i, leftFork, rightFork));
            } else {
                executorService.submit(new Philosopher(i, rightFork, leftFork));
            }

        }


        // ex. 5
        /*
        Executor, which provides single thread executions seems to be the best choice for this task,
        as we do not need any concurrent calculations, we just print a number and increment it by 1 fo the next print,
        thus we also do not have to synchronize any variable
        */

        class Timer extends Thread {
            private int counter = 0;

            @Override
            public void run() {

                System.out.println(counter);
                counter++;

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    System.err.println(e.getMessage());
                }
            }

        }

        ExecutorService executor = Executors.newSingleThreadExecutor();
        Timer timer = new Timer();

        // move this for
        for (int i = 0; i < 30; i++) {
            executor.submit(timer);
        }

        executor.shutdown();


    }
}
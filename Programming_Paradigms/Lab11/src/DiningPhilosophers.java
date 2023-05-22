import java.util.concurrent.Semaphore;
class DiningPhilosophers {
    private Semaphore[] forks;

    public DiningPhilosophers() {
        forks = new Semaphore[]{
                new Semaphore(1),
                new Semaphore(1),
                new Semaphore(1),
                new Semaphore(1),
                new Semaphore(1)
        };
    }

    private void eat(int philosopherNumber) {
        int leftFork = philosopherNumber;
        int rightFork = (philosopherNumber + 1) % 5;
        int firstFork = philosopherNumber;
        int secondFork = (philosopherNumber + 4) % 5;
        try {
            forks[firstFork].acquire();
            System.out.println("Philosopher " + philosopherNumber + " picked up left fork");
            forks[secondFork].acquire();
            System.out.println("Philosopher " + philosopherNumber + " picked up right fork and start eating");
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            forks[firstFork].release();
            forks[secondFork].release();
            System.out.println("Philosopher " + philosopherNumber + " finished eating and released both forks");
        }
    }

    public static void main(String[] args) {
        DiningPhilosophers dp = new DiningPhilosophers();
        for (int i = 0; i < 5; i++) {
            int finalI = i;
            new Thread(() -> {
                while (true) {
                    dp.eat(finalI);
                }
            }).start();
        }
    }
}

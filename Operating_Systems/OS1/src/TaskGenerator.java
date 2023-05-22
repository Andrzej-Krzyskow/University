import java.util.ArrayList;
import java.util.Random;

public class TaskGenerator {

    public TaskGenerator() {
    }

    public static ArrayList<Task> generateTasks(int n) {
        return generateTasks(n, 0);
    }


    public static ArrayList<Task> generateTasks(int n, long seed) {
        ArrayList<Task> tasks = new ArrayList<>();
        int arrivalTimeUpperLimit = 40;
        int processingTimeUpperLimit = 40;

        if (n < 1) {
            System.out.println("Improper number of elements");
            return tasks;
        }

        int counter = 0;
        Random random;
        if (seed == 0) {
            random = new Random();
        } else if (seed == -1) {
            int[] arrivalTime = {1, 2, 3, 4, 5};
            int[] processingTime = {1, 2, 3, 4, 5};

            for (int i = 0; i < arrivalTime.length; i++) {
                tasks.add(new Task(i, arrivalTime[i], processingTime[i]));
            }

            return tasks;
        } else {
            random = new Random(seed);
        }
/*
        if (n < 4) {
            tasks.add(new Task(counter, 0, random.nextInt(1, processingTimeUpperLimit)));
            counter++;
        }*/

        while (counter < (n / 4)) {
            tasks.add(new Task(counter, 0, random.nextInt(1, processingTimeUpperLimit)));
            counter++;
        }

        while (counter < n) {
            tasks.add(new Task(counter, random.nextInt(1, arrivalTimeUpperLimit), random.nextInt(1, processingTimeUpperLimit)));
            counter++;
        }


        return tasks;
    }
}

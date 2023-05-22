import java.util.Random;

public class Rand {

    public static int calculatePageFaults(Process[] processes, int frameNum) {
        int sumOfFaults = 0;
        int framesAllocated = 0;
        Random random = new Random(frameNum);

        for (int i = 0; i < processes.length; i++) {
            int newlyAllocatedFrames = random.nextInt(1, frameNum - framesAllocated - processes.length + i + 2);
            framesAllocated += newlyAllocatedFrames;
            sumOfFaults += OPT.calculatePageFaults(processes[i].getCalls(), newlyAllocatedFrames);
        }
        return sumOfFaults;
    }

}

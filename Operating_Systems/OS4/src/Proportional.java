public class Proportional {
    public static int calculatePageFaults(Process[] processes, int frameNum) {
        int sumOfFaults = 0;
        int sumOfSizes = 0;

        for (Process process : processes) {
            sumOfSizes += process.getSize();
        }

        for (Process process : processes) {
            sumOfFaults += OPT.calculatePageFaults(process.getCalls(), Math.max(1,(int) ((double)(process.getSize()) / sumOfSizes * frameNum)));
        }
        return sumOfFaults;
    }
}

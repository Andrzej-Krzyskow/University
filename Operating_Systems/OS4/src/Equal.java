public class Equal {
 public static int calculatePageFaults(Process[] processes, int frameNum) {
     int framesPerProcess = frameNum / processes.length;
    int sumOfFaults=0;
     for (Process process : processes) {
         sumOfFaults += OPT.calculatePageFaults(process.getCalls(), framesPerProcess);
     }
     return sumOfFaults;
 }
}

import java.util.*;

public class PageFaultFrequency {

    public static int calculatePageFaults(Process[] processes, int frameNum, int timeWindow, int max, int min) {
        class Page {
            final int value;
            final boolean causedPF;

            public Page(int value, boolean causedPF) {
                this.value = value;
                this.causedPF = causedPF;
            }

            public int getValue() {
                return value;
            }

            public boolean isCausedPF() {
                return causedPF;
            }
        }
        int sizeOfProcess = processes[0].getCalls().length;
        int sumOfFaults = 0;
        int filledFrames = 0;
        ArrayList<ArrayList<Integer>> setsOfPages = new ArrayList<>(); //set of pages for EVERY PROCESS
        ArrayList<Double> PFFrequency = new ArrayList<>();
        ArrayList<Queue<Page>> timeWindowMem = new ArrayList<>();


        for (int i = 0; i < processes.length; i++) {
            setsOfPages.add(new ArrayList<>());
            timeWindowMem.add(new LinkedList<>());
            PFFrequency.add(0.0);
        }

        for (int i = 0; i < sizeOfProcess; i++) { //number of call
            for (int j = 0; j < processes.length; j++) { //number of process
                int page = processes[j].getCalls()[i];
                Page newPage;

                PFFrequency.set(j, processes[j].getCausedPFNum() * 1.0 / timeWindow);

                if (i >= timeWindow) {
                    Page removedPage = timeWindowMem.get(j).peek();
                    //assert removedPage != null;
                    if (removedPage.causedPF) {
                        processes[j].decrementPF();
                    }
                }

                if (setsOfPages.get(j).contains(page)) {
                    newPage = new Page(page, false);
                } else {
                    newPage = new Page(page, true);
                    sumOfFaults++;

                    if (PFFrequency.get(j) <= max * 0.01) {
                        if (i != 0) {
                            setsOfPages.get(j).remove(0);
                        }
                        setsOfPages.get(j).add(newPage.getValue());
                    } else {
                        setsOfPages.get(j).add(newPage.getValue());
                        filledFrames++;
                        if (frameNum - filledFrames <= 0) {
                            Double minimum = Collections.min(PFFrequency);
                            int index = PFFrequency.indexOf(minimum);
                            if (setsOfPages.get(index).size() > 0) {
                                setsOfPages.get(index).remove(0);
                                filledFrames--;
                            }
                        }
                    }

                }

                timeWindowMem.get(j).add(newPage);
                processes[j].incrementPF();

            }
        }

        return sumOfFaults;
    }
}

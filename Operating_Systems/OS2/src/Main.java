import java.text.NumberFormat;
import java.util.Locale;

public class Main {
    public static void main(String[] args) {

        double[] sums1 = new double[12];
        final int TASK_NUM = 200;
        final int MAX_TRACK_POS = 100;
        final int MAX_ARRIVAL_TIME = 7000;
        final int ITERATIONS = 3;
        final int REAL_TIME_TASK_PERCENTAGE = 10;
        final int MAX_REAL_TIME = 50;

        for (int i = 0; i < ITERATIONS; i++) {

            FifoHdd fifoHdd = new FifoHdd(TASK_NUM, MAX_TRACK_POS, MAX_ARRIVAL_TIME, i);
            ScanHdd scanHdd = new ScanHdd(TASK_NUM, MAX_TRACK_POS, MAX_ARRIVAL_TIME, i);
            CScanHdd cScanHdd = new CScanHdd(TASK_NUM, MAX_TRACK_POS, MAX_ARRIVAL_TIME, i);
            SstfHdd sstfHdd = new SstfHdd(TASK_NUM, MAX_TRACK_POS, MAX_ARRIVAL_TIME, i);
            EdfHdd edfHdd = new EdfHdd(TASK_NUM, MAX_TRACK_POS, MAX_ARRIVAL_TIME, REAL_TIME_TASK_PERCENTAGE, MAX_REAL_TIME, i);
            EdfScanHdd edfScanHdd = new EdfScanHdd((TASK_NUM), MAX_TRACK_POS, MAX_ARRIVAL_TIME, REAL_TIME_TASK_PERCENTAGE, MAX_REAL_TIME, i);

            fifoHdd.run();
            scanHdd.run();
            cScanHdd.run();
            sstfHdd.run();
            edfHdd.run();
            edfScanHdd.run();

            sums1[0] += fifoHdd.getHeadMovements();
            sums1[1] += fifoHdd.getGreatestStarvationTime();

            sums1[2] += scanHdd.getHeadMovements();
            sums1[3] += scanHdd.getGreatestStarvationTime();

            sums1[4] += cScanHdd.getHeadMovements();
            sums1[5] += cScanHdd.getGreatestStarvationTime();

            sums1[6] += sstfHdd.getHeadMovements();
            sums1[7] += sstfHdd.getGreatestStarvationTime();

            sums1[8] += edfHdd.getHeadMovements();
            sums1[9] += edfHdd.getGreatestStarvationTime();

            sums1[10] += edfScanHdd.getHeadMovements();
            sums1[11] += edfScanHdd.getGreatestStarvationTime();

        }
        for (int i = 0; i < 12; i++) {

            sums1[i] = sums1[i] / ITERATIONS;

        }
        NumberFormat numberFormat = NumberFormat.getInstance(Locale.ENGLISH);
        numberFormat.setMaximumFractionDigits(2);


        System.out.println("FIFO avg: " + numberFormat.format(sums1[0]) + "  greatest starvation time: " + numberFormat.format(sums1[1]));
        System.out.println("SCAN avg: " + numberFormat.format(sums1[2]) + "  greatest starvation time: " + numberFormat.format(sums1[3]));
        System.out.println("CSCAN avg: " + numberFormat.format(sums1[4]) + "  greatest starvation time: " + numberFormat.format(sums1[5]));
        System.out.println("SSTF avg: " + numberFormat.format(sums1[6]) + "  greatest starvation time: " + numberFormat.format(sums1[7]));
        System.out.println("EDF avg: " + numberFormat.format(sums1[8]) + "  greatest starvation time: " + numberFormat.format(sums1[9]));
        System.out.println("EDFSCAN avg: " + numberFormat.format(sums1[10]) + "  greatest starvation time: " + numberFormat.format(sums1[11]));


    }
}
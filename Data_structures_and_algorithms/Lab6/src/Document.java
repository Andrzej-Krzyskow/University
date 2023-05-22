import java.util.Arrays;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Document {
    public String name;
    public TwoWayCycledOrderedListWithSentinel<Link> links;

    public Document(String name, Scanner scan) {
        this.name = name.toLowerCase();
        links = new TwoWayCycledOrderedListWithSentinel<Link>();
        load(scan);
    }

    public void load(Scanner scan) {
        boolean run = true;
        Pattern pattern = Pattern.compile("link=([\\w]+[(]\\d+[)]|\\w+\\s|\\w+$)");
        Matcher matcher;

        while (run && scan.hasNextLine()) {
            String line = scan.nextLine().toLowerCase();

            if (line.contains("eod")) {
                run = false;
                continue;
            }
            matcher = pattern.matcher(line);

            while (matcher.find()) {
                links.add(createLink(matcher.group(1).trim()));
            }
        }
    }

    public static boolean isCorrectId(String id) {
        boolean correct = false;
        Pattern pattern = Pattern.compile("^[A-Za-z][\\w]*");
        Matcher matcher = pattern.matcher(id);

        if (matcher.find()) {
            correct = true;
        }

        return correct;
    }

    public static Link createLink(String link) {
        Pattern pattern = Pattern.compile("(\\w+)([(](\\d+)[)]|$)");
        Matcher matcher = pattern.matcher(link);

        if (matcher.find()) {
            if (matcher.group(3) == null) {
                return new Link(matcher.group(1));
            } else {
                return new Link(matcher.group(1), Integer.parseInt(matcher.group(3)));
            }
        }
        return null;
    }

    @Override
    public String toString() {
        StringBuilder retStr = new StringBuilder("Document: " + name + " ");

        int rowCounter = 0;

        for (Link link : links) {

            if (rowCounter % 10 == 0) {
                retStr.setLength(retStr.length() - 1);
                retStr.append("\n");
                rowCounter = 0;
            }

            retStr.append(link.toString()).append(" ");

            rowCounter++;
        }

        if (retStr.charAt(retStr.length() - 1) == ' ') {
            retStr.setLength(retStr.length() - 1);
        }


        return retStr.toString();
    }

    public String toStringReverse() {
        StringBuilder retStr = new StringBuilder("Document: " + name + " ");
        int rowCounter = 0;

        ListIterator<Link> iter = links.listIterator();
        while (iter.hasNext())
            iter.next();

        while (iter.hasPrevious()) {

            if (rowCounter % 10 == 0) {
                retStr.setLength(retStr.length() - 1);
                retStr.append("\n");
                rowCounter = 0;
            }

            retStr.append(iter.previous().toString()).append(" ");

            rowCounter++;

        }

        if (retStr.charAt(retStr.length() - 1) == ' ') {
            retStr.setLength(retStr.length() - 1);
        }

        return retStr.toString();
    }

    public int[] getWeights() {
        int[] weights = new int[links.size];

        int i = 0;
        for (Link link : links) {
            weights[i] = link.weight;
            i++;
        }

        return weights;
    }

    public static void showArray(int[] arr) {
        StringBuilder stringBuilder = new StringBuilder();

        for (int integer : arr) {
            stringBuilder.append(integer).append(" ");
        }
        stringBuilder.setLength(stringBuilder.length() - 1);

        System.out.println(stringBuilder);
    }

    void bubbleSort(int[] arr) {
        showArray(arr);

        int n = arr.length;

        for (int i = 1; i < n; i++) {
            for (int j = n - 1; j >= i; j--) {
                if (arr[j - 1] > arr[j]) {
                    swap(arr, j - 1, j);
                }
            }

            showArray(arr);
        }
    }

    public void insertSort(int[] arr) {
        showArray(arr);

        int n = arr.length;

        for (int i = 1; i < n; i++) {
            int j = n - 1 - i;
            int temp = arr[j];

            for (; j < n - 1 && temp > arr[j + 1]; j++) {
                arr[j] = arr[j + 1];
            }
            arr[j] = temp;

            showArray(arr);
        }

    }

    public void selectSort(int[] arr) {
        showArray(arr);

        int n = arr.length;

        for (int i = 1; i < n; i++) {
            int indexOfCurrentMaxValue = 0;

            for (int j = 0; j <= n - i; j++) {
                if (arr[j] > arr[indexOfCurrentMaxValue]) {
                    indexOfCurrentMaxValue = j;
                }
            }
            swap(arr, indexOfCurrentMaxValue, n - i);
            showArray(arr);
        }

    }

    private void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public void iterativeMergeSort(int[] weights) {
        int currentSubSize;
        int leftIndex;

        showArray(weights);

        for (currentSubSize = 1; currentSubSize < weights.length - 1; currentSubSize *= 2) {

            for (leftIndex = 0; leftIndex < weights.length - 1; leftIndex += 2 * currentSubSize) {

                int middleIndex = Math.min(leftIndex + currentSubSize - 1, weights.length - 1);
                int rightIndex = Math.min(leftIndex + 2 * currentSubSize - 1, weights.length - 1);

                merge(weights, leftIndex, middleIndex, rightIndex);
            }

            showArray(weights);
        }

    }

    private void merge(int[] arr, int left, int middle, int right) {
        int sizeLeft = middle - left + 1;
        int sizeRight = right - middle;

        int[] leftArr = new int[sizeLeft];
        int[] rightArr = new int[sizeRight];

        System.arraycopy(arr, left, leftArr, 0, sizeLeft);
        System.arraycopy(arr, middle + 1, rightArr, 0, sizeRight);

        int leftCounter = 0;
        int rightCounter = 0;

        while (leftCounter < sizeLeft && rightCounter < sizeRight) {

            if (leftArr[leftCounter] <= rightArr[rightCounter]) {
                arr[left] = leftArr[leftCounter++];
            } else {
                arr[left] = rightArr[rightCounter++];
            }
            left++;
        }

        while (leftCounter < sizeLeft) {
            arr[left] = leftArr[leftCounter++];
            left++;
        }

        while (rightCounter < sizeRight) {
            arr[left] = rightArr[rightCounter++];
            left++;
        }
    }

    public void radixSort(int[] weights) {
        showArray(weights);

        for (int i = 0; i < 3; i++) {
            countSort(weights, i);
            showArray(weights);
        }

    }

    private void countSort(int[] weights, int digitPosition) {
        int[] result = new int[weights.length];
        int[] pos = new int[10];

        for (int integer : weights) {
            integer /= Math.pow(10,digitPosition);
            pos[integer % 10]++;
        }

        pos[0]--;
        for (int i = 1; i < 10; i++) {
            pos[i] += pos[i - 1];
        }

        for (int i = weights.length - 1; i >= 0; i--) {
            int integer = weights[i];

            for (int j = 0; j < digitPosition; j++) {
                integer /= 10;
            }

            result[pos[integer % 10]] = weights[i];
            pos[integer % 10]--;
        }

        System.arraycopy(result, 0, weights, 0, weights.length);
    }
}


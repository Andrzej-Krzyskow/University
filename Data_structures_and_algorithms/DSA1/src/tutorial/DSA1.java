package tutorial;

import java.util.Arrays;

public class DSA1 {

    public static int[] nextPascalLine(int[] input) {

        if (input == null || input.length == 0) {
            System.out.println("No data");
            return null;
        }

        int[] output = new int[input.length + 1];
        output[0] = output[output.length - 1] = 1;

        for (int i = 1; i < input.length; i++) {
            output[i] = input[i - 1] + input[i];
        }

        return output;
    }

    public static void generatePascal(int n) {

        if (n < 1) {
            System.out.println("No data");
            return;
        }


        int[] output = {1};

        System.out.println(" ".repeat(n) + Arrays.toString(output));
        for (int i = 1; i < n; i++) {
            output = nextPascalLine(output);
            System.out.println(" ".repeat(n - i) + Arrays.toString(output));
        }
    }

    public static int getSecondSmallest(int[] input) throws NoAnswerException {

        if (input == null || input.length < 2) {
            throw new NoAnswerException();
        }

        int minVal = input[0], secondMinVal, i = 1;

        while (i < input.length && minVal == input[i]) {
            i++;
        }

        if (i == input.length) {
            throw new NoAnswerException();
        }

        secondMinVal = input[i];
        i++;

        if (minVal > secondMinVal) {
            int temp = minVal;
            minVal = secondMinVal;
            secondMinVal = temp;
        }

        for (; i < input.length; i++) {

            if (input[i] < minVal) {
                secondMinVal = minVal;
                minVal = input[i];
            }

            if (input[i] != minVal && input[i] < secondMinVal) {
                secondMinVal = input[i];
            }
        }

        if (minVal == secondMinVal) {
            throw new NoAnswerException();
        }

        return secondMinVal;
    }

    private static void reverseArraySuffix(int[] input, int left, int right) {

        while (left < right) {
            int temp = input[left];
            input[left++] = input[right];
            input[right--] = temp;
        }

    }

    private static void swapTwoElements(int[] input, int first, int second) {
        int temp = input[first];

        input[first] = input[second];
        input[second] = temp;

    }

    public static boolean nextPermutation(int[] input) {

        if (input == null || input.length < 2) {
            return false;
        }

        int pivot = input.length - 2;
        for (; pivot >= 0; pivot--) {

            if (input[pivot + 1] > input[pivot]) {
                break;
            }
        }

        if (pivot < 0) {
            return false;
        }

        int nextGreaterIndex = input.length - 1;

        while (input[nextGreaterIndex] < input[pivot]) {
            nextGreaterIndex--;
        }

        swapTwoElements(input, pivot, nextGreaterIndex);
        reverseArraySuffix(input, pivot + 1, input.length - 1);

        System.out.println(Arrays.toString(input));
        return true;
    }


    public static void main(String[] args) {
        int[] arr = {1, 4, 6, 4, 1};
        int[] arr2 = nextPascalLine(arr);
        System.out.println(Arrays.toString(arr2));
        System.out.println();
        generatePascal(15);

        try {
            System.out.println(getSecondSmallest(new int[]{9, 3, 5, 4, 7, 1, 5, 1, 9}));
            System.out.println(getSecondSmallest(new int[]{1, 1, 2}));
        } catch (NoAnswerException e) {
            e.printStackTrace();
        }

        System.out.println("\n");
        nextPermutation(new int[]{3, 6, 2, 7, 5, 4, 1});
        nextPermutation(new int[]{3, 4, 2, 5, 1});
        nextPermutation(new int[]{3, 2, 1, 5, 4});
        nextPermutation(new int[]{3, 5, 1, 2, 4});
        nextPermutation(new int[]{3, 2, 1});

    }
}

package ir.amv.os.dcc.july.sixth;

import static ir.amv.os.dcc.july.Utils.printInputAndOutput;

/**
 * Good morning! Here's your coding interview problem for today.
 *
 * This problem was asked by Uber.
 *
 * Given an array of integers, return a new array such that each element at index i of the new array is the product of all the numbers in the original array except the one at i.
 *
 * For example, if our input was [1, 2, 3, 4, 5], the expected output would be [120, 60, 40, 30, 24]. If our input was [3, 2, 1], the expected output would be [2, 3, 6].
 *
 * Follow-up: what if you can't use division?
 */
public class ArrayMultiply {

    private int[] multiplyExceptSelf(int[] input) {
        return withoutDivision(input);
    }

    private int[] withoutDivision(int[] input) {
        int[] result = new int[input.length];
        if (input.length == 1) {
            return result;
        }
        result[0] = 1;
        for (int i = 1; i < input.length; i++) {
            result[i] = result[i - 1] * input[i - 1];
            for (int j = i - 1; j >= 0; j--) {
                result[j] = result[j] * input[i];
            }
        }
        return result;
    }

    private int[] withDivision(int[] input) {
        int[] result = new int[input.length];
        if (input.length == 1) {
            return result;
        }
        long multiplyAll = 1;
        for (int i : input) {
            multiplyAll *= i;
        }
        for (int i = 0; i < result.length; i++) {
            result[i] = (int) (multiplyAll / input[i]);
        }
        return result;
    }

    public static void main(String[] args) {
        printOutput(new int[]{5});
        printOutput(new int[]{1, 2, 3, 4, 5});
        printOutput(new int[]{2, 3, 4, 5, 6});
    }

    private static void printOutput(int[] input) {
        int[] output = new ArrayMultiply().multiplyExceptSelf(input);
        printInputAndOutput(output, input);
    }

}

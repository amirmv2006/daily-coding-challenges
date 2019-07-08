package ir.amv.os.dcc.july.eighth;

import ir.amv.os.dcc.july.Utils;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Good morning! Here's your coding interview problem for today.
 *
 * This problem was asked by Stripe.
 *
 * Given an array of integers, find the first missing positive integer in linear time and constant space. In other words, find the lowest positive integer that does not exist in the array. The array can contain duplicates and negative numbers as well.
 *
 * For example, the input [3, 4, -1, 1] should give 2. The input [1, 2, 0] should give 3.
 *
 * You can modify the input array in-place.
 */
public class LeastPositiveInt {

    private int findLeastMissingPositiveInteger(int[] input) {
        input = tempAddOne(input);
        int counter = 0;
        for (int i = 1; i < input.length; i++) {
            int n = input[i];
            counter++;
            while (n < input.length && n>0 && input[n] != n) {
                counter++;
                int x = input[n];
                input[n] = n;
                n = x;
            }
            // if index not valid, input[i] = x;
            if (n >= input.length || n <= 0) {
                input[i] = n;
            }
        }
        for (int i = 0; i < input.length; i++) {
            if (input[i] != i) {
                return i;
            }
        }
        return input.length;
    }

    private int[] tempAddOne(int[] input) {
        int[] result = new int[input.length + 1];
        System.arraycopy(input, 0, result, 1, result.length - 1);
        return result;
    }

    public static void main(String[] args) {
        print(1, 100);
    }

    private static void print(int from, int to) {
        for (int x = 0; x < 100; x++) {
            List<Integer> collect = IntStream.range(from, to).boxed().collect(Collectors.toList());
            Collections.shuffle(collect);
            Integer expectedResult = collect.remove(0);
            int result = new LeastPositiveInt().findLeastMissingPositiveInteger(
                    collect.stream().mapToInt(Integer::intValue).toArray());
            assert result == expectedResult;
            if (x == 0) {
                Utils.printInputAndOutput(result, collect);
            }
        }
    }
}

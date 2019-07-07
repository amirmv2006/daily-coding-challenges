package ir.amv.os.dcc.july.fifth;

import static ir.amv.os.dcc.july.Utils.printInputAndOutput;

/**
 Good morning! Here's your coding interview problem for today.

 This problem was recently asked by Google.

 Given a list of numbers and a number k, return whether any two numbers from the list add up to k.

 For example, given [10, 15, 3, 7] and k of 17, return true since 10 + 7 is 17.

 Bonus: Can you do this in one pass?
 */
public class TwoNumberSum {

    private static boolean existsTwoNumSumIs(int k, int[] array) {
        return sol2(k, array);
    }

    /**
     * assuming all numbers on the array are positive
     */
    private static boolean sol2(int k, int[] array) {
        boolean[] visitedNumbers = new boolean[k + 1]; // 0 and k
        for (int value : array) {
            int wanted = k - value;
            if (wanted < 0) {
                continue;
            }
            if (visitedNumbers[wanted]) {
                return true;
            }
            visitedNumbers[value] = true;
        }
        return false;
    }

    private static boolean sol1(int k, int[] array) {
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < i; j++) {
                if (array[i] + array[j] == k) {
                    return true;
                }
            }
        }
        return false;
    }

    public static void main(String[] args) {
        check(17, new int[]{10, 15, 3, 7});
        check(13, new int[]{10, 15, 3, 7});
        check(15, new int[]{10, 15, 3, 7});
        check(15, new int[]{0, 15, 3, 7});
        check(14, new int[]{10, 15, 3, 7});
        check(14, new int[]{10, 15, 3, 7, 7});
    }

    private static void check(int k, int[] array) {
        boolean exists = existsTwoNumSumIs(k, array);
        printInputAndOutput(exists, k, array);
    }
}

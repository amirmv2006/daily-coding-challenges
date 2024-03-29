package ir.amv.os.dcc.july.eleventh;

import ir.amv.os.dcc.july.Utils;

import java.util.HashMap;
import java.util.Map;

/**
 * Good morning! Here's your coding interview problem for today.
 *
 * This problem was asked by Facebook.
 *
 * Given the mapping a = 1, b = 2, ... z = 26, and an encoded message, count the number of ways it can be decoded.
 *
 * For example, the message '111' would give 3, since it could be decoded as 'aaa', 'ka', and 'ak'.
 *
 * You can assume that the messages are decodable. For example, '001' is not allowed.
 * @author Amir
 */
public class MessageDecoder {

    private Map<String, Integer> cache = new HashMap<>();
    private int timeOrder = 0;

    private int countDecode(String s) {
        if (cache.containsKey(s)) {
            return cache.get(s);
        }
        int result;
        if (s.startsWith("0")) {
            result = 0;
        } else if (s.equals("") || s.length() == 1) {
            result = 1;
        } else {
            result = countDecode(s.substring(1)); // count1
            String substring2 = s.substring(0, 2);
            if (Integer.parseInt(substring2) <= 26) {
                result += countDecode(s.substring(2));
            }
        }
        cache.put(s, result);
        timeOrder++;
        return result;
    }

    public static void main(String[] args) {
//        printResult("11");
//        printResult("111");
//        printResult("444");
//        printResult("4044");
//        printResult("001");
//        printResult("1001");
//        printResult("101");
//        printResult("1011");
        printResult("111111111111");
//        printResult("222222222222");
    }

    private static void printResult(final String s) {
        MessageDecoder decoder = new MessageDecoder();
        int result = decoder.countDecode(s);
        Utils.printInputAndOutput(result, s);
    }
}

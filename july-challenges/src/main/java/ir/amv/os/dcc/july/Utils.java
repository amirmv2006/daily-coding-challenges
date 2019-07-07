package ir.amv.os.dcc.july;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class Utils {

    public static void printInputAndOutput(Object output, Object... inputs) {
        StringBuilder sb = new StringBuilder("INPUT: ");
        if (inputs != null) {
            for (int i = 0; i < inputs.length; i++) {
                Object input = inputs[i];
                objToString(sb, input);
                if (i < inputs.length - 1) {
                    sb.append(", ");
                }
            }
        }
        sb.append(". OUTPUT: ");
        objToString(sb, output);
        System.out.println(sb.toString());
    }

    private static void objToString(StringBuilder sb, Object input) {
        if (input != null && input.getClass().isArray()) {
            List<Object> arrayToList = new ArrayList<>();
            for (int i = 0; i < Array.getLength(input); i++) {
                arrayToList.add(Array.get(input, i));
            }
            sb.append(arrayToList);
        } else {
            sb.append(input);
        }
    }
}

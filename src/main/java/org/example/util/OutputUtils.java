package org.example.util;

public final class OutputUtils {

    private OutputUtils() {}

    public static void printOutput(Integer order, Number value) {
        String output;
        if (value == null) {
            output = order + ". NO SUCH TRACE";
        } else {
            output = order + ". " + value;
        }
        System.out.println(output);
    }
}

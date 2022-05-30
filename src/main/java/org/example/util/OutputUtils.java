package org.example.util;

public final class OutputUtils {

    private OutputUtils() {
    }

    public static void printOutput(Integer order, Object value) {
        String output;
        if (value == null) {
            output = order + ". NO SUCH TRACE";
        } else {
            output = order + ". " + value;
        }
        System.out.println(output);
    }
}

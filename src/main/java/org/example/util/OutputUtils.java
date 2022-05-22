package org.example.util;

public class OutputUtils {

    public static void printOutput(Integer order, Integer value) {
        String output;
        if (value == null) {
            output = order + ". NO SUCH TRACE";
        } else {
            output = order + ". " + value;
        }
        System.out.println(output);
    }
}

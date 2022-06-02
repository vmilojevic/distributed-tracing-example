package org.example.util;

import java.nio.file.Files;
import java.nio.file.Path;

public final class IOUtils {

    private static final String TXT_EXTENSION = "txt";

    private IOUtils() {
    }

    public static String getFilenameFromArgs(String[] args) throws Exception {
        if (args.length < 1) {
            throw new Exception("You must specify the path to the file.");
        }

        return args[0];
    }

    public static String readTxtFileAsString(String filename) throws Exception {
        if (!TXT_EXTENSION.equals(getExtensionFromFilename(filename))) {
            throw new Exception("Input file must be a .txt file.");
        }

        Path path = Path.of(filename);
        return Files.readString(path).strip().toUpperCase();
    }

    public static void printOutput(int order, Object value) {
        String output;
        if (value == null) {
            output = order + ". NO SUCH TRACE";
        } else {
            output = order + ". " + value;
        }
        System.out.println(output);
    }

    private static String getExtensionFromFilename(String filename) {
        if (filename.contains(".")) {
            return filename.substring(filename.lastIndexOf(".") + 1);
        } else {
            return "";
        }
    }
}

package org.example.util;

import java.nio.file.Files;
import java.nio.file.Path;

public final class IOUtils {

    private static final String TXT_EXTENSION = "txt";
    private static final String REGEX_PATTERN = "([A-Z]{2}[0-9]{0,9}[,][ ])([A-Z]{2}[0-9]{0,9}[,][ ])*([A-Z]{2}[0-9]{0,9})";

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
        String content = Files.readString(path).strip().toUpperCase();
        if (content.matches(REGEX_PATTERN)) {
            return content;
        } else {
            throw new Exception("Content of the input file must be in format: " + REGEX_PATTERN);
        }
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

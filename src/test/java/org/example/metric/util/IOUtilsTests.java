package org.example.metric.util;

import org.example.util.IOUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.file.NoSuchFileException;

public class IOUtilsTests {

    @Test
    void Should_ReturnFirstArg_When_MultipleArgsAreProvided() throws Exception {
        String[] args = {"arg1", "arg2"};

        String firstArg = IOUtils.getFilenameFromArgs(args);

        Assertions.assertEquals("arg1", firstArg);
    }

    @Test
    void Should_ReturnFirstArg_When_OneArgIsProvided() throws Exception {
        String[] args = {"arg1"};

        String firstArg = IOUtils.getFilenameFromArgs(args);

        Assertions.assertEquals("arg1", firstArg);
    }

    @Test
    void Should_ThrowException_When_NoArgsAreProvided() {
        String[] args = {};

        Exception exception = Assertions.assertThrows(Exception.class, () -> IOUtils.getFilenameFromArgs(args));
        Assertions.assertEquals("You must specify the path to the file.", exception.getMessage());
    }

    @Test
    void Should_ThrowException_When_InputFileIsNotTxt() {
        String filename = "file-name.pdf";

        Exception exception = Assertions.assertThrows(Exception.class, () -> IOUtils.readTxtFileAsString(filename));
        Assertions.assertEquals("Input file must be a .txt file.", exception.getMessage());
    }

    @Test
    void Should_ThrowException_When_FileContentDoesNotMatchRegex() {
        String filename = "src/test/resources/invalid-graph.txt";

        Exception exception = Assertions.assertThrows(Exception.class, () -> IOUtils.readTxtFileAsString(filename));
        Assertions.assertEquals("Content of the input file must be in the following format: " +
                                "([A-Z]{2}[0-9]{0,9}[,][ ])([A-Z]{2}[0-9]{0,9}[,][ ])*([A-Z]{2}[0-9]{0,9})",
                                exception.getMessage());
    }

    @Test
    void Should_ThrowException_When_FileDoesNotExist() {
        String filename = "invalid-path.txt";

        Assertions.assertThrows(NoSuchFileException.class, () -> IOUtils.readTxtFileAsString(filename));
    }

    @Test
    void Should_ReturnCorrectString_When_FileIsValid() throws Exception {
        String filename = "src/test/resources/valid-graph.txt";

        String output = IOUtils.readTxtFileAsString(filename);

        Assertions.assertEquals("AB5, BC4, CD8, DC8, DE6, AD5, CE2, EB3, AE77", output);
    }

    @Test
    void Should_PrintCorrectOutput_When_ValueIsNotNull() {
        PrintStream standardOut = System.out;
        ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStreamCaptor));

        IOUtils.printOutput(1, "value");

        Assertions.assertEquals("1. value", outputStreamCaptor.toString().trim());

        System.setOut(standardOut);
    }

    @Test
    void Should_PrintNoSuchTrace_When_ValueIsNull() {
        PrintStream standardOut = System.out;
        ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStreamCaptor));

        IOUtils.printOutput(1, null);

        Assertions.assertEquals("1. NO SUCH TRACE", outputStreamCaptor.toString().trim());

        System.setOut(standardOut);
    }
}

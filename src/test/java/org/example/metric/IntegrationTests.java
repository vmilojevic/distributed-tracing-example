package org.example.metric;

import org.example.Main;
import org.example.util.IOUtils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class IntegrationTests {

    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @AfterEach
    void tearDown() {
        System.setOut(standardOut);
    }

    @Test
    void Should_PrintExpectedOutput_When_InputFileIsValid() throws Exception {
        String[] args = {"src/test/resources/valid-graph.txt"};
        Main.main(args);

        String expectedOutput = IOUtils.readTxtFileAsString("src/test/resources/expected-output.txt");
        Assertions.assertEquals(expectedOutput, outputStreamCaptor.toString().trim());
    }
}

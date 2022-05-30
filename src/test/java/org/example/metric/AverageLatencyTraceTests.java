package org.example.metric;

import org.example.graph.DirectedWeightedGraph;
import org.example.util.GraphUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

public class AverageLatencyTraceTests {

    @Test
    void Should_ReturnNull_When_ThereIsNoSuchTrace() {
        DirectedWeightedGraph<String> inputGraph = GraphUtils.readGraphFromString("AB5, CD1");

        Integer output = MetricService.getAverageLatencyTrace(inputGraph, Arrays.asList("A", "C"));

        Assertions.assertNull(output);
    }

    @Test
    void Should_ReturnValidResult_When_TraceExists() {
        DirectedWeightedGraph<String> inputGraph = GraphUtils.readGraphFromString("AB5, BC1, CD3");

        Integer output = MetricService.getAverageLatencyTrace(inputGraph, Arrays.asList("A", "B", "C", "D"));

        Assertions.assertNotNull(output);
        Assertions.assertEquals(9, output);
    }

    @Test
    void Should_ReturnNull_When_NodeDoesNotExist() {
        DirectedWeightedGraph<String> inputGraph = GraphUtils.readGraphFromString("AB5, CD1");

        Integer output = MetricService.getAverageLatencyTrace(inputGraph, Arrays.asList("A", "E"));

        Assertions.assertNull(output);
    }

    @Test
    void Should_ReturnZero_When_NodesParameterContainsOnlyOneNode() {
        DirectedWeightedGraph<String> inputGraph = GraphUtils.readGraphFromString("AB5, CD1");

        Integer output = MetricService.getAverageLatencyTrace(inputGraph, List.of("A"));

        Assertions.assertEquals(0, output);
    }
}
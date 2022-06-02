package org.example.metric.service;

import org.example.graph.DirectedWeightedGraph;
import org.example.metric.MetricService;
import org.example.util.GraphUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class LengthOfShortestTraceTests {

    @Test
    void Should_ReturnShortestTrace_When_TraceExists() throws Exception {
        DirectedWeightedGraph<String> inputGraph = GraphUtils.readGraphFromString("AB5, AC3, BE7, CD1, DE2");

        Integer output = MetricService.getLengthOfShortestTrace(inputGraph, "A", "E");

        Assertions.assertEquals(6, output);
    }

    @Test
    void Should_ReturnNull_When_TraceDoesNotExist() throws Exception {
        DirectedWeightedGraph<String> inputGraph = GraphUtils.readGraphFromString("AB5, BD2");

        Integer output = MetricService.getLengthOfShortestTrace(inputGraph, "A", "C");

        Assertions.assertNull(output);
    }

    @Test
    void Should_ReturnNull_When_StartAndEndNodeAreSameAndTraceDoesNotExist() throws Exception {
        DirectedWeightedGraph<String> inputGraph = GraphUtils.readGraphFromString("AB5, BD2");

        Integer output = MetricService.getLengthOfShortestTrace(inputGraph, "A", "A");

        Assertions.assertNull(output);
    }

    @Test
    void Should_ReturnNull_When_StartNodeDoesNotExist() throws Exception {
        DirectedWeightedGraph<String> inputGraph = GraphUtils.readGraphFromString("AB5, BD2");

        Integer output = MetricService.getLengthOfShortestTrace(inputGraph, "C", "A");

        Assertions.assertNull(output);
    }

    @Test
    void Should_ReturnNull_When_EndNodeDoesNotExist() throws Exception {
        DirectedWeightedGraph<String> inputGraph = GraphUtils.readGraphFromString("AB5, BD2");

        Integer output = MetricService.getLengthOfShortestTrace(inputGraph, "A", "C");

        Assertions.assertNull(output);
    }

    @Test
    void Should_ReturnNonZeroValue_When_StartAndEndNodeAreSameAndTraceExist() throws Exception {
        DirectedWeightedGraph<String> inputGraph = GraphUtils.readGraphFromString("AB5, BC2, AD6, CA8, DE1, EF2, FA1");

        Integer output = MetricService.getLengthOfShortestTrace(inputGraph, "A", "A");

        Assertions.assertEquals(10, output);
    }
}

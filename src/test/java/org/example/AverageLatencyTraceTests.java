package org.example;

import org.example.service.MetricService;
import org.example.util.GraphUtils;
import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class AverageLatencyTraceTests {

    @Test
    void Should_ReturnNull_When_ThereIsNoSuchTrace() {
        Graph<String, DefaultWeightedEdge> inputGraph = GraphUtils.readGraphFromString("AB5, CD1");

        Integer output = MetricService.getAverageLatencyTrace(inputGraph, "A", "C");

        Assertions.assertNull(output);
    }

    @Test
    void Should_ReturnValidResult_When_ThereIsSuchTrace() {
        Graph<String, DefaultWeightedEdge> inputGraph = GraphUtils.readGraphFromString("AB5, BC1, CD3");

        Integer output = MetricService.getAverageLatencyTrace(inputGraph, "A", "B", "C", "D");

        Assertions.assertNotNull(output);
        Assertions.assertEquals(9, output);
    }

    @Test
    void Should_ReturnNull_When_NodeDoesNotExist() {
        Graph<String, DefaultWeightedEdge> inputGraph = GraphUtils.readGraphFromString("AB5, CD1");

        Integer output = MetricService.getAverageLatencyTrace(inputGraph, "A", "E");

        Assertions.assertNull(output);
    }

    @Test
    void Should_ReturnZero_When_NodesParameterContainsOnlyOneNode() {
        Graph<String, DefaultWeightedEdge> inputGraph = GraphUtils.readGraphFromString("AB5, CD1");

        Integer output = MetricService.getAverageLatencyTrace(inputGraph, "A");

        Assertions.assertEquals(0, output);
    }
}

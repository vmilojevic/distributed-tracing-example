package org.example;

import org.example.service.MetricService;
import org.example.util.GraphUtils;
import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class LengthOfShortestTraceTests {

    @Test
    void Should_ReturnShortestTrace_When_TraceExists() {
        Graph<String, DefaultWeightedEdge> inputGraph = GraphUtils.readGraphFromString("AB5, AC3, BE7, CD1, DE2");

        Integer output = MetricService.getLengthOfShortestTrace(inputGraph, "A", "E");

        Assertions.assertEquals(6, output);
    }

    @Test
    void Should_ReturnNull_When_TraceDoesNotExist() {
        Graph<String, DefaultWeightedEdge> inputGraph = GraphUtils.readGraphFromString("AB5, BD2");

        Integer output = MetricService.getLengthOfShortestTrace(inputGraph, "A", "C");

        Assertions.assertNull(output);
    }

    @Test
    void Should_ReturnZero_When_StartAndEndNodeAreSame() {
        Graph<String, DefaultWeightedEdge> inputGraph = GraphUtils.readGraphFromString("AB5, BD2");

        Integer output = MetricService.getLengthOfShortestTrace(inputGraph, "A", "A");

        Assertions.assertEquals(0, output);
    }
}

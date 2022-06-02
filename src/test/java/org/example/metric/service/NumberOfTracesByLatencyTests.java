package org.example.metric.service;

import org.example.graph.DirectedWeightedGraph;
import org.example.metric.MetricConditionType;
import org.example.metric.MetricService;
import org.example.util.GraphUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class NumberOfTracesByLatencyTests {

    @Test
    void Should_ReturnZero_When_ThereIsNoSuchTrace() throws Exception {
        DirectedWeightedGraph<String> inputGraph = GraphUtils.readGraphFromString("AB5, CD1");

        int output = MetricService.getNumberOfTracesByLatency(inputGraph, "A", "C", 30, MetricConditionType.LESS_THAN);

        Assertions.assertEquals(0, output);
    }

    @Test
    void Should_ReturnNonZeroValue_When_StartAndEndAreSameAndThereIsSuchTrace() throws Exception {
        DirectedWeightedGraph<String> inputGraph = GraphUtils.readGraphFromString("AB5, CD1, CE4, BC8, EB7");

        int output = MetricService.getNumberOfTracesByLatency(inputGraph, "B", "B", 30, MetricConditionType.LESS_THAN);

        Assertions.assertEquals(1, output);
    }

    @Test
    void Should_ReturnZero_When_StartAndEndAreSameAndThereIsNoSuchTrace() throws Exception {
        DirectedWeightedGraph<String> inputGraph = GraphUtils.readGraphFromString("AB5, CD1, CE4, BC8, EB7");

        int output = MetricService.getNumberOfTracesByLatency(inputGraph, "B", "B", 5, MetricConditionType.LESS_THAN);

        Assertions.assertEquals(0, output);
    }

    @Test
    void Should_ThrowIllegalArgumentException_When_StartDoesNotExist() throws Exception {
        DirectedWeightedGraph<String> inputGraph = GraphUtils.readGraphFromString("AB5, CD1, CE4, BC8, EB7");

        IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            MetricService.getNumberOfTracesByLatency(inputGraph, "T", "C", 30, MetricConditionType.LESS_THAN);
        });

        Assertions.assertEquals("Start node must exist!", exception.getMessage());
    }

    @Test
    void Should_ThrowIllegalArgumentException_When_EndDoesNotExist() throws Exception {
        DirectedWeightedGraph<String> inputGraph = GraphUtils.readGraphFromString("AB5, CD1, CE4, BC8, EB7");

        IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            MetricService.getNumberOfTracesByLatency(inputGraph, "A", "T", 30, MetricConditionType.LESS_THAN);
        });

        Assertions.assertEquals("End node must exist!", exception.getMessage());
    }
}

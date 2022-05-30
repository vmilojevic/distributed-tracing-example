package org.example.metric;

import org.example.graph.DirectedWeightedGraph;
import org.example.util.GraphUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class NumberOfTracesByHopsTests {

    @Test
    void Should_ReturnZero_When_ThereIsNoSuchTrace() {
        DirectedWeightedGraph<String> inputGraph = GraphUtils.readGraphFromString("AB5, CD1");

        int output = MetricService.getNumberOfTracesByHops(inputGraph, "A", "C", 3, MetricConditionType.LESS_THAN);

        Assertions.assertEquals(0, output);
    }

    @Test
    void Should_ReturnNonZeroValue_When_StartAndEndAreSameAndThereIsSuchTrace() {
        DirectedWeightedGraph<String> inputGraph = GraphUtils.readGraphFromString("AB5, CD1, CE4, BC8, EB7");

        int output = MetricService.getNumberOfTracesByHops(inputGraph, "B", "B", 3, MetricConditionType.EQUALS);

        Assertions.assertNotEquals(0, output);
    }

    @Test
    void Should_ReturnZero_When_StartAndEndAreSameAndThereIsNoSuchTrace() {
        DirectedWeightedGraph<String> inputGraph = GraphUtils.readGraphFromString("AB5, CD1, CE4, BC8, EB7");

        int output = MetricService.getNumberOfTracesByHops(inputGraph, "B", "B", 3, MetricConditionType.LESS_THAN);

        Assertions.assertEquals(0, output);
    }

    @Test
    void Should_ThrowIllegalArgumentException_When_StartDoesNotExist() {
        DirectedWeightedGraph<String> inputGraph = GraphUtils.readGraphFromString("AB5, CD1, CE4, BC8, EB7");

        IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            MetricService.getNumberOfTracesByHops(inputGraph, "T", "C", 3, MetricConditionType.LESS_THAN);
        });

        Assertions.assertEquals("Start node must exist!", exception.getMessage());
    }

    @Test
    void Should_ThrowIllegalArgumentException_When_EndDoesNotExist() {
        DirectedWeightedGraph<String> inputGraph = GraphUtils.readGraphFromString("AB5, CD1, CE4, BC8, EB7");

        IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            MetricService.getNumberOfTracesByHops(inputGraph, "A", "T", 3, MetricConditionType.LESS_THAN);
        });

        Assertions.assertEquals("End node must exist!", exception.getMessage());
    }
}

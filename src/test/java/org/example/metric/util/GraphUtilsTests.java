package org.example.metric.util;

import org.example.graph.DirectedWeightedGraph;
import org.example.util.GraphUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class GraphUtilsTests {

    @Test
    void Should_ThrowException_When_InputDoesNotMatchRegex() {
        String input = "invalid-input";

        Exception exception = Assertions.assertThrows(Exception.class, () -> GraphUtils.readGraphFromString(input));
        Assertions.assertEquals("Content of the input file must be in the following format: " +
                                "([A-Z]{2}[0-9]{0,9}[,][ ])([A-Z]{2}[0-9]{0,9}[,][ ])*([A-Z]{2}[0-9]{0,9})",
                                exception.getMessage());
    }

    @Test
    void Should_ParseGraph_When_InputIsValid() throws Exception {
        String input = "AB5, AE77";

        DirectedWeightedGraph<String> graph = GraphUtils.readGraphFromString(input);

        Assertions.assertNotNull(graph);

        Assertions.assertTrue(graph.containsNode("A"));
        Assertions.assertTrue(graph.containsNode("B"));
        Assertions.assertTrue(graph.containsNode("E"));

        Assertions.assertEquals(5, graph.getEdgeWeight("A", "B"));
        Assertions.assertEquals(77, graph.getEdgeWeight("A", "E"));

        Assertions.assertTrue(graph.getAdjacentNodes("A").contains("B"));
        Assertions.assertTrue(graph.getAdjacentNodes("A").contains("E"));
        Assertions.assertEquals(2, graph.getAdjacentNodes("A").size());
        Assertions.assertEquals(0, graph.getAdjacentNodes("B").size());
        Assertions.assertEquals(0, graph.getAdjacentNodes("E").size());

        Assertions.assertNull(graph.getEdgeWeight("B", "E"));
        Assertions.assertNull(graph.getEdgeWeight("E", "B"));
        Assertions.assertNull(graph.getEdgeWeight("B", "A"));
        Assertions.assertNull(graph.getEdgeWeight("E", "A"));
    }
}

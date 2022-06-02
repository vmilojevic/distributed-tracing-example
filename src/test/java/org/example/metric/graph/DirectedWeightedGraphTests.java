package org.example.metric.graph;

import org.example.graph.DirectedWeightedGraph;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class DirectedWeightedGraphTests {

    @Test
    void Should_AddNode_When_AddNodeMethodIsCalled() {
        DirectedWeightedGraph<String> graph = new DirectedWeightedGraph<>();
        graph.addNode("A");

        Assertions.assertTrue(graph.containsNode("A"));
    }

    @Test
    void Should_AddEdge_When_NodesExist() {
        DirectedWeightedGraph<String> graph = new DirectedWeightedGraph<>();
        graph.addNode("A");
        graph.addNode("B");
        graph.addEdge("A", "B", 1);

        Assertions.assertEquals(1, graph.getEdgeWeight("A", "B"));
        Assertions.assertEquals(1, graph.getAdjacentNodes("A").size());
        Assertions.assertTrue(graph.getAdjacentNodes("A").contains("B"));
        Assertions.assertEquals(0, graph.getAdjacentNodes("B").size());
    }

    @Test
    void Should_AddEdgeAndNodes_When_NodesDoNotExist() {
        DirectedWeightedGraph<String> graph = new DirectedWeightedGraph<>();
        graph.addEdge("A", "B", 1);

        Assertions.assertTrue(graph.containsNode("A"));
        Assertions.assertTrue(graph.containsNode("B"));
        Assertions.assertEquals(1, graph.getEdgeWeight("A", "B"));
        Assertions.assertEquals(1, graph.getAdjacentNodes("A").size());
        Assertions.assertTrue(graph.getAdjacentNodes("A").contains("B"));
        Assertions.assertEquals(0, graph.getAdjacentNodes("B").size());
    }

    @Test
    void Should_ReturnNull_When_EdgeDoesNotExist() {
        DirectedWeightedGraph<String> graph = new DirectedWeightedGraph<>();
        graph.addNode("A");
        graph.addNode("B");

        Assertions.assertNull(graph.getEdgeWeight("A", "B"));
    }

    @Test
    void Should_ReturnNull_When_NodesDoNotExist() {
        DirectedWeightedGraph<String> graph = new DirectedWeightedGraph<>();

        Assertions.assertNull(graph.getEdgeWeight("A", "B"));
    }

    @Test
    void Should_ReturnFalse_When_GraphDoesNotContainNode() {
        DirectedWeightedGraph<String> graph = new DirectedWeightedGraph<>();

        Assertions.assertFalse(graph.containsNode("A"));
    }
}

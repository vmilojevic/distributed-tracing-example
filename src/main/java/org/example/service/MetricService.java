package org.example.service;

import org.jgrapht.Graph;
import org.jgrapht.alg.shortestpath.DijkstraShortestPath;
import org.jgrapht.graph.DefaultWeightedEdge;

import java.util.function.Predicate;

public class MetricService {

    public static Integer getAverageLatencyTrace(Graph<String, DefaultWeightedEdge> graph, String... nodes) {
        int sum = 0;
        for (int i = 0; i < nodes.length - 1; i++) {
            var edge = graph.getEdge(nodes[i], nodes[i + 1]);
            if (edge == null) {
                return null;
            }
            sum += graph.getEdgeWeight(edge);
        }
        return sum;
    }

    public static Integer getLengthOfShortestTrace(Graph<String, DefaultWeightedEdge> graph, String startNode, String endNode) {
        var dijkstra = new DijkstraShortestPath<>(graph);
        try {
            var path = dijkstra.getPath(startNode, endNode);
            return (int) path.getWeight();
        } catch (IllegalArgumentException e) {
            return null;
        }
    }

    public void getNumberOfDifferentTraces(String startNode, String endNode, Predicate<Integer> predicate) {

    }
}

package org.example.service;

import org.jgrapht.Graph;
import org.jgrapht.alg.shortestpath.AllDirectedPaths;
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

    public static Integer getAllTracesByNumberOfHops(
            Graph<String, DefaultWeightedEdge> graph,
            String startNode,
            String endNode,
            int maxHops) {
        var allPaths =
                new AllDirectedPaths<>(graph).getAllPaths(startNode, endNode, true, maxHops);
        if (startNode.equals(endNode)) {
            return allPaths.size() - 1;
        } else {
            return allPaths.size();
        }
    }

    public static Integer getAllTracesByLatency(
            Graph<String, DefaultWeightedEdge> graph,
            String startNode,
            String endNode,
            Predicate<Number> latencyPredicate) {
        return 1;
    }
}

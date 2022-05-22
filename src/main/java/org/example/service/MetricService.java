package org.example.service;

import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultWeightedEdge;

import java.util.function.Predicate;

public class MetricService {

    public static Integer getAverageLatencyTrace(Graph<String, DefaultWeightedEdge> graph, String... nodes) {
        int sum = 0;
        for (int i = 0; i < nodes.length - 1; i++) {
            DefaultWeightedEdge edge = graph.getEdge(nodes[i], nodes[i+1]);
            if (edge == null) {
                return null;
            }
            sum += graph.getEdgeWeight(edge);
        }
        return sum;
    }

    public void getLengthOfShortestTrace(String startNode, String endNode) {

    }

    public void getNumberOfDifferentTraces(String startNode, String endNode, Predicate<Integer> predicate) {

    }
}

package org.example.util;

import org.example.graph.DirectedWeightedGraph;

public final class GraphUtils {

    private GraphUtils() {}

    // TODO: read graph from a text file
    // TODO: check if input is in correct format
    public static DirectedWeightedGraph<String> readGraphFromString(String input) {
        String[] edges = input.split(", ");

        DirectedWeightedGraph<String> graph = new DirectedWeightedGraph<>();
        for (String edge: edges) {
            String start = String.valueOf(edge.charAt(0));
            String end = String.valueOf(edge.charAt(1));
            int weight = Integer.parseInt(String.valueOf(edge.charAt(2)));
            graph.addNode(start);
            graph.addNode(end);
            graph.addEdge(start, end, weight);
        }

        return graph;
    }
}

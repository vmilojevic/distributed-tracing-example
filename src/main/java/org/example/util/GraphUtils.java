package org.example.util;

import org.example.graph.DirectedWeightedGraph;

public final class GraphUtils {

    private static final String REGEX_PATTERN = "([A-Z]{2}[0-9]{0,9}[,][ ])([A-Z]{2}[0-9]{0,9}[,][ ])*([A-Z]{2}[0-9]{0,9})";

    private GraphUtils() {
    }

    public static DirectedWeightedGraph<String> readGraphFromString(String input) throws Exception {
        if (!input.matches(REGEX_PATTERN)) {
            throw new Exception("Content of the input file must be in the following format: " + REGEX_PATTERN);
        }
        String[] edges = input.split(", ");

        DirectedWeightedGraph<String> graph = new DirectedWeightedGraph<>();
        for (String edge : edges) {
            String start = String.valueOf(edge.charAt(0));
            String end = String.valueOf(edge.charAt(1));
            int weight = Integer.parseInt(edge.substring(2));
            graph.addNode(start);
            graph.addNode(end);
            graph.addEdge(start, end, weight);
        }

        return graph;
    }
}

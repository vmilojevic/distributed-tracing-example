package org.example.util;

import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleDirectedWeightedGraph;

public final class GraphUtils {

    private GraphUtils() {}

    // TODO: read graph from a text file
    // TODO: check if input is in correct format
    public static Graph<String, DefaultWeightedEdge> readGraphFromString(String input) {
        String[] edges = input.split(", ");

        Graph<String, DefaultWeightedEdge> graph = new SimpleDirectedWeightedGraph<>(DefaultWeightedEdge.class);
        for (String edge : edges) {
            graph.addVertex(String.valueOf(edge.charAt(0)));
            graph.addVertex(String.valueOf(edge.charAt(1)));
            DefaultWeightedEdge e = graph.addEdge(String.valueOf(edge.charAt(0)), String.valueOf(edge.charAt(1)));
            graph.setEdgeWeight(e, Integer.parseInt(String.valueOf(edge.charAt(2))));
        }

        return graph;
    }
}

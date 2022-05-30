package org.example.graph;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class DirectedWeightedGraph<T> {

    private final Map<T, Map<T, Integer>> adjacencyMap;

    public DirectedWeightedGraph() {
        this.adjacencyMap = new HashMap<>();
    }

    public void addNode(T node) {
        this.adjacencyMap.computeIfAbsent(node, k -> new HashMap<>());
    }

    public void addEdge(T start, T end, int weight) {
        addNode(start);
        addNode(end);
        this.adjacencyMap.get(start).put(end, weight);
    }

    public Integer getEdgeWeight(T start, T end) {
        if (this.containsNode(start) && this.containsNode(end)) {
            return this.adjacencyMap.get(start).get(end);
        }

        return null;
    }

    public Set<T> getAdjacentNodes(T node) {
        if (this.containsNode(node)) {
            return this.adjacencyMap.get(node).keySet();
        }

        return new HashSet<>();
    }

    public boolean containsNode(T node) {
        return this.adjacencyMap.containsKey(node);
    }
}

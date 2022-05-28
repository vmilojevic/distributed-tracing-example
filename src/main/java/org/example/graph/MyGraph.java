package org.example.graph;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class MyGraph<T> {

    private final Map<T, Map<T, Integer>> adj;

    public MyGraph() {
        this.adj = new HashMap<>();
    }

    public void addNode(T node) {
        this.adj.computeIfAbsent(node, k -> new HashMap<>());
    }

    public void addEdge(T start, T end, int weight) {
        checkIfNodeExist(start);
        checkIfNodeExist(end);
        this.adj.get(start).put(end, weight);
    }

    public Integer getEdgeWeight(T start, T end) {
        checkIfEdgeExists(start, end);
        return this.adj.get(start).get(end);
    }

    public Set<T> getAdjacentNodes(T node) {
        return this.adj.get(node).keySet();
    }

    private void checkIfNodeExist(T node) {
        if (this.adj.get(node) == null) {
            throw new IllegalArgumentException("Node " + node + " must exist!");
        }
    }

    private void checkIfEdgeExists(T start, T end) {
        checkIfNodeExist(start);
        checkIfNodeExist(end);
        if (!this.adj.get(start).containsKey(end)) {
            throw new IllegalArgumentException("Connection between " + start + " and " + end + " does not exist!");
        }
    }
}

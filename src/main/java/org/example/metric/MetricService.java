package org.example.metric;

import org.example.graph.DirectedWeightedGraph;
import org.example.graph.BfsLabel;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class MetricService {

    private static final String START_NODE_MUST_EXIST = "Start node must exist!";
    private static final String END_NODE_MUST_EXIST = "End node must exist!";

    public static <T> Integer getAverageLatencyTrace(DirectedWeightedGraph<T> graph, List<T> nodes) {
        int sum = 0;
        for (int i = 0; i < nodes.size() - 1; i++) {
            Integer weight = graph.getEdgeWeight(nodes.get(i), nodes.get(i + 1));
            if (weight == null) {
                return null;
            }
            sum += weight;
        }
        return sum;
    }

    public static <T> int getNumberOfTracesByHops(DirectedWeightedGraph<T> graph, T start, T end, int hops, MetricConditionType conditionType) {
        if (!graph.containsNode(start)) {
            throw new IllegalArgumentException(START_NODE_MUST_EXIST);
        }
        if (!graph.containsNode(end)) {
            throw new IllegalArgumentException(END_NODE_MUST_EXIST);
        }
        int count = 0;
        int depth = 0;
        Queue<BfsLabel<T>> queue = new LinkedList<>();
        queue.add(new BfsLabel<>(start, depth));

        while (depth <= hops && !queue.isEmpty()) {
            BfsLabel<T> bfsLabel = queue.poll();
            depth = bfsLabel.getValue();
            var adjacentNodes = graph. getAdjacentNodes(bfsLabel.getNode());
            for (T adjNode: adjacentNodes) {
                if (adjNode.equals(end) && conditionType.apply(depth + 1, hops)) {
                    count++;
                }
                queue.add(new BfsLabel<>(adjNode, depth + 1));
            }
        }

        return count;
    }

    public static <T> Integer getNumberOfTracesByLatency(DirectedWeightedGraph<T> graph, T start, T end, int avgLatency, MetricConditionType conditionType) {
        if (!graph.containsNode(start)) {
            throw new IllegalArgumentException(START_NODE_MUST_EXIST);
        }
        if (!graph.containsNode(end)) {
            throw new IllegalArgumentException(END_NODE_MUST_EXIST);
        }
        int count = 0;
        int latency = 0;
        Queue<BfsLabel<T>> queue = new LinkedList<>();
        queue.add(new BfsLabel<>(start, latency));

        while (!queue.isEmpty()) {
            BfsLabel<T> bfsLabel = queue.poll();
            latency = bfsLabel.getValue();
            var adjacentNodes = graph.getAdjacentNodes(bfsLabel.getNode());
            for (T adjNode: adjacentNodes) {
                int weight = graph.getEdgeWeight(bfsLabel.getNode(), adjNode);
                if (adjNode.equals(end) && conditionType.apply(latency + weight, avgLatency)) {
                    count++;
                }
                if (latency + weight <= avgLatency) {
                    queue.add(new BfsLabel<>(adjNode, latency + weight));
                }
            }
        }

        return count;
    }
}

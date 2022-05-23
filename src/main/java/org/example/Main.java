package org.example;

import org.example.service.MetricService;
import org.example.util.GraphUtils;
import org.example.util.OutputUtils;
import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultWeightedEdge;

public class Main {
    public static void main(String[] args) {
        String input = "AB5, BC4, CD8, DC8, DE6, AD5, CE2, EB3, AE7";
        Graph<String, DefaultWeightedEdge> graph = GraphUtils.readGraphFromString(input);

        OutputUtils.printOutput(1, MetricService.getAverageLatencyTrace(graph, "A", "B", "C"));
        OutputUtils.printOutput(2, MetricService.getAverageLatencyTrace(graph, "A", "D"));
        OutputUtils.printOutput(3, MetricService.getAverageLatencyTrace(graph, "A", "D", "C"));
        OutputUtils.printOutput(4, MetricService.getAverageLatencyTrace(graph, "A", "E", "B", "C", "D"));
        OutputUtils.printOutput(5, MetricService.getAverageLatencyTrace(graph, "A", "E", "D"));
        OutputUtils.printOutput(8, MetricService.getLengthOfShortestTrace(graph, "A", "C"));
        OutputUtils.printOutput(9, MetricService.getLengthOfShortestTrace(graph, "B", "B"));
    }
}

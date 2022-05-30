package org.example;

import org.example.graph.DirectedWeightedGraph;
import org.example.metric.MetricConditionType;
import org.example.metric.MetricService;
import org.example.util.GraphUtils;
import org.example.util.OutputUtils;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        String input = "AB5, BC4, CD8, DC8, DE6, AD5, CE2, EB3, AE7";
        DirectedWeightedGraph<String> graph = GraphUtils.readGraphFromString(input);

        OutputUtils.printOutput(1, MetricService.getAverageLatencyTrace(graph, Arrays.asList("A", "B", "C")));
        OutputUtils.printOutput(2, MetricService.getAverageLatencyTrace(graph, Arrays.asList("A", "D")));
        OutputUtils.printOutput(3, MetricService.getAverageLatencyTrace(graph, Arrays.asList("A", "D", "C")));
        OutputUtils.printOutput(4, MetricService.getAverageLatencyTrace(graph, Arrays.asList("A", "E", "B", "C", "D")));
        OutputUtils.printOutput(5, MetricService.getAverageLatencyTrace(graph, Arrays.asList("A", "E", "D")));
        OutputUtils.printOutput(6, MetricService.getNumberOfTracesByHops(graph, "C", "C", 4, MetricConditionType.LESS_THAN));
        OutputUtils.printOutput(7, MetricService.getNumberOfTracesByHops(graph, "A", "C", 4, MetricConditionType.EQUALS));
        OutputUtils.printOutput(10, MetricService.getNumberOfTracesByLatency(graph, "C", "C", 30, MetricConditionType.LESS_THAN));
    }
}

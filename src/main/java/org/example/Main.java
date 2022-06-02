package org.example;

import org.example.graph.DirectedWeightedGraph;
import org.example.metric.MetricConditionType;
import org.example.metric.MetricService;
import org.example.util.GraphUtils;
import org.example.util.IOUtils;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        try {
            String filename = IOUtils.getFilenameFromArgs(args);
            String graphString = IOUtils.readTxtFileAsString(filename);
            DirectedWeightedGraph<String> graph = GraphUtils.readGraphFromString(graphString);

            IOUtils.printOutput(1, MetricService.getAverageLatencyTrace(graph, Arrays.asList("A", "B", "C")));
            IOUtils.printOutput(2, MetricService.getAverageLatencyTrace(graph, Arrays.asList("A", "D")));
            IOUtils.printOutput(3, MetricService.getAverageLatencyTrace(graph, Arrays.asList("A", "D", "C")));
            IOUtils.printOutput(4, MetricService.getAverageLatencyTrace(graph, Arrays.asList("A", "E", "B", "C", "D")));
            IOUtils.printOutput(5, MetricService.getAverageLatencyTrace(graph, Arrays.asList("A", "E", "D")));
            IOUtils.printOutput(6, MetricService.getNumberOfTracesByHops(graph, "C", "C", 4, MetricConditionType.LESS_THAN));
            IOUtils.printOutput(7, MetricService.getNumberOfTracesByHops(graph, "A", "C", 4, MetricConditionType.EQUALS));
            IOUtils.printOutput(8, MetricService.getLengthOfShortestTrace(graph, "A", "C"));
            IOUtils.printOutput(9, MetricService.getLengthOfShortestTrace(graph, "C", "C"));
            IOUtils.printOutput(10, MetricService.getNumberOfTracesByLatency(graph, "C", "C", 30, MetricConditionType.LESS_THAN));
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("An error occurred. Exiting the program");
        }
    }
}

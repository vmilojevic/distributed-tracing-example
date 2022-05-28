package org.example.service;

import org.example.graph.Label;
import org.example.graph.MyGraph;

import java.util.LinkedList;
import java.util.Queue;

public class MyMetricService {

    public static Integer getAverageLatencyTrace(MyGraph<String> graph, String... nodes) {
        int sum = 0;
        for (int i = 0; i < nodes.length - 1; i++) {
            try {
                int weight = graph.getEdgeWeight(nodes[i], nodes[i + 1]);
                sum += weight;
            } catch (IllegalArgumentException e) {
                return null;
            }
        }
        return sum;
    }

    public static void bfs(MyGraph<String> graph, String start, String end, int n) {
        int count = 0;
        int depth = 0;
        Queue<Label> queue = new LinkedList<>();

        queue.add(new Label(start, depth, null));
        while (depth <= n) {
            Label l = queue.poll();
            System.out.print(l.getName());
            System.out.println(l.getValue());
//            if (l.getName().equals(end) && l.getValue() == n) {
//                System.out.println(getPath(l));
//            }
            depth = l.getValue();
            var adj = graph.getAdjacentNodes(l.getName());
            for (String a: adj) {
                if (a.equals(end) && depth == n) {
                    count++;
                    System.out.println(getPath(new Label(a, depth, l)));
                }
                queue.add(new Label(a, depth + 1, l));
            }
        }
        System.out.println("COUNT: " + count);
    }

    private static String getPath(Label l) {
        StringBuilder t = new StringBuilder(l.getName());
        Label p = l;
        while (p.getPrev() != null) {
            t.append(p.getPrev().getName());
            p = p.getPrev();
        }

        return t.reverse().toString();
    }
}

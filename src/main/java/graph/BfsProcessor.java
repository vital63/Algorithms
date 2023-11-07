package graph;

import static java.util.function.Predicate.not;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

public class BfsProcessor {

    private final Graph graph;

    private final Queue<Integer> queue = new LinkedList<>() {
    };

    private final List<Integer> visitedIndexes = new ArrayList<>();

    public BfsProcessor(Graph graph, int v) {
        this.graph = graph;
        process(v);
    }

    public List<Graph.Vertex> getResult() {
        return visitedIndexes.stream().map(graph::getVertex).toList();
    }

    private void process(int v) {
        queue.add(v);
        while (!queue.isEmpty()) {
            int current = queue.poll();
            visitedIndexes.add(current);
            graph.findAdjoinedVertexes(current).stream()
                    .filter(not(visitedIndexes::contains))
                    .forEach(queue::add);
        }
    }
}

package graph;

import static java.util.function.Predicate.not;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class DfsProcessor {

    private final Graph graph;

    private final Stack<Integer> stack = new Stack<>();

    private final List<Integer> visitedIndexes = new ArrayList<>();

    public DfsProcessor(Graph graph, int v) {
        this.graph = graph;
        process(v);
    }

    public List<Graph.Vertex> getResult() {
        return visitedIndexes.stream().map(graph::getVertex).toList();
    }

    private void process(int v) {
        visitVertex(v);
        while (!stack.empty()) {
            graph.findAdjoinedVertexes(stack.peek()).stream()
                    .filter(not(visitedIndexes::contains))
                    .findFirst()
                    .ifPresentOrElse(this::visitVertex, stack::pop);
        }
    }

    private void visitVertex(int v) {
        visitedIndexes.add(v);
        stack.push(v);
    }
}

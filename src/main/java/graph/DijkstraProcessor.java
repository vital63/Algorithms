package graph;

import static java.util.function.Predicate.not;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

public class DijkstraProcessor {

    private final Graph graph;
    private final Set<Integer> visitedIndexes = new HashSet<>();
    private final Map<Integer, Path> shortestPaths = new HashMap<>();

    public DijkstraProcessor(Graph graph, int v) {
        this.graph = graph;
        process(v);
    }

    public Map<Integer, Path> getResult() {
        return shortestPaths;
    }

    private void process(int startVertex) {
        visitedIndexes.add(startVertex);
        graph.findAdjoinedVertexes(startVertex)
                .forEach(i -> {
                    Integer distance = graph.getDistance(startVertex, i);
                    shortestPaths.put(i, new Path(distance, List.of(startVertex)));
                });
        Optional<Integer> indexMinOptional;
        while ((indexMinOptional = getUnvisitedNearestToTree()).isPresent()) {
            int indexMin = indexMinOptional.get();
            visitedIndexes.add(indexMin);
            updateShortestPaths(indexMin);
        }
    }

    private Optional<Integer> getUnvisitedNearestToTree() {
        return visitedIndexes.stream()
                .map(graph::findAdjoinedVertexes)
                .flatMap(List::stream)
                .filter(not(visitedIndexes::contains))
                .min((i1, i2) -> Integer.compare(
                        shortestPaths.get(i1).getDistance(),
                        shortestPaths.get(i2).getDistance())
                );
    }

    private void updateShortestPaths(int currentVertex) {
        int distanceToCurrent = shortestPaths.get(currentVertex).getDistance();
        graph.findAdjoinedVertexes(currentVertex).stream()
                .filter(not(visitedIndexes::contains))
                .forEach(adjoinedIndex -> {
                    int distanceToAdjoined = distanceToCurrent + graph.getDistance(currentVertex, adjoinedIndex);

                    boolean needToUpdate = !shortestPaths.containsKey(adjoinedIndex)
                            || shortestPaths.get(adjoinedIndex).getDistance() > distanceToAdjoined;

                    if (needToUpdate) {
                        List<Integer> newParents = new ArrayList<>(shortestPaths.get(currentVertex).getParentVertices());
                        newParents.add(currentVertex);
                        shortestPaths.put(adjoinedIndex, new Path(distanceToAdjoined, newParents));
                    }
                });
    }
}

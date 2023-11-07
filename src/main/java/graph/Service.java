package graph;

import java.util.List;

public class Service {
    public static void main(String[] args) {
//        Graph graph = buildGraph();
//
//        List<Graph.Vertex> result = new DfsProcessor(graph, 0).getResult();
//        System.out.println("Dfs Visits: ");
//        System.out.println(result.stream().map(Graph.Vertex::label).toList());
//
//        result = new BfsProcessor(graph, 0).getResult();
//        System.out.println("Bfs Visits: ");
//        System.out.println(result.stream().map(Graph.Vertex::label).toList());

        Graph graphWithDistance = buildGraphWithDistance();
        new DijkstraProcessor(graphWithDistance, 0);
    }

    public static Graph buildGraph() {
        Graph graph = new Graph(List.of('A', 'B', 'C', 'D', 'E', 'F', 'G'));
        graph.addEdge('A', 'B');
        graph.addEdge('A', 'C');
        graph.addEdge('A', 'D');
        graph.addEdge('B', 'E');
        graph.addEdge('D', 'F');
        graph.addEdge('F', 'G');
        return graph;
    }

    public static Graph buildGraphWithDistance() {
        Graph graph = new Graph(List.of('A', 'B', 'C', 'D', 'E', 'F', 'G'));

        graph.addEdge('A', 'B', 3);
        graph.addEdge('A', 'C', 5);
        graph.addEdge('A', 'D', 7);
        graph.addEdge('B', 'E', 6);
        graph.addEdge('C', 'E', 4);
        graph.addEdge('C', 'D', 3);
        graph.addEdge('D', 'F', 3);
        graph.addEdge('E', 'G', 6);
        graph.addEdge('F', 'G', 4);

        return graph;
    }
}

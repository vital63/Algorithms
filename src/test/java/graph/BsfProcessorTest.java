package graph;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;

class BsfProcessorTest {

    @Test
    void test_bsf() {
        //given
        Graph graph = buildGraph();
        //when
        List<Graph.Vertex> result = new BfsProcessor(graph, 0).getResult();
        //then
        List<Character> labels = result.stream().map(Graph.Vertex::label).toList();
        assertThat(labels).isEqualTo(List.of('A', 'B', 'C', 'D', 'E', 'F', 'G'));
    }

    private static Graph buildGraph() {
        Graph graph = new Graph(List.of('A', 'B', 'C', 'D', 'E', 'F', 'G'));
        graph.addEdge('A', 'B');
        graph.addEdge('A', 'C');
        graph.addEdge('A', 'D');
        graph.addEdge('B', 'E');
        graph.addEdge('D', 'F');
        graph.addEdge('F', 'G');
        return graph;
    }
}
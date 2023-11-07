package graph;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;

class DijkstraProcessorTest {

    @Test
    void test_dsf() {
        //given
        Graph graph = buildGraph();
        //when
        Map<Integer, Path> result = new DijkstraProcessor(graph, 0).getResult();
        //then
        Path pathToG = result.get(6);
        assertThat(pathToG.getDistance()).isEqualTo(14);
        assertThat(pathToG.getParentVertices()).isEqualTo(List.of(0, 3, 5));
    }

    public static Graph buildGraph() {
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
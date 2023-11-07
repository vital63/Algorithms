package graph;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class Graph {

    public record Vertex(char label){}

    private final List<Vertex> vertexList = new ArrayList<>(); //массив вершин

    private final Integer[][] adjoiningMatrix; // матрица смежности

    public Graph(List<Character> labels) {
        addVertexes(labels);
        int size = labels.size();
        adjoiningMatrix = new Integer[size][size];

        for (int i = 0; i < size; i++) {// матрица смежности заполняется
            for (int k = 0; k < size; k++) { // бесконечными расстояниями
                adjoiningMatrix[i][k] = 1000000; // задания значений по умолчанию
            }
        }
    }

    public List<Integer> findAdjoinedVertexes(int v) {
        return IntStream.range(0, vertexList.size()).boxed()
                .filter(j -> adjoiningMatrix[v][j] == 1)
                .toList();
    }

    public Vertex getVertex(int j) {
        return vertexList.get(j);
    }

    public int getIndex(Vertex v) {
        return vertexList.indexOf(v);
    }

    public int getCountOfVertises() {
        return vertexList.size();
    }

    private void addVertexes(List<Character> labels) {
        labels.forEach(this::addVertex);
    }

    private void addVertex(char label) {
        vertexList.add(new Vertex(label));
    }

    public void addEdge(char startLabel, char endLabel) {
        int start = getIndex(new Vertex(startLabel));
        int end = getIndex(new Vertex(endLabel));
        addEdge(start, end);
    }

    public void addEdge(char startLabel, char endLabel, int distance) {
        int start = getIndex(new Vertex(startLabel));
        int end = getIndex(new Vertex(endLabel));
        addEdge(start, end, distance);
    }

    public void addEdge(int start, int end) {
        adjoiningMatrix[start][end] = 1;
        adjoiningMatrix[end][start] = 1;
    }

    public void addEdge(int start, int end, int distance) {
        adjoiningMatrix[start][end] = distance;
    }

    public int getDistance(int start, int end) {
        return adjoiningMatrix[start][end];
    }
}

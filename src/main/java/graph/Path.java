package graph;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class Path { // объект данного класса содержащий расстояние и предыдущие и пройденные вершины

    private int distance; // текущая дистанция от начальной вершины
    private List<Integer> parentVertices; // текущий родитель вершины

    public Path(int distance) {
        this.distance = distance;
        this.parentVertices = new ArrayList<>();
    }
}

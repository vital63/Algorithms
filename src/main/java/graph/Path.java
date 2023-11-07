package graph;

import java.util.List;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class Path { // объект данного класса содержащий расстояние и предыдущие и пройденные вершины

    private final int distance; // текущая дистанция от начальной вершины
    private final List<Integer> parentVertices; // текущий родитель вершины
}

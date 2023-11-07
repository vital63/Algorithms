package graph;

import java.util.ArrayList;
import java.util.List;

public class DijkstraProcessor {

    private static final int INFINITY = 1000000;

    private final Graph graph;
    private final List<Integer> visitedIndexes = new ArrayList<>();
    private int countOfVertexInTree = 0; // количество рассмотренных вершин в дереве
    private final List<Path> shortestPaths = new ArrayList<>(); // список данных кратчайших путей
    private int currentVertex; // текущая вершина
    private int startToCurrent; //расстояние до currentVertex

    public DijkstraProcessor(Graph graph, int v) {
        this.graph = graph;
        process(v);
    }

    public void getResult() {

    }

    private void process(int v) {
        //  задание данных для стартовой вершины
        int startTree = v; // стартуем с вершины v
        visitedIndexes.add(v);// включение в состав дерева первого элемента
        countOfVertexInTree = 1;

        // заполнение коротких путей для вершин смежных с стартовой
        for (int i = 0; i < graph.getCountOfVertises(); i++) {
            int tempDist = graph.getDistance(startTree, i);
            Path path = new Path(tempDist);
            path.getParentVertices().add(0);// первым родительским элементом, будет всегда стартовая вершина
            shortestPaths.add(path);
        }
        // пока все вершины не окажутся в дереве
        while (countOfVertexInTree < graph.getCountOfVertises()) { // выполняем, пока количество вершин в дереве не сравняется с общим количеством вершин
            int indexMin = getMin();//получаем индекс вершины с наименшей дистанцией, из вершин еще не входящих в дерево
            int minDist = shortestPaths.get(indexMin).getDistance();// минимальная дистанция вершины, из тек которые ещё не в дереве

            if (minDist == INFINITY) {
                System.out.println("В графе пристувствуют недостижимые вершины");
                break;// в случае если остались непройденными только недостижимые вершины, мы выходим из цикла
            } else {
                currentVertex = indexMin; // переводим указатель currentVert к текущей вершине
                startToCurrent = shortestPaths.get(indexMin).getDistance();// задаем дистанцию к текущей вершине
            }
            visitedIndexes.add(currentVertex);  //включение текущей вершины в дерево
            countOfVertexInTree++; // увеличиваем счетчик вершин в дереве
            updateShortestPaths(); // обновление списка кратчайших путей
        }
        displayPaths(); // выводим в консоль результаты
    }
    public void clean() { // очиска дерева
        countOfVertexInTree = 0;
        visitedIndexes.clear();
    }

    private int getMin() {
        int minDist = INFINITY; // за точку старта взята "бесконечная" длина
        int indexMin = 0;
        for (int i = 1; i < graph.getCountOfVertises(); i++) {// для каждой вершины
            if (!visitedIndexes.contains(i) && shortestPaths.get(i).getDistance() < minDist) {
                // если вершина ещё не в дереве и её расcтояние меньше старого минимума
                minDist = shortestPaths.get(i).getDistance(); // задаётся новый минимум
                indexMin = i; // обновление индекса вершины содержащую минимаьную дистанцию
            }
        }
        return indexMin; //возвращает индекс вершины с наименшей дистанцией, из вершин еще не входящих в дерево
    }

    private void updateShortestPaths() {
        int vertexIndex = 1; // стартовая вершина пропускается
        while (vertexIndex < graph.getCountOfVertises()) { // перебор столбцов

            if (visitedIndexes.contains(vertexIndex)) { // если вершина column уже включена в дерево, она пропускается
                vertexIndex++;
                continue;
            }
            // вычисление расстояния для одного элемента sPath получение ребра от currentVert к column
            int currentToFringe = graph.getDistance(currentVertex, vertexIndex);
            // суммирование всех расстояний
            int startToFringe = startToCurrent + currentToFringe;
            // определение расстояния текущего элемента vertexIndex
            int shortPathDistance = shortestPaths.get(vertexIndex).getDistance();

            // сравнение расстояния через currentVertex с текущим расстоянием в вершине с индексом vertexIndex
            if (startToFringe < shortPathDistance) {// если меньше, то у вершины под индексом vertexIndex будет задан новый кратчайший путь
                List<Integer> newParents = new ArrayList<>(shortestPaths.get(currentVertex).getParentVertices());//создаём копию списка родителей
                // вершины currentVert
                newParents.add(currentVertex);// задаём в него и currentVertex как предыдущий
                shortestPaths.get(vertexIndex).setParentVertices(newParents); // соохраняем новый маршут
                shortestPaths.get(vertexIndex).setDistance(startToFringe); // соохраняем новую дистанцию
            }
            vertexIndex++;
        }
    }

    private void displayPaths() { // метод для вывода кратчайших путей на экран
        for (int i = 0; i < graph.getCountOfVertises(); i++) {
            System.out.print(graph.getVertex(i).label() + " = ");
            if (shortestPaths.get(i).getDistance() == INFINITY) {
                System.out.println("0");
            } else {
                String result = shortestPaths.get(i).getDistance() + " (";
                List<Integer> parents = shortestPaths.get(i).getParentVertices();
                for (int j = 0; j < parents.size(); j++) {
                    result += graph.getVertex(parents.get(j)).label() + " -> ";
                }
                System.out.println(result + graph.getVertex(i).label() + ")");
            }
        }
    }
}

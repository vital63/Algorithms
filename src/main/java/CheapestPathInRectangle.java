import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/**
 * 2. Самый дешевый путь
 * легкаяdynamic programming 2D
 * В каждой клетке прямоугольной таблицы
 * �
 * ×
 * �
 * N×M записано некоторое число. Изначально игрок находится в левой верхней клетке. За один ход ему разрешается перемещаться в соседнюю клетку либо вправо, либо вниз (влево и вверх перемещаться запрещено). При проходе через клетку с игрока берут столько килограммов еды, какое число записано в этой клетке (еду берут также за первую и последнюю клетки его пути).
 *
 * Требуется найти минимальный вес еды в килограммах, отдав которую игрок может попасть в правый нижний угол.
 *
 * Формат ввода
 * Вводятся два числа N и M — размеры таблицы (
 * 1
 * ≤
 * �
 * ≤
 * 20
 * 1≤N≤20,
 * 1
 * ≤
 * �
 * ≤
 * 20
 * 1≤M≤20). Затем идет N строк по M чисел в каждой — размеры штрафов в килограммах за прохождение через соответствующие клетки (числа от 0 до 100).
 *
 * Формат вывода
 * Выведите минимальный вес еды в килограммах, отдав которую можно попасть в правый нижний угол.
 *
 * Пример 1
 * Ввод
 * 5 5
 * 1 1 1 1 1
 * 3 100 100 100 100
 * 1 1 1 1 1
 * 2 2 2 2 1
 * 1 1 1 1 1
 * Вывод
 * 11
 */

public class CheapestPathInRectangle {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] nm = reader.readLine().split(" ");
        int n = Integer.parseInt(nm[0]);
        int m = Integer.parseInt(nm[1]);
        int[][] board = new int[n][m];
        for (int i = 0; i < m; i++) {
            String[] row = reader.readLine().split(" ");
            for (int j = 0; j < n; j++) {
                board[i][j] = Integer.parseInt(row[j]);
            }
        }
        int result = calculateSteps(board);
        writer.write(String.valueOf(result));

        reader.close();
        writer.close();
    }

    private int result;

    public static int calculateSteps(int[][] board) {
        CheapestPathInRectangle service = new CheapestPathInRectangle();
        service.result = Integer.MAX_VALUE;
        int n = board[0].length;
        int m = board.length;
        service.calculateSteps(board, 0, 0, n, m, 0);
        return service.result;
    }

    private void calculateSteps(int[][] board, int x, int y, int n, int m, int cost) {
        cost += board[y][x];
        if (x + 1 == n && y + 1 == m) {
            result = Math.min(result, cost);
        }
        if (x + 1 < n) {
            calculateSteps(board, x + 1, y, n, m, cost);
        }
        if (y + 1 < m) {
            calculateSteps(board, x, y + 1, n, m, cost);
        }
    }

}

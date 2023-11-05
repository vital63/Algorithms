import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
class CheapestPathInRectangleTest {

    @Test
    void test1() {
        doTest(new int[][]{
                        {1, 1, 1, 1, 1},
                        {3, 100, 100, 100, 100},
                        {1, 1, 1, 1, 1},
                        {2, 2, 2, 2, 1},
                        {1, 1, 1, 1, 1}
                },
                11);
    }

    @Test
    void test2() {
        doTest(new int[][]{
                        {1},
                        {3},
                        {1},
                        {2},
                        {1}
                },
                8);
    }

    @Test
    void test3() {
        doTest(new int[][]{{1, 3, 1, 2, 1}}, 8);
    }

    @Test
    void test4() {
        doTest(new int[][]{{10}}, 10);
    }

    @Test
    void test5() {
        doTest(new int[][]{
                        {1, 100},
                        {1, 100},
                        {1, 1},
                        {100, 1},
                        {100, 1}
                },
                6);
    }

    void doTest(int[][] board, int expectedResult) {
        int result = CheapestPathInRectangle.calculateSteps(board);
        assertThat(result).isEqualTo(expectedResult);
    }
}
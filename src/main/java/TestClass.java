import java.util.List;

public class TestClass {

    public static void main(String[] args) {
        List<Integer> list = List.of(6, 1, 8, 1, 6);
        Integer result = list.stream()
                .reduce((i1, i2) -> i1 ^ i2).get();

        System.out.println(result);
    }
}

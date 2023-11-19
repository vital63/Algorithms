import java.util.ArrayList;
import java.util.List;

public class LongestIncreasingSubsequence {

    public static void main(String[] args) {
        List<Integer> input = List.of(2, 3, 6, 4, 1, 3, 5, 4, 7);
        int result = calculate(input);
        System.out.println(result);

    }

    public static int calculate(List<Integer> input) {
        System.out.println("i: " + input);
        List<Integer> dp = new ArrayList<>();
        input.forEach(ignore -> dp.add(1));
        System.out.println(0 + ": " + dp);
        for (int i = 1; i < input.size(); ++i) {
            for (int j = 0; j < i; ++j) {
                if (input.get(j) < input.get(i)) {
                    dp.set(i, Math.max(dp.get(i), dp.get(j) + 1));
                }
            }
            System.out.println(i + ": " + dp);
        }
        return dp.stream().max(Integer::compareTo).get();
    }
}

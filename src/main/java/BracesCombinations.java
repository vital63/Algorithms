import java.util.ArrayList;
import java.util.List;

public class BracesCombinations {

    public static void main(String[] args) {
        for (int n = 1; n <=15; n++) {
            List<String> strings = generate(n);
            strings.forEach(System.out::println);
        }
    }

    private static final StringBuilder accum = new StringBuilder();

    private static List<String> generate(int n) {
        List<String> result = new ArrayList<>();
        generate(n, n, result);
        return result;
    }

    private static void generate(
        int leftOpen,
        int leftClosed,
        List<String> result
    ) {
        if (leftOpen == 0 && leftClosed == 0) {
            result.add(accum.toString());
            return;
        }
        if (leftOpen > 0) {
            accum.append('(');
            generate(leftOpen - 1, leftClosed, result);
            accum.setLength(accum.length() - 1);
        }
        if (leftOpen < leftClosed) {
            accum.append(')');
            generate(leftOpen, leftClosed - 1, result);
            accum.setLength(accum.length() - 1);
        }
    }
}

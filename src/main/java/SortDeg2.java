import java.util.Arrays;

public class SortDeg2 {

    public static void main(String[] args) {
        int[] input = new int[]{-6, -4, 2, 3, 4, 8};
        int[] res = new int[input.length];

        int left = 0;
        int right = input.length - 1;
        int cur = input.length - 1;
        while (cur >= 0) {
            res[cur--] = Math.abs(input[left]) < Math.abs(input[right])
                    ? (int)Math.pow(input[right--], 2)
                    : (int)Math.pow(input[left++], 2);
        }
        System.out.println(Arrays.stream(res).boxed().toList());
    }
}

public class BitShift {

    public static void main(String[] args) {
        int i = 0b11111111;
        printBinary(i);
        i = i << 24;
        printBinary(i);
        i = (i & 0b11111111000000000000000000000000) >> 24;
        printBinary(i);
    }

    public static void printBinary(int i) {
        System.out.println("0b" + Integer.toBinaryString(i));
    }
}

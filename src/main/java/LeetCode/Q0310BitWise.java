package LeetCode;


public class Q0310BitWise {

    /**
     * 计算int的32位二进制
     * @param num
     */
    public static void print(int num) {
        for (int i = 31; i >= 0; i--) {
            System.out.print((num & (1 << i)) == 0 ? "0" : "1");
        }
    }

    public static void main(String[] args) {
        int num = 12;
        print(num);
    }
}

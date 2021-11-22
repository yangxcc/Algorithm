package 剑指offer;

/**
 * 打印从1到最大的n位数
 * 输入数字 n，按顺序打印出从 1 到最大的 n 位十进制数。比如输入 3，则打印出 1、2、3 一直到最大的 3 位数 999。
 */
public class offer17 {
    // 这道题没什么好说的
    public int[] printNumbers(int n) {
        if (n < 1) {
            return new int[0];
        }
        int length = (int) Math.pow(10, n) - 1;
        int[] res = new int[length];
        for (int i = 0; i < length; i++) {
            res[i] = i + 1;
        }
        return res;
    }
}

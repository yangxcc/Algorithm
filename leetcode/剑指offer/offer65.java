package 剑指offer;

/**
 * 不用加减乘除做加法
 *
 * https://leetcode-cn.com/problems/bu-yong-jia-jian-cheng-chu-zuo-jia-fa-lcof/
 *
 * 位运算的知识点
 */
public class offer65 {
    /**
     * 这里需要记住的知识点
     * 异或 ^ 运算相当于无进位加法
     * 并 & 运算相当于每位的进位数
     */
    public int add(int a, int b) {
        // 当进位不为0的时候
        while (b != 0) {
            int c = (a & b) << 1;   // 左移是因为：比如这一位上是1+1，那么进位应该加到左边的那一位上
            a ^= b;    // a = a ^ b, b = (a & b) << 1
            b = c;
        }
        return a;
    }
}

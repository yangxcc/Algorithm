package 剑指offer;

/**
 * 斐波那契数列
 *
 * 题目链接：https://leetcode-cn.com/problems/fei-bo-na-qi-shu-lie-lcof/
 * */
public class offer10_1 {

    // 斐波那契数列很容易就能够写出来，但是这样在leetcode中时间通不过去
    public int fib01(int n) {
        if (n < 2) {
            return n;
        }
        return (fib01(n - 1) + fib01(n - 2)) % 1000000007;  // 防止两个数加起来溢出
    }

    // 使用动态规划的思想
    public int fib02(int n) {
        int[] dp = new int[n + 1];
        dp[0] = 0;
        dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n];
    }
}

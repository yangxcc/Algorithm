package 剑指offer;

/**
 * 青蛙跳台阶
 *
 * 一只青蛙一次可以跳上1级台阶，也可以跳上2级台阶。求该青蛙跳上一个 n 级的台阶总共有多少种跳法。
 *
 * 答案需要取模 1e9+7（1000000007），如计算初始结果为：1000000008，请返回 1。
 *
 * 链接：https://leetcode-cn.com/problems/qing-wa-tiao-tai-jie-wen-ti-lcof
 **/
public class offer10_2 {

    /**
     * 其实这个题和斐波那契数列一样，不同的是base case不一样
     * @param n
     * @return
     */
    public int numWays01(int n) {
        if (n < 2) {
            return 1; // base case,fib中返回的是n
        }
        return (numWays01(n - 1) + numWays01(n - 2)) % 1000000007;
    }

    public int numWays02(int n) {
        int[] dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n];
    }
}

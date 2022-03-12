package dp.背包问题.完全背包;

/**
 * 爬楼梯，扩展一下题意
 * 共有m阶台阶，一步能够走一个台阶，两个台阶，三个台阶，.......，直到 m个台阶。
 * 问有多少种不同的方法可以爬到楼顶呢？
 */
public class ClimbingStairs {
    /**
     * 其实这道题就能够将1,2,..m看作物品，台阶高度n看作背包
     * 我们要做的就是通过物品填满这个背包，其中每个物品的数量都是无限的
     *
     * @param n  台阶高度
     * @param m  1,2,...m
     * @return dp[n]
     */
    public int climbStairs(int n, int m) {
        int[] dp = new int[n + 1];
        dp[0] = 1;
        // 很显然，1，2，1和1，1，2是不同的走法，因此这个属于排列
        for (int i = 0; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if (i >= m) {
                    dp[i] += dp[i - m];
                }
            }
        }
        return dp[n];
    }

    // 对于经典的爬楼梯，只需要将m=2带入即可
}

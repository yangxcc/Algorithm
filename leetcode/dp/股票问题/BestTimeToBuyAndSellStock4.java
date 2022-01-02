package dp.股票问题;

/**
 * 设计一个算法来计算你所能获取的最大利润。你最多可以完成 k 笔交易。
 *
 * 这里规定了最多可以完成交易的次数
 * 所以状态又多了一个，现在的状态变成了 天数，交易的次数，是否持有股票
 */
public class BestTimeToBuyAndSellStock4 {
    public int maxProfit(int k, int[] prices) {
        int n = prices.length;
        if (n == 0) {
            return 0;
        }

        // leetcode中可以没有这个判断语句
//        if (k > n / 2) {
//            // 相当于k没有限制
//            // 因为一次交易至少需要两天
//            BestTimeToBuyAndSellStock2.maxProfit(prices);
//        }
        // dp[i][j][0]表示在第i天不持有股票，最大交易次数限额为k的最大利润
        int[][][] dp = new int[n][k + 1][2];
        for (int i = 0; i < n; i++) {
            dp[i][0][0] = 0;    // 能够发生0次交易的最大利润
            dp[i][0][1] = Integer.MIN_VALUE;  // 能够发生0次交易但是却持有了股票，这显然是不合法的，所以给他附上一个最小值
        }

        for (int i = 0; i < n; i++) {
            // 这里k从小到大，和从大到小都可以进行遍历，因为dp[i][k] 不会依赖 dp[i][k - 1/k+1]，而是依赖 dp[i - 1][k - 1/k+1]，
            // 也就是依赖于上一层的，所以无论是k--还是k++都是能够算出结果来的
            // 但是我为什么使用k++通过不了所有测试用例
            for (int j = k; j >= 1; j--) {
                if (i - 1 == -1) {
                    dp[i][j][0] = 0;
                    dp[i][j][1] = -prices[i];
                    continue;
                }
                dp[i][j][0] = Math.max(dp[i - 1][j][0], dp[i - 1][j][1] + prices[i]);
                // 这里要买入股票，所以最多能够做的交易数-1
                dp[i][j][1] = Math.max(dp[i - 1][j][1], dp[i - 1][j - 1][0] - prices[i]);
            }
        }
        return dp[n - 1][k][0];
    }
}

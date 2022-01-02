package dp.股票问题;

/**
 * 你可以尽可能地完成更多的交易（多次买卖一支股票）。
 * <p>
 * 和第一题的不同之处就在于它能够进行多次交易，所以只需要改动一下状态方程就好了
 */
public class BestTimeToBuyAndSellStock2 {
    public static int maxProfit(int[] prices) {
        int n = prices.length;
        if (n == 0) {
            return 0;
        }
        // dp[i][0]表示第i天不持有股票的最大利润
        int[][] dp = new int[n][2];
        // base case,初始化
        dp[0][0] = 0;          // 第一天不持有股票的最大利润当然是0
        dp[0][1] = -prices[0]; // 第一天持有股票，也就是第一天就购入了股票，这时候最大利润为0-prices[0]

        for (int i = 1; i < n; i++) {
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i]);
            dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] - prices[i]);
            // 这里一定要注意，因为题目中说了可以有多次交易，
            // 所以要是发生了买入，最大利润就要从dp[i - 1][0]的基础上来减去prices[i]
        }
        return dp[n - 1][0];
    }
}

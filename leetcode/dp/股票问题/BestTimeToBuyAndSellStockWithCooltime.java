package dp.股票问题;

/**
 * 最佳买卖股票时机含冷冻期
 *
 * 卖出股票后，你无法在第二天买入股票 (即冷冻期为 1 天)。
 */
public class BestTimeToBuyAndSellStockWithCooltime {
    public int maxProfit(int[] prices){
        int n = prices.length;
        if (n == 0) {
            return 0;
        }
        // dp[i][0]表示第i天不持有股票的最大利润
        int[][] dp = new int[n][2];
        // base case,初始化
        dp[0][0] = 0;          // 第一天不持有股票的最大利润当然是0
        dp[0][1] = -prices[0]; // 第一天持有股票，也就是第一天就购入了股票，这时候最大利润为0-prices[0]

        for (int i = 1; i < n; i++) {  // 状态

            if (i - 2 == -1) {
                dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i]);
                dp[i][1] = Math.max(dp[i - 1][1], -prices[i]);
                continue;   // 可不要忘了continue，因为这种情况下下面的就不要再去执行了
            }

            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i]);

            // 这里需要注意，因为有冷静期，所以买入股票的时候，买入的是它前两天的股票，所以还需要加上base case，
            // 在for循环里面加上判断也行
            dp[i][1] = Math.max(dp[i - 1][1], dp[i - 2][0] - prices[i]);

        }
        return dp[n - 1][0];
    }
}

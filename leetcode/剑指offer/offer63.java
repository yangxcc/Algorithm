package 剑指offer;

/**
 * 股票的最大利润
 */
public class offer63 {
    public int maxProfit(int[] prices) {
        if (prices.length == 0) {
            return -1;
        }
        int max = -1;
        int[] dp = new int[prices.length];
        // dp[i]的含义就是第i天卖出能够获得的最大收益
        dp[0] = 0;  // 其实默认值本来就是0
        for (int i = 1; i < prices.length; i++) {
            dp[i] = Math.max(0, dp[i - 1] + prices[i] - prices[i - 1]);
            // dp[i - 1] + prices[i] - prices[i - 1]表示第i-1天不买了，第i天去买
            max = Math.max(max, dp[i]);
        }
        return max;
    }
}

package dp.股票问题;

/**
 * 买卖股票的最佳时机
 *
 * 给定一个数组 prices ，它的第 i 个元素 prices[i] 表示一支给定股票第 i 天的价格。
 *
 * 你只能选择 某一天 买入这只股票，并选择在 未来的某一个不同的日子 卖出该股票。设计一个算法来计算你所能获取的最大利润。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
// 股票问题是典型的动态规划
public class BestTimeToBuyAndSellStock {
    // 可以看到这道题目中，只会发生一次交易
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
            // 这里就不用 for (int j = 0; j < 2; j++)了，因为第二个状态总共就两种选择，持有或者不持有，直接写出来就行
            // 我们知道dp[i][0]的含义是第i天不持有股票的最大利润，
            // 那么dp[i][0]的状态总共有两种：一种是在i-1天的时候就不持有股票；另一种是在i-1天的时候持有了股票，但是在第i天卖出去了
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i]);
            // dp[i][1]表示第i天持有股票的最大利润
            // 那么dp[i][1]的状态总共有两种:一种是在i-1天的时候就持有股票，另一种是在i-1天的时候不持有股票，但是在第i天买入了
            dp[i][1] = Math.max(dp[i - 1][1], -prices[i]);
            // 这里一定要注意，因为题目中说了只有一次交易，所以只要是发生了买入，最大利润就变成了-prices[i]，因为买入之前的利润肯定是0
        }
        return dp[n - 1][0];
    }
}

package dp.背包问题;

/**
 * 经典的01背包问题
 * 给你一个可装载重量为W的背包，和N件物品，每个物品有重量和价值两种属性，其中第i件物品的重量为wt[i]，
 * 价值为val[i]，现在让这个背包装物品，最多能装的价值是多少
 */
public class Packet {
    public int knapsack(int W, int N, int[] weights, int[] values) {
        // dp[i][j]的含义表示前i个物品，在重量j以内的最大价值是多少，所以返回值就是dp[N][W]
        int[][] dp = new int[N + 1][W + 1];
        // base case
//        for (int i = 0; i <= N; i++) {
//            // 第一列
//            dp[i][0] = 0;   // 当重量为0的时候，能够装的最大价值当然是0了
//        }
//        for (int j = 0; j <= W; j++) {
//            // 第一行
//            dp[0][j] = 0;   // 没有物品，肯定也是0
//        }
//        // 其实在这道题里面不用初始化，因为数组的默认值就是0

        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= W; j++) {
                // 对于第i个物品的选择是将他加入背包，或者是不将它加入背包
//                dp[i][j] = dp[i - 1][j];  // 不加入背包
//                dp[i][j] = dp[i - 1][j - weights[i - 1]] + values[i - 1];  // 因为下标是从0开始，所以这里第i个元素的下标是i-1
                if (j - weights[i - 1] < 0) {
                    dp[i][j] = dp[i - 1][j];  // 这种情况下只能够不选择i号物品了
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - weights[i - 1]] + values[i - 1]);
                }
            }
        }


        return dp[N][W];
    }
}

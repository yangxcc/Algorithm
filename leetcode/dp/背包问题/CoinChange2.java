package dp.背包问题;

/**
 * 给你一个整数数组 coins 表示不同面额的硬币，另给一个整数 amount 表示总金额。
 *
 * 请你计算并返回可以凑成总金额的硬币组合数。如果任何硬币组合都无法凑出总金额，返回 0 。
 *
 * 假设每一种面额的硬币有无限个。 
 *
 * 题目数据保证结果符合 32 位带符号整数。
 *
 * 和CoinChange的区别就在于本题是求能够凑出amount的组合数，而CoinChange是求能够凑出amount的最小硬币数
 *
 * 两道题中硬币的数量都是无限的，所以他们又被称为完全背包问题
 */
public class CoinChange2 {
    public int change(int amount, int[] coins) {
        // 先使用二维dp
        // dp[i][j]表示前i个硬币能够凑成j的组合数
        int[][] dp = new int[coins.length + 1][amount + 1];
        // dp[0][..]表示硬币为0，此时组合数肯定为0
        // dp[..][0]表示背包容量为0，此时组合数为1
        for (int row = 0; row <= coins.length; row++) {
            dp[row][0] = 1;
        }

        for (int i = 1; i <= coins.length; i++) {
            for (int j = 1; j <= amount; j++) {
                // 组合数肯定就是选择这个硬币和不选这个硬币的和
                // dp[i - 1][j]不选择这个硬币
                // dp[i][j - coins[i - 1]]选择这个硬币，因为i-1表示第i个硬币
                // 选择这个硬币为什么不是i-1
                if (j - coins[i - 1] >= 0) {
                    dp[i][j] = dp[i][j - coins[i - 1]] + dp[i - 1][j];
                } else {
                    // 只能做一种选择，那就是不选
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }
        return dp[amount][coins.length];
    }

    // 把二维数组压缩成一维数组
    public int change2(int amount, int[] coins) {
        // dp[j]表示凑成j的最小组合数
        int[] dp = new int[amount + 1];
        dp[0] = 1;

        for (int i = 0; i <= coins.length; i++) {
            for (int j = 0; j <= amount; j++) {
                if (j - coins[i] >= 0) {
                    dp[j] = dp[j - coins[i]] + dp[j];
                }
            }
        }
        return dp[amount];
    }
}

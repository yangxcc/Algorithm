package dp;

import java.util.Arrays;

/**
 * 零钱兑换
 * 给你一个整数数组 coins ，表示不同面额的硬币；以及一个整数 amount ，表示总金额。
 */

// 暴力递归是自顶向下
// 动态规划dp是自底向上
public class CoinChange {

    public int coinChange(int[] coins, int amount){
        if (coins.length == 0 || amount <= 0) {
            return 0;
        }
        return dp(coins, amount);
    }

    // 表示凑到amount需要的最小硬币数
    public int dp(int[] coins, int amount) {
        if (amount == 0) {
            return 0;
        }
        if (amount < 0) {
            return -1;
        }
        int res = Integer.MAX_VALUE;
        for (int i = 0; i < coins.length; i++) {
            int subProblem = dp(coins, amount - coins[i]);
            if (subProblem == -1) {  // 当前这个钱币不合适，换下一个
                continue;
            }
            res = Math.min(res, subProblem) + 1;
        }
        return res == Integer.MAX_VALUE ? -1 : res;
    }


    // 上面这种方法的时间复杂度很高，因为他需要遍历整颗的决策树，所以我们可以使用备忘录来降低部分的时间复杂度
    public int coinChange2(int[] coins, int amount) {
        if (coins.length == 0 || amount <= 0) {
            return 0;
        }
        memo = new int[coins.length];
        return dp(coins, amount);
    }

    int[] memo;
    // 可以在dp方法的基础上加上一个备忘录，用来裁剪决策树
    public int dp2(int[] coins, int amount) {
        if (amount == 0) {
            return 0;
        }
        if (amount < 0) {
            return -1;
        }
        if (memo[amount] != -666) {
            return memo[amount];
        }
        int res = Integer.MAX_VALUE;
        for (int coin : coins) {
            int sub = dp2(coins, amount - coin);
            if (sub == -1) continue;
            res = Math.min(res, sub) + 1;
        }
        memo[amount] = res == Integer.MAX_VALUE ? -1 : res;
        return memo[amount];
    }


    // 无论是添加直接暴力递归还是添加了一个备忘录的递归，都是自顶向下的解决方式
    // 我们这里使用dp数组，自底向上的来解决这个问题
    public int coinChange3(int[] coins, int amount) {
        // dp[i]表示的就是凑成i所需零钱的最小个数
        int[] dp = new int[amount + 1];
        // dp数组初始化
        Arrays.fill(dp, amount + 1);  // 初始化表示凑成i所需要的零钱的个数都是大于amount的，因为实际运行中最大就是amount个就能凑出amount来
        dp[0] = 0;
        for (int i = 0; i < dp.length; i++) {
            for (int coin : coins) {
                if (i - coin > 0) {
                    dp[i] = Math.min(dp[i], dp[i - coin]) + 1;
                }
            }
        }
        return dp[amount] == amount + 1 ? -1 : dp[amount];
    }
}

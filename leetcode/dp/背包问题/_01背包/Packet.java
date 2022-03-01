package dp.背包问题._01背包;

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

    // 状态压缩，从上面的状态方程可以看出，dp[i][j]相关的值都是和它上面的i-1的值有关，也就是上一层
    public int knapsack2(int W, int N, int[] weights, int[] values) {
        // dp[j]表示背包容量为j，对于下标为[0-i]内的物品进行选择，能够得到的最大价值
        int[] dp = new int[W + 1];
        dp[0] = 0;  // 背包容量为0的时候，价值肯定也是0
        /**
         * 对于一维数组，需要注意两个点：
         * 一是 一定要先遍历物品，再去遍历背包容量，二维数组中顺序可以倒换
         *      因为一维数组中，如果是遍历物品在内层循环，那么对于一个背包容量，无论大小，都只是放入一件物品的重量
         * 二是 背包的重量要从大到小遍历，为的是避免重复选择，
         *      举一个例子：物品0的重量weight[0] = 1，价值value[0] = 15
         *      如果是正序遍历，dp[1] = dp[1 - weight[0]] + value[0] = 15; dp[2] = dp[2 - weight[0]] + value[0] = 30
         *      dp[2]=30这意味着1号物品被选择了两次，在dp[1]中被选择了，在dp[2]中又被选择了一次
         *      如果是倒序遍历，dp[2] = dp[2 - weight[0]] + value[0] = 15;  dp[1] = dp[1 - weight[0]] + value[0] = 15
         *      所以从后往前循环，每次取得状态不会和之前取得状态重合，这样每种物品就只取一次了。
         */
        for (int i = 0; i < weights.length; i++) {
            for (int j = W; j >= weights[i]; j--) {
                dp[j] = Math.max(dp[j - weights[i]] + values[i], dp[j]);
            }
        }
        return dp[W];
    }

}

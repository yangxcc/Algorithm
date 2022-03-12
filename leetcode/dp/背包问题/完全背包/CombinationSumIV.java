package dp.背包问题.完全背包;

/**
 * 给你一个由 不同 整数组成的数组 nums ，和一个目标整数 target 。
 * 请你从 nums 中找出并返回总和为 target 的元素组合的个数。
 */
public class CombinationSumIV {
    // 这一道题，有条件说明了：顺序不同的序列被视作不同的组合。
    // 也就是说，这道题其实是一个排列问题，因此，先遍历背包，在遍历物品
    public int combinationSum4(int[] nums, int target) {
        int[] dp = new int[target + 1];
        dp[0] = 1;
        // 因为顺序不同的序列被视为不同的组合，所以可以看成排列
        for (int i = 0; i <= target; i++) {
            for (int j = 0; j < nums.length; j++) {
                if (i >= nums[j]) {
                    dp[i] += dp[i - nums[j]];
                }
            }
        }
        return dp[target];
    }
}

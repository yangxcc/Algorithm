package dp.子序列;

import java.util.Arrays;

/**
 * 最长递增子序列
 * <p>
 * 给你一个整数数组 nums ，找到其中最长严格递增子序列的长度。
 * <p>
 * 子序列是由数组派生而来的序列，删除（或不删除）数组中的元素而不改变其余元素的顺序。
 * 例如，[3,6,2,7] 是数组 [0,3,1,6,2,2,7] 的子序列。
 * <p>
 * 因为子序列不一定是连续的，所以不能使用滑动窗口
 */
public class LongestIncreasingSubsequence {
    public int lengthOfLIS(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }

        // dp[i]表示nums[0, ... i]的最长子序列
        int[] dp = new int[nums.length];
        // dp初始化
        Arrays.fill(dp, 1);  // 最长子序列都是自己本身
        int res = 0;
        for (int i = 1; i < nums.length; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                    // 最长子序列是dp数组中的最大值，所以需要找到dp数组中的最大值
                    res = Math.max(res, dp[i]);
                }
            }
        }
        return res;
    }

    // 表示nums[0, ... i]的最长子序列
//    public int dp(int[] nums, int i) {
//
//    }
}

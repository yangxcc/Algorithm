package dp.子序列;

/**
 * 最大子数组和
 *
 * 给你一个整数数组 nums ，请你找出一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
 *
 * 子数组 是数组中的一个连续部分。
 *
 * 虽然子数组是连续的，但是由于题目中给出的用例中有负数，所以还是不可以使用滑动窗口
 * nums = [-2,1,-3,4,-1,2,1,-5,4]
 */
public class MaximumSubarray {
    public int maxSubArray(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        // dp[i]表示以i结尾的有最大和的连续子数组
        // 即nums[0, ..i]中有最大和的连续子数组
        int[] dp = new int[nums.length];
        // dp初始化
        dp[0] = nums[0];
        int res = nums[0];
        for (int i = 1; i < dp.length; i++) {
            if (dp[i - 1] > 0) {
                dp[i] = dp[i - 1] + nums[i];
            } else {
                dp[i] = nums[i];
            }
            res = Math.max(res, dp[i]);
        }
        return res;
    }
}

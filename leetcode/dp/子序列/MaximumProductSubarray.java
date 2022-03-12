package dp.子序列;

import java.util.Arrays;

/**
 * 给你一个整数数组 nums ，请你找出数组中乘积最大的非空连续子数组（该子数组中至少包含一个数字），并返回该子数组所对应的乘积。
 *
 * 测试用例的答案是一个 32-位 整数。
 *
 * 子数组 是数组的连续子序列。
 *
 */
public class MaximumProductSubarray {
    // dp[i] = Math.max(dp[i - 1] * nums[i], nums[i])，dp[i]表示的是以nums[i]结尾的连续子数组的最大成绩
    // 对于上面这个状态转移方程很容易就能得出来，但是这个状态转移是不对的，比如[5,6,-3,4,-3]
    // 对于上面这个例子，如果按照上述状态转移方程，得到的dp数组是[5,30,-3,4,-3]
    // 正确的应该是[5,30,-90,-360,1080]
    // 这是因为如果nums[i]是一个负数的话，我们希望dp[i-1]也是一个负数，而且尽可能负的大，也就是尽量小
    // 如果nums[i]是一个证书的话，我们希望dp[i-1]是一个正数，而且尽可能正的多，也就是尽量大
    // 这样我们就能够得到状态转移方程了
    // dp_max = max(dp_max(i-1) * nums[i], dp_min(i-1) * nums[i], nums[i])
    // dp_min = min(dp_max(i-1) * nums[i], dp_min(i-1) * nums[i], nums[i])
    public int maxProduct(int[] nums) {
        int n = nums.length;
        int[] dp_max = new int[n];
        int[] dp_min = new int[n];
        System.arraycopy(nums, 0, dp_max, 0, n);
        System.arraycopy(nums, 0, dp_min, 0, n);

        for (int i = 1; i < n; i++) {
            dp_max[i] = Math.max(dp_max[i - 1] * nums[i], Math.max(nums[i], dp_min[i - 1] * nums[i]));
            dp_min[i] = Math.min(dp_max[i - 1] * nums[i], Math.min(nums[i], dp_min[i - 1] * nums[i]));
        }

        int res = nums[0];
        for (int i = 1; i < n; i++) {
            res = Math.max(res, dp_max[i]);
        }
        return res;
    }
}

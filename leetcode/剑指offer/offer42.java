package 剑指offer;

/**
 * 连续子数组的最大和
 * */
public class offer42 {
    public int maxSubArray(int[] nums) {
        if (nums.length == 0) return Integer.MIN_VALUE;
        int[] dp = new int[nums.length];
        // dp[i]表示的是以i为结尾，0-i位置的数的最大和
        dp[0] = nums[0];
        int max = Integer.MIN_VALUE;
        for (int i = 1; i < nums.length; i++) {
            dp[i] = Math.max(dp[i - 1] + nums[i], nums[i]);
            max = Math.max(dp[i], max);
        }
        return max;
    }
}

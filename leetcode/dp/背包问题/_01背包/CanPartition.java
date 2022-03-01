package dp.背包问题._01背包;

/**
 * 分割等和的子集
 *
 * 给你一个 只包含正整数 的 非空 数组 nums 。请你判断是否可以将这个数组分割成两个子集，使得两个子集的元素和相等。
 *
 * 输入：nums = [1,5,11,5]
 * 输出：true
 * 解释：数组可以分割成 [1, 5, 5] 和 [11] 。
 */
public class CanPartition {
    public boolean partition(int[] nums) {
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        if (sum % 2 == 1) {
            return false;   // 如果sum是奇数的话，那么是一定不会凑出两份完全相等的来
        }
        sum /= 2;

        int n = nums.length;
        boolean[][] dp = new boolean[n + 1][sum + 1];
        // dp[i][j]表示的是前i个数能否凑出j来
        // dp[0][..]当没有数的时候，肯定凑不出j来，所以dp[0][..] = false;使用默认值就行
        // dp[..][0]当j == 0的时候，不需要凑了，返回true
        for (int i = 0; i <= n; i++) {
            dp[i][0] = true;
        }

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= sum; j++) {
                if (j - nums[i - 1] >= 0) {
                    // dp[i - 1][j] 表示不选择nums[i]这个数，因此此时的状态就应该延续上一个
                    dp[i][j] = dp[i - 1][j] || dp[i - 1][j - nums[i - 1]];
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }
        return dp[n][sum];
    }

    // 可以看到dp[i][j]都是通过上一行dp[i-1][..]转移过来的，所以能够将二维数组压缩成一维
    public boolean partition2(int[] nums) {
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        if (sum % 2 == 1) {
            return false;   // 如果sum是奇数的话，那么是一定不会凑出两份完全相等的来
        }
        sum /= 2;

        // dp[j]表示能不能凑出j来
        boolean[] dp = new boolean[sum + 1];
        // base case
        dp[0] = true;

        for (int i = 0; i < nums.length; i++) {
            // 需要注意的有两点，分别是
            //    j 应该从后往前反向遍历，因为每个物品（或者说数字）只能用⼀次，以免之前的结果影响其他的结果。
            //    先遍历物品，在遍历背包
            for (int j = sum; j >= 0; j--) {
                if (j - nums[i] >= 0) {
                    dp[j] = dp[j] || dp[j - nums[i]];
                }
            }
        }
        return dp[sum];
    }
}

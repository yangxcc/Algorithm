package dp.打家劫舍;

/**
 * 这个地方所有的房屋都 围成一圈 ，这意味着第一个房屋和最后一个房屋是紧挨着的
 * <p>
 * 输入：nums = [2,3,2]
 * 输出：3
 * 解释：你不能先偷窃 1 号房屋（金额 = 2），然后偷窃 3 号房屋（金额 = 2）, 因为他们是相邻的。
 */
public class HouseRobber_II {
    // 这道题，刚一上来我以为直接取模就行了，因为对于有环的题目取模是比较好的一种办法
    // 但是对这道题没成功
    // 换一种思路，即使房子连成了环，受到影响的其实也只有第一个房子和第二个房子
    // 所以这里就出现了三种情况
    //  1. 选了第一间房子，但是没选最后一间房子
    //  2. 选了最后一间房子，但是没选第一间房子
    //  3. 两个房子都没有选
    // 第三种情况是肯定不如第1和2中情况大的，因为两头不会影响到中间房子的选择，所以加上只会增大
    public int rob(int[] nums) {
        int n = nums.length;
        if (n == 1) {
            return nums[0];
        }
        // 所以可以定义一个函数，意义是求[start, end]这个闭区间内的最大利润
        return Math.max(dfs(nums, 0, n - 2),   // 选了第一个，不选最后一个
                        dfs(nums, 1, n - 1));  // 不选第一个，选最后一个
    }

    public int dfs(int[] nums, int start, int end) {
        int n = nums.length;
        int[] dp = new int[n + 2];
        for (int i = end; i >= start; i--) {
            dp[i] = Math.max(dp[i + 1], dp[i + 2] + nums[i]);
        }
        return dp[start];
    }
}

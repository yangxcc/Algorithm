package dp.打家劫舍;

/**
 * 如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警。
 * 输入：[1,2,3,1]
 * 输出：4
 * 解释：偷窃 1 号房屋 (金额 = 1) ，然后偷窃 3 号房屋 (金额 = 3)。
 *      偷窃到的最高金额 = 1 + 3 = 4 。
 *
 */

// 其实最开始看到这个题的时候，因为前一天刚刚看了股票问题，所以很自然而然的就想到了使用二维DP来解决问题，如下
public class HouseRobber_I {
    public int rob(int[] nums) {
        int n = nums.length;
        // dp[i][0]表示目前一共抢了i个房子，但是对于第i个房子不抢的最大利润
        // dp[i][1]表示目前一共抢了i个房子，但是对于第i个房子抢的最大利润
        int[][] dp = new int[n][2];
        dp[0][0] = 0;
        dp[0][1] = nums[0];
        for (int i = 1; i < n; i++) {
            // 第i号房子不抢，那么选出第i-1号房子抢和不抢的最大利润就好了
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1]);
            if (i - 2 == -1) {
                dp[i][1] = dp[i - 1][0] + nums[i];
                continue;
            }
            // 抢i号房子，那么需要比较i-2号房子抢没抢，和i - 1号房子没抢的最大值
            dp[i][1] = max(dp[i - 2][0], dp[i - 2][1],dp[i - 1][0]) + nums[i];
        }
        return Math.max(dp[n - 1][0], dp[n - 1][1]);
    }

    public int max(int a, int b, int c) {
        return Math.max(a, Math.max(b, c));
    }


    // 虽然能够使用二维dp解决问题，但是如果先使用我们正常的递归来看的话，使用一维dp也可以
    // 先看看递归 dfs
    // rob2的含义是从第i个房子出发，能够获得的最大利益
    // return rob2(nums, 0);
    int[] memo;
    // memo = new int[n]; Arrays.fill(memo, -1)
    public int rob2(int[] nums, int i) {
        if (i >= nums.length) {   // 因为后面有i+1，i+2，所以要使用大于等于
            return 0;
        }
        if (memo[i] != -1) {
            return memo[i];
        }
        memo[i] = Math.max(nums[i] + rob2(nums, i + 2), rob2(nums, i + 1));
        // 对于i这个房子，有两种选择，抢或不抢
        // 抢了的话，i+1就不能抢了，也就是nums[i] + rob2(nums, i + 2)
        // 不抢的话，直接跳过去就行了，看看i+1的选择，rob2(nums, i + 1)
//        return Math.max(nums[i] + rob2(nums, i + 2), rob2(nums, i + 1));
        return memo[i];
        // 可以加上个备忘录
    }


    // 把上面的dfs改成动态规划，可以改成一维的
    // dp
    // 在改成dp的时候，我遇到了一个坑，如果是把dp[i]的含义定义成直到第i个房间，能够获得的最大利润
    // 也就是说，我们的遍历顺序是从前往后，当i=0，i=1的时候很麻烦，所以我们这时候
    // 应该会一个思路，改变dp数组的含义，让他从后往前遍历
    // 于是，我们定义dp[i]的含义是从i出发，能够抢劫的最大利润
    // 所以，这时候返回的就是dp[0]
    public int rob3(int[] nums) {
        int n = nums.length;
        int[] dp = new int[n + 2];
        dp[n + 1] = 0;
        dp[n] = 0;
        for (int i = n - 1; i >= 0; i--) {
            // dp[i + 1]就表示i房子没抢
            dp[i] = Math.max(dp[i + 2] + nums[i], dp[i + 1]);
        }
        return dp[0];
    }

    // 从上面的一维dp可以看出，dp[i]只和dp[i + 1]以及dp[i+2]有关系，所以还可以进一步状态压缩
    // 让空间复杂度变成O(1)
    public int rob4(int[] nums) {
        int n = nums.length;
        int v_i_1 = 0;   // 代表i+1的值
        int v_i_2 = 0;   // 代表i+2的值
        int v_i = 0;     // 代表i的值
        for (int i = n - 1; i >= 0; i--) {
            v_i = Math.max(v_i_1, v_i_2 + nums[i]);
            v_i_2 = v_i_1;
            v_i_1 = v_i;
        }
        return v_i;
    }
}

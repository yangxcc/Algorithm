package dp.背包问题._01背包;

import javax.swing.plaf.IconUIResource;

/**
 * 给你一个整数数组 nums 和一个整数 target 。
 *
 * 向数组中的每个整数前添加 '+' 或 '-' ，然后串联起所有整数，可以构造一个 表达式 ：
 *
 * 例如，nums = [2, 1] ，可以在 2 之前添加 '+' ，在 1 之前添加 '-' ，然后串联起来得到表达式 "+2-1" 。
 * 返回可以通过上述方法构造的、运算结果等于 target 的不同 表达式 的数目。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/target-sum
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class TargetSum {

    // 方法1：直接使用dfs，深度遍历
    public int findTargetSumWays(int[] nums, int target) {
        dfs(nums, target, 0, 0);
        return ans;
    }

    int ans = 0;
    public void dfs(int[] nums, int target, int index, int curSum) {
        if (index == nums.length) {
            if (curSum == target) ans++;
            return;
        }
        dfs(nums, target, index + 1, curSum + nums[index]);
        dfs(nums, target, index + 1, curSum - nums[index]);
    }

    // 方法2：使用回溯穷举
    // 因为回溯就涉及到了在nums数组中找到数据来凑成target，见方法3中的分析
    public int findTargetSumWays2(int[] nums, int target) {
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        // 题目中给定了0 <= nums[i] <= 1000
        if (sum < Math.abs(target)) return 0;

        if ((sum + target) % 2 != 0) return 0;  // 比如sum = 5, target = 2这种情况下是没法凑出来的

        int x = (sum + target) / 2;
        if (x < 0) x = -x;

        backtrack(nums, x, 0, 0);
        return ans;
    }

    public void backtrack(int[] nums, int target, int curSum, int index) {
        if (curSum == target) {
            ans++;
//            return;
        }
        for (int i = index; i < nums.length; i++) {
            // 做选择
            curSum += nums[i];
            backtrack(nums, target, curSum, i + 1);
            // 撤销选择
            curSum -= nums[i];
        }
    }

    // 方法3：使用动态规划，因为是求的组合数，而不需要得到组合路径，所以可以是用动态规划
    // 因为这道题中涉及到了是加上这个数还是减去这个数，所以需要重新找一下这个target
    public int findTargetSumWays3(int[] nums, int target) {
        // 假设x是所有取+号的数的和，sum为nums中给所有数据的和，那么sum-x就是所有取-号的数的和
        // 根据题意：x - (sum - x) = target ---> x = (sum + target) / 2;
        // 因此，我们可以去找nums中能够组成x的组合数
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        // 题目中给定了0 <= nums[i] <= 1000
        if (sum < Math.abs(target)) return 0;

        if ((sum + target) % 2 != 0) return 0;  // 比如sum = 5, target = 2这种情况下是没法凑出来的

        int x = (sum + target) / 2;
        if (x < 0) x = -x;
        // dp[j]表示凑出j的组合数
        int[] dp = new int[x + 1];
        // 已经有一个1（nums[i]） 的话，有 dp[4]种方法 凑成 dp[5]
        // 已经有一个2（nums[i]） 的话，有 dp[3]种方法 凑成 dp[5]
        // 已经有一个3（nums[i]） 的话，有 dp[2]中方法 凑成 dp[5]
        // 已经有一个4（nums[i]） 的话，有 dp[1]中方法 凑成 dp[5]
        // 已经有一个5（nums[i]） 的话，有 dp[0]中方法 凑成 dp[5]
        for (int i = 0; i < nums.length; i++) {   // 先遍历物品
            for (int j = x; j >= nums[i]; j--) {  // 再遍历背包，还要倒叙遍历背包
                dp[j] += dp[j - nums[i]];
            }
        }
        return dp[x];
    }

}

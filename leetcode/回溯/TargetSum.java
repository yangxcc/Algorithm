package 回溯;

import java.util.Arrays;

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
    /**
     * 这道题我是这么想的，对于nums数组，其中肯定是分成了两个部分：一部分做加法，另一部分做减法
     * 假设做加法的数据之和为x，那么做减法的数据之和就是sum-x，那么对于target，那就是
     * x - (sum - x) = target   --->  x = (sum + target) / 2
     * 所以我们可以通过组合的方式，在nums中通过回溯，来找到这个x，有多少种找到x的方法，就是多少种找到target的方法
     *
     * @param nums    从这个给定的数组做选择
     * @param target  目标值
     * @return        能够满足要求的个数
     */

    int ans = 0;
    public int findTargetSumWays(int[] nums, int target) {
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        // 如果nums种的数据全都取正或者全都取负都比target小，那么只能说明nums中的数无法构成target
        if (sum < Math.abs(target)) return 0;

        int x = (sum + target) / 2;
        if ((sum + target) % 2 != 0) {
            return 0;  // 如果不能整除2，向下取整，会导致做加法的x实际上少了1
        }

        Arrays.sort(nums);
        backtrack(nums, x, 0, 0);
        return ans;
    }

    public void backtrack(int[] nums, int target, int index, int curSum) {
        if (curSum == target) {
            ans++;
//            return;
            // 后面有可能还行，比如+1，又-1，所以这里不要有return了
        }
        if (index == nums.length) return;
        for (int i = index; i < nums.length; i++) {
            curSum += nums[i];
            if (curSum > target) {
                break;      // 这条路就不行了，所以是break
            }
            backtrack(nums, target, i + 1, curSum);
            curSum -= nums[i];
        }
    }



    public int findTargetSumWaysUsingDP(int[] nums, int target) {
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        // 如果nums种的数据全都取正或者全都取负都比target小，那么只能说明nums中的数无法构成target
        if (sum < Math.abs(target)) return 0;

        int x = (sum + target) / 2;
        if ((sum + target) % 2 != 0) {
            return 0;  // 如果不能整除2，向下取整，会导致做加法的x实际上少了1
        }

        // dp也是去找x
        // int[][] dp = new int[nums.length][x + 1];
        // dp[i][j] 表示0-i个数能够组合出j共有 dp[i][j]种方法
        // 每个数都只能选一次，而且只有一个，所以这是一个01背包问题
        // 可以进行状态压缩
        // dp[j]表示组成成j共有dp[j]种方法
        int[] dp = new int[x + 1];
        dp[0] = 1;
        for (int i = 0; i < nums.length; i++) {
            for (int j = x; j >= nums[i]; j--) {
                dp[j] += dp[j - nums[i]];
            }
        }
        return dp[x];
    }

}

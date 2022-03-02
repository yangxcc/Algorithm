package 前缀和;

import java.util.HashMap;

/**
 * 和为k的子数组
 *
 * 给你一个整数数组 nums 和一个整数 k ，请你统计并返回该数组中和为 k 的连续子数组的个数。以及和为k的连续子数组的最大长度
 */
public class SubarraySumEqualsK {
    public int subArraySum(int[] nums, int k) {
        // 首先，算出一个前缀和数组来
        int n = nums.length;
        if (n < 1) {
            return -1;
        }

        // preSum[i]表示的是0到i-1的和
        int count = 0;  // 等于k的子数组的个数
        int len = 0;    // 计算等于k的子数组的最大长度
        int[] preSum = new int[n + 1];
        preSum[0] = 0;
        for (int i = 1; i <= n; i++) {
            preSum[i] = preSum[i - 1] + nums[i - 1];
        }

        // 时间复杂度是O(n^2)
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j < i; j++) {
                if (preSum[i] - preSum[j] == k) {
                    count++;
                    len = Math.max(len, i - j + 1);
                }
            }
        }
        return count;
    }

    public int subArraySum2(int[] nums, int k) {
        // 首先，算出一个前缀和数组来
        int n = nums.length;
        if (n < 1) {
            return -1;
        }

        // preSum[i]表示的是0到i-1的和
        int count = 0;  // 等于k的子数组的个数
        int len = 0;    // 计算等于k的子数组的最大长度
//        int[] preSum = new int[n + 1];
//        preSum[0] = 0;

        // key用来记录前缀和，value用来该前缀和出现的次数
        HashMap<Integer, Integer> hm = new HashMap<>();
        // 这里为什么要加上这个base case，不加上就不对了呢
        // 因为这个base case的作用就是为了当preSum和k第一次相等的时候用
        hm.put(0, 1);

        int preSum = 0;
        for (int i = 0; i < n; i++) {
            preSum += nums[i];
            if (hm.containsKey(preSum - k)) {
                count += hm.get(preSum - k);
            }
            hm.put(preSum, hm.getOrDefault(preSum, 0) + 1);
        }

//        // 如果是求长度，那么HashMap的key存放的是preSum，value存放的是index，索引，即0-index
//        HashMap<Integer, Integer> memo = new HashMap<>();
//        memo.put(0, 0);
//        int maxLen = 0;
//        int preSum1 = 0;
//        for (int i = 0; i < n; i++) {
//            preSum1 += nums[i];
//            if (memo.containsKey(preSum1 - k)) {
//                maxLen = Math.max(maxLen, i - memo.get(preSum1 - k) + 1);
//            }
//            memo.put(preSum1, i);
//        }
        return count;
    }
}

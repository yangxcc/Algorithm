package dp.背包问题._01背包;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * 最后一块石头的重量
 * 有一堆石头，用整数数组 stones 表示。其中 stones[i] 表示第 i 块石头的重量。
 * <p>
 * 每一回合，从中选出任意两块石头，然后将它们一起粉碎。假设石头的重量分别为 x 和 y，且 x <= y。那么粉碎的可能结果如下：
 * <p>
 * 如果 x == y，那么两块石头都会被完全粉碎；
 * 如果 x != y，那么重量为 x 的石头将会完全粉碎，而重量为 y 的石头新重量为 y-x。
 * 最后，最多只会剩下一块 石头。返回此石头 最小的可能重量 。如果没有石头剩下，就返回 0。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/last-stone-weight-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LastStoneWeight2 {
    // 这道题的思路其实就是“分割等和的子集”这道题
    // 因为这道题比较难想到的一个点就是  尽量把这堆石头分成重量尽可能接近的两堆
    public static int lastStoneWeightII(int[] stones) {
        int n = stones.length;
        int sum = 0;
        for (int i = 0; i < n; i++) {
            sum += stones[i];
        }
        int target = sum / 2;
        // 这里不能直接用sum /= 2，因为如果sum是奇数的话，后面sum * 2 != sum除2之前的
        // 比如23 / 2 = 11,  11 * 2 = 22

        // dp[j]表示j重量的石头
        int[] dp = new int[target + 1];
        dp[0] = 0;
        // 先遍历物品，在遍历重量
        for (int i = 0; i < n; i++) {
            // 重量从大到小，确保每块石头都只被放入背包一次
            for (int j = target; j >= stones[i]; j--) {
                dp[j] = Math.max(dp[j], dp[j - stones[i]] + stones[i]);
            }
        }

        System.out.println(sum);
        System.out.println(Arrays.toString(dp));

        return sum - dp[target] * 2;
        // sum * 2表示所有石头的总重量
        // sum * 2 - dp[sum]表示凑出了sum/2之后，剩余石头的重量
        // 两堆石头之差就是这道题的答案，即最后剩余的一块石头的最小重量
    }

    public static void main(String[] args) {
        int[] nums = {2,7,4,1,8,1};
        System.out.println(lastStoneWeightII(nums));
    }
}

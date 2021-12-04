package 二分;

/**
 * 传送带上的包裹必须在 D 天内从一个港口运送到另一个港口。
 * <p>
 * 传送带上的第 i 个包裹的重量为 weights[i]。每一天，我们都会按给出重量的顺序往传送带上装载包裹。
 * 我们装载的重量不会超过船的最大运载重量。
 * <p>
 * 返回能在 D 天内将传送带上的所有包裹送达的船的最低运载能力。
 * <p>
 * 链接：https://leetcode-cn.com/problems/capacity-to-ship-packages-within-d-days
 */

// 很明显，这道题也能够根据运载能力作为自变量，将运算天数作为因变量，
// 会有一个单调递减函数

public class CapacityToShipPackagesWithinDays {
    public int f(int[] nums, int x) {
        int days = 0;
        for (int i = 0; i < nums.length; ) {
            while (i < nums.length) {
                if (nums[i] < x) {
                    x -= nums[i];
                    i++;
                } else {
                    break;
                }
            }
            days++;
        }
        return days;
    }

    public int shipWithinDays(int[] weights, int days) {
        // 这时候运载能力的最小值应该是weights中的最大值，保证每天都能够有货物被运送
        // 运载能力的最大值是weights数组的和，一天都运完
        int left = 0, right = 0;
        for (int i = 0; i < weights.length; i++) {
            left = Math.max(left, weights[i]);
            right += weights[i];
        }
        while (left < right) {
            int mid = left + (right - left) / 2;
            // 一定要注意，这是单调递减函数，不要框架中的升序
            if (f(weights, mid) <= days) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }
}

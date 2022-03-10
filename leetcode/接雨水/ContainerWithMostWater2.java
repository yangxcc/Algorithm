package 接雨水;

/**
 * 接雨水问题改编2，字节面试原题
 * 柱子没有宽度，求的是这些柱子最多能够存多少水
 * ContainerWithMostWater中问的是两个柱子之间最多能成多少水
 *
 */
public class ContainerWithMostWater2 {
    public static int process(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        // 这个可以过两遍单调栈
        // 从左到右过一遍，然后从右到左过一遍
        int highestIndex = 0;
        int res = 0;

        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > nums[highestIndex]) {
                res += nums[highestIndex] * (i - highestIndex);
                highestIndex = i;
            }
        }

        highestIndex = nums.length - 1;
        for (int i = nums.length - 2; i >= 0; i--) {
            if (nums[i] > nums[highestIndex]) {
                res += nums[highestIndex] * (highestIndex - i);
                highestIndex = i;
            }
        }

        return res;
    }

    public static void main(String[] args) {
        int[] nums = {3,2,5,4,6,2};
        System.out.println(process(nums));
    }
}

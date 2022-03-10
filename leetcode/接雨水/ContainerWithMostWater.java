package 接雨水;

/**
 * 这个题其实就是接雨水问题，只不过这道题里面的柱子是没有宽度的
 * 这是让求哪两个杆之间的水是最多的，不是一共能成多少水
 *
 * leetcode 11 : https://leetcode-cn.com/problems/container-with-most-water/
 */
public class ContainerWithMostWater {
    public static int maxArea(int[] height) {
        int left = 0, right = height.length - 1;
        int res = 0;
        while (left < right) {
            res = Math.max(res, Math.min(height[left], height[right]) * (right - left));

            if (height[left] < height[right]) {
                left++;  // 只有只有这样，才有可能提高下界
            } else {
                right--;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        int[] nums = {3,2,5,4,6,2};
        System.out.println(maxArea(nums));
    }
}

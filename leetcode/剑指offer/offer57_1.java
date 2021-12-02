package 剑指offer;

/**
 * 和为s的两个数字
 *
 * 输入一个递增排序的数组和一个数字s，在数组中查找两个数，
 * 使得它们的和正好是s。如果有多对数字的和等于s，则输出任意一对即可
 * */
public class offer57_1 {

    // 双指针
    public int[] twoSum(int[] nums, int target) {
        if (nums.length < 2) {
            return new int[0];
        }
        int left = 0;
        int right = nums.length - 1;
        while (left < right) {
            if (nums[left] + nums[right] == target) {
                return new int[]{nums[left], nums[right]};
            } else if (nums[left] + nums[right] < target) {
                left++;
            } else {
                right--;
            }
        }
        return new int[0];
    }
}

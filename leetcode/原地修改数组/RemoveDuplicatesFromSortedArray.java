package 原地修改数组;

/**
 * 给你一个有序数组 nums ，请你 原地 删除重复出现的元素，使每个元素只出现一次 ，
 * 返回删除后数组的新长度。
 *
 * 不要使用额外的数组空间，你必须在 原地 修改输入数组 并在使用 O(1) 额外空间的条件下完成
 *
 *
 * 链接：https://leetcode-cn.com/problems/remove-duplicates-from-sorted-array
 * */

// 对于这种类型的题目，我们一般需要使用双指针中的快慢指针技巧

public class RemoveDuplicatesFromSortedArray {
    // [0,slow]这个区间内是没有重复数据的区间
    public int removeDuplicates(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        int slow = 0, fast = 0;
        while (fast < nums.length) {
            if (nums[slow] != nums[fast]) {
                nums[slow] = nums[fast];
                slow++;
            }
            fast++;
        }
        return slow + 1;
    }
}

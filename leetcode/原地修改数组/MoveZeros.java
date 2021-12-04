package 原地修改数组;

/**
 * 给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序。
 *
 * 输入: [0,1,0,3,12]
 * 输出: [1,3,12,0,0]
 *
 * 链接 https://leetcode-cn.com/problems/move-zeroes/
 *
 */
public class MoveZeros {
    // 相当于先删除数组中的0元素，然后把后面都补上0元素
    public void moveZeroes(int[] nums){
        if (nums.length == 0) {
            return;
        }
        int slow = 0, fast = 0;
        while (fast < nums.length) {
            if (nums[fast] != 0) {
                slow++;
                nums[slow] = nums[fast];
            }
            fast++;
        }

        // 将[slow,nums.length-1]区间内的元素设置为0
        for (int i = slow; i < nums.length; i++) {
            nums[i] = 0;
        }
    }
}

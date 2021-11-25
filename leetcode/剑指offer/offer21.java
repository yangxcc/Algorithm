package 剑指offer;

/**
 * 调整数组顺序使奇数位于偶数前面
 *
 * 双指针
 *
 * */
public class offer21 {
    public int[] exchange(int[] nums) {
        if (nums.length == 0) {
            return new int[0];
        }
        int left = 0;
        int right = nums.length - 1;
        // 这里应该是小于或者小于等于都无所谓
        // 小雨的话，最后剩下的那个数的位置也没必要懂了，相当于自己和自己交换
        while (left < right) {
            if (judge(nums[left]) && !judge(nums[right])) {
                // 左边是偶数，右边是奇数
                swap(nums, left, right);
                left++;
                right--;
            }
            if (!judge(nums[left])) {
                left++;
            }
            if (judge(nums[right])) {
                right--;
            }
        }
        return nums;
    }

    public void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    // 判断一个数是不是偶数
    public boolean judge (int n) {
        return n % 2 == 0;
    }
}

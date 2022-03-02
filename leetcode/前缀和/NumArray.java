package 前缀和;

import java.util.Arrays;

/**
 * 前缀和通常适用于频繁计算某个区间的和，她能够快速的得到某个区间的和
 */
public class NumArray {
    private static int[] ele;
    // ele[i]表示0到i - 1的元素之和
//    int[] ele;
    public NumArray(int[] nums) {
        int n = nums.length;
//        this.ele = new int[n];
        this.ele = new int[n + 1];  // 这里ele的长度不能是n，因为如果是n的话，ele[3]-ele[0]并不是计算的nums[0..3]的和，而是nums[1,..3]的和
//        ele[0] = nums[0];
        ele[0] = 0; // 这里就要是ele[0] = 0了
        for (int i = 1; i < ele.length; i++) {
            ele[i] = ele[i - 1] + nums[i - 1];
        }
    }

    // 返回left-right之间的总和
    // 这样是包含left和right的
    // 计算[left, right]闭区间的
    public static int sumRange(int left, int right) {
        return ele[right + 1] - ele[left];
    }

    public static void main(String[] args) {
        int[] nums = {-2, 0, 3, -5, 2, -1};
        NumArray x = new NumArray(nums);
        System.out.println(Arrays.toString(x.ele));
        System.out.println(x.sumRange(0, 2));
    }
}

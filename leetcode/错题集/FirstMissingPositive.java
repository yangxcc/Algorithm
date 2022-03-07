package 错题集;

import java.util.Arrays;

/**
 * 缺失的第一个正数
 *
 * 给你一个未排序的整数数组 nums ，请你找出其中没有出现的最小的正整数。
 *
 * 请你实现时间复杂度为 O(n) 并且只使用常数级别额外空间的解决方案。
 *
 * https://leetcode-cn.com/problems/first-missing-positive/
 *
 * 示例 1：
 *
 * 输入：nums = [1,2,0]
 * 输出：3
 * 示例 2：
 *
 * 输入：nums = [3,4,-1,1]
 * 输出：2
 * 示例 3：
 *
 * 输入：nums = [7,8,9,11,12]
 * 输出：1
 */
public class FirstMissingPositive {
    // 使用额外空间
    public int firstMissingPositive(int[] nums) {
        int n = nums.length;
        int[] helper = new int[n];
        for (int num : nums) {
            if (num - 1 < 0 || num - 1 >= n) {
                continue;  // 抛弃不符合条件的数据，让helper中只保存一部分数据即可
            } else {
                helper[num - 1] = 1;   // 比如num=3，那么就让helper[2] = 1，放到了helper的第三个位置
            }
        }

        for (int i = 0; i < n; i++) {
            if (helper[i] != 1) {
                return i;
            }
        }

        return n + 1;  // 如果helper中的位置都被标记了，那么缺失的第一个正数就是n+1
    }


    // 如果不使用额外空间，其实就是对nums数组中的部分数据进行排序，
    // 将符合条件的数据放到相应的位置
    public int firstMissingPositive2(int[] nums) {
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            while (nums[i] > 0 && nums[i] <= n && nums[i] != nums[nums[i] - 1]) {
                swap(nums, i, nums[i] - 1);
            }
        }

        for (int j = 0; j < n; j++) {
            if (nums[j] != j + 1) {
                return j + 1;
            }
        }
        return n + 1;
    }

    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }


    public static int firstMissingNumBiggerThanK(int[] nums, int k) {
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            while (nums[i] - k >= 0 && nums[i] - k < n && nums[i] != nums[nums[i] - k]) {
                swap(nums, i, nums[i] - k);
            }
        }

        System.out.println(Arrays.toString(nums));
        if (nums[0] > k) {
            return k + 1;
        }

        for (int i = 0; i < n; i++) {
            if (nums[i] != i + k) {
                return i + k;
            }
        }
        return n + k;
    }


    public static void main(String[] args) {
        int[] nums = {4, 7,8};
        System.out.println(firstMissingNumBiggerThanK(nums, 4));
    }
}

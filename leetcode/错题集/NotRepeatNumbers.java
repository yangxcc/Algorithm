package 错题集;

import java.util.HashSet;

/**
 * 不重复的数字
 *
 * input: [1, 1, 3, 9, 10, 30, 11, 10, 10, 2] 先递增，后递减，output: 7（不重复数字的个数），空间复杂度O(1)
 */
public class NotRepeatNumbers {
    public static int notRepeatNumbers(int[] nums) {
        // 如果不要求空间复杂度，直接使用HashSet把这些数装起来就好了，最后求一个hastSet.size()
        // 但是给了先递增后递减这一条件，肯定是想让我们用到它
        // 这一条件就是告诉我们这个数组存在最大值，所以我们就从最大值那里开始，往两边遍历
        int maxIndex = 0;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] >= nums[maxIndex]) {
                maxIndex = i;
            } else {
                break;
            }
        }


        int left = maxIndex;
        int right = maxIndex + 1;
        int res = 0;
        while (left >= 0 && right < nums.length) {
            res++;
            if (nums[left] == nums[right]) {
                while (left - 1 >= 0 && nums[left] == nums[left - 1]) {
                    left--;
                }
                while (right + 1 < nums.length && nums[right] == nums[right + 1]) {
                    right++;
                }
                left--;
                right++;
            } else if (nums[left] < nums[right]) {
                // 谁大谁动
                while (right + 1 < nums.length && nums[right] == nums[right + 1]) {
                    right++;
                }
                right++;
            } else {
                while (left - 1 >= 0 && nums[left] == nums[left - 1]) {
                    left--;
                }
                left--;
            }
        }

        while (left >= 0) {
            res++;
            while (left - 1 >= 0 && nums[left] == nums[left - 1]) {
                left--;
            }
            left--;
        }

        while (right < nums.length) {
            res++;
            while (right + 1 < nums.length && nums[right] == nums[right + 1]) {
                right++;
            }
            right++;
        }
        return res;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{1, 1, 3, 9, 10, 30, 11, 10, 10, 2};
        HashSet<Integer> hs = new HashSet<>();
        for (int num : nums) {
            hs.add(num);
        }
        System.out.println(hs.size());
        System.out.println(notRepeatNumbers(nums));
    }
}

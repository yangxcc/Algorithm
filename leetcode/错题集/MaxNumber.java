package 错题集;

import java.util.HashSet;

/**
 * 在先递增后递减数组中找到最大值
 */
public class MaxNumber {
    // 很明显，出这道题的意义不是让我使用排序算法
    public static int maxNumber(int[] nums) {
        int left = 0;
        int right = nums.length - 1;
        while (left + 1 < nums.length && nums[left] == nums[left + 1]) {
            left++;
        }

        while (right - 1 >= 0 && nums[right] == nums[right - 1]) {
            right--;
        }

        while (left < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] > nums[mid + 1]) {
                // 说明最大值肯定在左边,或者就是这个mid
                right = mid;
                while (right - 1 >= 0 && nums[right] == nums[right - 1]) {
                    right--;
                }
            } else {
                left = mid + 1;
                while (left + 1 < nums.length && nums[left] == nums[left + 1]) {
                    left++;
                }
            }
        }
        return left;
    }

    // 这种方式更适合我，但是需要注意，这种方法中没有涉及到去重，
    // 如果数组不是严格递增和递减的，也需要向上面那个方法那样去重
    public static int findPeakElement(int[] nums) {
        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (mid - 1 >= 0 && mid + 1 < nums.length && nums[mid] > nums[mid - 1] && nums[mid] > nums[mid + 1]) {
                return mid;
            } else if (nums[mid] > nums[mid] + 1) {
                // 在左边
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{1, 1, 3, 9, 10, 30, 11, 10, 10, 2};
        int[] nums2 = new int[]{1,2,3,4,5,5,5,5,6,7,3,1};
        int[] nums3 = new int[]{1,2,3,4,5,4,3,2,1};
        System.out.println(maxNumber(nums2));
        System.out.println(findPeakElement(nums3));
    }
}

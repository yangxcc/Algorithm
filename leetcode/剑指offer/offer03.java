package 剑指offer;

import java.util.Arrays;
import java.util.HashSet;

/**
 * 数组中的重复数字
 * 题目：在一个长度为 n 的数组 nums 里的所有数字都在 0～n-1 的范围内。
 * 数组中某些数字是重复的，但不知道有几个数字重复了，也不知道每个数字重复了几次。请找出数组中任意一个重复的数字。
 *
 * 题目链接 https://leetcode-cn.com/problems/shu-zu-zhong-zhong-fu-de-shu-zi-lcof/
 * */
public class offer03 {

    /**
     * 时间复杂度和空间复杂度均为O(N)
     * */
    public static int findRepeatNum01(int[] nums) {
        if (nums.length == 0) {
            return Integer.MIN_VALUE;
        }
        int res = Integer.MIN_VALUE;
        HashSet<Integer> hs = new HashSet<>();
        for (int num : nums) {
            if (hs.contains(num)) {
                res = num;
                break;
            } else {
                hs.add(num);
            }
        }
        return res;
    }

    /**
     * 时间复杂度优化成了O(N)
     * 空间复杂度为O(1)
     * */
    public static int findRepeatNum02(int[] nums) {
        if (nums.length == 0) {
            return Integer.MIN_VALUE;
        }
//        int res = Integer.MIN_VALUE;
        Arrays.sort(nums);   // 时间复杂度O(N*logN)
        int pre = nums[0];
        for (int i = 1; i < nums.length; i++) {   // 最差情况下会把整个数组遍历到最后
            if (pre == nums[i]) {
                return pre;
            }
            pre = nums[i];
        }
        return Integer.MIN_VALUE;
    }


    /**
     * 虽然时间复杂度也是O(N)，但是他比上面那种方法快
     * */
    public static int findRepeatNum03(int[] nums) {
        if (nums.length == 0) {
            return Integer.MIN_VALUE;
        }
        // 因为题目中给出了要求，数组中的数是0~n-1的，所以我们排序可以这样想
        // 可以让 位置i 的地方放元素i。如果位置i的元素不是i的话，
        // 那么我们就把i元素的位置放到它应该在的位置，即 nums[i] 和nums[nums[i]]的元素交换，
        // 这样就把原来在nums[i]的元素正确归位了。
        // 如果发现 要把 nums[i]正确归位的时候，发现nums[i]（这个nums[i]是下标）
        // 那个位置上的元素和要归位的元素已经一样了，说明就重复了，重复了就return

        for (int i = 0; i < nums.length; i++) {
            while (nums[i] != i) {
                if (nums[i] == nums[nums[i]]) {
                    return nums[i];
                }
                int temp = nums[i];
                nums[i] = nums[temp];
                nums[temp] = temp;
            }
        }
        return Integer.MIN_VALUE;
    }
}

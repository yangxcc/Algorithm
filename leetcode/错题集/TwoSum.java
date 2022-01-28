package 错题集;

import java.util.Arrays;
import java.util.HashMap;

/**
 * 两数之和
 * 给定一个数组，以及一个target值，找到两个数的和为target的下标
 */
public class TwoSum {
    public int[] towSum(int[] nums, int target) {
        if (nums.length == 0) {
            return new int[0];
        }
        // 我的第一种做法
        Arrays.sort(nums);
        int left = 0;
        int right = nums.length - 1;
        while (left < right) {
            if (nums[left] + nums[right] == target) {
                return new int[]{left, right};
            } else if (nums[left] + nums[right] < target) {
                left++;
            } else {
                right--;
            }
        }
        // 这种方案第一个明显的错误之处就在于
        // 数组排序之后，每个数的index发生了变化
        return new int[0];
    }

    // 根据上面的错误，可以使用一个HashMap将index和value对应起来
    public int[] towSum2(int[] nums, int target) {
        if (nums.length == 0) {
            return new int[0];
        }

        HashMap<Integer, Integer> hm = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            hm.put(nums[i], i);
        }

        Arrays.sort(nums);
        int left = 0;
        int right = nums.length - 1;
        while (left < right) {
            if (nums[left] + nums[right] == target) {
                return new int[]{hm.get(nums[left]), hm.get(nums[right])};
            } else if (nums[left] + nums[right] < target) {
                left++;
            } else {
                right--;
            }
        }
        return new int[0];
    }

    // 第二种解法的错误之处在于，如果数组中存在重复值怎么办呢？
    // 比如[3, 3] target = 6
    // 返回值是[1,1]

    public int[] twoSum3(int[] nums, int target) {
        if (nums.length == 0) {
            return new int[0];
        }
        int[] res = new int[2];
        HashMap<Integer, Integer> hm = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (hm.containsKey(nums[i])) {
                res[0] = i;
                res[1] = hm.get(nums[i]);
                return res;
            }
            hm.put(target - nums[i], i);   // hm中存放的是补数和下标
        }
        return res;
    }
}

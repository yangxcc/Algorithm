package 错题集;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 给你一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，
 * 使得 a + b + c = 0 ？请你找出所有和为 0 且不重复的三元组。
 * <p>
 * 注意：答案中不可以包含重复的三元组。
 */
public class ThreeSum {
    public List<List<Integer>> threeSum(int[] nums) {
        if (nums == null || nums.length == 0) {
            return new ArrayList<>();
        }
        // 这道题比较难想到的地方是去重的做法
        // 最简单的去重很明显就是使用HashSet
        // 但是有更好的方法，这种方法在 回溯 - 组合题中用到过
        // 其实 这道题也是能够用回溯解决的，但是回溯能够找到所有和为target的组合
        // 不只是三个数的，有些大材小用了
        Arrays.sort(nums);
        List<List<Integer>> res = new ArrayList<>();
        for (int k = nums.length - 1; k >= 2; k--) {
            while (k < nums.length - 1 && nums[k] == nums[k + 1]) {   // 去重
                continue;
            }
            for (int i = 0, j = k - 1; i < j; ) {
                if (nums[i] + nums[j] == -nums[k]) {
                    List<Integer> temp = new ArrayList<>();
                    temp.add(i);
                    temp.add(j);
                    temp.add(k);
                    res.add(temp);
                    while (i < j && nums[i] == nums[i + 1]) {
                        i++;
                    }
                    while (i < j && nums[j] == nums[j - 1]) {
                        j--;
                    }
                    i++;
                    j--;
                } else if (nums[i] + nums[j] < -nums[k]) {
                    i++;
                } else {
                    j--;
                }
            }
        }
        return res;
    }
}

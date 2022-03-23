package 回溯;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 全排列2
 * 给定一个可包含重复数字的序列 nums ，按任意顺序 返回所有不重复的全排列。
 */
public class Permutations2 {
    List<List<Integer>> res;
    public List<List<Integer>> permuteUnique(int[] nums) {
        res = new ArrayList<>();
        List<Integer> path = new ArrayList<>();
        int n = nums.length;
        boolean[] visited = new boolean[n];
        // 使用下面的去重代码，一定不能忘记要对数组先进行排序，后面的去重代码才能生效
        Arrays.sort(nums);
        backtrack(nums, visited, path);
        return res;
    }

    public void backtrack(int[] nums, boolean[] visited, List<Integer> path) {
        if (path.size() == nums.length) {
            res.add(new ArrayList<>(path));
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            // 在同一个树枝上剪枝
            if (visited[i]) continue;
            // 在同一树层进行剪枝，去重的代码，可以去看carl写的，很好很明白
            if (i > 0 && nums[i] == nums[i - 1] && visited[i - 1] == false) {
                continue;
            }
            // 做选择
            visited[i] = true;
            path.add(nums[i]);
            backtrack(nums, visited, path);
            visited[i] = false;
            path.remove(path.size() - 1);
        }
    }
}

package 回溯;

import java.util.ArrayList;
import java.util.List;

/**
 * 全排列
 *
 * 输入：nums = [1,2,3]
 * 输出：[[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
 */
public class Permutations {
    List<List<Integer>> res = new ArrayList<>();
    // 题目中说了nums中不含有重复数字
    public List<List<Integer>> permute(int[] nums){
        if (nums.length == 0) {
            return new ArrayList<>();
        }
        boolean[] visited = new boolean[nums.length];
        backtrack(nums, visited, new ArrayList<Integer>());
        return res;
    }

    public void backtrack(int[] nums, boolean[] visited, List<Integer> temp) {
        // index表示的是第index个数
        if (temp.size() == nums.length) {
            res.add(new ArrayList<>(temp));
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            if (visited[i]) continue;
            visited[i] = true;
            temp.add(nums[i]);
            backtrack(nums, visited, temp);
            visited[i] = false;
            temp.remove(temp.size() - 1);
        }
    }
}

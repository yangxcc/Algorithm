package 回溯;

import java.util.ArrayList;
import java.util.List;

/**
 * 子集
 *
 * 输入：nums = [1,2,3]
 * 输出：[[],[1],[2],[1,2],[3],[1,3],[2,3],[1,2,3]]
 */
public class subSets {
    List<List<Integer>> res = new ArrayList<>();

    public List<List<Integer>> subsets(int[] nums){
        if (nums.length == 0) {
            res.add(new ArrayList<>());
            return res;
        }
        List<Integer> path = new ArrayList<>();
        backtrack(nums, 0, path);
        return res;
    }

    // index表示第几个数，为了不重复，第index前的数就不会再访问了
    public void backtrack(int[] nums, int index, List<Integer> path) {
        if (index == nums.length) {
            return;
        }
        // 进入回溯就先把这个path加进来
        res.add(new ArrayList<>(path));

        for (int i = index; i < nums.length; i++) {
            // 做选择
            path.add(nums[i]);

            // 一定要注意，这里是i+1，不能够是index + 1
            backtrack(nums, i + 1, path);

            // 撤销选择
            path.remove(path.size() - 1);
        }
    }
}

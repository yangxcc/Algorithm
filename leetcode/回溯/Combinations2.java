package 回溯;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 题目中有条件说明，说明了candidates中的元素都是正数
 * 输入：candidates = [2,3,6,7], target = 7
 * 输出：[[2,2,3],[7]]
 * 解释：
 * 2 和 3 可以形成一组候选，2 + 2 + 3 = 7 。注意 2 可以使用多次。
 * 7 也是一个候选， 7 = 7 。
 * 仅有这两种组合。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/combination-sum
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Combinations2 {
    List<List<Integer>> res;
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        if (candidates.length == 0 || target <= 0) {
            return new ArrayList<>();
        }
        res = new ArrayList<>();
        List<Integer> path = new ArrayList<>();
        // 可以对回溯进行剪枝，首先对数组排序
        Arrays.sort(candidates);
        backtrack(candidates, target, path, 0, 0);
        return res;
    }

    /**
     *
     * @param candidates
     * @param target
     * @param path      路径
     * @param sum       和
     * @param index    对于第index个数怎么处理
     */
    public void backtrack(int[] candidates, int target, List<Integer> path, int sum, int index) {
        if (sum == target) {
            res.add(new ArrayList<>(path));
            return;
        }
        if (index == candidates.length) {
            return;
        }
        for (int i = index; i < candidates.length; i++) {
            // 第二，因为数组中的元素都是正数，所以一旦sum>target之后，直接放弃掉这条路径就可以了
            if (sum > target) {
                break;
            }
            // 做选择
            path.add(candidates[i]);
            sum += candidates[i];

            // 回溯，一定要注意，这里是能够选择重复的数字，所以这里不能是i+1，而是要是i
//            backtrack(candidates, target, path, sum, i + 1);
            backtrack(candidates, target, path, sum, i);

            // 撤销选择
            path.remove(path.size() - 1);
            sum -= candidates[i];
        }
    }
}

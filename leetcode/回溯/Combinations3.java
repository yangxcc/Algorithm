package 回溯;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 数组中的每个元素都只能够用一次
 * 而且解集中不能够有重复的元素，比如[1,1,6]和[1,6,1]是相同的
 * 所以只是单纯的将backtrack(i)换成backtrack(i+1)是不行的
 * 还需要对解集去重，当然可以使用HashSet进行去重，但是那样时间复杂度太高了
 *
 * 输入: candidates = [10,1,2,7,6,1,5], target = 8,
 * 输出:
 * [
 * [1,1,6],
 * [1,2,5],
 * [1,7],
 * [2,6]
 * ]
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/combination-sum-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Combinations3 {
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

    public void backtrack(int[] candidates, int target, List<Integer> path, int index, int sum) {
        if (sum == target) {
            res.add(path);
            return;
        }
        if (index == candidates.length) {
            return;
        }

        for (int i = index; i < candidates.length; i++) {
            if (sum > target) {
                break;   // 大剪枝， 为了提高效率
            }
            // 对于同一树层中的元素进行跳过
            if (i > index && candidates[i] == candidates[i - 1]) {
                continue;   // 小剪枝，提高效率的同时，能够去掉重复值
            }
            // 为什么上面这个代码能够去掉重复值
            /**
             * 同一层数值相同的结点第 2、3 ... 个结点，因为数值相同的第 1 个结点已经搜索出了包含了这个数值的全部结果
             */
            path.add(candidates[i]);
            sum += candidates[i];

            // 这里因为每个数都只能够用一次，所以这里是i+1
            backtrack(candidates, target, path, i + 1, sum);

            path.remove(path.size() - 1);
            sum -= candidates[i];
        }
    }

}

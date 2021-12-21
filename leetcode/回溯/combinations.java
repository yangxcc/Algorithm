package 回溯;

import java.util.ArrayList;
import java.util.List;

/**
 * 组合，思想和子集那道题是一样的，重点在于需要使用一个index排除已经选择过的数据，避免重复
 *
 * 给定两个整数 n 和 k，返回范围 [1, n] 中所有可能的 k 个数的组合。
 *
 * 你可以按 任何顺序 返回答案。
 *
 * 输入：n = 4, k = 2
 * 输出：
 * [
 *   [2,4],
 *   [3,4],
 *   [2,3],
 *   [1,2],
 *   [1,3],
 *   [1,4],
 * ]
 */
public class combinations {
    List<List<Integer>> res = new ArrayList<>();

    public List<List<Integer>> combine(int n, int k){
        if (k > n || n <= 0) {
            return new ArrayList<>();
        }

        List<Integer> path = new ArrayList<>();
        backtrack(n, k, path, 1);
        return res;
    }

    /**
     *
     * @param n
     * @param k
     * @param path
     * @param index 重点就是这个index，他和 “子集” 一题中的作用是一样的，都是为了保证不再访问访问过的数据
     *              要注意这两道题和全排列的区别
     */
    public void backtrack(int n, int k, List<Integer> path, int index) {
        if (k == path.size()) {
            res.add(path);
            return;
        }

        for (int i = index; i <= n; i++) {
            path.add(i);

            backtrack(n, k, path, i + 1);

            path.remove(path.size() - 1);
        }
    }
}

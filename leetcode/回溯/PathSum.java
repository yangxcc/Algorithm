package 回溯;

import java.util.ArrayList;
import java.util.List;

/**
 * 给你二叉树的根节点 root 和一个整数目标和 targetSum ，
 * 找出所有 从根节点到叶子节点 路径总和等于给定目标和的路径。
 */
public class PathSum {

    List<List<Integer>> res = new ArrayList<>();
    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        if (root == null) {
            return new ArrayList<>();
        }
        backtrack(root, 0, targetSum, new ArrayList<>());
        return res;
    }

    // curSum当前的路径和
    public void backtrack(TreeNode root, int curSum, int targetSum, List<Integer> path) {
        if (root == null) {
            return;
        }

        // 做选择
        curSum += root.value;
        path.add(root.value);

        if (root.left == null && root.right == null) {
            if (curSum == targetSum) {
                res.add(new ArrayList<>(path));
//                return;   // 不要在这里return，只要是到了叶子节点，不管符不符合条件，都是需要撤销选择的
            }
            // 这里不要忘了撤销选择
            curSum -= root.value;
            path.remove(path.size() - 1);
            return;
        }

        // 回溯
        backtrack(root.left, curSum, targetSum, path);
        backtrack(root.right, curSum, targetSum, path);

        // 撤销选择
        curSum -= root.value;
        path.remove(path.size() - 1);
    }
}


class TreeNode{
    TreeNode left;
    TreeNode right;
    int value;
    public TreeNode(int value) {
        this.value = value;
    }
}

package tree;

/**
 * leetcode 404 左叶子之和 simple
 *
 * 计算给定二叉树的所有左叶子之和。
 * */
public class sumOfLeftLeaves {
    public int sumOfLeftLeavesAlgorithm(Node root) {
        if (root ==null) {
            return 0;
        }
        int res = 0;
        if (root.left != null && root.left.right == null && root.left.left == null) {
            res += root.left.value;
        }
        return sumOfLeftLeavesAlgorithm(root.left) + sumOfLeftLeavesAlgorithm(root.right) + res;
    }
}

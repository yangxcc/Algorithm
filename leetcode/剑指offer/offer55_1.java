package 剑指offer;

/**
 * 二叉树的深度
 * */
public class offer55_1 {
    public int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return Math.max(maxDepth(root.left), maxDepth(root.right)) + 1;
    }
}

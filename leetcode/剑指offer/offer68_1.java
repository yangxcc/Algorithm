package 剑指offer;

/**
 * 二叉搜索树中的最近公共祖先
 */
public class offer68_1 {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) {
            return null;
        }

        // 都在右子树上
        if (root.val - p.val < 0 && root.val - q.val < 0) {
            return lowestCommonAncestor(root.right, p, q);
        }
        // 都在左子树
        if (root.val - p.val > 0 && root.val - q.val > 0) {
            return lowestCommonAncestor(root.left, p, q);
        }
        // 不在同一棵子树上
        return root;
    }
}

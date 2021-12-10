package 剑指offer;

/**
 * 二叉树的最近公共节点
 */
public class offer68_2 {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || root == p || root == q) {
            return root;
        }

        // 去左子树中找p或q
        TreeNode leftFind = lowestCommonAncestor(root.left, p, q);
        // 去右子树中找p或q
        TreeNode rightFind = lowestCommonAncestor(root.right, p, q);

        // 左右子树上都找到了
        if (leftFind != null && rightFind != null) {
            return root;
        }

        if (leftFind == null) {
            return rightFind;
        }
        return leftFind;
    }
}

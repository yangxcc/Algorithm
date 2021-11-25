package 剑指offer;

/**
 * 对称二叉树
 */
public class offer28 {

    public boolean isSymmetric(TreeNode root) {
        if (root == null) return true;
        if (root.left == null && root.right == null) {
            return true;
        }
        return process(root.left, root.right);
    }

    public boolean process(TreeNode leftNode, TreeNode rightNode) {
        if (leftNode == null || rightNode == null) {
            return false;
        }
        if (leftNode == null && rightNode == null) {
            return true;
        }
        if (leftNode.val != rightNode.val) {
            return false;
        }

        return process(leftNode.left, rightNode.right) &&
                process(leftNode.right, rightNode.left);

    }
}

package 剑指offer;


/**
 * 二叉树的镜像
 * */
public class offer27 {

    public TreeNode mirrorTree(TreeNode root) {
        if (root == null) return null;

        TreeNode leftNode = root.left;
        TreeNode rightNode = root.right;
        TreeNode temp = leftNode;
        root.left = rightNode;
        root.right = temp;

        mirrorTree(root.left);
        mirrorTree(root.right);

        return root;
    }
}

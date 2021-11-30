package 剑指offer;

/**
 * 平衡二叉树
 *
 * 输入一棵二叉树的根节点，判断该树是不是平衡二叉树。
 * 如果某二叉树中任意节点的左右子树的深度相差不超过1，那么它就是一棵平衡二叉树。
 * */
public class offer55_2 {

    /**
     * 很明显，这一种方法是递归套着递归
     * 不好，虽然容易理解但是存在大量的计算
     * */
    public boolean isBalanced(TreeNode root) {
        if (root == null) {
            return true;
        }
        if (Math.abs(depth(root.left) - depth(root.right)) > 1) {
            return false;
        }
        return isBalanced(root.left) && isBalanced(root.right);
    }

    public int depth(TreeNode node) {
        if (node == null) {
            return 0;
        }
        return Math.max(depth(node.left), depth(node.right)) + 1;
    }

    /**
     * 方法2：不管怎么说，本题中判断平衡树就是需要看高度
     * 那么我们不能得出高度来之后马上就能够直到他是不是平衡树呢
     *
     * 通过上面的判断，知道这道题还可以时候后序遍历
     * */
    public boolean isBalanced2(TreeNode root) {
        if (root == null) {
            return true;
        }
        return postOrderForHeight(root) >= 0;
    }

    public int postOrderForHeight(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int leftHeight = postOrderForHeight(root.left);
        if (leftHeight == -1) {
            return -1;
        }
        int rightHeight = postOrderForHeight(root.right);
        if (rightHeight == -1) {
            return -1;
        }
        // 如果没有上面的这两个判断剪纸，那么实际上的计算量也差不多
        // 上面两个判断的意思就是如果左子树或者右子树不是平衡二叉树，就没必要继续往下算了

        if (leftHeight > 0 && rightHeight > 0 && Math.abs(leftHeight - rightHeight) <= 1) {
            return Math.max(leftHeight, rightHeight) + 1;
        } else {
            return -1;   // -1就表示不是平衡二叉树，所以在if那里还要去判断一下leftHeight和rightHeight是不是-1
        }
    }
}

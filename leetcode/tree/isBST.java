package tree;

import binaryTree.Node;

/**
 * 98. 验证二叉搜索树
 */
public class isBST {

    public boolean isValidBST(Node root) {
        if (root == null) {
            return false;
        }
        Node min = null;
        Node max = null;
        return process(root, min, max);
    }

    // 要有min和max的原因是为了避免这种情况的出现
    /**
     *      4
     *     / \
     *    1   3
     *         \
     *          5
     *
     *  所以用来定义子树上的最大值和最小值
     *
     *  */
    public boolean process(Node root, Node min, Node max) {
        if (root == null) {
            return true;
        }
        if (min != null && root.val <= min.val) {
            return false;
        }
        if (max != null && root.val >= max.val) {
            return false;
        }
        return process(root.left, min, root) && process(root.right, root, max);
    }
}

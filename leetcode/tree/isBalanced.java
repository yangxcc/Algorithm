package tree;

import binaryTree.Node;

/**
 * leetcode 110 平衡二叉树 simple
 * 给定一个二叉树，判断它是否是高度平衡的二叉树。
 *
 * 高度平衡的二叉树的定义是 一个二叉树每个节点 的左右两个子树的高度差的绝对值不超过 1 。
 * */
public class isBalanced {

    public boolean isBalancedAlgorithm(Node root) {
        if (root == null) {
            return true;
        }
        return height(root) >= 0;
    }

    public int height(Node node) {
        if (node == null) {
            return 0;
        }
        if (node.left == null && node.right == null) {
            return 1;
        }
        int leftHeight = height(node.left);
        int rightHeight = height(node.right);
        if (leftHeight >= 0 && rightHeight >= 0 && Math.abs(leftHeight - rightHeight) <= 1) {
            return Math.max(leftHeight, rightHeight) + 1;
        } else {
            return -1;  // 这个就表示不平衡了
        }
    }
}

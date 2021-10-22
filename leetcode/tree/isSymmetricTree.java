package tree;

import binaryTree.Node;

/**
 * leetcode 100 对称二叉树 simple
 *
 * 给定一个二叉树，检查它是否是镜像对称的。
 * 例如，二叉树 [1,2,2,3,4,4,3] 是对称的。
 *
 * */
public class isSymmetricTree {

    public boolean isSymmetric(Node root) {
        if (root == null) {
            return false;
        }
        // 使用两个指针
        Node p = root.left;
        Node q = root.right;
        return process(p, q);
    }

    public boolean process(Node p, Node q) {
        if (p == null && q == null) {
            return true;
        }
        if (p != null && q != null && p.val == q.val) {
            return process(p.left, q.right) && process(p.right, q.left);
        } else {
            return false;
        }
    }
}

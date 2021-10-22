package tree;

import binaryTree.Node;

/**
 * leetcode 100. 相同的树  simple
 *
 * 给你两棵二叉树的根节点 p 和 q ，编写一个函数来检验这两棵树是否相同。
 *
 * 如果两个树在结构上相同，并且节点具有相同的值，则认为它们是相同的。
 * */
public class isSameTree {

    public boolean isSame(Node p, Node q) {
        if (p == null && q == null) {
            return true;
        }
        return process(p, q);
    }

    public boolean process(Node p, Node q) {
        if (p == null && q == null) {
            return true;
        }
        if (p!= null && q != null && p.val == q.val) {
            return process(p.left, q.left) && process(p.right, q.right);
        } else {
            return false;
        }
    }
}

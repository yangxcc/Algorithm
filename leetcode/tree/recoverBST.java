package tree;

import binaryTree.Node;

/**
 * 99. 恢复二叉搜索树
 * 给你二叉搜索树的根节点 root ，该树中的两个节点被错误地交换。请在不改变其结构的情况下，恢复这棵树。
 *
 * 如果规定空间复杂度为O(N)，那么我就能够通过中序遍历将BST中的节点打印到额外的空间中，看哪两个顺序不对就替换
 * */
public class recoverBST {

    // 这里使用两个全局变量来记录应该交换的值
    Node t1, t2, pre;    // pre用来记录当前节点的前一个节点

    public void recover(Node root) {
        if (root == null) {
            return;
        }
        inOrder(root);
        int temp = t1.val;
        t1.val = t2.val;
        t2.val = temp;
    }

    public void inOrder(Node root) {
        if (root == null) {
            return;   // base case
        }
        inOrder(root.left);
        if (pre != null && pre.val >= root.val) {
            if (t1 == null) {
                t1 = pre;   // 第一个不符合条件的肯定是前面的值
            }
            t2 = root;      // 第二个不符合条件的肯定是后面的值
        }
        pre = root;
        inOrder(root.right);
    }
}

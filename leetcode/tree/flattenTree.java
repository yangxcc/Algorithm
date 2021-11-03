package tree;

import binaryTree.Node;

import java.util.ArrayList;
import java.util.List;

/**
 * leetcode 114 二叉树展开未链表 middle
 *
 * 给你二叉树的根结点 root ，请你将它展开为一个单链表：
 *
 * 展开后的单链表应该同样使用 TreeNode ，其中 right 子指针指向链表中下一个结点，而左子指针始终为 null 。
 * 展开后的单链表应该与二叉树 先序遍历 顺序相同。
 *
 **/
public class flattenTree {

    /**
     * 第一种方法算是投机取巧的，先得到树先序遍历的顺序，然后再按照这个顺序弄出后面的这个树
     * */
    public void flatten1(Node root) {
        if (root == null) {
            return;
        }
        List<Node> temp = new ArrayList<>();
        preOrder(root, temp);
        build(temp);
    }

    public void preOrder(Node root, List<Node> temp) {
        if (root == null) {
            return;
        }
        temp.add(root);
        preOrder(root.left, temp);
        preOrder(root.right, temp);
    }


    public void build(List<Node> temp) {
        for (int i = 0; i < temp.size(); i++) {
            Node node = temp.get(i);
            node.left = null;
            if (i + 1 <= temp.size() - 1) {
                node.right = temp.get(i + 1);
            } else {
                node.right = null;
            }
        }
    }


    // 比较好的方法，使用后序遍历
    // 后续遍历的实质就是先处理叶子节点
    public void flatten2(Node root) {
        if (root == null) return;

        flatten2(root.left);     // 把左子树捋直
        flatten2(root.right);    // 把右子树捋直

        Node temp = root.right;   // 备份一下捋直的右子树
        root.right = root.left;   // 把捋直后的左子树挂到root的右边
        root.left = null;         // 把左子树置空
        while (root.right != null) {     // 找到现在右子树的最右边（也就是最开始左子树的最左边）
            root = root.right;
        }
        root.right = temp;     // 找到了最右侧的点，把捋直的右子树给挂上去
    }
}

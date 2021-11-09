package tree;

import binaryTree.Node;

/**
 * leetcode 129 求根节点到叶节点数字之和 middle
 *
 * 给你一个二叉树的根节点 root ，树中每个节点都存放有一个 0 到 9 之间的数字。
 * 每条从根节点到叶节点的路径都代表一个数字：
 *
 * 例如，从根节点到叶节点的路径 1 -> 2 -> 3 表示数字 123 。
 * 计算从根节点到叶节点生成的 所有数字之和 。
 *
 * 叶节点 是指没有子节点的节点。
 *
 */
public class sumNumbers {
    int res = 0;   //全局变量用来记录结果
    public int sum(Node root) {
        if (root == null) return -1;
        process(root, 0);
        return res;
    }

    // curValue表示的是当前路径的值
    public void process(Node node, int curValue) {
        if (node == null) {
            return;
        }

        curValue = curValue * 10 + node.val;   // 更新当前路径的值,注意要把这句话写到叶子节点前面！

        if (node.left == null && node.right == null) {
            res += curValue;
            return;
        }
        process(node.left, curValue);
        process(node.right, curValue);
    }
}

package tree;

import binaryTree.Node;

/**
 * leetcode 112. 路径总和 simple
 *
 * 给你二叉树的根节点 root 和一个表示目标和的整数 targetSum ，判断该树中是否存在
 * 根节点到叶子节点 的路径，这条路径上所有节点值相加等于目标和 targetSum 。
 *
 * */
public class pathSum {

    public boolean hasPathSum(Node root, int targetSum) {
        if (root == null) {
            return false;
        }
        return judge(root, targetSum);
    }

    public boolean judge(Node node, int targetSum) {
        if (node == null) {
            return false;
        }
        if (node.left == null && node.right == null && targetSum - node.val == 0) {
            return true;
        }
        return judge(node.left, targetSum - node.val) || judge(node.right, targetSum - node.val);
        // 注意这里要用 || 啊！！！表示左树上是否有path，或者右树上是否有path
    }
}

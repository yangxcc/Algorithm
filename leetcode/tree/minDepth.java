package tree;

import binaryTree.Node;

/**
 * leetcode 111 给定一个二叉树，找出其最小深度。 simple
 *
 * 最小深度是从根节点到最近叶子节点的最短路径上的节点数量。
 *
 * 说明：叶子节点是指没有子节点的节点。
 *
 * */
public class minDepth {

    public int minDepthAlgorithm(Node root) {
        if (root == null) {
            return 0;
        }
        return depth(root);
    }

    public int depth(Node node) {
        if (node == null) {
            return 0;
        }
        if (node.left == null && node.right == null) {
            return 1;
        }
        if (node.left == null) {                // 这道题的难点就在于这里！！
            return depth(node.right) + 1;       // 一定不要忘记这两种情况，如图所示
        }
        /**
         *       A
         *      / \             应该返回3，如果没有这两个条件的话，他就返回1了
         *   null  B
         *          \
         *           C
         * */
        if (node.right == null) {
            return depth(node.left) + 1;
        }
        return Math.min(depth(node.left), depth(node.right)) + 1;
    }
}

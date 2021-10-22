package tree;

import binaryTree.Node;

/**
 * leetcode 104. 二叉树的最大深度 simple
 *
 * 给定一个二叉树，找出其最大深度。
 * 二叉树的深度为根节点到最远叶子节点的最长路径上的节点数。
 *
 * */
public class Depth {

    public int maxDepth(Node root) {
        if (root == null) {
            return 0;
        }
        return process(root);
    }

    public int process(Node root) {
        if (root == null) {
            return 0;
        }
        return Math.max(process(root.left), process(root.right)) + 1;
    }
}

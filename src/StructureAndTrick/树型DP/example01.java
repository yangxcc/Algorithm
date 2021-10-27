package StructureAndTrick.树型DP;

import binaryTree.Node;

/**
 * 二叉树间最大距离问题
 * 从二叉树的 a 节点出发，可以向上走，也可以向下走，但沿途的节点只能够通过1次，到达节点b时
 * 路径上的节点个数叫做a到b的距离，那么二叉树上任意两个节点之间都有距离，求整棵树上的最大距离
 *
 *
 * 解题思路
 * 整棵二叉树的最大距离只有两种情况
 *    - 经过节点x
 *       最大距离就是 左树高度 + 右树高度 + 1
 *    - 不经过节点x
 *        左数上的最大距离或者是右树上的最大距离
 *
 *             x
 *            / \
 *           I   O
 *          /\
 *         O  O
 *        /    \
 *       O      O
 *      /        \
 *     O          O
 *
 *   很明显，上面这种情况下，最大距离就是 O-O-O-I-O-O-O
 * */
public class example01 {

    public static class Info {
        int maxDistance;
        int Height;
        public Info(int dis, int hei) {
            this.maxDistance = dis;
            this.Height = hei;
        }
    }



    public static Info process(Node node) {
        if (node == null) {
            return new Info(0, 0);
        }
        Info leftInfo = process(node.left);
        Info rightInfo = process(node.right);
        int p1 = leftInfo.Height + rightInfo.Height + 1;
        int p2 = leftInfo.maxDistance;
        int p3 = rightInfo.maxDistance;
        int maxDistance = Math.max(p1, Math.max(p2, p3));
        int height = Math.max(leftInfo.Height, rightInfo.Height) + 1;
        return new Info(maxDistance, height);
    }

    public static int maxDistance(Node root) {
        if (root == null) {
            return 0;
        }
        Info info = process(root);
        return info.maxDistance;
    }
}

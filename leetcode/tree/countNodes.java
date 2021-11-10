package tree;

/**
 * leetcode 222 完全二叉树的节点个数 middle
 *
 * 给你一棵 完全二叉树 的根节点 root ，求出该树的节点个数。
 *
 * 完全二叉树 的定义如下：在完全二叉树中，除了最底层节点可能没填满外，其余每层节点数都达到最大值，
 * 并且最下面一层的节点都集中在该层最左边的若干位置。若最底层为第 h 层，则该层包含 1~ 2h 个节点。
 *
 */
public class countNodes {
    public int countNodesAlgorithm(Node root) {
        if (root == null) {
            return 0;
        }
        // 完全二叉树有可能包含了满二叉树，如果是满二叉树可以直接得出节点个数
        Node l = root;
        Node r = root;
        int leftDepth = 0;
        int rightDepth = 0;
        while (l != null) {   // 最左边的长度
            leftDepth++;
            l = l.left;
        }
        while (r != null) {   // 最右边的长度
            rightDepth++;
            r = r.right;
        }
        if (leftDepth == rightDepth) {
            // 满二叉树
            return (int) Math.pow(2, leftDepth) + 1;
        }

        return countNodesAlgorithm(root.left) + countNodesAlgorithm(root.right) + 1;
    }
}

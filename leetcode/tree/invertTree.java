package tree;

/**
 * leetcode 226 翻转二叉树 simple
 *
 */
public class invertTree {
    public Node invertTreeAlgorithm(Node root) {
        if (root == null) {
            return null;
        }
        process(root);
        return root;
    }

    public void process(Node node) {
        if (node == null) {
            return;
        }
        Node leftNode = node.left;
        Node rightNode = node.right;

        node.left = rightNode;    // 注意再反转中没必要向数交换一样，再来一个temp，而且写法也不能够像那种交换一样
        node.right = leftNode;

        process(node.left);
        process(node.right);
    }
}

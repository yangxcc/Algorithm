package tree;

/**
 * 这道题和上面不同的地方在于这里的树不再是完美二叉树了，所以需要考虑更多的边界条件
 */
public class paddingPointer2 {

    public Node connect(Node root) {
        if (root == null) {
            return null;
        }
        return process(root);
    }

    public Node process(Node node) {
        if (node == null) {
            return null;
        }
        if (node.left != null && node.right != null) {
            node.left.next = node.right;
        }
        if (node.left == null && node.right == null) {
            return node;
        }
        if (node.left != null && node.right == null) {
            node.left.next = getNextNode(node.next);
        }
        if (node.right != null) {
            node.right.next = getNextNode(node.next);
        }

        process(node.right);  // 这里需要注意要先处理右子树，在处理左子树
        process(node.left);

        return node;
    }

    public Node getNextNode(Node node) {
        if (node == null) {
            return null;
        }
        if (node.left != null) {
            return node.left;
        }
        if (node.right != null) {    // node.left == null && node.right != null
            return node.right;
        }
        return getNextNode(node.next);     // node.left == null && node.right == null
    }
}

/**
 *
 * 给出一个树，如下
 *             2
 *            / \
 *           1   3
 *          /\   / \
 *         0 7  9  10
 *        / /\     /\
 *       11 8 4   5 12
 *
 *
 *   如果我们先处理左子树，那么树中的指针会如下所示
 *                2
 *  *            / \
 *  *           1 ->3
 *  *          /\   / \
 *  *         0->7  9->10
 *  *        /  /\     /\
 *  *       11->8->4  5->12
 *
 *          */

// 先确保 root.right 下的节点的已完全连接，因 root.left 下的节点的连接
// 需要 root.left.next 下的节点的信息，若 root.right 下的节点未完全连
// 接（即先对 root.left 递归），则 root.left.next 下的信息链不完整，将
// 返回错误的信息。可能出现的错误情况如下图所示。此时，底层最左边节点将无
// 法获得正确的 next 信息：



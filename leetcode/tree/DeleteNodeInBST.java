package tree;

/**
 * 给定一个二叉搜索树的根节点 root 和一个值 key，
 * 删除二叉搜索树中的 key 对应的节点，并保证二叉搜索树的性质不变。返回二叉搜索树（有可能被更新）的根节点的引用。
 *
 * 一般来说，删除节点可分为两个步骤：
 *
 * 首先找到需要删除的节点；
 * 如果找到了，删除它。
 *
 */
public class DeleteNodeInBST {
    // 在递归函数中，root指向的是将要被删除的节点
    public Node deleteNode(Node root, int key) {
        if (root == null) {
            return null;
        }
        if (root.value < key) {
            // 这里一定要注意deleteNode(root.right, key)返回的是root.right，而不是root
            // 从右子树中删除
            root.right = deleteNode(root.right, key);
            return root;
        } else if (root.value > key) {
            root.left = deleteNode(root.left, key);
            return root;
        } else {
            // 将要被删除的节点有三种情况：
            // 1.是叶子节点
            // 2.有左子树或者右子树
            // 3.左右子树都有

            // 删除root这个节点
            if (root.left == null) {
                return root.right;
            } else if (root.right == null) {
                return root.left;
            } else {
                // 两者都不为空
                // 去找到root右子树上的最小值,用它来代替root
                Node node = minOnRight(root.right);
//                node.left = root.left;
//                node.right = deleteNode(root.right, node.value);   // 在root的右子树中删除node这个节点
                // 顺序也很重要，为什么不能是上面那个顺序
                // 上面这种顺序是指先把root的左子树挂到node的左子树下面，那么在root的右子树中删除node这个节点的时候，
                // 必然会有其他节点（可以为null）来顶替这个node的位置，但是这时候node的子树中多了一些节点，这些节点是root的左子树
                // 而不应该多处这些节点来，这也是为什么leetcode的输出中会有多的值
                // 所以应该现在root的右子树中把node给删除掉，再去给node的左子树挂上root的左子树
                node.right = deleteNode(root.right, node.value);   // 在root的右子树中删除node这个节点
                node.left = root.left;

                return node;
            }
        }
    }

    public Node minOnRight(Node root) {
        if (root == null) {
            return null;
        }
        while (root.left != null) {
            root = root.left;
        }
        return root;
    }

}

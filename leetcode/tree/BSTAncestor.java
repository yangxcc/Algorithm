package tree;

/**
 * leetcode 235 二叉搜索树的最近公共祖先 simple
 * */
public class BSTAncestor {
    // 因为是二叉搜索树，所以利用BST的性质能够很容易的知道他们两个在不在同一颗子树上
    public Node lowestCommonAncestor(Node root, Node p, Node q) {
        if (root == null) {
            return null;
        }
        if ((root.value - p.value) * (root.value - q.value) <= 0) {
            // 不在同一颗子树上
            return root;
        } else if (root.value < p.value && root.value < q.value){
            return lowestCommonAncestor(root.right, p, q);    // 都在右子树上
        } else {
            return lowestCommonAncestor(root.left, p, q);   // 都在左子树上
        }
    }
}

package 剑指offer;

import sun.reflect.generics.tree.Tree;

/**
 * 树的子结构
 * 约定空树不是任意一个树的子结构
 */
public class offer26 {
    public boolean isSubStructure(TreeNode A, TreeNode B) {
        if (A == null || B == null) {
            return false;
        }
        if (A.val == B.val && check(A, B)) {
            return true;
        }
        return isSubStructure(A.left, B) || isSubStructure(A.right, B);
    }

    public boolean check(TreeNode A, TreeNode B) {
        if (A == null && B == null) return true;
        if (A == null) return false;
        if (B == null) return true;

        return A.val == B.val && check(A.left, B.left) && check(A.right, B.right);
    }
}


class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
        val = x;
    }
}

package tree;

/**
 * 检查一棵树是不是另一棵树的子树
 */
public class SubtreeOfAnotherTree {
    public boolean isSubtree(Node root, Node subRoot) {
        if (root == null && subRoot != null) {
            return false;
        }
        if (root != null && subRoot == null) {
            return true;
        }
        return isSameTree(root, subRoot) || isSubtree(root.left, subRoot) || isSubtree(root.right, subRoot);
    }

    private boolean isSameTree(Node root, Node subRoot) {
        if (root == null && subRoot == null) {
            return true;
        }
        return root.value == subRoot.value
                && isSubtree(root.left, subRoot.left)
                && isSubtree(root.right, subRoot.right);
    }


//    public boolean isSubtree2(Node root, Node subRoot) {
//        String rootStr = "";
//        preOrder(root, rootStr);
//        String subRootStr = "";
//        preOrder(subRoot, subRootStr);
//
//        // 判断subRootStr在rootStr中是否出现过
//        for (int i = 0, j = 0; i < rootStr.length() && j < subRootStr.length(); i++) {
//            if (rootStr.charAt(i) == subRootStr.charAt(j)) {
//                j++;
//                if (j == subRootStr.length()) {
//                    return true;
//                }
//            }
//        }
//        return false;
//    }
//
//    private void preOrder(Node root, String str) {
//        if (root == null) {
//            return;
//        }
//        str += root.value;
//        preOrder(root.left, str);
//        preOrder(root.right, str);
//    }

}

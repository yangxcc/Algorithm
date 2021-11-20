package 剑指offer;


import java.util.HashMap;

/**
 * 重建二叉树
 *
 * 输入某二叉树的前序遍历和中序遍历的结果，请构建该二叉树并返回其根节点。
 *
 * 假设输入的前序遍历和中序遍历的结果中都不含重复的数字。
 * */
public class offer07 {

    /**
     * 这次自己一下子就写出来了，哈哈，对于这种就要快速的写出来
     * */
    HashMap<Integer, Integer> hm = new HashMap<>();

    public TreeNode buildTree(int[] preorder, int[] inorder){
        if (preorder.length == 0 || inorder.length == 0 || preorder.length != inorder.length) {
            return null;
        }

        for (int i = 0; i < inorder.length; i++) {
            hm.put(inorder[i], i);
        }

        TreeNode root = build(preorder, 0, preorder.length - 1,
                                inorder, 0, inorder.length - 1);

        return root;
    }

    public TreeNode build(int[] preorder, int preS, int preE,
                          int[] inorder, int inS, int inE) {
        if (preS > preE) {
            return null;
        }

        TreeNode root = new TreeNode(preorder[preS]);
        int position = hm.get(preorder[preS]);
        int leftSize = position - inS;

        root.left = build(preorder, preS + 1, preS + leftSize, inorder, inS, position - 1);
        root.right = build(preorder, preS + position + 1, preE, inorder, position + 1, inE);

        return root;
    }


    private class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        public TreeNode(int x) {
            val = x;
        }
    }
}

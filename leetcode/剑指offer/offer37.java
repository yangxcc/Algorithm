package 剑指offer;


import sun.reflect.generics.tree.Tree;

/**
 * 序列化二叉树
 * */
public class offer37 {
    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        if (root == null) return "";
        StringBuilder sb = new StringBuilder();
        preOrder(root, sb);
        return sb.toString();
    }

    public void preOrder(TreeNode root, StringBuilder sb) {
        if (root == null) {
            sb.append("#,");
            return;
        }
        sb.append(root.val).append(",");

        preOrder(root.left, sb);
        preOrder(root.right, sb);
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if (data.equals("") || data.length() == 0) {
            return null;
        }
        String[] nodes = data.split(",");
        return buildTree(nodes);
    }

    int index = -1;
    public TreeNode buildTree(String[] nodes) {

//        TreeNode root = new TreeNode(Integer.valueOf(nodes[start]));
        index++;
        String rootVal = nodes[index];
        if (rootVal.equals("#")) {
            return null;
        } else {
            TreeNode root = new TreeNode(Integer.valueOf(nodes[index]));
            root.left = buildTree(nodes);
            root.right = buildTree(nodes);
            return root;
        }
    }
}

package tree;

import java.util.ArrayList;
import java.util.List;

/**
 * leetcode 257 二叉树的所有路径 simple
 * */
public class paths {

    List<String> res = new ArrayList<>();
    public List<String> binaryTreePaths(Node root) {
        if (root == null) {
            return new ArrayList<>();
        }

        process(root, "");
        return res;
    }

    public void process(Node root, String str) {
        if (root == null) {
            return;
        }
        str += root.value;
        if (root.left == null && root.right == null) {
            res.add(str);
            return;
        }
        process(root.left, str + "->");
        process(root.right, str + "->");
    }
}

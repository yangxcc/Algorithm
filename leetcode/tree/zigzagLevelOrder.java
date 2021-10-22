package tree;

import binaryTree.Node;

import java.util.*;

/**
 * leetcode 103. 二叉树的锯齿形层序遍历  middle
 *
 * 给定一个二叉树，返回其节点值的锯齿形层序遍历。
 * （即先从左往右，再从右往左进行下一层遍历，以此类推，层与层之间交替进行）。
 *
 * 具体算法就是在层序遍历的基础上加一层偶数数组反转
 * */
public class zigzagLevelOrder {

    public List<List<Integer>> zigzagLevelOrderAlgorithm(Node root) {
        if (root == null) {
            return new ArrayList<>();
        }
        List<List<Integer>> res = new ArrayList<>();
        process(root, res);
        return res;

    }

    public void process(Node root, List<List<Integer>> res) {
        if (root == null) {
            return;
        }
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        int level = 0;
        while (!queue.isEmpty()) {
            List<Integer> levelNodes = new ArrayList<>();
            int size = queue.size();
            level++;
            for (int i = 0; i < size; i++) {
                Node node = queue.poll();
                levelNodes.add(node.val);
                if (node.left != null) {
                    queue.add(node.left);
                }
                if (node.right != null) {
                    queue.add(node.right);
                }
            }
            if (level % 2 == 0) {
                // reverse(levelNodes);
                Collections.reverse(levelNodes);  // 可以直接调用这个函数
            }
            res.add(levelNodes);
        }
    }

    public void reverse(List<Integer> levelNodes) {
        int size = levelNodes.size() - 1;
        for (int i = 0, j = size; i <= j; i++, j--) {
            swap(levelNodes, i, j);
        }
    }

    public void swap(List<Integer> list, int i, int j) {
        int temp = list.get(i);
        list.set(i, list.get(j));
        list.set(j, temp);
    }
}

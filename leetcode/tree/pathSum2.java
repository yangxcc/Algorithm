package tree;

import binaryTree.Node;

import java.util.ArrayList;
import java.util.List;

/**
 * leetcode 113 路经总和II middle
 *
 * 给你二叉树的根节点 root 和一个整数目标和 targetSum ，找出所有从根节点到叶子节点 路径总和等于给定目标和的路径。
 *
 * */
public class pathSum2 {
    public List<List<Integer>> pathSum(Node root, int targetSum) {
        if (root == null) {
            return new ArrayList<>();
        }
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> temp = new ArrayList<>();
        process(root, targetSum, res, temp);
        return res;
    }

    public void process(Node node, int targetSum, List<List<Integer>> res, List<Integer> temp) {
        if (node == null) {
            return;
        }
        // base case
        if (node.left == null && node.right == null) {
            if (targetSum - node.val == 0) {
                temp.add(node.val);
                res.add(new ArrayList<>(temp));
                temp.remove(temp.size() - 1);  // 让进来的这个节点再出去，比如进来的是node的left，符合条件，
                return;                              // 有可能right也符合条件，所以把left先给remove了，再去看看right合不合适
            }
        }
        temp.add(node.val);
        process(node.left, targetSum - node.val, res, temp);
        process(node.right, targetSum - node.val, res, temp);
        temp.remove(temp.size() - 1);   // 同理
    }
}

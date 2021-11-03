package tree;

import binaryTree.Node;

import java.util.ArrayList;
import java.util.List;

/**
 * leetcode 95. 不同的二叉搜索树 II middle
 * 给你一个整数 n ，请你生成并返回所有由 n 个节点组成且节点值从 1 到 n 互不相同的不同 二叉搜索树 。
 * 可以按 任意顺序 返回答案。
 * */
public class numsOfBST2 {
    public List<Node> generateTrees(int n) {
        if (n <= 0) {
            return new ArrayList<>();
        }
        return generate(1, n);
    }

    public List<Node> generate(int left, int right) {
        List<Node> res = new ArrayList<>();
        if (left > right) {
            res.add(null);
            return res;     // 这里要这么写，下面才不会出现空指针
        }

        for (int i = left; i<= right; i++) {
            List<Node> leftNodes = generate(left, i - 1);
            List<Node> rightNodes = generate(i + 1, right);
            for (Node leftNode : leftNodes) {           // 如果在left > right那里返回的是null，那么leftNodes和rightNodes都是null的时候，那就不行了
                for (Node rightNode : rightNodes) {
                    Node root = new Node(i);
                    root.left = leftNode;
                    root.right = rightNode;
                    res.add(root);
                }
            }
        }
        return res;
    }
}

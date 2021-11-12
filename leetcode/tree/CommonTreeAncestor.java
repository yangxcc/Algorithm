package tree;

import java.util.HashMap;
import java.util.HashSet;

/**
 * leetcode 236 普通二叉树的公共祖先 middle
 *
 * 其实对于这个题，我们还是能够更具BST的思路扩展一下
 * p和q其实只有两种情况，一种是p在q的子树上，此时返回q，或者q在p的子树上，此时返回p
 * 另一种是q和p不在同一颗子树上，这时候返回node
 *
 *
 * 但是其实还有一个万能解法，就是通过一个hashmap把每个节点的父节点都存储起来，这种方法思路简单，但是空间复杂度很高
 * */
public class CommonTreeAncestor {
    public Node lowestCommonAncestor(Node root, Node p, Node q) {
        if (root == null) {
            return null;
        }
        HashMap<Node, Node> hm = new HashMap<>();
        hm.put(root, null);
        traverseTree(root, hm);
        return solve1(root, p, q, hm);
    }

    private Node solve1(Node root, Node p, Node q, HashMap<Node, Node> hm) {
        // 首先从hm中把从p开始的这条路径给找出来
        HashSet<Node> hs = new HashSet<>();
        hs.add(p);
        Node cur = p;
        while (hm.get(cur) != null) {
            hs.add(hm.get(cur));
            cur = hm.get(cur);
        }
        cur = q;
        while (!hs.contains(cur)) {
            cur = hm.get(cur);
        }
        return cur;
    }

    public void traverseTree(Node root, HashMap<Node, Node> hm) {
        if (root.left != null) {
            hm.put(root.left, root);
            traverseTree(root.left, hm);
        }
        if (root.right != null) {
            hm.put(root.right, root);
            traverseTree(root.right, hm);
        }
    }


    /**
     * 方法二：
     * p和q其实只有两种情况，一种是p在q的子树上，此时返回q，或者q在p的子树上，此时返回p
     * 另一种是q和p不在同一颗子树上，这时候返回node
     * */
    public Node solve2(Node root, Node p, Node q) {
        // 先碰到p就先返回p，先碰到q就先返回q
        if (root == null || root == p || root == q) {
            return root;
        }
        Node findInLeft = solve2(root.left, p, q);
        Node findInRight = solve2(root.right, p, q);
        if (findInLeft != null && findInRight != null) {
            return root;
        }
        return findInLeft != null ? findInLeft : findInRight;
    }
}

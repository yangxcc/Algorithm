package binaryTree;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;

/**
 * 给定二叉树的两个节点node1和node2，找到他们的最低公共节点
 *
 * 这道题的难点我感觉在于怎么向上访问
 * */
public class lowestAncestor {
    /**
     * 思路一
     * 遍历二叉树，将每个节点和其父节点都放入到hashMap中
     *
     * */
    public static Node findLowestAncestor (Node head, Node o1, Node o2) {
        if (head == null) {
            return null;
        }
        HashMap<Node, Node> hashMap = new LinkedHashMap<>();
        findParent(head,hashMap);
        hashMap.put(head,null);
        Node cur = o1;
        HashSet<Node> hashSet = new HashSet<>();
        hashSet.add(cur);
        while (hashMap.get(cur) != null) {   // 把节点o1所在的那条链给提取出来
            hashSet.add(hashMap.get(cur));
            cur = hashMap.get(cur);
        }

        // 看o2所在的链中的节点谁最先出现在o1链中
        cur = o2;
        while (!hashSet.contains(cur)) {
            cur = hashMap.get(cur);
        }
        return cur;
    }

    public static void findParent(Node head, HashMap<Node, Node> hashMap) {
        if (head.left != null) {
            hashMap.put(head.left,head);
            findParent(head.left,hashMap);
        }
        if (head.right != null) {
            hashMap.put(head.right,head);
            findParent(head.right,hashMap);
        }
    }


    /**
     * 使用一种更好的方法
     *
     * 寻找共同祖先，其实总共就有两种形式
     * 第一种是 node2在node1的子树上，那么这种情况就返回node1，或者是node1在node2的子树上，那么此时返回node2
     * 第二种是 node2和node1不在同一颗子树上
     * */
    public static Node findLowestAncestor02 (Node head, Node o1, Node o2) {
        /**
         * 当 head 为空的时候，就返回空，当head碰到o1或者o2的时候就返回o1或者o2
         * */
        if (head == null || head == o1 || head == o2) {
            return head;
        }
        // 在左子树中去找o1或者o2
        Node left = findLowestAncestor02(head.left,o1,o2);
        // 在右子树中去找o1或者o2
        Node right = findLowestAncestor02(head.right,o1,o2);
        // 如果左右子树中都找到了，也就是o1和o2不在同一个子树下，那么就返回此时的头节点
        // 因为如果都不为空，那么必然会向上传递，直到公共祖先，那么就把公共祖先返回出来，直到最后的输出
        if (left != null && right != null) {
            return head;
        }
        return left != null ? left : right;
        // 如果只找到了一个，那么返回那一个，因为另一个在他的子树上了
    }
}

package 剑指offer;

/**
 * 二叉搜索树与双向链表
 * 输入一棵二叉搜索树，将该二叉搜索树转换成一个排序的循环双向链表。
 * 要求不能创建任何新的节点，只能调整树中节点指针的指向
 *
 * 我们希望将这个二叉搜索树转化为双向循环链表。链表中的每个节点都有一个前驱和后继指针。
 * 树中节点的左指针需要指向前驱，树中节点的右指针需要指向后继。
 * 对于双向循环链表，第一个节点的前驱是最后一个节点，最后一个节点的后继是第一个节点。
 *
 * 链接：https://leetcode-cn.com/problems/er-cha-sou-suo-shu-yu-shuang-xiang-lian-biao-lcof
 * */
public class offer36 {

    class Node {
        public int val;
        public Node left;
        public Node right;

        public Node() {}

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val,Node _left,Node _right) {
            val = _val;
            left = _left;
            right = _right;
        }
    }


    Node head, pre;
    // pre表示的是当前节点的前一个节点
    public Node treeToDoublyList(Node root) {
        if (root == null) {
            return null;
        }

        inOrder(root);

        head.left = pre;
        pre.right = head;

        return head;
    }

    public void inOrder(Node cur) {
        if (cur == null) {
            return ;
        }
        inOrder(cur.left);

        //
        if (pre == null) {
            // node表示的是第一个节点
            head = cur;
        } else {
            pre.right = cur;
        }
        cur.left = pre;
        pre = cur;

        inOrder(cur.right);
    }
}

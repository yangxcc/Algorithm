package binaryTree;

import java.util.LinkedList;
import java.util.Queue;

// is Completely Binary Tree
public class isCBT {

    /**
     * 如果整棵二叉树中除了叶子节点，每个节点都有左右孩子，这样的肯定是完全二叉树
     * 另外的两种情况
     * 节点只有右孩子，这样的一定不是完全二叉树
     * 节点只有左孩子，这种情况下，在这个结点之后如果其他节点都是叶子节点那么这棵树也是完全二叉树
     * */

    public static boolean isCompletelyBT (Node head) {
        if (head == null) {
            return true;
        }
        Queue<Node> queue = new LinkedList<>();
        Node l = null;
        Node r = null;
        queue.add(head);
        // 这个变量表示是否遇到了左右两个孩子不双全的节点
        boolean leaf = false;
        while (!queue.isEmpty()) {
            Node cur = queue.poll();
            l = cur.left;
            r = cur.right;
            // 看看是否遇到了某个节点只有右节点没有左节点 或者是 碰到非双全节点之后又遇到了非叶子节点
            if ((l == null && r != null) || (leaf && (l != null || r != null))) {
                return false;
            }
            if (l != null) {
                queue.add(l);
            }
            if (r != null) {
                queue.add(r);
            }
            if (l == null || r == null) {
                leaf = true;
            }
        }
        return true;
    }

    public static Node createBST() {
        Node head = new Node(4);
        Node node1 = new Node(2);
        Node node2 = new Node(6);
        Node node3 = new Node(1);
        Node node4 = new Node(3);
        Node node5 = new Node(5);
        Node node6 = new Node(7);
        head.left = node1;
        head.right = node2;
//        node1.left = node3;
        node1.right = node4;
        node2.left = node5;
        node2.right = node6;
        return head;
    }

    public static void main (String[] args) {
        Node head = createBST();
        System.out.println(isCompletelyBT(head));
    }
}

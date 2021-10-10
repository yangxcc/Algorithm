package binaryTree;

class Node2 {
    public int val;
    public Node2 left;
    public Node2 right;
    public Node2 parent;

    public Node2(int val) {
        this.val = val;
    }
}

/**
 * 寻找一个节点的后继结点
 * 在一个二叉树中，如果将一个二叉树按照中序遍历展开，那么一个节点后的节点就叫他的后继节点
 * */
public class findSuccessorNode {

    public static Node2 findSuccessorNode01 (Node2 head, Node2 node) {
        if (head == null || node == null) {
            return null;
        }
        // 第一种思路就是将这个二叉树中序遍历出来到一个数组中，去找node后面的那个节点
        // ...
        return node;
    }

    /**
     * node节点只有两种情况：
     * 1.node节点有右子树，这种情况下，node节点的后继节点就是 它右子树的最左边的那一个
     * 2.node节点没有右子树，这种情况下，需要讨论
     *   - 在parent节点的左子树，这种情况下，他的后继就是parent节点
     *   - 在parent节点的右子树，这种情况下，还要继续向上遍历
     *   */
    public static Node2 findSuccessorNode02 (Node2 head, Node2 node) {
        if (head == null || node ==null) {
            return null;
        }
        if (node.right != null) {   // 有右子树
            return getLeftestNode(node.right);
        } else {
            Node2 parent = node.parent;
            while (parent != null && parent.right == node) {
                node = parent;
                parent = node.parent;
            }
            return parent;
        }
    }

    public static Node2 getLeftestNode (Node2 head) {
        if (head == null) {
            return null;
        }
        while (head.left != null) {
            head = head.left;
        }
        return head;
    }

    public static Node2 createNodes() {
        Node2 head = new Node2(0);
        Node2 node1 = new Node2(1);
        Node2 node2 = new Node2(2);
        Node2 node3 = new Node2(3);
        Node2 node4 = new Node2(4);
        Node2 node5 = new Node2(5);
        Node2 node6 = new Node2(6);
        Node2 node7 = new Node2(7);
        head.left = node1;
        node1.parent = head;
        head.right = node2;
        node2.parent = head;
        node3.parent = node1;
        node4.parent = node1;
        node1.left = node3;
        node1.right = node4;
        node2.left = node5;
        node2.right = node6;
        node5.parent = node2;
        node6.parent = node2;
        node6.right = node7;
        node7.parent = node6;
        return head;
    }

    public static void main(String[] args) {
        Node2 head = createNodes();
        Node2 node01 = findSuccessorNode02(head, head.right.left);
        System.out.println(node01.val);
    }
}

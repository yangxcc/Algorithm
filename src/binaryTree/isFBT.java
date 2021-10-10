package binaryTree;

/**
 * 判断是不是满二叉树
 * */
public class isFBT {

    /**
     * 一种思路是先求出二叉树的高度 h，在求出它的节点数nodes，
     * 看能否匹配 nodes = 2^h - 1
     * */
    public static boolean isFull (Node head) {
        if (head == null) {
            return true;
        }
        int h = height(head);
        int n = Nodes(head);
        return ((1 << h) - 1) == n;
    }

    public static int height (Node head) {
        if (head == null) {
            return 0;
        }
        int leftHeight = height(head.left);
        int rightHeight = height(head.right);
        return Math.max(leftHeight,rightHeight) + 1;
    }

    public static int Nodes (Node head) {
        if (head == null) {
            return 0;
        }
        int leftNodes = Nodes(head.left);
        int rightNodes = Nodes(head.right);
        return leftNodes + rightNodes + 1;
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
        node1.left = node3;
        node1.right = node4;
        node2.left = node5;
        node2.right = node6;
        return head;
    }

    public static void main(String[] args) {
        Node head = createBST();
        System.out.println(height(head));
        System.out.println(Nodes(head));
        System.out.println(isFull(head));
    }
}

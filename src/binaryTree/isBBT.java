package binaryTree;

// is Balanced Binary Tree 是否为平衡二叉树

/**
 * 平衡二叉树的判断方式
 * 左子树需要为平衡二叉树，右子树也是需要为平衡二叉树
 * 而且左数和右数的高度差的绝对值不能够超过 1
 * */
public class isBBT {

    public static boolean isBalancedBT (Node head) {
        if (head == null) {
            return true;
        }
        return process(head).isBBT;
    }

    public static Info process (Node head) {
        if (head == null) {
            return new Info(true,0);
        }
        Info leftInfo = process(head.left);
        Info rightInfo = process(head.right);
        int height = Math.max(leftInfo.height,rightInfo.height) + 1;
        boolean isBBT = leftInfo.isBBT && rightInfo.isBBT && Math.abs(leftInfo.height - rightInfo.height) < 2;
        return new Info(isBBT,height);
    }

    public static Node createBST() {
        Node head = new Node(4);
        Node node1 = new Node(2);
        Node node2 = new Node(6);
        Node node3 = new Node(1);
        Node node4 = new Node(3);
        Node node5 = new Node(5);
        Node node6 = new Node(7);
        Node node7 = new Node(7);
        Node node8 = new Node(7);
        head.left = node1;
        head.right = node2;
        node1.left = node3;
        node1.right = node4;
        node2.left = node5;
        node2.right = node6;
        node6.left = node7;
        node7.right = node8;
        return head;
    }

    public static void main(String[] args) {
        Node head = createBST();
        System.out.println(isBalancedBT(head));
    }

}

class Info {
    boolean isBBT;
    int height;
    public Info(boolean is, int h) {
        this.isBBT = is;
        this.height = h;
    }
}


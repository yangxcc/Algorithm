package binaryTree;

// Binary Search Tree

import java.util.Stack;

/**
 * 二叉搜索树的概念是对于任何一个节点，它的左子树一定是比这个节点小，左子树一定比这个节点大
 * 因为二叉搜索树有这样的性质，所以使用中序遍历二叉搜索树的话一定能够升序地输出每个节点的值
 * <p>
 * 所以判断一棵二叉树是不是二叉搜索树的思路是
 * 它的左右子树都需要是二叉搜索树
 * 根节点大于左子树的最大值，小于右子树的最小值
 */
public class isBST {


    static int preValue = Integer.MIN_VALUE;

    public static boolean isBinarySearchTree01(Node head) {
        if (head == null) {
            return true;
        }
        boolean isLeftBST = isBinarySearchTree01(head.left);
        if (!isLeftBST) {
            return false;
        }
        if (head.val < preValue) {    // 判断左子树的值是不是小于根节点的值
            return false;
        } else {
            preValue = head.val;
        }
        return isBinarySearchTree01(head.right);
    }

    // 上面的中序遍历是递归形式的，使用非递归的
    public static boolean isBinarySearchTree01_UnRecursion(Node head) {
        if (head == null) {
            return true;
        }
        Stack<Node> stack = new Stack<>();
        int preValue = Integer.MIN_VALUE;
        while (!stack.isEmpty() || head != null) {
            if (head != null) {
                stack.push(head);
                head = head.left;
            } else {
                head = stack.pop();
                //System.out.println(head.val);
                if (head.val < preValue) {
                    return false;
                } else {
                    preValue = head.val;
                }
                head = head.right;
            }
        }
        return true;
    }

    /**
     * 根据二叉搜索树的性质来做，中序遍历二叉搜索树，将结果放到一个list中，看看这个list是不是升序的，不是就返回false
     */
    public static boolean isBinarySearchTree02(Node head) {
        // 不详细展示代码了
        return false;
    }

    /**
     * 后面会总结这种方法，树形DP
     * */
    public static boolean isBinarySearchTree(Node head) {
        if (head == null) {
            return true;
        }
        return process(head).isBST;
    }

    public static returnType process (Node head) {
        if (head == null) {
            return null;
        }
        returnType leftInfo = process(head.left);
        returnType rightInfo = process(head.right);
        int max = head.val;
        int min = head.val;
        if (leftInfo != null) {
            min = Math.min(min, leftInfo.min);
            max = Math.max(max, leftInfo.max);
        }
        if (rightInfo != null) {
            min = Math.min(min, rightInfo.min);
            max = Math.max(max, rightInfo.max);
        }
        boolean isBST = true;
        if (
                (leftInfo != null && !leftInfo.isBST) ||
                        (rightInfo != null && !rightInfo.isBST) ||
                        (leftInfo != null && leftInfo.max > head.val) ||
                        (rightInfo != null && rightInfo.min < head.val)
        ) {
            isBST = false;
        }
        return new returnType(isBST,min,max);
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
        System.out.println(isBinarySearchTree01(head));
        System.out.println("=================");
        System.out.println(isBinarySearchTree01_UnRecursion(head));
        System.out.println("================");
        System.out.println(isBinarySearchTree(head));
    }
}

class returnType {
    public boolean isBST;
    public int min;
    public int max;

    public returnType(boolean isBST, int mi, int ma) {
        this.isBST = isBST;
        this.min = mi;
        this.max = ma;
    }

    public returnType(boolean isBST) {
        this.isBST = isBST;
    }
}



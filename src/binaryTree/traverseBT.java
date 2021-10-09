package binaryTree;

import java.util.Stack;

/**
 * traverse Binary Tree
 * 分别使用递归和非递归的方式来前序、中序、后序地来遍历二叉树
 * */
public class traverseBT {

    /**
     * 前序遍历，根左右*/
    public static void preOrder (Node head) {
        if (head == null) {
            return;
        }
        System.out.println(head.val);
        preOrder(head.left);
        preOrder(head.right);
    }

    /**
     * 中序遍历，左根右*/
    public static void inOrder (Node head) {
        if (head == null) {
            return;
        }
        inOrder(head.left);
        System.out.println(head.val);
        inOrder(head.right);
    }

    /**
     * 后序遍历，左右根
     * */
    public static void postOrder (Node head) {
        if (head == null) {
            return;
        }
        postOrder(head.left);
        postOrder(head.right);
        System.out.println(head.val);
    }

    /**
     * 其实我们可以看出，前序、中序、后序遍历的递归做法只是输出的位置不同而已，下面我们来解释一下
     *
     *
     *                    1
     *                   / \
     *                  2   3
     *                 / \  / \
     *                4   5 6  7
     *
     * 对于上面这个个二叉树来说，不过是哪一种遍历方法，使用递归中间都会产生一个递归序
     * 每个节点都会进入3次，以前序遍历为例
     *   最开始进入 1，之后进入到左孩子2中，2在进入它的左孩子4中
     *   4没有左孩子，回到本身，也没有有孩子，再次回到自身，随后在进入到2中
     *   然后进入2的右孩子5，5没有左孩子，回到本身，也没有有孩子，再次回到自身，随后在进入到2中
     *   ...
     *   通过上面的过程可以看到每个节点都会被进入三次，因此产生的递归序如下
     *   1 2 4 4 4 2 5 5 5 2 1 3  6 6 6 3 7 7 7 3 1
     *   所谓的前序、中序、后序只是递归序中不同数字第几个被输出
     *   比如前序，那就是第一个数字被输出，结果是 1 2 4 5 3 6 7
     *       中序，那就是第二个数字被输出，结果是 4 2 5 1 6 3 7
     *       后序，那就是第三个数字被输出，结果是 4 5 2 6 7 3 1
     *  */


    /**
     * 前序遍历非递归的解题思路
     * 使用一个栈
     * 先弹出栈顶元素
     * 将当前栈顶元素的左右孩子压入栈中，先压入右孩子，在押入左孩子
     *
     * */
    public static void preOrderUnRecursion (Node head) {
        if (head == null) {
            return;
        }
        Stack<Node> stack = new Stack<>();
        Node cur = head;
        stack.push(cur);
        while (!stack.isEmpty()) {
            cur = stack.pop();
            System.out.println(cur.val);
            if (cur.right != null) {
                stack.push(cur.right);
            }
            if (cur.left != null) {
                stack.push(cur.left);
            }
        }
    }

    /**
     * 中序遍历非递归的解题思路
     *
     * 最开始先把二叉树的左边界给加入到栈中
     *
     * */
    public static void inOrderUnRecursion (Node head) {
        if (head == null) {
            return;
        }
        Stack<Node> stack = new Stack<>();
        Node cur = head;
        while (!stack.isEmpty() || cur != null) {
            if (cur != null) {
                stack.push(cur);
                cur = cur.left;
            } else {
                cur = stack.pop();
                System.out.println(cur.val);
                cur = cur.right;
            }
        }
    }

    /**
     * 后序遍历非递归的解题思路 1： 使用两个栈
     *
     * */
    public static void postOrderUnRecursion (Node head) {
        if (head == null) {
            return;
        }
        Stack<Node> stack1 = new Stack<>();
        Stack<Node> stack2 = new Stack<>();
        stack1.push(head);
        while (!stack1.isEmpty()) {
            head = stack1.pop();
            stack2.push(head);
            if (head.left != null) {
                stack1.push(head.left);
            }
            if (head.right != null) {
                stack1.push(head.right);
            }
        }
        while (!stack2.isEmpty()) {
            System.out.println(stack2.pop().val);
        }
    }

    /**
     * 后序遍历非递归解题思路 2：使用一个栈
     * */
    public static void postOrderUnRecursion2 (Node head) {
        if (head == null) {
            return;
        }
        Stack<Node> stack = new Stack<>();
        stack.push(head);
        Node cur = null;
        while (!stack.isEmpty()) {
            cur = stack.peek();   // cur指向栈顶元素，但是不会弹出栈顶元素
            if (cur.left != null && head != cur.left && head != cur.right) {
                stack.push(cur.left);
            } else if (cur.right != null && head != cur.right) {
                stack.push(cur.right);
            } else {
                System.out.println(stack.pop().val);
                head = cur;
            }
        }
    }

    public static Node createBT () {
        Node head = new Node(1);
        Node node1 = new Node(2);
        Node node2 = new Node(3);
        Node node3 = new Node(4);
        Node node4 = new Node(5);
        Node node5 = new Node(6);
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
        Node head = createBT();
        preOrder(head);
        System.out.println("======================");
        preOrderUnRecursion(head);
        System.out.println("中序.....");
        inOrder(head);
        System.out.println("====================");
        inOrderUnRecursion(head);
        System.out.println("后序.....");
        postOrder(head);
        System.out.println("====================");
        postOrderUnRecursion(head);
        System.out.println("========后序遍历2=======");
        postOrderUnRecursion2(head);

    }
}

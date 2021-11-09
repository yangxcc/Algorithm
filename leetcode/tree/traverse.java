package tree;

import binaryTree.Node;

import java.util.Stack;

/**
 * 源自 leetcode 94. 二叉树的中序遍历
 *
 * 对于二叉树的遍历，前序中序后续，递归和非递归分别实现
 * */
public class traverse {

    public void preOrder(Node root) {
        if (root == null) {
            return ;
        }
        System.out.println(root.val);
        preOrder(root.left);
        preOrder(root.right);
    }

    public void preOrderUnRecursion(Node root) {
        if (root == null) {
            return;
        }
        Stack<Node> stack = new Stack<>();
        Node cur = root;
        stack.add(cur);
        while (!stack.isEmpty()) {
            cur = stack.pop();
            System.out.println(cur.val);
            if (cur.right != null) {
                stack.add(cur.right);
            }
            if (cur.left != null) {
                stack.add(cur.left);
            }
        }
    }


    public void inOrder(Node root) {
        if (root == null) {
            return;
        }
        inOrder(root.left);
        System.out.println(root);
        inOrder(root.right);
    }


    /**
     *
     * A
     *  \
     *   B
     *   这样可以嘛*/
    public void inOrderUnRecursion(Node root) {
        if (root == null) {
            return;
        }
        Stack<Node> stack = new Stack<>();
        Node cur = root;
//        stack.add(cur);    不能有这句话
        while (!stack.isEmpty() || cur != null) {   // 有可能栈空了，但是这时候右边的还没有遍历了
            if (cur != null) {
                stack.push(cur);
                cur = cur.left;          // 把左侧的都给压进去
            } else {
                cur = stack.pop();
                System.out.println(cur.val);
                cur = cur.right;
            }
        }
    }


    public void postOrder(Node root) {
        if (root == null) {
            return;
        }
        postOrder(root.left);
        postOrder(root.right);
        System.out.println(root);
    }


    public void postOrderUnRecursion(Node root){
        if (root == null) {
            return;
        }
        Stack<Node> stack = new Stack<>();
        Node cur = root;
        stack.add(cur);
        while(!stack.isEmpty()) {
            cur = stack.peek();
            if (cur.left != null && root != cur.left && root != cur.right) {
                stack.add(cur.left);
            } else if (cur.right != null && root != cur.right) {
                stack.add(cur.right);
            } else {
                System.out.println(stack.pop().val);
                root = cur;
            }
        }
    }
}

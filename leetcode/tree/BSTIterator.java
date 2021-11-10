package tree;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * leetcode 173 二叉搜索树迭代器 middle
 *
 * 实现一个二叉搜索树迭代器类BSTIterator ，表示一个按 中序遍历 二叉搜索树（BST）的迭代器：
 * BSTIterator(TreeNode root) 初始化 BSTIterator 类的一个对象。BST 的根节点 root 会作为构造函数的一部分给出。指针应初始化为一个不存在于 BST 中的数字，且该数字小于 BST 中的任何元素。
 * boolean hasNext() 如果向指针右侧遍历存在数字，则返回 true ；否则返回 false 。
 * int next()将指针向右移动，然后返回指针处的数字。
 * 注意，指针初始化为一个不存在于 BST 中的数字，所以对 next() 的首次调用将返回 BST 中的最小元素。
 *
 * 你可以假设 next() 调用总是有效的，也就是说，当调用 next() 时，BST 的中序遍历中至少存在一个下一个数字。
 *
 */
public class BSTIterator {

    // 其实就是中序遍历BST
//    // 先实现一个简单的方法，直接使用一个队列把BST按照中序遍历弄进去
//    Queue<Integer> queue = new LinkedList<>();
//
//    public BSTIterator(Node root) {
//        inOrder(root);
//    }
//
//    public void inOrder(Node root) {
//        if (root == null) {
//            return;
//        }
//        inOrder(root.left);
//        queue.add(root.value);
//        inOrder(root.right);
//    }
//
//    public int next() {
//        return queue.poll();
//    }
//
//
//    public boolean hasNext() {
//        return !queue.isEmpty();
//    }

    // 其实还可以使用一个栈，使用中序遍历非递归的思想，
    // 只往stack里面存入半棵树，左子树

    Stack<Node> stack;

    public BSTIterator(Node root) {
        stack = new Stack<>();
        Node cur = root;
        while (cur != null) {
            stack.add(cur);
            cur = cur.left;
        }
    }

    public int next() {
        int res = 0;
        Node node = stack.pop();
        res = node.value;
        Node cur = node.right;
        while (cur != null) {
            stack.add(cur);
            cur = cur.left;
        }

        return res;
    }

    public boolean hasNext() {
        return !stack.isEmpty();
    }
}

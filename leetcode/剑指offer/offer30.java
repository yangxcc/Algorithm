package 剑指offer;

import java.util.Stack;

/**
 * 包含min函数的栈
 */
public class offer30 {
    // 两种方法
    // 第一种，使用两个栈，一个是push栈，一个是min栈
    Stack<Integer> pushStack;
    Stack<Integer> minStack;

    public offer30() {
        pushStack = new Stack<>();
        minStack = new Stack<>();
    }

    public void push(int x) {
        pushStack.push(x);
        if (minStack.isEmpty()) {
            minStack.push(x);
        } else {
            Integer minVal = minStack.peek();
            minVal = minVal < x ? minVal : x;
            minStack.push(minVal);
        }
    }

    public void pop() {
        pushStack.pop();
        minStack.pop();
    }

    public int top() {
        return pushStack.peek();
    }

    public int min() {
        return minStack.peek();
    }
}

// 第二种方法，使用链表，能够保证top,push,pop等方法的时间复杂度为O(1)
// 自定义一个数据结构
class Node {
    int val;
    Node next;
    int minValCur;  // 目前为止的最小值

    public Node(int val, Node next, int min) {
        this.val = val;
        this.next = next;
        this.minValCur = min;
    }

    public Node(int val, int min) {
        this.val = val;
        this.minValCur = min;
    }
}

class MinStack {

    Node head;
    public MinStack() {
        head = null;
    }

    public void push(int x) {
        if (head == null) {
            head = new Node(x, null, x);
        } else {
            int min = head.minValCur < x ? head.minValCur : x;
            Node node = new Node(x, null, min);
            // 把这个节点插入到队头
            node.next = head;
            head = node;
        }
    }

    public void pop() {
        head = head.next;
    }

    public int top() {
        return head.val;
    }

    public int min() {
        return head.minValCur;
    }
}

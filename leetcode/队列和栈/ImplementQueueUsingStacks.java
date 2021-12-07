package 队列和栈;

import java.util.Stack;

/**
 * 用栈实现队列
 */
public class ImplementQueueUsingStacks {
    // 使用两个栈就能够把这个问题解决了
    Stack<Integer> stack1;
    Stack<Integer> stack2;

    public ImplementQueueUsingStacks() {
        stack1 = new Stack<>();
        stack2 = new Stack<>();
    }

    // push
    public void push(int val) {
        stack1.push(val);
    }

    // pop
    public int pop() {
        if (stack2.isEmpty()) {
            // stack2为空，那么把stack1中的数据倒进stack2中
            while (!stack1.isEmpty()) {
                stack2.push(stack1.pop());
            }
        }
        return stack2.pop();
    }

    // peek
    public int peek() {
        if (stack2.isEmpty()) {
            while (!stack1.isEmpty()) {
                stack2.push(stack1.pop());
            }
        }
        return stack2.peek();
    }

    // empty
    public boolean isEmpty() {
        return stack1.isEmpty() && stack2.isEmpty();
    }
}

package 队列和栈;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 用队列实现栈
 */
public class ImplementStackUsingQueues {
    Queue<Integer> queue;
    int top_elem;  // 记录队顶元素

    public ImplementStackUsingQueues() {
        queue = new LinkedList<>();
    }

    public void push(int x) {
        queue.offer(x);
        top_elem = x;
    }

    public int pop() {
        int size = queue.size();
        // 把队中元素依次弹出再加入
        while (size > 2) {
            queue.offer(queue.poll());
            size--;
        }
        // 一定要注意，弹出之后要更新top元素
        top_elem = queue.peek();
        queue.offer(queue.poll());
        return queue.poll();
    }

    public int top() {
        return top_elem;
    }

    public boolean empty() {
        return queue.isEmpty();
    }
}

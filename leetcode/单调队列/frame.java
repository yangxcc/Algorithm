package 单调队列;

import java.util.LinkedList;

/**
 * 单调队列的实现
 */
public class frame {
    // MonotonicQueue
    LinkedList<Integer> queue = new LinkedList<>();

    public void push(int n) {
        while (!queue.isEmpty() && queue.peekLast() < n) {
            queue.pollLast();
        }
        queue.addLast(n);
    }

    // 这里有这个x参数n原因是判断n是否已经在push的过程中就被弹出了
    public void pop(int n) {
        if (n == queue.getFirst()) {
            queue.pollFirst();
        }
    }

    // 当前单调队列中的最大值
    public int max() {
        return queue.getFirst();
    }
}

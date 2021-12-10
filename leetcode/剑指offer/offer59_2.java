package 剑指offer;

import java.util.LinkedList;

/**
 * 队列的最大值
 *
 * 请定义一个队列并实现函数 max_value 得到队列里的最大值，要求函数max_value、push_back 和 pop_front 的均摊时间复杂度都是O(1)。
 *
 * 若队列为空，pop_front 和 max_value 需要返回 -1
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/dui-lie-de-zui-da-zhi-lcof
 */
public class offer59_2 {
    // 这道题我们可以借鉴最小栈实现的思想，这里维护了一个单调队列
    LinkedList<Integer> queue;
    LinkedList<Integer> helper;
    // MaxQueue
    public offer59_2() {
        queue = new LinkedList<>();
        helper = new LinkedList<>();
    }

    public int max_value() {
        return helper.isEmpty() ? -1 : helper.peekFirst();
    }

    public void push_back(int value) {
        queue.addLast(value);
        while (!helper.isEmpty() && helper.peekLast() < value) {
            helper.pollLast();
        }
        helper.addLast(value);
    }

    public int pop_front() {
        if (queue.isEmpty()) {
            return -1;
        }
        int val = queue.pollFirst();
        if (val == helper.getFirst()) {
            helper.pollFirst();
        }
        return val;
    }
}

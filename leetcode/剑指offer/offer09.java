package 剑指offer;

import java.util.LinkedList;
import java.util.Stack;

/**
 * 用两个栈实现队列
 *
 * 题目链接：https://leetcode-cn.com/problems/yong-liang-ge-zhan-shi-xian-dui-lie-lcof/submissions/
 *
 * 其实通过这道题的思路，同样能够解决最小栈的问题，https://leetcode-cn.com/problems/min-stack/
 * 实现最小栈，我们也可以通过两个站，一个push栈，一个min栈
 * 当往里加数据的时候，把数据加入到push栈中，如果min栈为空，把数据也加到min中，如果min不为空，那么用这个加入的数据和min
 * 栈的栈顶相比，如果小就加进去，如果打，就在加入一份栈顶到min栈中
 *
 * 当然，也可以使用更好的办法，用链表来表示，给链表中的每个节点加上一个树型min，表示自己往后最小的数
 * */
public class offer09 {
    // 使用两个栈，stack1作为push栈，stack2作为pop栈
    // 只有当pop栈为空的时候，push栈才把它里面的数据倒进pop栈中

    Stack<Integer> stack1;
    Stack<Integer> stack2;

    public offer09() {
        stack1 = new Stack<Integer>();
        stack2 = new Stack<Integer>();
    }

    public void appendTail(int value) {
        stack1.push(value);
    }

    public int deleteHead() {
        if (stack2.isEmpty()) {
            if (stack1.isEmpty()) return -1;   // 如果两个栈都空了就是都没有元素了，队空了
            while (!stack1.isEmpty()) {
                stack2.push(stack1.pop());
            }
        }
        return stack2.pop();
    }

}


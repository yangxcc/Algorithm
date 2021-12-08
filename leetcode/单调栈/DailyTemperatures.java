package 单调栈;

import java.util.Stack;

/**
 * 输入: temperatures = [73,74,75,71,69,72,76,73]
 * 输出: [1,1,4,2,1,1,0,0]
 *
 * 使用单调队列的一个简单变形，这里不是求next greater number的值，而是求他的索引
 */
public class DailyTemperatures {
    public int[] dailyTemperatures(int[] temperatures) {
        if (temperatures.length == 0) {
            return new int[0];
        }
        int[] res = new int[temperatures.length];
        Stack<Integer> stack = new Stack<>();

        for (int i = temperatures.length - 1; i >= 0; i--) {
            while (!stack.isEmpty() && temperatures[stack.peek()] <= temperatures[i]) {
                stack.pop();
            }
            res[i] = stack.isEmpty() ? 0 : stack.peek() - i;
            stack.push(i);  // 这里存放的是索引，而不是值
        }
        return res;
    }
}

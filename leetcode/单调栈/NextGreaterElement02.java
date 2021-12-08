package 单调栈;

import java.util.Stack;

/**
 * 输入: [1,2,1]
 * 输出: [2,-1,2]
 * 解释: 第一个 1 的下一个更大的数是 2；
 * 数字 2 找不到下一个更大的数；
 * 第二个 1 的下一个最大的数需要循环搜索，结果也是 2。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/next-greater-element-ii
 */
public class NextGreaterElement02 {
    // 这个问题肯定还是要⽤单调栈的解题模板，但难点在于，⽐如输⼊是 [2,1,2,4,3]，对于最后⼀个元素 3，
    // 如何找到元素 4 作为 Next Greater Number。
    // 对于这种需求，常用套路就是将数组⻓度翻倍：
    // 但是我们可以不⽤构造新数组，而是利用循环数组的技巧来模拟数组⻓度翻倍的效果。
    public int[] nextGreaterElements(int[] nums) {
        if (nums.length == 0) {
            return new int[0];
        }

        int n = nums.length;

        int[] res = new int[n];
        Stack<Integer> stack = new Stack<>();

        // 从2n-1开始
        for (int i = 2 * n - 1; i >= 0; i--) {
            while (!stack.isEmpty() && stack.peek() <= nums[i % n]) {
                stack.pop();
            }
            res[i % n] = stack.isEmpty() ? -1 : stack.peek();
            stack.push(nums[i % n]);
        }
        return res;
    }
}

package 单调栈;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Stack;

/**
 * 单调栈的框架
 *
 * 单调栈用来解决next greater number问题
 * 给你⼀个数组 nums，请你返回⼀个等⻓的结果数组，
 * 结果数组中对应索引存储着下⼀个更⼤元素，如果没有
 * 更⼤的元素，就存 -1。
 *
 * ⽐如说，输⼊⼀个数组 nums = [2,1,2,4,3]，你返回数组 [4,2,4,-1,-1]。
 */
public class frame {
    public int[] findNextGreatNumber(int[] nums) {
        if (nums.length == 0) {
            return new int[0];
        }
        Deque<Integer> stack = new ArrayDeque<>();
        int[] res = new int[nums.length];

        // 倒着来，因为是next greater element，倒着来才能够先知道后面的情况
        for (int i = nums.length - 1; i >= 0; i--) {
            while (!stack.isEmpty() && stack.peekLast() <= nums[i]) {
                stack.pollLast();
            }
            res[i] = stack.isEmpty() ? -1 : stack.peekLast();
            stack.addLast(nums[i]);
        }
        return res;
    }
}

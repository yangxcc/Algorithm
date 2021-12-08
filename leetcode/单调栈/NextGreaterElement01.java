package 单调栈;

import java.util.HashMap;
import java.util.Stack;

/**
 * 输入: nums1 = [4,1,2], nums2 = [1,3,4,2].
 * 输出: [-1,3,-1]
 * 解释:
 * 对于 num1 中的数字 4 ，你无法在第二个数组中找到下一个更大的数字，因此输出 -1 。
 * 对于 num1 中的数字 1 ，第二个数组中数字1右边的下一个较大数字是 3 。
 * 对于 num1 中的数字 2 ，第二个数组中没有下一个更大的数字，因此输出 -1 。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/next-greater-element-i
 */
public class NextGreaterElement01 {
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        if (nums1.length == 0) {
            return new int[0];
        }

        // 对nums2维护一个单调栈
        int[] helper = new int[nums2.length];
        Stack<Integer> stack = new Stack<>();
        // 来一个hashmap记录nums1中相应元素在nums2中的对应位置
        HashMap<Integer, Integer> hm = new HashMap<>();
        int[] res = new int[nums1.length];

        for (int i = nums2.length - 1; i >= 0; i--) {
            while (!stack.isEmpty() && stack.peek() <= nums2[i]) {
                stack.pop();
            }
            helper[i] = stack.isEmpty() ? -1 : stack.peek();
            stack.push(nums2[i]);
            hm.put(nums2[i], i);
        }

        for (int i = 0; i < nums1.length; i++) {
            int index = hm.get(nums1[i]);
            res[i] = helper[index];
        }
        return res;
    }
}

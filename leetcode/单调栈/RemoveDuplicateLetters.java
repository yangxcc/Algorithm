package 单调栈;

import java.util.Stack;

/**
 * 值得注意的是，前面几个删除重复元素的题都是在排序数组中，所以他们可以直接使用快慢指针
 *
 * 这道题算法删除重复元素的最难的题了
 * 给你一个字符串 s ，请你去除字符串中重复的字母，使得每个字母只出现一次。
 * 需保证 返回结果的字典序最小（要求不能打乱其他字符的相对位置）。
 * https://leetcode-cn.com/problems/remove-duplicate-letters/
 * leetcode 316
 *
 * 这道题需要使用单调栈，而且他也不能够原地修改了
 */
public class RemoveDuplicateLetters {

    public String removeDuplicateLetters(String s) {
        if (s.equals("")) {
            return "";
        }
        Stack<Character> stack = new Stack<>();
        boolean[] inStack = new boolean[256];  // ASCII码对照表中一共有256种可能性
        int[] count = new int[256];   // 还需要有一个计数器，看看这个字符串中某个字符出现了几次
        for (char c : s.toCharArray()) {
            count[c]++;
        }
        for (char c : s.toCharArray()) {
            count[c]--;   // 表示已经访问过这个字符了
            if (inStack[c]) {
                continue;   // 因为需要去重，所以如果stack中有这个字符了，跳过就好了
            }
            while (!stack.isEmpty() && stack.peek() < c) {
                if (count[c] == 0) {
                    break;
                }
//                stack.pop();
                inStack[stack.pop()] = false;
                // 只有当c出现次数超过1次的时候，才能够把他弹出来，否则就不弹了
            }
            inStack[c] = true;
            stack.push(c);
        }
        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()) {
            sb.append(stack.pop());
        }
        return sb.reverse().toString();
    }

}

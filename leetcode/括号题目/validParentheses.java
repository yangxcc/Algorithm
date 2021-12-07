package 括号题目;

import java.util.Stack;

/**
 * 有效的括号
 * 输入：s = "([)]"
 * 输出：false
 * */
public class validParentheses {
    public boolean isValid(String s) {
        if (s.equals("") || s.length() == 0) {
            return false;
        }
        Stack<Character> stack = new Stack<>();
        // 将左括号压入栈中
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(' || c == '[' || c == '{') {
                stack.push(c);
            } else {
                if (!stack.isEmpty() && want(c) == stack.peek()) {
                    stack.pop();
                } else {
                    return false;
                }
            }
        }
        return stack.isEmpty();   // 看看左括号有没有匹配完
    }

    public char want(char c) {
        if (c == ']') {
            return '[';
        } else if(c == ')') {
            return '(';
        } else {
            return '{';
        }
    }
}

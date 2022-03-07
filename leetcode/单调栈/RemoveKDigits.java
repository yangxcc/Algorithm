package 单调栈;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 *
 */
public class RemoveKDigits {
    public static String removeKdigits(String num, int k) {
        Deque<Character> q = new ArrayDeque<>();
        q.addLast(num.charAt(0));
        for (int i = 1; i < num.length(); i++) {
            while (!q.isEmpty() && q.peekLast() > num.charAt(i) && k > 0) {
                q.pollLast();
                k--;
            }
            q.addLast(num.charAt(i));
        }

        // 有可能栈中元素是num的全部，即num是一个升序的数字，比如112，12345等
        // 所以这里需要在弹出k个元素
        for (int i = 0; i < k; i++) {
            q.pollLast();
        }

        StringBuilder res = new StringBuilder();
        while (!q.isEmpty()) {
            res.append(q.pollFirst());
        }
        String s = res.toString();
        // 去除前导0
        if (s.length() == 0) {
            return "0";
        }
        int index = 0;
        while (index < s.length()) {
            if (s.charAt(index) == '0') {
                index++;
            } else {
                break;
            }
        }
        return index == s.length() ? "0" : s.substring(index);
    }

    public static void main(String[] args) {
        String s = "10";
        System.out.println(removeKdigits(s, 3));
    }
}

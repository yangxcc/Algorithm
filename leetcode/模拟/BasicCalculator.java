package 模拟;

import java.util.ArrayDeque;
import java.util.Deque;

public class BasicCalculator {
    // 这一种做法没有考虑到优先级
    public static int calculate(String s) {
        Deque<Integer> numStack = new ArrayDeque<>();
        Deque<Character> charStack = new ArrayDeque<>();
        char[] chs = s.toCharArray();
        int num = 0;
        for (char ch : chs) {
            // if (ch == ' ') {
            //     continue;
            // }
            if (Character.isDigit(ch)) {
                // 是数字,有可能是多位数字
                num = num * 10 + ch - '0';
            } else if (ch == ' ') {
                continue;
            } else {
                // 是符号
                numStack.addLast(num);
                num = 0;
                charStack.addLast(ch);
            }
        }

        numStack.addLast(num);   // 这一句话是debug出来的，因为是碰到符号才入栈，所以最后面的数入不了栈

        // 弹栈
        while (!charStack.isEmpty()) {
            char c = charStack.removeLast();
            int num1 = numStack.removeLast();
            int num2 = numStack.removeLast();
            int res = calculateHelper(num2, num1, c);
            numStack.addLast(res);
        }
        return numStack.removeLast();
    }

    public static int calculateHelper(int a, int b, char ch) {
        // 计算a和b
        if (ch == '+') {
            return a + b;
        } else if (ch == '-') {
            return a - b;
        } else if (ch == '*') {
            return a * b;
        } else {
            return a / b;
        }
    }

    public static int calculate2(String s) {
        // 保存上一个符号
        char sign = '+';
        Deque<Integer> numStack = new ArrayDeque<>();
        int num = 0;
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (Character.isDigit(ch)) {
                // 记录当前数字
                num = num * 10 + ch - '0';
            }
            if ((ch < '0' && ch != ' ') || i == s.length() - 1) {
                // 这里应该是在判断上一个符号是什么，所以应该是sign
                if (sign == '+') {
                    numStack.addLast(num);
                } else if (sign == '-') {
                    numStack.addLast(-num);
                } else if (sign == '*') {
                    numStack.addLast(numStack.removeLast() * num);
                } else if (sign == '/') {
                    numStack.addLast(numStack.removeLast() / num);
                }
                // 记录当前符号
                sign = ch;
                // 数字清零
                num = 0;
            }
        }
        int res = 0;
        while (!numStack.isEmpty()) {
            res += numStack.removeLast();
        }
        return res;
    }
    public static void main(String[] args) {
        String s = "3+2*2";
        System.out.println(calculate2(s));
    }
}

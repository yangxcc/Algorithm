package 剑指offer;

import java.util.Arrays;

/**
 * 字符串转成整数
 *
 * 输入: "42"
 * 输出: 42
 *
 * 输入: "   -42"
 * 输出: -42
 * 解释: 第一个非空白字符为 '-', 它是一个负号。
 *      我们尽可能将负号与后面所有连续出现的数字组合起来，最后得到 -42 。
 *
 * 输入: "4193 with words"
 * 输出: 4193
 * 解释: 转换截止于数字 '3' ，因为它的下一个字符不为数字。
 *
 * 输入: "words and 987"
 * 输出: 0
 * 解释: 第一个非空字符是 'w', 但它不是数字或正、负号。
 *      因此无法执行有效的转换。
 *
 * 输入: "-91283472332"
 * 输出: -2147483648
 * 解释: 数字 "-91283472332" 超过 32 位有符号整数范围。
 *      因此返回 INT_MIN (−231) 。
 *
 */
public class offer67 {
    public int strToInt(String str) {
        String s = str.trim();
        if (s.equals("") || s.length() == 0) {
            return 0;
        }
        boolean flag = false;  // 是不是负数
        // 第一个字符必须是+或者-或者数字
        // 判断一个字符是不是数字 Character.isDigit()
        char[] chs = s.toCharArray();
        long res = 0;
        if (chs[0] == '+' || chs[0] == '-' || Character.isDigit(chs[0])) {
            if (chs[0] == '+' || chs[0] == '-') {
                if (chs[0] == '-') {
                    flag = true;
                }
                // 把第一个字符删除掉，这样做的目的是为了统一下标，因为如果第一个字符不是符号的话，那我们要从0开始，而现在这种情况是要从1开始
                Arrays.copyOfRange(chs, 1, chs.length);
            }
            // 通过上面，不管第一个字符是数字还是符号，都从0开始就可以了
            int index = 0;
            while (index < chs.length && Character.isDigit(chs[index])) {
                res *= 10;
                res += chs[index] - '0';  // char字符转换成int，需要在后面-0
                index++;
                // 顺便在这里判断一下是否越界
                if (res > Integer.MAX_VALUE) {
                    return flag ? Integer.MIN_VALUE : Integer.MAX_VALUE;
                }
            }
            return flag ? -(int)res : (int)res;
        } else {
            return 0;
        }
    }
}

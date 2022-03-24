package 反转字符串数组;

/**
 * 输入：s = "the sky is blue"
 * 输出："blue is sky the"
 *
 * 要求去掉多余的空格，如果使用库函数就没什么意思了
 * 在题目的基础上，在加上一个约束条件：空间复杂度为O(1)
 *
 * 思路是：去掉字符串中多余的空格，反转整个字符串，反转字符串中的每个单词
 */
public class ReverseWords {
    public String reverseWords(String s) {
        int n = s.length();
        // 先去掉字符串最前面和最后面以及中间多余的空格
        StringBuilder sb = removeSpace(s);

        reverse(sb, 0, sb.length() - 1);

        reverseEachWord(sb);

        return sb.toString();
    }

    public StringBuilder removeSpace(String s) {
        int start = 0;
        int end = s.length() - 1;
        // 去掉前后的空格
        while (start <= end && s.charAt(start) == ' ') {
            start++;
        }
        while (start <= end && s.charAt(end) == ' ') {
            end--;
        }
        StringBuilder sb = new StringBuilder();
        while (start <= end) {
            char c = s.charAt(start);
            // 后面这个条件是为了让每个单词后面都跟着一个空格
            if (c != ' ' || sb.charAt(sb.length() - 1) != ' ') {
                sb.append(c);
            }
            start++;
        }
        return sb;
    }

    public void reverse(StringBuilder sb, int begin, int end) {
        while (begin < end) {
            char temp = sb.charAt(begin);
            sb.setCharAt(begin, sb.charAt(end));
            sb.setCharAt(end, temp);
            begin++;
            end--;
        }
    }

    // 反转字符串中的每个单词
    public void reverseEachWord(StringBuilder sb) {
        int n = sb.length() - 1;
        int begin = 0;
        int end = 0;

        while (begin <= n) {
            while (end <= n && sb.charAt(end) != ' ') {
                end++;
            }
            reverse(sb, begin, end - 1);
            begin = end + 1;
            end = begin + 1;
        }
    }
}

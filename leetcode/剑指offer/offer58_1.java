package 剑指offer;

/**
 * 反转单词顺序
 * 输入一个英文句子，翻转句子中单词的顺序，但单词内字符的顺序不变。
 * 为简单起见，标点符号和普通字母一样处理。例如输入字符串"I am a student. "，
 * 则输出"student. a am I"
 *
 *
 * 链接：https://leetcode-cn.com/problems/fan-zhuan-dan-ci-shun-xu-lcof
 *
 * 这道题特别烦的就是题目中的一些要求
 *  1. 输入字符串可以在前面或者后面包含多余的空格，但是反转后的字符不能包括。
 *  2. 如果两个单词间有多余的空格，将反转后单词间的空格减少到只含一个
 **/
public class offer58_1 {
    public String reverseWords(String s) {
        if (s.equals("")) {
            return "";
        }
        String[] strs = s.trim().split(" ");
        // trim函数就是去掉字符串两端的空格
        StringBuilder sb = new StringBuilder();
        for (int i = strs.length - 1; i >= 0 ; i--) {
            // 如果str == "   "，split之后就是""空字符串
            if (strs[i].equals("")) {
                continue;
            }
            if (i == 0) {
                sb.append(strs[i].trim());
            } else {
                sb.append(strs[i].trim()).append(" ");
            }
        }
        return sb.toString();
    }
}

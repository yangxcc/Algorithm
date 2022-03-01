package dp;

/**
 * 正则表达式的匹配
 * https://leetcode-cn.com/problems/regular-expression-matching/
 * 终于到了正则表达式了
 *
 * 正则表达式匹配一共有四种情况，具体写到了 `正则表达式.md` 文档中
 */
public class RegularExpressionMatching {
    /**
     *
     * @param s  字符串s
     * @param p  模式串p
     * @return  判断字符串s能不能和模式串p相匹配
     */
    // 说到底还是两个字符串上的动态规划问题，因为涉及到了穷举
    public boolean isMatch(String s, String p) {
        return dp(s, 0, p, 0);
    }

    // 判断字符串s(i,...)能不能和p(j,..)相匹配
    public boolean dp(String s, int i, String p, int j) {
        // base case
        if (j == p.length()) {  // 模式串走到了头
            return i == s.length();   // 再看看字符串是不是也走到了头
        }
        if (i == s.length()) {  // 如果字符串先走到了头，那么需要判断一下是否存在这种情况
            // s = "a",  p = "ab*c*"，也就是看看j往后的字符串是不是一个字符配上一个*
            if ((p.length() - j) % 2 == 1) {
                return false;   // 都是奇数了，肯定不能是一个字符一个*号了
            }
            for (; j + 1 < p.length(); j+=2) {
                if (p.charAt(j + 1) != '*') {
                    return false;
                }
            }
            return true;
        }
        // 正则表达式中.可以匹配任何的一 个 字符
        if (s.charAt(i) == p.charAt(j) || p.charAt(j) == '.') {
            // 还需要看看p[j + 1]是不是*
            if (j < p.length() - 1 && p.charAt(j + 1) == '*') {
                // 是*号的话就可以选择是匹配0个还是匹配多个
                return dp(s, i, p, j + 2) || dp(s, i + 1, p, j);
                // 选择匹配0个字符                 选择匹配多个字符
            } else {
                return dp(s, i + 1, p, j + 1);
            }
        } else {
            // 当s.charAt(i) != s.charAt(j) 并且 s.charAt(j) != '.'
            if (j + 1 < p.length() && p.charAt(j + 1) == '*') {
                // 这里可以只能选择匹配0个字符，将不相等的这个字符删除下去
                return dp(s, i, p, j + 2);
            } else {
                return false;
            }
        }
    }
}

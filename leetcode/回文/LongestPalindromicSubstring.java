package 回文;

/**
 * 最长回文子串
 *
 * 中心思想就是确定一个中心点，以中心点开始，向两边扩展
 * 但是需要注意的是字符串长度为偶数时，我们应该怎么处理
 * 一种方式就是改造字符串，让字符串变成奇数长度，比如 1221 -> #1#2#2#1#
 *
 * 但是另一种方法就是我们可以分别以一个位置为中点，和  两个位置为中心点
 * 为什么不使用3个位置作为中点呢，因为3个相当于1个两边加上2个回文字符，4个相当于两个两边加上2个回文字符
 *
 */
public class LongestPalindromicSubstring {
    // 时间复杂度是O(n^2)，空间复杂度是O(1)
    public String longestPalindrome(String s) {
        if (s.equals("") || s.length() == 0) {
            return "";
        }
        String res = "";
        for (int i = 0; i < s.length(); i++) {
            // 以i为中心点
            String s1 = findPalindrome(s, i, i);
            // 以i和i+1为中心点
            String s2 = findPalindrome(s, i, i + 1);

            res = s1.length() > res.length() ? s1 : res;
            res = s2.length() > res.length() ? s2 : res;
        }
        return res;
    }

    public String findPalindrome(String s, int left, int right) {
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            left--;
            right++;
        }

        // substring返回的是左闭右开的区间
        return s.substring(left + 1, right);
    }


    // 寻找最长回文子串还可以使用动态规划
    // 我发现这种寻找子序列、子数组、子串的题目好像都可以使用动态规划
    public String longestPalindromeWithDP(String s) {
        if (s.equals("") || s.length() == 0) {
            return "";
        }
        int maxLen = 0;
        int left = 0, right = 0;
        int n = s.length();
        // dp[i][j]表示区间[i,j]能是否存在回文字符串
        boolean[][] dp = new boolean[n][n];
        // 如果s[i] != s[j]  false
        // 如果s[i] == s[j]有三种情况：
        //      i == j 时， true
        //      j - i == 1时， true
        //      其他情况下，dp[i][j] == dp[i+1][j-1]
        // 从最后一种情况也能够推出，dp数组是从后往前的
        for (int i = n - 1; i >= 0; i--) {
            for (int j = i; j < n; j++) {
                if (s.charAt(i) != s.charAt(j)) {
                    dp[i][j] = false;
                } else {
                    if (i == j || j - i == 1) {
                        dp[i][j] = true;
                    } else {
                        dp[i][j] = dp[i + 1][j - 1];
                    }
                }

                if (dp[i][j] && maxLen < j - i + 1) {   // 一定不要忘了dp[i][j]，当区间[i,j]内存在回文字符串时在更新边界
                    maxLen = j - i + 1;
                    left = i;
                    right = j;
                }
            }
        }
        return s.substring(left, right + 1);
    }

    // 最长回文字符串是少有的使用动态规划时间复杂度和空间复杂度都是O(n^2)的情况

    /**
     * 解决最长回文字符串问题最好的办法其实是manacher方法
     * 时间复杂度O(n),空间复杂度O(1)
     */
}

package dp.子序列;

/**
 * 最长公共子序列
 * 输入：text1 = "abcde", text2 = "ace"
 * 输出：3
 * 解释：最长公共子序列是 "ace" ，它的长度为 3 。
 */
public class LongestCommonSubsequence {
    public int longestCommonSubsequence(String text1, String text2){
        memo = new int[text1.length()][text2.length()];
        return dp(text1, 0, text2, 0);
    }

    int[][] memo;

    // dp表示求s1(i,...)和s2(j,...)之间的最长子序列
    public int dp(String s1, int i, String s2, int j) {
        if (i >= s1.length() || j >= s2.length()) {
            return 0;
        }
        if (memo[i][j] != -1) {
            return memo[i][j];
        }
        if (s1.charAt(i) == s2.charAt(j)) {
            memo[i][j] = 1 + dp(s1, i + 1, s2, j + 1);
//            return memo[i][j];
        } else {
            memo[i][j] = Math.max(dp(s1, i + 1, s2, j), dp(s1, i, s2, j + 1));
//            return memo[i][j];
        }
        return memo[i][j];
    }


    // 使用自底向上的动态规划
    public int longestCommonSubsequence2(String text1, String text2){
        int m = text1.length();
        int n = text2.length();
        int[][] dp = new int[m + 1][n + 1];
        // dp[i][j]表示 text1[0,..i]和text2[0,...j]的最长公共子序列
        // 初始化，第0行和第0列都是0，所以直接使用默认值就行了
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (text1.charAt(i - 1) == text2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }

        return dp[m][n];
    }
}

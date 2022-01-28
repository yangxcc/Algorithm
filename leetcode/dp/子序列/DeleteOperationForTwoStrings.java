package dp.子序列;

/**
 * 两个字符串的删除操作
 * 给定两个单词 word1 和 word2，找到使得 word1 和 word2 相同所需的最小步数，每步可以删除任意一个字符串中的一个字符。
 *
 * 输入: "sea", "eat"
 * 输出: 2
 * 解释: 第一步将"sea"变为"ea"，第二步将"eat"变为"ea"
 */
public class DeleteOperationForTwoStrings {
    public int minDistance(String word1, String word2) {
        // 找到word1和word2的最长公共字符串  1143题   LongestCommonSubsequence
        int step = findMaxPublicPart(word1, word2);
        return word1.length() - step + word2.length() - step;
    }

    public int findMaxPublicPart(String s1, String s2) {
        int m = s1.length();
        int n = s2.length();
        // dp[i][j]表示s1{0,..i}和s2{0,..j}的最长公共字符串
        int[][] dp = new int[m + 1][n + 1];
        // 初始化，第0行和第0列用默认值就行
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        return dp[m][n];
    }
}

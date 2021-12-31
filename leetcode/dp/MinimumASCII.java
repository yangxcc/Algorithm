package dp;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * 两个字符串的最小ASCII删除和
 * 给定两个字符串s1, s2，找到使两个字符串相等所需删除字符的ASCII值的最小和。
 *
 * 输入: s1 = "sea", s2 = "eat"
 * 输出: 231
 * 解释: 在 "sea" 中删除 "s" 并将 "s" 的值(115)加入总和。
 * 在 "eat" 中删除 "t" 并将 116 加入总和。
 * 结束时，两个字符串相等，115 + 116 = 231 就是符合条件的最小和。
 *
 */
public class MinimumASCII {
    public int minimumDeleteSum(String s1, String s2){
        int[][] memo = new int[s1.length()][s2.length()];
        for (int[] row : memo) {
            Arrays.fill(row, -1);
        }
        return dp(s1, 0, s2, 0);
    }

    // 计算s1[i,..]和s2[j,..]的最小ASCII码删除和，和找到最大公共子序列的思想一致，只不过要
    // 更改一下base case
    int[][] memo;
    public int dp(String s1, int i, String s2, int j) {
        int res = 0;
        // base case
        if (i == s1.length()) {
            for (; j < s2.length(); i++) {
                res += s2.charAt(j);
            }
            return res;
        }

        if (j == s2.length()) {
            for (; i < s1.length(); i++) {
                res += s1.charAt(i);
            }
            return res;
        }

        if (memo[i][j] != -1) {
            return memo[i][j];
        }

        if (s1.charAt(i) == s2.charAt(j)) {
            memo[i][j] = dp(s1, i + 1, s2, j + 1);
            return memo[i][j];
        } else {
            res += Math.min(s1.charAt(i) + dp(s1, i + 1, s2, j),
                                s2.charAt(j) + dp(s1, i, s2, j + 1));
            memo[i][j] = res;
            return memo[i][j];
        }
    }
}

package dp.背包问题.完全背包;

import java.util.List;

/**
 * 给你一个字符串 s 和一个字符串列表 wordDict 作为字典。请你判断是否可以利用字典中出现的单词拼接出 s 。
 *
 * 注意：不要求字典中出现的单词全部都使用，并且字典中的单词可以重复使用。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/word-break
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class WordBreak {
    public boolean wordBreak(String s, List<String> wordDict) {
        return dfs(s, 0, wordDict);
    }

    // 我能够想到的是bfs，但是不能通过全部测试用例，因为里面需要涉及到回溯
    private boolean dfs(String s, int i, List<String> wordDict) {
        if (i == s.length()) {
            return true;
        }
        for (int j = i; j < s.length(); j++) {
            if (wordDict.contains(s.substring(i, j + 1))) {
                return dfs(s, j + 1, wordDict);
                // 这里这么些不完全对，因为测试用例
                //"aaaaaaa"
                //["aaaa","aaa"]
                // 过不去，这里需要用到回溯，暂时还不知道怎么做
            }
        }
        return false;
    }

    // 不太清楚为什么这样就能够实现回溯
    // 实现以下记忆化搜索，虽然时间复杂度还是O(2^n)，但是优化效果很明显，leetcode上不会超时了
    public boolean dfs2(String s, int startIndex, List<String> wordDict, int[] memo) {
        if (startIndex == s.length()) {
            return true;
        }

        if (memo[startIndex] != 0) {
            return memo[startIndex] == 1 ? true : false;
        }

        for (int j = startIndex; j < s.length(); j++) {
            if (wordDict.contains(s.substring(startIndex, j + 1)) && dfs2(s, j + 1, wordDict, memo)) {
                memo[startIndex] = 1;
                return true;
            }
        }
        memo[startIndex] = -1;
        return false;
    }


    // 通过动态规划来做
    public boolean wordBreakUsingDP(String s, List<String> wordDict) {
        int n = s.length();
        // dp[i]表示的是第一个元素到第i个元素能否由wordDict中的元素构成
        boolean[] dp = new boolean[n + 1];
        dp[0] = true;
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (wordDict.contains(s.substring(j, i)) && dp[j]) {
                    dp[i] = true;
                }
            }
        }
        return dp[n];
    }
}

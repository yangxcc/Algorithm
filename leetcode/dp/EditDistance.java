package dp;

import java.util.Arrays;

/**
 * 编辑距离
 *
 * 给你两个单词 word1 和 word2，请你计算出将 word1 转换成 word2 所使用的最少操作数 。
 *
 * 你可以对一个单词进行如下三种操作：
 *
 * 插入一个字符
 * 删除一个字符
 * 替换一个字符
 *
 * 输入：word1 = "horse", word2 = "ros"
 * 输出：3
 * 解释：
 * horse -> rorse (将 'h' 替换为 'r')
 * rorse -> rose (删除 'r')
 * rose -> ros (删除 'e')
 *
 */
public class EditDistance {
    /**
     * 这里需要记住，对于两个字符串的动态规划问题，通常是使用两个指针i，j分别指向两个字符串，逐渐把大问题缩小
     * @param word1 第一个字符串
     * @param word2 第二个字符串
     * @return 两个字符串的最小编辑距离，让word1变成word2和让word2变成word1最后的结果都是一样的
     */
    public int minDistance(String word1, String word2) {
        int m = word1.length();
        int n = word2.length();
        memo = new int[m][n];
        for (int[] row : memo) {
            Arrays.fill(row, -1);
        }
        return dp(word1, 0, word2, 0);
    }

    /**
     * dp函数的作用可以是求出s1(i,..)和s2(j,..)字符串之间的最小编辑距离 （这种思路和求最长公共字符串一样）
     * 或者是或一种思路：dp的作用是返回s1(0,...i)和s2(0,..j)的最小编辑距离（其实我更倾向于把他当成dp数组的含义）
     * 其实就是一个是从前往后走，另一个是从后往前走
     * @param s1
     * @param i
     * @param s2
     * @param j
     * @return 让s1变成s2的最小步数
     */
    int[][] memo;
    // 这里我决定从前往后走，和公共字符串保持一样的思路
    public int dp(String s1, int i, String s2, int j) {
        // base case
        if (i == s1.length()) {      // 如果s1都走到头了，s2还没有，那么需要把s2剩下的字符串都添加到s1上，因为这里是统计个数，所以只需要计算个数
            // 而我们在计算最小ASCII码的时候，就需要把剩下每个字符的ASCII码都加上了
            return s2.length() - j;
        }
        if (j == s2.length()) {    // 同理，如果s2走到头了，那么s1就要把它剩下的都删除了
            return s1.length() - i;
        }

        if (memo[i][j] != -1) {
            return memo[i][j];
        }

        if (s1.charAt(i) == s2.charAt(j)) {
            memo[i][j] = dp(s1, i + 1, s2, j + 1);
            // 如果这两个字符相等，那么需要跳过这个字符，不需要进行任何的操作
//            return dp(s1, i + 1, s2, j + 1);
        } else {
            // 分别表示删除，添加，和替换
            // 因为是让s1变成s2，所以上面都是对s1的操作
            memo[i][j] = min(dp(s1, i + 1, s2, j),    // 删除这个数就相当于把i往后移动了一下，不再看i了，去看i+1
                    dp(s1, i, s2, j + 1),       // 添加一个数肯定是添加一个和s2.charAt(j)相等的数，所以就等价于把j往后移动一位
                    dp(s1, i + 1, s2, j + 1)) + 1;        // 替换s1中的i位置，就相当于将i和j都往后移一位
                    // 还有一点就是不要忘记+1
        }
        return memo[i][j];
    }

    public int min(int a, int b, int c) {
        return Math.min(a, Math.min(b, c));
    }

    // 如果是从后往前走的话，那么dp函数就应该这样写
    public int dpFromBackTOFront(String s1, int i, String s2, int j) {
        if (i == -1) {
            return j + 1;
        }
        if (j == -1) {
            return i + 1;
        }

        if (s1.charAt(i) == s2.charAt(j)) {
            return dp(s1, i - 1, s2, j - 1);
        } else {
            return min(dp(s1, i - 1, s2, j),  // 删除
                    dp(s1, i, s2, j - 1),            // 添加
                    dp(s1, i - 1, s2, j - 1)) + 1;  // 替换
        }
    }

    // 使用dp数组来解决这个问题
    public int minDistance2(String word1, String word2){
        int m = word1.length();
        int n = word2.length();
        // dp数组的含义就是dp[i][j]表示word1[0,...i]和word[0,...j]的最小编辑举例
        int[][] dp = new int[m + 1][n + 1];
        // dp初始化
        for (int i = 0; i <= n; i++) {
            // 第一行,含义就是当word1=""的时候
            dp[0][i] = i;
        }
        for (int j = 0; j <= m; j++) {
            // 第一列，含义就是当word2=""的时候
            dp[j][0] = j;
        }

        for (int row = 1; row <= m; row++) {
            for (int col = 1; col <= n; col++) {
                if (word1.charAt(row - 1) == word2.charAt(col - 1)) {
                    dp[row][col] = dp[row - 1][col - 1];
                } else {
                    dp[row][col] = min(dp[row - 1][col], dp[row][col - 1], dp[row - 1][col - 1]) + 1;
                }
            }
        }

        return dp[m][n];
    }
}

package 剑指offer;

/**
 * 把n个骰子扔在地上，所有骰子朝上一面的点数之和为s。输入n，打印出s的所有可能的值出现的概率。
 *
 * 你需要用一个浮点数数组返回答案，其中第 i 个元素代表这 n 个骰子所能掷出的点数集合中第 i 小的那个的概率。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/nge-tou-zi-de-dian-shu-lcof
 *
 */
public class offer60 {
    public double[] dicesProbability(int n) {
        double all = Math.pow(6, n);
        double[] res = new double[5 * n + 1];

        /**
         * f(s, n) = f(s - 1, n - 1) + f(s - 2, n - 1) + ... + f(s - 6, n - 1)
         */
        int[][] dp = new int[n + 1][6 * n + 1];

        for (int i = 1; i <= 6; i++) {
            dp[1][i] = 1;
        }

        for (int i = 1; i <= n; i++) {         // 骰子个数     n
            for (int j = i; j <= 6 * n; j++) { // 骰子当前点数 s
                for (int k = 1; k <= 6; k++) {
                    dp[i][j] += j - k >= 0 ? dp[i - 1][j - k] : 0;
                    if (i == n) {   // 骰子个数达到要求之后
                        res[j - i] = dp[i][j] / all;
                    }
                }
            }
        }
        return res;
    }
}

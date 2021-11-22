package 剑指offer;

/**
 * 剪绳子
 *
 * 给你一根长度为 n 的绳子，请把绳子剪成整数长度的 m 段（m、n都是整数，n>1并且m>1），
 * 每段绳子的长度记为 k[0],k[1]...k[m-1] 。请问 k[0]*k[1]*...*k[m-1] 可能的最大乘积是多少？
 * 例如，当绳子的长度是8时，我们把它剪成长度分别为2、3、3的三段，此时得到的最大乘积是18。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/jian-sheng-zi-lcof
 */
public class offer14_1 {

    public int cuttingRope(int n) {
        if (n <= 3) {
            return n - 1;
        }
        return process(n);
    }

    /**
     * 最开始递归一定通不过的原因在于我没有考虑到
     * 当绳子长度<=3的时候，不分割才是最大的，所以不能在base case那里return n - 1
     * */
    public int process(int n) {
        if (n <= 3) return n;
        int max = 0;
        for (int i = 4; i <= n; i++) {
            for (int j = 1; j <= i / 2; j++) {
                // i/2也是一个小优化，p(1)*p(3) == p(3) * p(1)
                max = Math.max(process(j) * process(i - j), max);
            }
        }
        return max;
    }


    public int cuttingRope_DP(int n) {
        if (n <= 3) {
            return n - 1;
        }
        int[] dp = new int[n + 1];
        // 因为当n <= 3的时候，不分割的长度比分割的长度大
        // 所以需要有下面的初始化
        dp[1] = 1;
        dp[2] = 2;
        dp[3] = 3;
        for (int i = 4; i <= n; i++) {
            for (int j = 1; j <= i / 2; j++) {
                dp[i] = Math.max(dp[i], dp[j] * dp[i - j]);
            }
        }
        return dp[n];
    }
}

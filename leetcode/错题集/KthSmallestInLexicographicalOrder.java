package 错题集;

/**
 * 字典序的第k小数字
 * 给定整数n和k，返回[1,n]中字典序第k小的数字
 */
public class KthSmallestInLexicographicalOrder {
    public int findKthNumber(int n, int k) {
        long cur = 1;
        k--;
        while (k > 0) {
            int steps = calSteps(n, cur, cur + 1);  // 计算以cur为根节点的树的节点个数
            if (steps <= k) {
                // 在另一棵树上
                cur += 1;
                k -= steps;
            } else {
                cur *= 10;
                k--;
            }
        }
        return (int)cur;
    }

    private int calSteps(int n, long n1, long n2) {
        long steps = 0;
        while (n1 <= n) {
            // 按照每一层来进行计算
            steps += Math.min(n2, n + 1) - n1;
            n1 *= 10;
            n2 *= 10;
        }
        return (int)steps;
    }
}

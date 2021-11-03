package tree;

/**
 * leetcode 96 不同的二叉搜索树 middle
 * <p>
 * 给你一个整数 n ，求恰由 n 个节点组成且节点值从 1 到 n 互不相同的 二叉搜索树 有多少种？
 * 返回满足题意的二叉搜索树的种数。
 */
public class numsOfBST {

    public int numsBST(int n) {
        if (n <= 0) {
            return 0;
        }
        // 不加入备忘录在leetcode中时间过不去
        int[][] dp = new int[n + 1][n + 1];
        return build(0, n, dp);
    }

    public int build(int left, int right, int[][] dp) {
        if (left > right) {
            return 1;
        }
        if (dp[left][right] != 0) {
            return dp[left][right];
        }
        int res = 0;
        for (int i = left; i <= right; i++) {
            res += build(left, i - 1, dp) * build(i + 1, right, dp);
        }
        dp[left][right] = res;
        return res;
    }
}

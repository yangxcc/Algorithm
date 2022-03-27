package DFS.非岛问题;

import java.util.Arrays;

/**
 * 矩阵中的最长递增子路径
 * <p>
 * 给定一个 m x n 整数矩阵 matrix ，找出其中 最长递增路径 的长度。
 * <p>
 * 对于每个单元格，你可以往上，下，左，右四个方向移动。 你 不能 在 对角线 方向上移动或移动到 边界外（即不允许环绕）。
 */
public class LongestIncreasingPathInMatrix {
    // 如果没有猜错的话，这个应该不能使用dp
    // 因为这是四个方向，可以使用一个二维数组来表示方向

    // 能不能从每个点开始进行dfs
    public int longestIncreasingPath(int[][] matrix) {
        int n = matrix.length;
        int m = matrix[0].length;
        int res = 1;
        memo = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                res = Math.max(res, dfs(matrix, i, j));
            }
        }
        return res;
    }

    // 添加一个备忘录
    int[][] memo;

    // 从i，j开始的最长递增路径
    public int dfs(int[][] matrix, int i, int j) {
        if (i < 0 || j < 0 || i >= matrix.length || j >= matrix[0].length) {
            return 0;
        }
        if (memo[i][j] != 0) {
            return memo[i][j];
        }
        int max = 1;
        if (i + 1 < matrix.length && matrix[i + 1][j] > matrix[i][j]) {
            max = Math.max(max, dfs(matrix, i + 1, j) + 1);
        }
        if (i - 1 >= 0 && matrix[i - 1][j] > matrix[i][j]) {
            max = Math.max(max, dfs(matrix, i - 1, j) + 1);
        }
        if (j + 1 < matrix[0].length && matrix[i][j + 1] > matrix[i][j]) {
            max = Math.max(max, dfs(matrix, i, j + 1) + 1);
        }
        if (j - 1 >= 0 && matrix[i][j - 1] > matrix[i][j]) {
            max = Math.max(max, dfs(matrix, i, j - 1) + 1);
        }
        memo[i][j] = max;
        return max;
    }

    // 果然是不能使用dp，感觉不是最优子问题
    public int longestIncreasingPathUsingDP(int[][] matrix) {
        int n = matrix.length;
        int m = matrix[0].length;
        // dp[i][j]表示以matrix[i][j]结尾的最长子路径
        int[][] dp = new int[n][m];
        for (int[] x : dp) {
            Arrays.fill(x, 1);
        }

        int max = 1;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (i + 1 < n && matrix[i][j] > matrix[i + 1][j]) {
                    dp[i][j] = Math.max(dp[i + 1][j] + 1, dp[i][j]);
                }
                if (i - 1 >= 0 && matrix[i][j] > matrix[i - 1][j]) {
                    dp[i][j] = Math.max(dp[i - 1][j] + 1, dp[i][j]);
                }
                if (j + 1 < m && matrix[i][j] > matrix[i][j + 1]) {
                    dp[i][j] = Math.max(dp[i][j + 1] + 1, dp[i][j]);
                }
                if (j - 1 >= 0 && matrix[i][j] > matrix[i][j - 1]) {
                    dp[i][j] = Math.max(dp[i][j - 1] + 1, dp[i][j]);
                }
                max = Math.max(max, dp[i][j]);
            }
        }
        return max;
    }
}

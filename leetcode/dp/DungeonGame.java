package dp;

/**
 * 地下城游戏
 * 只能向右或者向下走，每个位置的数都不能小于0
 * https://leetcode-cn.com/problems/dungeon-game/
 */
public class DungeonGame {
    public int calculateMinimumHP(int[][] dungeon) {
        return dp(dungeon, 0, 0);
    }

    // dp表示从i,j开始到右下角的最小血量
    // 很像最小路径和的求法
    public int dp(int[][] grid, int i, int j) {
        int m = grid.length;
        int n = grid[0].length;
        if (i == m || j == n) {
            return Integer.MAX_VALUE;
        }
        if (i == m - 1 && j == n - 1) {
            // 当右下角这个点是小于0的，那么最低血量就是要加上这个数之后，还要剩下个1
            return grid[i][j] <= 0 ? 1 - grid[i][j] : 1;
        }

        int res = Math.min(dp(grid, i + 1, j),
                            dp(grid, i, j + 1)) - grid[i][j];

        return res <= 0 ? 1 : res;
    }

    public int dp2(int[][] dungeon) {
        int m = dungeon.length;
        int n = dungeon[0].length;
        // dp[i][j]表示的是从i,j开始到右下角的最小血量
        int[][] dp = new int[m][n];
        // 初始化
        dp[m - 1][n - 1] = helper(1 - dungeon[m - 1][n - 1]);
        for (int i = m - 2; i >= 0; i--) {
            // 最后一列，只能向下
            dp[i][n - 1] = helper(dp[i + 1][n - 1] - dungeon[i][n - 1]);
        }
        for (int j = n - 2; j >= 0; j--) {
            dp[m - 1][j] = helper(dp[m - 1][j + 1] - dungeon[m - 1][j]);
        }

        for (int i = m - 2; i >= 0; i--) {
            for (int j = n - 2; j >= 0; j--) {
                int val = dungeon[i][j];
                dp[i][j] = Math.min(helper(dp[i + 1][j] - val), helper(dp[i][j + 1] - val));
            }
        }
        return dp[0][0];
    }

    public int helper(int ele) {
        // 血量最少也要是1
        return ele <= 0 ? 1 : ele;
    }
}

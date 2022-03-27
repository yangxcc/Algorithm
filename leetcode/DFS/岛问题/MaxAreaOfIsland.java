package DFS.岛问题;

/**
 * 岛屿的最大面积
 *
 * 在前面岛屿数量的基础上，判断一下哪个岛屿的“面积最大”
 */
public class MaxAreaOfIsland {
    public int maxAreaOfIsland(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int res = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    res = Math.max(res, dfs(grid, i, j));
                }
            }
        }
        return res;
    }

    // 这里使函数返回每个岛的数量
    public int dfs(int[][] grid, int i, int j) {
        int m = grid.length;
        int n = grid[0].length;
        if (i < 0 || j < 0 || i == m || j == n) {
            return 0;
        }
        if (grid[i][j] == 0) {
            return 0;
        }

        return dfs(grid, i - 1, j) +
                dfs(grid, i + 1, j) +
                dfs(grid, i, j - 1) +
                dfs(grid, i, j + 1) + 1;
    }
}

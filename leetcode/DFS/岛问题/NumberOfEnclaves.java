package DFS.岛问题;

/**
 * 给出一个二维数组 A，每个单元格为 0（代表海）或 1（代表陆地）。
 * <p>
 * 移动是指在陆地上从一个地方走到另一个地方（朝四个方向之一）或离开网格的边界。
 * <p>
 * 返回网格中无法在任意次数的移动中离开网格边界的陆地单元格的数量
 * <p>
 * 这道题同理，和封闭岛屿的思想一摸一样，只是他需要求封闭岛中单元格的数量
 * NumberOfClosedIslands
 */
public class NumberOfEnclaves {
    public int numOfEnclaves(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int res = 0;

        // 先把上下两个边上的岛屿给“淹了”
        for (int col = 0; col < n; col++) {
            dfs(grid, 0, col);       // 淹没第一行上的岛屿
            dfs(grid, m - 1, col);   // 淹没最后一行上的岛屿
        }

        // 同理，淹没第一列和最后一列的岛屿
        for (int row = 0; row < m; row++) {
            dfs(grid, row, 0);
            dfs(grid, row, n - 1);
        }

        // 边上的岛屿都处理完了之后，就能够去统计封闭岛屿的数量了
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    res++;
//                    dfs(grid, i, j);  // 这里只是统计封闭岛中单元格的数量，就不需要在dfs了
                }
            }
        }
        return res;
    }


    // 还是dfs框架
    public void dfs(int[][] grid, int i, int j) {
        int m = grid.length;
        int n = grid[0].length;
        int res = 0;
        if (i < 0 || j < 0 || i == m || j == n) {
            return;
        }
        if (grid[i][j] == 0) {
            return;
        }

        grid[i][j] = 0;

        dfs(grid, i - 1, j);
        dfs(grid, i + 1, j);
        dfs(grid, i, j - 1);
        dfs(grid, i, j + 1);
    }
}

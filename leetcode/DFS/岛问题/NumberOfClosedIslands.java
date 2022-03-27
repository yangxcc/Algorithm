package DFS.岛问题;

/**
 * 封闭岛屿的数量
 * 所谓封闭的意思就是一座岛屿的上下左右都必须是水，不能够是边界
 *
 * 这道题的解法就是在NumberOfIslands这道题的基础上，把边界上的岛屿先给“淹了”，然后就能够去统计封闭岛屿的数量了
 * 因为四个边的岛屿“淹掉”，剩下的都是封闭的了，再有岛屿，也没有能够到达边上的了
 */
public class NumberOfClosedIslands {
    public int closedIsland(int[][] grid){
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
                    dfs(grid, i, j);
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

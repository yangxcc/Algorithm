package DFS解决岛屿题目;

/**
 * 岛屿数量
 * 0表示水，1表示岛
 * 最基础的dfs求解岛屿数量
 */
public class NumberOfIslands {

    public int numIsLands(char[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int res = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == '1') {  // 碰见了岛，就调用dfs，把和他相连的全部岛都“淹了”
                    res++;
                    dfs(grid, i, j);
                }
            }
        }
        return res;
    }

    public void dfs(char[][] grid, int i, int j) {
        int m = grid.length;
        int n = grid[0].length;
        if (i < 0 || j < 0 || i == m || j == n) {
            return;
        }
        if (grid[i][j] == '0') {
            return;
        }

        grid[i][j] = '0';  // 把岛“淹”了，表示已经访问过了
        dfs(grid, i - 1, j);
        dfs(grid, i + 1, j);
        dfs(grid, i, j - 1);
        dfs(grid, i, j + 1);
    }
}

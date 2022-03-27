package DFS.岛问题;

/**
 * 统计子岛屿的数量
 *
 * 如果 grid2 的一个岛屿，被 grid1 的一个岛屿完全包含，
 * 也就是说 grid2 中该岛屿的每一个格子都被 grid1 中同一个岛屿完全包含，那么我们称 grid2 中的这个岛屿为 子岛屿 
 *
 */
public class CountSubIslands {
    // grid1和grid2的形状是一样的
    public int countSubIslands(int[][] grid1, int[][] grid2){
        int m = grid1.length;
        int n = grid1[0].length;
        int res = 0;

        // 什么情况下grid2中的岛不是grid1中的岛的子集
        // 当相同的位置下，grid2中是岛，而grid1中是水的情况下，grid2中的岛不是grid1中岛的子集
        // 因此，我们把上面这种不符合条件的岛去掉，再去统计就是答案了
        // 思路和“封闭的岛”一题类似，不同的是，这道题是去掉不符合条件的，后一题是去掉边上的

        /**
         * 去掉grid2中不符合条件的岛
         */
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid1[i][j] == 0 && grid2[i][j] == 1) {
                    // 1中是水，2中是岛，不符合条件，去掉grid2中的这些岛
                    dfs(grid2, i, j);  // 把这些岛淹掉
                }
            }
        }

        // 经过上面的步骤就可以进行统计了
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid2[i][j] == 1){
                    res++;
                    dfs(grid2, i, j);
                }
            }
        }
        return res;
    }


    // 第一步就先把dfs的模板写出来
    public void dfs(int[][] grid, int i, int j) {
        int m = grid.length;
        int n = grid[0].length;
        if (i < 0 || j < 0 || i == m || j == n) {
            return;
        }
        if (grid[i][j] == 0) {
            return;
        }
        dfs(grid, i - 1, j);
        dfs(grid, i + 1, j);
        dfs(grid, i, j - 1);
        dfs(grid, i, j + 1);
    }
}

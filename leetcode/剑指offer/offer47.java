package 剑指offer;

/**
 * 礼物的最大价值
 *
 * 和offer46本质上是同一类题，这道题就能够通过
 * */
public class offer47 {
    // 方法1：暴力递归
    public int maxValue(int[][] grid) {
        if (grid.length == 0 || grid[0].length == 0) {
            return -1;
        }
        return process(grid, 0, 0);
    }

    public int process(int[][] grid, int row, int col) {
        if (row == grid.length) {
            return 0;
        }
        if (col == grid[0].length) {
            return 0;
        }
        if (row == grid.length - 1 && col == grid[0].length - 1) {
            return grid[row][col];
        }
        return Math.max(process(grid, row + 1, col), process(grid, row, col + 1)) + grid[row][col];
    }

    // 方法2：通过暴力递归修改DP
    public int maxValue2(int[][] grid) {
        if (grid.length == 0 || grid[0].length == 0) {
            return -1;
        }
        int[][] dp = new int[grid.length + 1][grid[0].length + 1];
        // 初始化dp数组
        dp[grid.length - 1][grid[0].length - 1] = grid[grid.length - 1][grid[0].length - 1];
        // 其他的地方本来就是0
        for (int i = grid.length - 1; i >= 0; i--) {
            for (int j = grid[0].length - 1; j >= 0; j--) {
                dp[i][j] = grid[i][j] + Math.max(dp[i + 1][j], dp[i][j + 1]);
            }
        }
        return dp[0][0];
    }
}

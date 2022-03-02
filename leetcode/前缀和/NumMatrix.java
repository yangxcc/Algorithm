package 前缀和;

/**
 * 二维数组中使用前缀和
 */
public class NumMatrix {
    private int[][] preSum;
    public NumMatrix(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        if (m == 0 || n == 0) {
            return;
        }
        // preSum[i][j]表示(0,0)到(i,j)的和
        preSum = new int[m + 1][n + 1];
        preSum[0][0] = 0;
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                preSum[i][j] = preSum[i - 1][j] + preSum[i][j - 1] + matrix[i][j] - preSum[i - 1][j - 1];
            }
        }
    }

    // 计算[x1,y1]到[x2,y2]之间的和，包含x1,y1
    public int sumRegion(int x1, int y1, int x2, int y2) {
        return preSum[x2 + 1][y2 + 1] - preSum[x2][y2 + 1] - preSum[x2 + 1][y2] + preSum[x1][y1];
    }
}

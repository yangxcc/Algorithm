package 错题集;

/**
 * 将一个n*n的二维数组顺时针反转90度
 *
 * 输入：matrix = [[1,2,3],[4,5,6],[7,8,9]]
 * 输出：[[7,4,1],[8,5,2],[9,6,3]]
 */
public class RotateImage {
    // 共有两步：先水平（上下）反转，再按照主对角线反转
    public void rotate(int[][] matrix) {
        // 先上下反转
        int n = matrix.length; // 行数，0行和n-1行换，1行和n-2行换   i行和n - i - 1换
        for (int i = 0; i < n / 2; i++) {
            for (int j = 0; j < n; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[n - i - 1][j];
                matrix[n - i - 1][j] = temp;
            }
        }

        // 在按照对角线反转
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < i; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }

    }
}

/**
 * 总结一下：
 *      顺时针90度是先上下翻转，再按照主对角线反转
 *      逆时针90度是先左右翻转，再按照主对角线反转
 */

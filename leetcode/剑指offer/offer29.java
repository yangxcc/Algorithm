package 剑指offer;

/**
 * 顺时针打印矩阵，这种的都是固定四个点，通过边框来限制打印的范围
 * */
public class offer29 {

    public int[] spiralOrder(int[][] matrix) {
        if (matrix.length == 0) {
            return new int[0];
        }
        int up = 0;
        int down = matrix[0].length - 1;
        int left = 0;
        int right = matrix.length - 1;

        int[] res = new int[matrix[0].length * matrix.length];
        int index = 0;

        while (true) {
            // 从左往右打印
            for (int i = left; i <= right; i++) {
                res[index++] = matrix[up][i];
            }
            up++;
            if (up > down) {
                break;
            }

            // 从上往下
            for (int i = up; i <= down; i++) {
                res[index++] = matrix[i][right];
            }
            right--;
            if (left > right) {
                break;
            }

            // 从右往左打印
            for (int i = right; i >= left; i--) {
                res[index++] = matrix[down][i];
            }
            down--;
            if (up > down) {
                break;
            }

            // 从下往上打印
            for (int i = down; i >= up; i--) {
                res[index++] = matrix[i][left];
            }
            left++;
            if (left > right) {
                break;
            }
        }
        return res;
    }
}

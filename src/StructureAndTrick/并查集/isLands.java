package StructureAndTrick.并查集;

/**
 * 一个矩阵中只有0和1两种值，每个位置都只能和自己的上下左右相连，
 * 如果有一片1连在一起，这部分叫做一个岛，求一个矩阵中有多少个岛？
 * */
public class isLands {

    public int countIsLands(int[][] matrix) {
        int row = matrix.length - 1;
        int column = matrix[0].length - 1;
        int res = 0;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                if (matrix[i][j] == 1) {
                    res++;
                    infect(matrix, i, j, row, column);
                }
            }
        }
        return res;
    }

    public void infect(int[][] matrix, int i, int j, int row, int column) {
        if (i < 0 || j < 0 || i > row || j > column || matrix[i][j] != 1) {
            return;
        }
        matrix[i][j] = 2;
        infect(matrix, i -1, j, row, column);
        infect(matrix, i + 1, j, row, column);
        infect(matrix, i, j - 1, row, column);
        infect(matrix, i ,j + 1, row, column);
    }
}

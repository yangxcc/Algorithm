package 回溯;

/**
 * N皇后问题也是经典的回溯问题
 *
 * 给你一个整数 n ，返回 n 皇后问题 不同的解决方案的数量。
 */
public class NQueen {
    int res = 0;

    public int totalNQueens(int n) {
        if (n <= 0) {
            return 0;
        }
        // record用来表示第i个（行）的皇后放在第几列
        // record[0] = 1，表示第1个皇后放在第2列
        int[] record = new int[n];
        backtrack(record, n, 0);
        return res;
    }

    public void backtrack(int[] record, int n, int row) {
        // row表示的是第几行的皇后
        if (row == n) {
            res++;
            return;
        }

        for (int col = 0; col < n; col++) {
            if (!valid(record, row, col)) continue;

            // 合法的话开始做选择
            record[row] = col;

            backtrack(record, n, row + 1);

            // 撤销选择
            record[row] = 0;
        }
    }

    public boolean valid(int[] record, int row, int col) {
        // 皇后之间不能同行，同列，共对角线
        // 同行肯定不可能，因为我们的代码是从上到下一行一行的来的
        for (int i = 0; i < row; i++) {
            // i代表的是row以前的行
            if (record[i] == col || Math.abs(record[i] - col) == row - i) {
                // 斜率相等，说明在同一个对角线上
                return false;
            }
        }
        return true;
    }
}

package forecrecursion;

/**
 * N皇后问题
 * 在N*N的棋盘上要摆N个皇后，要求任何两个皇后不同行，不同列，也不再同一条斜线上
 *
 * 给定一个整数n，返回n皇后的摆法有多少种
 *
 * */
public class example08 {

    public static int queens(int n) {
        if (n <= 0) {
            return 0;
        }
        int[] record = new int[n];   // 用来记录第i个皇后放在了多少列，比如record[0] = 1 就表示第一个皇后放在了第2列
        return process(n, 0, record);
    }

    // i 表示的是第i行，前i-1行已经摆好了，不管了
    public static int process(int n, int i, int[] record) {
        if (i == n) {
            return 1;
        }

        int res = 0;
        // 第i行的皇后，放在第j列会不会和前i-1行的皇后共列，共斜线，共行是一定不可能的了
        for (int j = 0; j < n; j++) {
            if (isVaild(i,j,record)) {
                record[i] = j;
                res += process(n,i+1,record);
            }
        }
        return res;
    }

    // 第i个皇后放在了第j列，和record中的皇后是否冲突
    public static boolean isVaild(int i, int j, int[] record) {
        for (int k = 0; k < i; k++) {
            if (j == record[k] || Math.abs(j - record[k]) == Math.abs(i - k)) {
                return false;
            }
        }
        return true;
    }


    public static void main(String[] args) {
        System.out.println(queens(8));
    }
}

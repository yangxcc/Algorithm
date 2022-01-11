package dp.扔鸡蛋;

import java.util.Arrays;

/**
 * 鸡蛋掉落
 *
 * 给你 k 枚相同的鸡蛋，并可以使用一栋从第 1 层到第 n 层共有 n 层楼的建筑。
 *
 * 已知存在楼层 f ，满足 0 <= f <= n ，任何从 高于 f 的楼层落下的鸡蛋都会碎，从 f 楼层或比它低的楼层落下的鸡蛋都不会破。
 *
 * 每次操作，你可以取一枚没有碎的鸡蛋并把它从任一楼层 x 扔下（满足 1 <= x <= n）。
 * 如果鸡蛋碎了，你就不能再次使用它。如果某枚鸡蛋扔下后没有摔碎，则可以在之后的操作中 重复使用这枚鸡蛋。
 *
 * 请你计算并返回要确定 f 确切的值 的 最小操作次数 是多少？
 *
 */
public class SuperEggDrop {
    // k表示的是鸡蛋的个数，n表示的是楼层的层数
    public int eggDrop(int k, int n) {
        memo = new int[k + 1][n + 1];
        for (int[] row : memo) {
            Arrays.fill(row, -1);
        }
        return dp(k, n);
    }

    int[][] memo;
    // 使用k个鸡蛋检查n层楼
    public int dp(int k, int n) {
        // base case
        // 如果只有一个鸡蛋，那么只能从最底层开始往上扔，最差的结果是到第n层这个鸡蛋才碎
        if (k == 1) {
            return n;
        }
        // 如果楼层层数为0，那么返回0即可
        if (n == 0) {
            return 0;
        }

        if (memo[k][n] != -1) {
            return memo[k][n];
        }

        // 扔鸡蛋，一共有两个选择，一个是鸡蛋破了，不能继续使用，另一个是鸡蛋没破，可以继续使用
        int res = Integer.MAX_VALUE;
        for (int i = 1; i <= n; i++) {
            // 如果鸡蛋在第i层被摔破了，那么只需要去判断剩下1 ~ i - 1层
            // 如果鸡蛋在第i层没被摔破，那么需要去判断剩下i + 1 ~ n 层
            res = Math.min(res, Math.max(dp(k - 1, i - 1), dp(k, n - i)) + 1);
        }
        memo[k][n] = res;
        return res;
    }

    /**
     * 即使是使用备忘录的方法，在leetcode中仍然是超出时间复杂度的
     * 可以试着把楼层的遍历换成二分
     */

    public int dp2(int k, int n) {
        if (k == 1) {
            return n;
        }
        if (n == 0) {
            return 0;
        }
        if (memo[k][n] == -1) {
            return memo[k][n];
        }

        int left = 1;
        int right = n;
        int res = Integer.MAX_VALUE;
        while (left <= right) {
            int mid = (left + right) / 2;
            int broken = dp2(k - 1, mid - 1);
            int not_broken = dp2(k, n - mid);

            if (broken > not_broken) {
                res = Math.min(res, broken + 1);
                right = mid - 1;
            } else {
                res = Math.min(res, not_broken + 1);
                left = mid + 1;
            }
        }
        memo[k][n] = res;
        return res;
    }
}

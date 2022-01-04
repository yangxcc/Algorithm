package dp.博弈问题;

import org.jetbrains.annotations.NotNull;

/**
 * Alice 和 Bob 用几堆石子在做游戏。一共有偶数堆石子，排成一行；每堆都有 正 整数颗石子，数目为 piles[i] 。
 * <p>
 * 游戏以谁手中的石子最多来决出胜负。石子的 总数 是 奇数 ，所以没有平局。
 * <p>
 * Alice 和 Bob 轮流进行，Alice 先开始 。 每回合，玩家从行的 开始 或 结束 处取走整堆石头。
 * 这种情况一直持续到没有更多的石子堆为止，此时手中 石子最多 的玩家 获胜 。
 * <p>
 * 假设 Alice 和 Bob 都发挥出最佳水平，当 Alice 赢得比赛时返回 true ，当 Bob 赢得比赛时返回 false 。
 * <p>
 * 输入：piles = [5,3,4,5]
 * 输出：true
 * 解释：
 * Alice 先开始，只能拿前 5 颗或后 5 颗石子 。
 * 假设他取了前 5 颗，这一行就变成了 [3,4,5] 。
 * 如果 Bob 拿走前 3 颗，那么剩下的是 [4,5]，Alice 拿走后 5 颗赢得 10 分。
 * 如果 Bob 拿走后 5 颗，那么剩下的是 [3,4]，Alice 拿走后 4 颗赢得 9 分。
 * 这表明，取前 5 颗石子对 Alice 来说是一个胜利的举动，所以返回 true 。
 */
public class StoneGame {
    public boolean game(int[] piles) {
        int n = piles.length;
//        return first(piles, 0, n - 1) > second(piles, 0, n - 1);
        // dp[i][j][0]表示从[i,j]这个闭区间上，先手的最大利润
        // dp[i][j][1]表示从[i,j]这个闭区间上，后手的最大利润
        int[][][] dp = new int[n][n][2];
        // dp初始化，base case
        for (int k = 0; k < n; k++) {
            dp[k][k][0] = piles[k];   // 只有一个元素选择先手，只能把这个元素选走了
            dp[k][k][1] = 0;          // 只有一个元素后手，肯定是一个也没了
        }

        // 我们从返回的数值看出来了，应该是从后往前遍历，我们其实就可以把这个三维dp想成一个二维的，时刻想着那个图
        for (int i = n - 2; i >= 0; i--) {
            for (int j = i + 1; j < n; j++) {
                // left和right都是针对先手的人来说的
                int left = piles[i] + dp[i + 1][j][1];  // 选择最左边的那个元素,那么在[i+1,j]上就是后手了
                int right = piles[j] + dp[i][j - 1][1]; // 选择最右边的那个元素，那么在[i,j-1]上就是后手了
                // 一定要注意，这里是不能够直接向下面这样写的
                // dp[i][j][0] = Math.max(piles[i] + dp[i+1][j][1], piles[j] + dp[i][j-1][1]);
                // dp[i][j][1] = Math.max(dp[i+1][j][0], dp[i][j-1][0]);
                // 因为先手选择的顺序，会影响后续的顺序，所以他们先手和后手必须是一一对应的
                // 上面有可能出现这样的情况dp[i][j][0] = piles[i] + dp[i+1][j][1]，
                //                       dp[i][j][1] = dp[i][j-1][0];
                // 先手明明选了第i个，后手还有可能选到他，这显然是不对的
                if (left > right) {
                    dp[i][j][0] = left;  // 先手肯定想要自己最大
                    dp[i][j][1] = dp[i + 1][j][0];
                } else {
                    dp[i][j][0] = right;
                    dp[i][j][1] = dp[i][j - 1][0];
                }
            }
        }

        return dp[0][n - 1][0] - dp[0][n - 1][1] > 0;
        // 从0~n-1上先手的最大利润减去后手的最大利润
        // 从返回的区间中我们就可以判断了，他返回的是右上角的数组元素，所以我们
        // 选择从上到下进行遍历，这样来填满dp数组
    }

    // 其实对于这个题，我最初的想法就是进行暴力递归，如下
    // 但是暴力递归在leetcode中时间复杂度会降低，所以还是需要使用动态规划
    // 还有一个问题就是这个直接加上备忘录是不行的
    public int first(int[] piles, int start, int end) {
        if (start > end) {
            return 0;
        }
        return Math.max(piles[start] + second(piles, start + 1, end),
                piles[end] + second(piles, start, end - 1));
    }

    public int second(int[] piles, int start, int end) {
        if (start > end) {
            return 0;
        }
        // 因为是后手，所以先手的人肯定会让自己最多，从而让后手的人最少
        return Math.min(first(piles, start + 1, end),
                first(piles, start, end - 1));
    }


    // 其实还可以将三维数组压缩成一个二维数组
    // 因为第三维就是一个int[2]，表示状态，所以我们可以用一个对象来代替它
    // 其实就是在二维数组上，放上一个元组
    public boolean game2(@NotNull int[] piles) {
        int n = piles.length;
        // dp[i][j]表示[i,j]上的先手以及后手最大值
        Pair[][] dp = new Pair[n][n];   // 创建一个元组类型的数组
        // 因为每个格子里存放的是Pair对象，所以如果没有初始化，那么存放的就是null
        // 所以需要初始化一下，不然后面dp[i][j].fir就会出现空指针异常了
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                dp[i][j] = new Pair(0, 0);
            }
        }
        // base case
        for (int k = 0; k < n; k++) {
            dp[k][k] = new Pair(piles[k], 0);
        }

        for (int i = n - 2; i >= 0; i--) {
            for (int j = i + 1; j < n; j++) {
                int left = piles[i] + dp[i+1][j].sec;
                int right = piles[j] + dp[i][j-1].sec;
                if (left > right) {
                    dp[i][j].fir = left;
                    dp[i][j].sec = dp[i+1][j].fir;
                } else {
                    dp[i][j].fir = right;
                    dp[i][j].sec = dp[i][j-1].fir;
                }
            }
        }
        return dp[0][n - 1].fir - dp[0][n - 1].sec > 0;
    }
}

class Pair {
    int fir;   // 先手的最大值
    int sec;   // 后手的最大值

    public Pair(int f, int s) {
        this.fir = f;
        this.sec = s;
    }
}

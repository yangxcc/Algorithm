package 剑指offer;

/**
 * 机器人的运动范围
 *
 * 地上有一个m行n列的方格，从坐标 [0,0] 到坐标 [m-1,n-1] 。
 * 一个机器人从坐标 [0, 0] 的格子开始移动，它每次可以向左、右、上、下移动一格（不能移动到方格外），
 * 也不能进入行坐标和列坐标的数位之和大于k的格子。例如，当k为18时，机器人能够进入方格 [35, 37] ，
 * 因为3+5+3+7=18。但它不能进入方格 [35, 38]，因为3+5+3+8=19。请问该机器人能够到达多少个格子？
 *
 * 链接：https://leetcode-cn.com/problems/ji-qi-ren-de-yun-dong-fan-wei-lcof
 * 。*/
public class offer13 {
    public int movingCount(int m, int n, int k) {
        boolean[][] visited = new boolean[m][n];
        return process(m, n, k, 0, 0, visited);
    }

    public int process(int m, int n, int k, int i, int j, boolean[][] visited) {
        // visited[i][j] 已经访问过了就不在访问了
        // sum(i, j) > k题目要求
        // 同样是别忘了i<0,j<0
        if (i < 0 || j < 0 || i >= m || j >= n || visited[i][j] || sum(i, j) > k) {
            return 0;
        }
        visited[i][j] = true;
        return process(m, n, k, i - 1, j, visited) +
                process(m, n, k, i + 1, j, visited) +
                process(m, n, k, i, j - 1, visited) +
                process(m, n, k, i, j + 1, visited) +
                1;
        // 一定不要忘了 +1
        /**
         * 这里和offer12，机器人运动不同的是，没有把visited[i][j]在标记回未访问
         * 因为如果在标记回未访问会导致重复计算
         * */
    }


    public int sum(int x, int y) {
        int res = 0;
        while (x != 0 || y != 0) {
            res += x % 10 + y % 10;
            x /= 10;
            y /= 10;
        }
        return res;
    }
}

// 2021年11月21
// 这道题能够换成dp吗
// 目前我的水平感觉这道题改不成dp，因为目前点的状态判断是需要sum(i,j)<=k，
// 跟它的上下左右没有关联关系，也就是说它的上下左右影响不了他？
// 不对，上面的说法不完全对，因为这是一个数组，一个数组的下标是有规律的，是递增的
// 所以他虽然改不成dp，但是能够进行压缩,比方说sum(i,j) > k了，他后面的(i + 1, j)和(i, j + 1)都不用判断了
// https://leetcode-cn.com/problems/ji-qi-ren-de-yun-dong-fan-wei-lcof/solution/di-tui-zhuang-tai-ya-suo-shuang-bai-by-d-hvgt/
// 上面链接的这个人做了压缩，但是我现在还看不太懂，先留着？！


/**
 * 看评论中的一种错误解法
 * class Solution {
 *     public int movingCount(int m, int n, int k) {
 *         int ans = 0;
 *         for (int i = 0; i < m; i++) {
 *             for (int j = 0; j < n; j++) {
 *                 if (k - (i / 10 + i % 10) - (j / 10 + j % 10) >= 0)
 *                     ans++;
 *             }
 *         }
 *         return ans;
 *     }
 * }
 * 这样直接遍历是不行的，因为有的格子相当于障碍物，过不去，不可能遍历全部的格子！！
 * */

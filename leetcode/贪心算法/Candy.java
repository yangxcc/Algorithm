package 贪心算法;

/**
 * 分发糖果
 * n 个孩子站成一排。给你一个整数数组 ratings 表示每个孩子的评分。
 *
 * 你需要按照以下要求，给这些孩子分发糖果：
 *
 * 每个孩子至少分配到 1 个糖果。
 * 相邻两个孩子评分更高的孩子会获得更多的糖果。
 * 请你给每个孩子分发糖果，计算并返回需要准备的 最少糖果数目 。
 *
 * 其实这道题和接雨水ContainerWithMostWater2很像，这道题要简单一些，
 * 因为这道题判断的是相邻元素之间的大小，不需要用到单调栈
 */
public class Candy {
    public int candy(int[] ratings) {
        int n = ratings.length;
        int[] res = new int[n];
        int ans = 0;
        // 左一遍，右一遍，走两遍
        // 先看看左边的是否比当前小
        for (int i = 1; i < n; i++) {
            if (ratings[i] > ratings[i - 1]) {
                res[i] = res[i - 1] + 1;
            }
        }

        // 再看看有百年的是否比当前小
        for (int i = n - 2; i >= 0; i++) {
            if (ratings[i] > ratings[i + 1]) {
                // 这里这个要写上max，是为了避免再次比较的时候发生变化
                // 比如测试用例 [1,3,4,5,2]
                res[i] = Math.max(res[i + 1] + 1, res[i]);
            }
            ans += ratings[i];
        }

        return ans + ratings[n - 1];
    }
}

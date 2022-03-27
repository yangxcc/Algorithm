package DFS.岛问题;

import java.util.HashSet;

/**
 * 给定一个非空01二维数组表示的网格，一个岛屿由四连通(上、下、左、右四个方向)的1组成，你可以认为网格的四周被海水包围。
 *
 * 请你计算这个网格中共有多少个形状不同的岛屿。两个岛屿被认为是相同的，
 * 当且仅当一个岛屿可以通过平移变换(不可以旋转、翻转)和另一个岛屿重合。
 *
 * 这道题是会员题目。。。
 * 看这图会更好理解什么是相同的岛
 */
public class NumberOfDistinctIslands {
    /**
     * 我们应该怎么判断两个岛是否一致呢？
     * 也就是怎么知道岛的形状呢？受到二叉树的序列化和反序列化思想的启发，我们也可以在遍历过程中
     * 把岛的形状给输出出来，如果序列化的结果是相同的，那么表示这两个岛是相同的
     *
     * @param grid 岛&水 地图
     * @return 不同岛的个数
     */
    public int numDistinctIslands(int[][] grid) {
        // 因为要求是不同的岛，所以需要用到hashset
        HashSet<String> ans = new HashSet<>();
        int m = grid.length;
        int n = grid[0].length;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    StringBuilder sb = new StringBuilder();
                    // 初始化direction时，任何值都行
                    dfs(grid, i, j, sb, 555);
                    ans.add(sb.toString());
                }
            }
        }

        return ans.size();
    }

    // 通过遍历的顺序就能够序列化出岛的形状
    // 和二叉树的序列化有不同，二叉树的节点都有值，而grid中除了1就是0，我们只能够通过遍历的方向来序列化
    // 所以我们需要添加一个变量来表明方向
    public void dfs(int[][] grid, int i, int j, StringBuilder sb, int direction) {
        int m = grid.length;
        int n = grid[0].length;
        if (i < 0 || j < 0 || i == m || j == n) {
            return;
        }
        if (grid[i][j] == 0) {
            return;
        }

        // 前序遍历，进入某个节点
        grid[i][j] = 0;
        sb.append(direction).append(",");

        dfs(grid, i - 1, j, sb, 1);  // 上
        dfs(grid, i + 1, j, sb, 2);  // 下
        dfs(grid, i, j - 1, sb, 3);  // 左
        dfs(grid, i, j + 1, sb, 4);  // 右

        // 后序遍历，离开某个节点
        sb.append(-direction).append(",");
    }
}

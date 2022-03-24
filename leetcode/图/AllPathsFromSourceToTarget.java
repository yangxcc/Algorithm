package 图;

import java.util.ArrayList;
import java.util.List;

/**
 * 给你一个有 n 个节点的 有向无环图（DAG），请你找出所有从节点 0 到节点 n-1 的路径并输出（不要求按特定顺序）
 *
 *  graph[i] 是一个从节点 i 可以访问的所有节点的列表（即从节点 i 到节点 graph[i][j]存在一条有向边）。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/all-paths-from-source-to-target
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class AllPathsFromSourceToTarget {

    List<List<Integer>> res = new ArrayList<>();
    public List<List<Integer>> allPathsSourceTarget(int[][] graph) {
        List<Integer> path = new ArrayList<>();
        dfs(graph, 0, path);
        return res;
    }

    // 因为题目中说了，这是一个有向无环图，所以不需要visited数组来保证不重复遍历
    public void dfs(int[][] graph, int index, List<Integer> path) {
        path.add(index);

        if (index == graph.length - 1) {
            res.add(new ArrayList<>(path));
            path.remove(path.size() - 1);
            return;
        }

        for (int neighbor : graph[index]) {
            dfs(graph, neighbor, path);
        }

        path.remove(path.size() - 1);
    }
}

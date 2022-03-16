package 图.二分图;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 判断是否为二分图，和双色问题（给节点染色）是一个问题
 * 二分图其实就是将节点分成两个部分（子集），所有边分别都依附于这两个子集中的一个节点
 * 染色问题就是将图中的所有节点染色，让每一条边的两个节点的颜色都不相同
 *
 * 所以判断是否为二分图的方法是：一边遍历，一边染色
 */
public class IsGraphBipartite {
    boolean[] visited;
    boolean[] color;
    boolean ok;
    // graph就是邻接矩阵
    public boolean isBipartite(int[][] graph){
        int n = graph.length;
        visited = new boolean[n];
        color = new boolean[n];
        ok = true;
        // 因为题目中说了此图可能不是连通图，所以需要一个节点一个节点的来判断
        for (int i = 0; i < n; i++) {
//            dfs(graph, i);
            bfs(graph, 0);
        }
        return ok;
    }


    public void dfs(int[][] graph, int v) {
        if (visited[v] || !ok) {
            // 如果已经访问过这个节点或者以及判断出不是二分图了
            return;
        }

        visited[v] = true;
        for (int neighbor : graph[v]) {
            if (visited[neighbor]) {
                if (color[neighbor] == color[v]) {
                    ok = false;
                    return;
                } else {
                    color[neighbor] = !color[v];
                    dfs(graph, neighbor);
                }
            }
        }
    }


    public void bfs(int[][] graph, int v) {
        Queue<Integer> q = new LinkedList<>();
        q.add(v);
        visited[v] = true;
        while (!q.isEmpty()) {
            int vertex = q.poll();
            for (int neighbor : graph[vertex]) {
                if (!visited[neighbor]) {
                    visited[neighbor] = true;
                    color[neighbor] = !color[vertex];
                    q.add(neighbor);
                } else {
                    if (color[neighbor] == color[vertex]) {
                        ok = false;
                        return;
                    }
                }
            }
        }
    }

}

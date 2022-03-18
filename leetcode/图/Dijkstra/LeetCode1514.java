package 图.Dijkstra;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;

/**
 * 经典dijkstra是为了解决有向图中最短路径的问题（而无向图）
 * 而这道题是让求最大概率的路径，依我的看法就是使用BFS来解决这个问题
 * 写法同dijkstra，labuladong有解释，但是没太看懂
 */
public class LeetCode1514 {
    public double maxProbability(int n, int[][] edges, double[] succProb, int start, int end) {
        List<double[]>[] graph = buildGraph(edges, succProb, n);
        // 起始点到i的最大概率
        double[] proTo = new double[n];
        Arrays.fill(proTo, -1);
        proTo[start] = 1;

        // 定义一个优先级队列，按照概率降序排列
        PriorityQueue<Vertex> q = new PriorityQueue<>(
                (a, b) -> {
                    return Double.compare(b.proFromStart, a.proFromStart);
                }
        );

        q.offer(new Vertex(start, 1));
        while (!q.isEmpty()) {
            Vertex v = q.poll();
            int curVertexID = v.id;
            double prob = v.proFromStart;
            if (curVertexID == end) {
                return prob;
            }
            if (proTo[curVertexID] > prob) {
                continue;
            }
            for (double[] neighbors : graph[curVertexID]) {
                int to = (int) neighbors[0];
                double probability = neighbors[1];
                if (proTo[to] < proTo[curVertexID] * probability) {
                    proTo[to] = proTo[curVertexID] * probability;
                    q.offer(new Vertex(to, proTo[to]));
                }
            }
        }
        return 0.0;
    }

    public List<double[]>[] buildGraph(int[][] edges, double[] succProb, int n) {
        List<double[]>[] graph = new LinkedList[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new LinkedList<>();
        }

        for (int i = 0; i < edges.length; i++) {
            int from = edges[i][0];
            int to = edges[i][1];
            double pro = succProb[i];
            // 无向图
            graph[from].add(new double[]{(double) to, pro});
            graph[to].add(new double[]{(double) from, pro});
        }
        return graph;
    }
}

class Vertex {
    int id; // 图节点的id
    double proFromStart;

    public Vertex(int id, double proFromStart) {
        this.id = id;
        this.proFromStart = proFromStart;
    }
}


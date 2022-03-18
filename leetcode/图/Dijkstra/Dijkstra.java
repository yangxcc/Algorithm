package 图.Dijkstra;

import java.util.*;

/**
 * BFS的思路
 * 在给定图和起始结点的基础上，能够求出所有其他结点到起始结点的最短路径。
 * leetcode 743:网络延迟时间
 * 有 n 个网络节点，标记为 1 到 n。
 *
 * 给你一个列表 times，表示信号经过 有向 边的传递时间。 times[i] = (ui, vi, wi)，其中 ui 是源节点，vi 是目标节点，
 * wi 是一个信号从源节点传递到目标节点的时间。
 *
 * 现在，从某个节点 K 发出一个信号。需要多久才能使所有节点都收到信号？如果不能使所有节点收到信号，返回 -1 。
 *
 */
// Dijkstra的应用前提是：加权有向图、没有负权重边、求最短路径
public class Dijkstra {
    public int networkDelayTime(int[][] times, int n, int k) {
        HashMap<Integer, List<int[]>> graph = buildGraph(times, n);
        int[] distTo = new int[n + 1];
        // 注意后面处理的时候越过distTo[0]
        Arrays.fill(distTo, Integer.MAX_VALUE);
        distTo[k] = 0;
        PriorityQueue<State> q = new PriorityQueue<>(new Comparator<State>(){
            @Override
            public int compare(State o1, State o2) {
                return o1.distFromStart - o2.distFromStart;
            }
        });
        q.add(new State(k, 0));

        while(!q.isEmpty()) {
            State curVertex = q.poll();
            int curVertexID = curVertex.id;
            int distToCurVertex = curVertex.distFromStart;
            // 已经有较短的路径了
            if (distToCurVertex > distTo[curVertexID]) {
                continue;
            }
            for (int[] struct : graph.get(curVertexID)) {
                int neighborID = struct[0];
                int dist = struct[1];
                int distToNei = distToCurVertex + dist;  // start到nei的距离
                if (distToNei < distTo[neighborID]) {
                    distTo[neighborID] = distToNei;
                    q.add(new State(neighborID, distToNei));
                }
            }
        }

        // 上面的while循环之后，distTo中就是k到其他点的最短距离，找出其中最大的
        int res = Integer.MIN_VALUE;
        // 要从1开始
        for (int i = 1; i <= n; i++) {
            if (distTo[i] == Integer.MAX_VALUE) {
                return -1;
            }
            res = Math.max(res, distTo[i]);
        }
        return res;
    }

    // 构建图
    public HashMap<Integer, List<int[]>> buildGraph(int[][] times, int n) {
        HashMap<Integer, List<int[]>> graph = new HashMap<>();
        // int n = times.length;
        for (int i = 0; i <= n; i++) {
            graph.put(i, new ArrayList<>());
        }

        for (int[] edge : times) {
            int from = edge[0];
            int to = edge[1];
            int weight = edge[2];
            List<int[]> temp = graph.get(from);
            temp.add(new int[]{to, weight});
            graph.put(from, temp);
        }
        return graph;
    }
}

class State {
    int id;                   // 哪个顶点
    int distFromStart;        // 该顶点到起始点的距离

    public State(int id, int distFromStart) {
        this.id = id;
        this.distFromStart = distFromStart;
    }
}

package 图.最小生成树;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 树和图的根本区别是：树中不会包含环，图中可能会有环，即树是无环连通图
 * 生成树就是包含树中所有节点的子树，即包含所有节点的无环连通子图
 * 在所有的生成树中，权值最小的被称作最小生成树
 */
public class Kruskal {
    /**
     * 想象一下你是个城市基建规划者，地图上有 N 座城市，它们按以 1 到 N 的次序编号。
     *
     * 给你一些可连接的选项 conections，其中每个选项 conections[i] = [city1, city2, cost]
     * 表示将城市 city1 和城市 city2 连接所要的成本。
     * （连接是双向的，也就是说城市 city1 和城市 city2 相连也同样意味着城市 city2 和城市 city1 相连）。
     *
     * 返回使得每对城市间都存在将它们连接在一起的连通路径（可能长度为 1 的）最小成本。
     * 该最小成本应该是所用全部连接代价的综合。如果根据已知条件无法完成该项任务，则请你返回 -1。
     *
     */
    public int process(int n, int[][] connections) {
        // 根据边的权重，进行一下排序
//        PriorityQueue<int[]> p = new PriorityQueue<int[]>(new Comparator<int[]>() {
//            @Override
//            public int compare(int[] o1, int[] o2) {
//                return o1[2] - o2[2];
//            }
//        });

//        for (int[] edge : connections) {
//            p.offer(edge);
//        }

        Arrays.sort(connections, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[2] - o2[2];
            }
        });

        // 因为城市的编号是1-N，所以这里是n+1
        UnionFind uf = new UnionFind(n + 1);
        int mst = 0;

        for (int[] edge : connections) {
            int from = edge[0];
            int to = edge[1];
            int weight = edge[2];

            if (uf.connect(from, to)) {
                // 会形成环，不能选择这条边
                continue;
            }
            mst += weight;
            uf.union(from, to);
        }

        // 因为编号是1-N，所以并查集大小为n+1，0号节点没用到，理论上应该是count==1表示全部都连通了，但是这里因为0号没用到，所以是2
        return uf.count() == 2 ? mst : -1;
    }
}

class UnionFind {
    private int[] parent;
    private int[] size;
    private int count;

    public UnionFind(int n) {
        this.parent = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }
        this.size = new int[n];
        Arrays.fill(size, 1);
        this.count = n;
    }

    public void union(int p, int q) {
        int rootP = find(p);
        int rootQ = find(q);
        if (rootP == rootQ) {
            return;
        }
        if (size[rootP] < size[rootQ]) {
            // 把以rootP为根节点的树挂到以rootQ为根节点的树上
            parent[rootP] = rootQ;
            size[rootQ] += size[rootP];
        } else {
            parent[rootQ] = rootP;
            size[rootP] += size[rootQ];
        }
        count--;
    }

    public boolean connect(int p, int q) {
        int rootP = find(p);
        int rootQ = find(q);
        return rootP == rootQ;
    }

    public int find(int x) {
        while (x != parent[x]) {
            // 路径压缩
            parent[x] = parent[parent[x]];
            x = parent[x];
        }
        return x;
    }

    public int count() {
        return this.count;
    }
}

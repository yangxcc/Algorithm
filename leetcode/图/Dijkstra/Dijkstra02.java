package 图.Dijkstra;

import java.util.*;

/**
 * 你准备参加一场远足活动。给你一个二维 rows x columns 的地图 heights ，
 * 其中 heights[row][col] 表示格子 (row, col) 的高度。一开始你在最左上角的格子 (0, 0) ，
 * 且你希望去最右下角的格子 (rows-1, columns-1) （注意下标从 0 开始编号）。
 * 你每次可以往 上，下，左，右 四个方向之一移动，你想要找到耗费 体力 最小的一条路径。
 * <p>
 * 一条路径耗费的 体力值 是路径上相邻格子之间 高度差绝对值 的 最大值 决定的。
 * <p>
 * 请你返回从左上角走到右下角的最小 体力消耗值 。
 */
public class Dijkstra02 {
    /**
     * 以左上角为起点，右下角为终点，找到两点之间的最短路径，相邻坐标对应值的差就是边的权值
     * 只不过题目中要评价一条路径长短的标准不是路径权重之和，而是一条路径中最大的权值
     *
     * @param heights 二维数组，可以看每个元素都看作图中的一个顶点
     * @return [0, 0]到[m, n]的最短路径
     * <p>
     * 实现dijkstra
     */
    public int minimumEffortPath(int[][] heights) {
        int m = heights.length;
        int n = heights[0].length;
        // (0,0)到(i,j)的距离
        int[][] distTo = new int[m][n];
        for (int[] dist : distTo) {
            Arrays.fill(dist, Integer.MAX_VALUE);
        }
        distTo[0][0] = 0;
        PriorityQueue<Vertex> q = new PriorityQueue<>(new Comparator<Vertex>() {
            @Override
            public int compare(Vertex o1, Vertex o2) {
                return o1.distFromStart - o2.distFromStart;
            }
        });

        q.offer(new Vertex(0, 0, distTo[0][0]));

        while (!q.isEmpty()) {
            Vertex v = q.poll();
            int x = v.x;
            int y = v.y;
            int dist = v.distFromStart;
            if (dist > distTo[x][y]) {
                continue;
            }
            // 这种写法也是dijkstra返回起点到某一个点的写法
            if (x == m - 1 && y == n - 1) {
                return dist;
            }

            for (int[] nei : neighbors(heights, x, y)) {
                int i = nei[0];
                int j = nei[1];
                // 注意这里不是路径的权重之和了，而是一条路径中的最大值
//                int distToNei = distTo[x][y] + Math.abs(heights[x][y] - heights[i][j]);
                int effortToNextVertex = Math.max(Math.abs(heights[x][y] - heights[i][j]), distTo[x][y]);
                if (distTo[i][j] > effortToNextVertex) {
                    distTo[i][j] = effortToNextVertex;
                    q.add(new Vertex(i, j, distTo[i][j]));
                }
            }
        }
        return -1;
    }

    // 二维数组中经常这么来表示上下左右四个方向，这是一个小技巧
    static int[][] dirs = new int[][]{{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

    // 坐标(i,j)的邻居，上下左右四个
    public static List<int[]> neighbors(int[][] matrix, int i, int j) {
        List<int[]> neis = new ArrayList<>();
        int m = matrix.length;
        int n = matrix[0].length;

        for (int[] dir : dirs) {
            int x = i + dir[0];
            int y = j + dir[1];
            if (x >= m || y >= n || x < 0 || y < 0) {
                continue;
            }
            neis.add(new int[]{x, y});
        }
        return neis;
    }




    /**
     * 换一种思路，我们将这 m*n 个节点放入并查集中，实时维护它们的连通性。
     * <p>
     * 由于我们需要找到从左上角到右下角的最短路径，因此我们可以将图中的所有边按照权值从小到大进行排序，
     * 并依次加入并查集中。当我们加入一条权值为 x 的边之后，如果左上角和右下角从非连通状态变为连通状态，那么 x 即为答案。
     *
     * @param heights 二维数组，可以看每个元素都看作图中的一个顶点
     * @return [0, 0]到[m, n]的最短路径
     */
    public static List<int[]> buildGraph(int[][] heights) {
        List<int[]> graph = new ArrayList<>();
        int m = heights.length;
        int n = heights[0].length;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int idx = i * n + j;   // 二维坐标换成一维索引
                if (i > 0) {
                    graph.add(new int[]{idx - n, idx, Math.abs(heights[i][j] - heights[i - 1][j])});
                }
                if (j > 0) {
                    graph.add(new int[]{idx - 1, idx, Math.abs(heights[i][j] - heights[i][j - 1])});
                }
            }
        }
        return graph;
    }

    public static int minimumEffortPath2(int[][] heights) {
        int m = heights.length;
        int n = heights[0].length;
        List<int[]> graph = buildGraph(heights);
        Collections.sort(graph, (int[] o1, int[] o2) -> o1[2] - o2[2]);
        UnionFind uf = new UnionFind(m * n);
        int ans = 0;
        for (int[] edge : graph) {
            int from = edge[0];
            int to = edge[1];
            int weight = edge[2];
            uf.union(from, to);
            if (uf.connect(0, m * n - 1)) {
                ans = weight;
                break;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        int[][] heights = new int[][]{{1,2,2},{3,8,2},{5,3,5}};
        minimumEffortPath2(heights);
    }

    // 因为是二维坐标了，所以State类也要做出相应的变化
    class Vertex {
        int x, y;
        int distFromStart;

        public Vertex(int x, int y, int distFromStart) {
            this.x = x;
            this.y = y;
            this.distFromStart = distFromStart;
        }
    }

    static class UnionFind {
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
}

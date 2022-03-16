package 图.UnionFind;

/**
 * 给你输入编号从0到n - 1的n个结点，和一个无向边列表edges（每条边用节点二元组表示），
 * 请你判断输入的这些边组成的结构是否是一棵树。
 */
public class JudgingTree {
    // 树其实就是无环连通子图，
    // 如果两个连通的节点之间存在边，那么就会产生环
    // 判断来连通性就是并查集的拿手好戏
    public boolean validTree(int n, int[][] edges) {
        UnionFind uf = new UnionFind(n);
        for (int[] edge : edges) {
            int from = edge[0];
            int to = edge[1];
            // 如果他们两个处于同一个连通分量中，那么再加上这条边，就会产生环
            if (uf.connected(from, to)) {
                return false;
            }
            uf.union(from, to);
        }
        return uf.count() == 1;   // 如果能够形成树的话，最后的连通分量肯定是只剩下一个
    }
}

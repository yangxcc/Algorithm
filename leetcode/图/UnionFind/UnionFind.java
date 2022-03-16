package 图.UnionFind;

public class UnionFind {
    private int[] parents;     // 用数组来模拟森林
    private int count;         // 记录图中联通节点的个数
    private int[] size;        // 记录每颗树中节点的个数

    /**
     * @param n 节点个数
     */
    public UnionFind(int n) {
        parents = new int[n];
        size = new int[n];
        for (int i = 0; i < n; i++) {
            parents[i] = i;   // 每个节点都指向自己，也就是自环
            size[i] = 1;      // 将每棵树的节点个数设置成1
        }
        this.count = n;       // 有多少个节点，最开始就有多少个环，也就是有多少连通节点
    }

    /**
     * 将节点p和节点q连接
     * @param p
     * @param q
     * 方法是找到p和q最顶层的父节点，让其中任意的一个父节点作为另一个的子节点
     */
    public void union(int p, int q){
        int rootP = find(p);
        int rootQ = find(q);
        if (rootP == rootQ) {
            return;
        }
        // 通过树的节点个数来判断一下，有点像SB树
        // 把小树接到大树下面
        if (size[rootP] < size[rootQ]) {
            parents[rootP] = rootQ;
            size[rootQ] += size[rootP];
        } else {
            parents[rootQ] = rootP;
            size[rootP] += size[rootQ];
        }
        count--;      // 两个分量合二为一 （自环一直存在）
    }

    /**
     * @param p
     * @param q
     * @return 判断节点p和节点q是否连通
     */
    public boolean connected(int p, int q) {
        int rootP = find(p);
        int rootQ = find(q);
        return rootP == rootQ;
    }

    /**
     * @return 返回图中共有多少个来连通分量
     */
    public int count() {
        return this.count;
    }

    /**
     * @param x
     * @return  返回x的最顶层父节点
     */
    public int find(int x) {
        while (parents[x] != x) {
            // 通过这一行代码就实现了路径压缩
            parents[x] = parents[parents[x]];
            x = parents[x];
        }
        return x;
    }

    /**
     * 可以看到union和connect的时间复杂度都取决于find函数，而find函数的时间复杂度很明显就是树的高度，最坏的情况下树可能会退化成链表，
     * 所以这时的时间复杂度是O（logn），当数据量很大时，这个时间复杂度是不能接受的，因为我们需要把树变成平衡二叉树
     * 每个进行union的时候，总是把节点数小的树连到节点数大的树上面，所以这时候需要一个size数组，来保存树中节点的个数
     *
     * 有了这个size数组之后，我们的时间复杂度就到了O(logn)
     *
     * 我们还可以在find的过程中进行路径压缩，让树变得更加扁平，从而使得时间复杂度变成O(1)
     *
     */
}


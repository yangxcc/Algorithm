package 图.UnionFind;

/**
 * 并查集结构的使用，主要是用来解决动态连通性的问题，其实岛问题也可以使用并查集
 *
 * 给你⼀个数组 equations，装着若干字符串表示的算式。每个算式 equations[i] ⻓度都是 4，⽽且只有
 * 这两种情况：a==b 或者 a!=b，其中 a,b 可以是任意⼩写字母。你写⼀个算法，如果 equations 中所有算
 * 式都不会互相冲突，返回 true，否则返回 false。
 * ⽐如说，输⼊ ["a==b","b!=c","c==a"]，算法返回 false，因为这三个算式不可能同时正确。
 * 再⽐如，输⼊ ["c==c","b==d","x!=z"]，算法返回 true，因为这三个算式并不会造成逻辑冲突。
 */
public class UsingUF {
    public boolean judge(String[] equations) {
        // 最多有26个字母
        UnionFind uf = new UnionFind(26);

        for (String ele : equations) {
            // 如果等式两边相等的话，那么就union
            if (ele.charAt(1) == '=') {
                int x = ele.charAt(0);
                int y = ele.charAt(3);
                uf.union(x - 'a', y - 'a');
            }
        }

        for (String ele : equations) {
            // 如果等式两边不等的话，那么就判断是否连通
            if (ele.charAt(1) == '!') {
                int x = ele.charAt(0);
                int y = ele.charAt(3);
                if (uf.connected(x - 'a', y - 'a')) {
                    return false;
                }
            }
        }
        return true;
    }
}

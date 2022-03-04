package 错题集;

import java.util.*;

/**
 * 二叉树中所有距离为k的节点
 *
 * 给定一个二叉树（具有根结点 root）， 一个目标结点 target ，和一个整数值 k 。
 *
 * 返回到目标结点 target 距离为 k 的所有结点的值的列表。 答案可以以 任何顺序 返回。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/all-nodes-distance-k-in-binary-tree
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class AllNodesDistanceKInBinaryTree {
    // 如果这个题目给的是 图中某个点，与这个点距离K的点，应该能够很容易想到使用BFS
    // 所以，这道题的思路就是把二叉树给转换给图，对于图有两种表示方式，一种是邻接表，另一种是邻接矩阵
    // 这里使用邻接矩阵
    // 同时，因为题目中给出了每个节点的value不一样，所以key是int类型就可以
    HashMap<Integer, List<Integer>> graph = new HashMap<>();
    HashSet<Integer> visited = new HashSet<>();

    public List<Integer> distanceK(TreeNode root, TreeNode target, int k) {
        List<Integer> res = new ArrayList<>();

        // 先把二叉树图化
        dfs(root);
        // 在使用BFS来遍历这个图
        Queue<Integer> q = new LinkedList<>();
        q.add(target.val);  // 这里是要假如target，不要思维定式写成root
        visited.add(target.val);

        while (!q.isEmpty() && k >= 0) {
            int size = q.size();

            for (int i = 0; i < size; i++) {
                int v = q.poll();

                if (k == 0) {
                    res.add(v);
                }

                List<Integer> neighbor = graph.get(v);
                if (neighbor == null) continue;

                for (int j = 0; j < neighbor.size(); j++) {
                    // 在这里需要判断接是否被访问过了，如果被访问过了就不要再加入进去了
                    if (!visited.contains(neighbor.get(j))) {
                        q.add(neighbor.get(j));
                        visited.add(neighbor.get(j));
                    }
                }
            }
            k--;
        }
        return res;
    }

    // 通过dfs把二叉树给转化成图
    public void dfs(TreeNode root) {
        if (root == null) return;
        if (root.left != null) {
            // 把root和root.left加入到graph中
            // 还要注意，因为是无向图，所以需要加入两次
            add(root.val, root.left.val);
            add(root.left.val, root.val);
            dfs(root.left);
        }

        if (root.right != null) {
            add(root.val, root.right.val);
            add(root.right.val, root.val);
            dfs(root.right);
        }
    }

    public void add(int a, int b) {
        if (graph.get(a) == null) {
            List<Integer> neighbor = new ArrayList<>();
            neighbor.add(b);
            graph.put(a, neighbor);
        } else {
            List<Integer> neighbor = graph.get(a);
            neighbor.add(b);
            graph.put(a, neighbor);
        }
    }
}

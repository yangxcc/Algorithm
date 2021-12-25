package BFS;

import binaryTree.Node;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 最小深度指的是从根节点到最近叶子节点的最短路径上的节点数量
 */
public class MinimumDepthOfBinaryTree {

    // 对于这道题，当然可以使用递归，很轻易就能够写出来
    public int minDepth01(Node root) {
        if (root == null) {
            return 0;
        }
        // 因为他要求的是到叶子节点的深度，所以一定要有这两个判断
        if (root.left == null && root.right != null) {
            return minDepth01(root.right) + 1;
        }
        if (root.right == null && root.left != null) {
            return minDepth01(root.left) + 1;
        }

        return Math.min(minDepth01(root.left), minDepth01(root.right)) + 1;
    }


    // 这道题还可以使用bfs，直接套用bfs的模板就行
    // 因为我们可以把深度当成从根节点出发（起点）到叶子节点（终点）的步骤（step）
    public int minDepth02(Node root) {
        if (root == null) {
            return 0;
        }
        Queue<Node> q = new LinkedList<>();
        q.add(root);
        int step = 1;   // 注意这里要初始化为1，因为root节点本身就算是一层了
        while (!q.isEmpty()) {
            int sz = q.size();
            for (int i = 0; i < sz; i++) {
                Node cur = q.poll();
                if (cur.left == null && cur.right == null) {
                    return step;
                }
                if (cur.left != null) {
                    q.add(cur.left);
                }
                if (cur.right != null) {
                    q.add(cur.right);
                }
            }
            step++;  // 更新步数，也就是再向外扩散一层
        }
        return 0;
    }
}

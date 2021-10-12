package graph.traverseGraph;

import graph.structure.Node;
import sun.awt.image.ImageWatched;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 广度优先遍历
 * 思路
 *        A  ---- B
 *        |     /  \
 *       |    /     \
 *       C --D       E
 * 如果从A出发，那么我将首先遍历与A直接相连的节点，BC，BC的顺序无所谓
 * 紧接着我将遍历与B、C相连的节点DE，同样DE的顺序也无所谓
 *
 * 广度优先遍历使用队列
 * */
public class bfs {

    // 广度优先遍历只告诉图中的一个节点即可，即从这个结点出发开始遍历
    public void bfs (Node head) {
        if (head == null) {
            return;
        }
        Queue<Node> queue = new LinkedList<>();
        queue.add(head);
        HashSet<Node> set = new HashSet<>();   // 用来存放已经遍历过的节点，避免重复
        set.add(head);
        while (!queue.isEmpty()) {
            Node cur = queue.poll();
            System.out.println(cur.value);
            for (Node node : cur.nexts) {
                if (!set.contains(node)) {
                    queue.add(node);
                    set.add(node);
                }
            }
        }
    }
}

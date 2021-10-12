package graph.traverseGraph;

import graph.structure.Node;

import java.util.HashSet;
import java.util.Stack;

/**
 * 深度优先遍历
 * 思路就是 一条路走到黑！
 *
 *
 *          A  ---- B
 *          |     /  \
 *         |    /     \
 *         C --D       E
 * 如果从A出发，那么可以到达B/C，如果是到了C，接下来就是D,B,E
 * 如果是到达了B，接下来可以是D/E
 *   如果是D，那么后面就是C和E（有回溯的过程）
 *   如果是E，那么后面就是D和C，同样也有回溯的过程
 *
 * 深度优先遍历使用栈
 */
public class dfs {

    public void dfs(Node head) {
        if (head == null) {
            return;
        }

        Stack<Node> stack = new Stack<>();
        stack.add(head);
        HashSet<Node> set = new HashSet<>();
        set.add(head);
        while (!stack.isEmpty()) {
            Node cur = stack.pop();
            for (Node next : cur.nexts) {
                if (!set.contains(next)) {
                    stack.push(cur);   // 再把这个cur放回去，以备回溯使用
                    stack.push(next);  // 把与cur相连的节点放到栈中
                    set.add(next);
                    System.out.println(next.value);
                    break; // break在这里控制着每次只添加进一个next节点来
                }
            }
        }
    }
}

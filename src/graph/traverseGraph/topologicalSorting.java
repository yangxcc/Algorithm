package graph.traverseGraph;

import graph.structure.Graph;
import graph.structure.Node;

import java.util.*;

/**
 * 拓扑排序算法即可以用在连通图，也可以用在非连通图上
 * 但是拓扑排序算法的一个前提是
 * 他必须应用在有向图中，而且这个图中有至少一个入度为0的点，且没有环
 * 上面这个条件其实很好理解
 * 比如我们的一个项目中需要依赖很多的包，有A,B,C,D,E,...包，他们的依赖关系如下
 * A
 * /  \
 * B -> C
 * B依赖于A，C依赖于AB，那么程序加载时首先要加载的是A，才能加在其他的包
 * 但是B不能够在依赖A了，因为如果两个包相互依赖，那么先加载谁呢？
 * <p>
 * <p>
 * 代码的实现思路就是不断找图中入度为0的节点，并在找到之后把相应点上的入读 -1
 */
public class topologicalSorting {

    public List<Node> sortedTopological(Graph graph) {
        if (graph == null) {
            return null;
        }

        // 这个hashmap用于存放某个节点的入度
        HashMap<Node, Integer> inMap = new HashMap<>();
        Queue<Node> zeroQueue = new LinkedList<>(); // 只有入度为0的点才能够进入这个队列

        // 统计图中的点的入度，并找出入读为0的点加入到队列中
        for (Node node : graph.nodes.values()) {
            inMap.put(node, node.in);
            if (node.in == 0) {
                zeroQueue.add(node);
            }
        }

        // 把拓扑排序的结果依次加入到res中
        List<Node> res = new ArrayList<>();
        while (!zeroQueue.isEmpty()) {
            Node zeroInNode = zeroQueue.poll();
            res.add(zeroInNode);
            for (Node next : zeroInNode.nexts) {
                inMap.put(next, inMap.get(next) - 1);
                if (inMap.get(next) == 0) {
                    zeroQueue.add(next);
                }
            }
        }
        return res;
    }
}

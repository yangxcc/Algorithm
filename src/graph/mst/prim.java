package graph.mst;

import graph.structure.Edge;
import graph.structure.Graph;
import graph.structure.Node;

import java.util.*;

/**
 * 不同于kruskal算法，prim算法是从节点的角度来进行的
 * prim算法从任意一个点开始，每次选择一个与当前顶点集最近的一个顶点，并将两顶点之间的边加入到树中
 * 可以看出来prim算法也是使用了贪心的思想
 * */
public class prim {


    public static class EdgeComparator implements Comparator<Edge> {
        @Override
        public int compare(Edge o1, Edge o2) {
            return o1.weight - o2.weight;
        }
    }

    public Set<Edge> primMST(Graph graph) {

        PriorityQueue<Edge> priorityQueue = new PriorityQueue<>(new EdgeComparator());

        Set<Edge> result = new HashSet<>();
        HashSet<Node> set = new HashSet<>();   // 用于存放已经访问过的node


        /**
         * 其实这个for循环它是用来避免给的这个图不是一个连通图，
         * 如果不是一个连通图，那么就会生成多个最小生成树
         *
         * 如果给定了这个图是一个连通图的话，那么不需要这个for循环，直接随机
         * 选择出一个节点去运行if下面的代码即可
         * */
        for (Node node : graph.nodes.values()) {

            if (!set.contains(node)) {
                set.add(node);
                for (Edge edge : node.edges) {
                    priorityQueue.add(edge);
                }

                while (!priorityQueue.isEmpty()) {
                    Edge minEdge = priorityQueue.poll();
                    Node toNode = minEdge.toNode;
                    if (!set.contains(toNode)) {
                        set.add(toNode);
                        result.add(minEdge);
                        for (Edge edge : toNode.edges) {
                            priorityQueue.add(edge);
                        }
                    }
                }
            }
        }
        return result;
    }
}

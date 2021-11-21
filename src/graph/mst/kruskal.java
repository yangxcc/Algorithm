package graph.mst;

import graph.structure.Edge;
import graph.structure.Graph;
import graph.structure.Node;

import java.util.*;

/**
 * 最小生成树 Minimum Spanning Tree\
 * 最小生成树的搜索主要有两种方式，Kruskal算法是一种，另一种是Prim算法
 *
 * Kruskal算法的思路
 * 在某地分布着N个村庄，现在需要在N个村庄之间修路，每个村庄之前的距离不同，问怎么修最短的路，将各个村庄连接起来，
 * 这种问题就可以被称为最小生成树问题
 *
 * 首先把所有边的权值从小到大排列，然后按照顺序选择每一条边，如果这条边的两个端点不属于同一个集合，那么就将他们合并，
 * 直到所有的节点都在一个集合内
 *
 * 所以kruskal算法是基于贪心的思想的，这个算法是从边的角度来考虑的，他只看一件事在加边的过程中有没有形成环
 */

// 比较器，用来比较边的大小，小根堆里面要用
class EdgeComparator implements Comparator<Edge> {
    @Override
    public int compare(Edge o1, Edge o2) {
        return o1.weight - o2.weight;
    }
}

public class kruskal {

    public static class MySets {
        public HashMap<Node, List<Node>> setMap;  // key是node节点，value是这个节点所在的链表

        public MySets(List<Node> Nodes) {
            for (Node node : Nodes) {
                List<Node> set = new ArrayList<>();
                set.add(node);
                setMap.put(node, set);  // 最开始每个节点自己就是一个链表
            }
        }

        // 判断from和to节点对应的是不是同一个链表，如果是的话就不能够合并，也就是不能够加入这条边，因为会形成环
        public boolean isSameSet(Node from, Node to) {
            List<Node> fromSet = setMap.get(from);
            List<Node> toSet = setMap.get(to);
            return fromSet == toSet;
        }

        // 合并 from节点的链表和 to节点的链表
        // 不仅要把点合并过来，还要把节点所指向的list合并过来
        public void union(Node from, Node to) {
            List<Node> fromSet = setMap.get(from);
            List<Node> toSet = setMap.get(to);
            for (Node toNode : toSet) {
                fromSet.add(toNode);
                setMap.put(toNode, fromSet);
            }
        }
    }

    public Set<Edge> kruskalMST(Graph graph) {
        if (graph == null) {
            return null;
        }
        MySets mySets = new MySets((List<Node>) graph.nodes.values());

        // 直接使用小根堆，将Edge的顺序排出来，注意需要实现一个比较器
        PriorityQueue<Edge> priorityQueue = new PriorityQueue<>(new EdgeComparator());
        for (Edge edge : graph.edges) {
            priorityQueue.add(edge);
        }
        Set<Edge> res = new HashSet<>();
        while (!priorityQueue.isEmpty()) {
            Edge edge = priorityQueue.poll();
            if (!mySets.isSameSet(edge.fromNode, edge.toNode)) {
                res.add(edge);
                mySets.union(edge.fromNode, edge.toNode);
            }
        }
        return res;
    }
}

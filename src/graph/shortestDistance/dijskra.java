package graph.shortestDistance;

import graph.structure.Edge;
import graph.structure.Node;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

/**
 * dijskra算法是给定图中的一个点，求解出这个点到其他点的最短路径
 */


public class dijskra {

    // dijskra算法的经典实现，没有优化
    public static HashMap<Node, Integer> classicDijskra(Node node) {
        // key是某一个节点，value是距离，这个距离指的是目前为止key所指节点到源节点（最开始指定的源节点）的最小距离
        HashMap<Node, Integer> distanceMap = new HashMap<>();
        distanceMap.put(node, 0);  // 源节点到自身的距离设置为0
        HashSet<Node> visitedSet = new HashSet<>();  // 这个set用来表示已经被访问过的结点

        Node minNode = getMinDistanceAndUnvisitedNode(distanceMap, visitedSet);
        while (minNode != null) {
            int distance = distanceMap.get(minNode);  // 源节点到选出来这个节点的最短距离
            for (Edge edge : minNode.edges) {
                Node toNode = edge.toNode;
                if (!visitedSet.contains(toNode)) {
                    distanceMap.put(toNode, distance);
                } else {
                    distanceMap.put(toNode, Math.min(distance + edge.weight, distanceMap.get(toNode)));
                }
            }
            visitedSet.add(minNode);
            minNode = getMinDistanceAndUnvisitedNode(distanceMap, visitedSet);
        }
        return distanceMap;
    }

    // 找到距离源节点最近的，而且没有被访问过的节点
    public static Node getMinDistanceAndUnvisitedNode(HashMap<Node, Integer> distanceMap, HashSet<Node> visitedSet) {
        Node minNode = null;
        Integer minDistance = Integer.MAX_VALUE;
        for (Map.Entry<Node, Integer> entry : distanceMap.entrySet()) {
            Node node = entry.getKey();
            int distance = entry.getValue();
            if (!visitedSet.contains(node) && distance < minDistance) {
                minDistance = distance;
                minNode = node;
            }
        }
        return minNode;
    }
}

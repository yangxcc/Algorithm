package graph.shortestDistance;

import graph.structure.Edge;
import graph.structure.Node;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

/**
 * dijskra的优化算法主要是在选取最小且未被选择的节点过程，经典算法是使用遍历的方式
 * 而优化算法的优化策略也是针对这个遍历选择节点的过程，具体做法是通过堆结构
 * 如果仅仅是通过系统提供的PriorityQueue来实现小顶堆/大顶堆的话没有办法满足要求，因为系统提供
 * 的不能够在修改堆里面的值之后再去进行排序
 */
public class dijskraBetter {

    /**
     * 复习一下经典的算法实现，把按这个写熟
     */
    public static HashMap<Node, Integer> dijskra(Node node) {
        if (node == null) {
            return null;
        }
        HashMap<Node, Integer> distanceMap = new HashMap<>();
        HashSet<Node> visitedSet = new HashSet<>();
        distanceMap.put(node, 0);
        Node minDistanceAndUnvisitedNode = getMinDistanceAndUnvisitedNode(distanceMap, visitedSet);
        while (minDistanceAndUnvisitedNode != null) {
            int distance = distanceMap.get(minDistanceAndUnvisitedNode);
            for (Edge edge : minDistanceAndUnvisitedNode.edges) {
                Node toNode = edge.toNode;
                if (!visitedSet.contains(toNode)) {
                    distanceMap.put(toNode, distance + edge.weight);
                } else {
                    distanceMap.put(toNode, Math.min(distance + edge.weight, distanceMap.get(toNode)));
                }
                visitedSet.add(toNode);
                minDistanceAndUnvisitedNode = getMinDistanceAndUnvisitedNode(distanceMap, visitedSet);
            }
        }

        return distanceMap;
    }

    public static Node getMinDistanceAndUnvisitedNode(HashMap<Node, Integer> distanceMap, HashSet<Node> isVisited) {
        Node minNode = null;
        int minDistance = Integer.MAX_VALUE;
        for (Map.Entry<Node, Integer> entry : distanceMap.entrySet()) {
            Node node = entry.getKey();
            int dis = entry.getValue();
            if (!isVisited.contains(node) && dis < minDistance) {
                minNode = node;
                minDistance = dis;
            }
        }
        return minNode;
    }


     // 需要自己实现堆
     // 改进后的dijkstra算法
     // 从head出发，所有head能到达的节点，生成到达每个节点的最小路径记录并返回
    public static HashMap<Node, Integer> dijkstra2(Node head, int size) {
        heapNode nodeHeap = new heapNode(size);
        nodeHeap.addOrUpdateOrIgnore(head, 0);
        HashMap<Node, Integer> result = new HashMap<>();
        while (!nodeHeap.isEmpty()) {
            NodeRecord record = nodeHeap.pop();
            Node cur = record.node;
            int distance = record.distance;
            for (Edge edge : cur.edges) {
                nodeHeap.addOrUpdateOrIgnore(edge.toNode, edge.weight + distance);
            }
            result.put(cur, distance);
        }
        return result;
    }

}

class heapNode {
    Node[] nodes;  // 实际的堆结构
    HashMap<Node, Integer> distanceMap;   // node节点到源节点的最小距离
    HashMap<Node, Integer> indexMap;      // node节点在堆中的位置
    int size;  // 堆上有多少个点

    public heapNode(int size) {
        nodes = new Node[size];
        distanceMap = new HashMap<>();
        indexMap = new HashMap<>();
        size = 0;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void swap(int x, int y) {
        indexMap.put(nodes[x], x);
        indexMap.put(nodes[y], y);
        Node temp = nodes[x];
        nodes[x] = nodes[y];
        nodes[y] = temp;
    }

    // node与父节点比较，向上比较
    public void heapInsert(Node node, int index) {
        while (distanceMap.get(nodes[index]) < distanceMap.get(nodes[(index - 1) / 2])) {
            swap(index, (index - 1) / 2);
            index = (index - 1) / 2;
        }
    }

    // 向下比较，左右节点哪个小选哪个，重新构建小根堆
    public void heapify(int index, int size) {
        int left = 2 * index + 1;
        while (left < size) {
            // 左右节点谁小选谁
            int smallest = left + 1 < size && distanceMap.get(nodes[left]) < distanceMap.get(nodes[left + 1])
                    ? left : left + 1;
            smallest = distanceMap.get(nodes[index]) < distanceMap.get(nodes[smallest]) ? index : smallest;

            if (smallest == index) {
                break;
            }
            swap(index, smallest);
            index = smallest;
            left = 2 * index + 1;
        }
    }

    // 看看这个node节点是否被插入过堆中
    public boolean isEnter(Node node) {
        return indexMap.containsKey(node);
    }

    public boolean inHeap(Node node) {
        return isEnter(node) && indexMap.get(node) != -1;
    }

    // 添加node节点
    public void addOrUpdateOrIgnore(Node node, int distance) {
        if (inHeap(node)) {  // 在堆中，update
            distanceMap.put(node, Math.min(distance, distanceMap.get(node)));
            heapInsert(node, indexMap.get(node));
        }
        if (!isEnter(node)) {    // 没有被插入过
            nodes[size] = node;
            indexMap.put(node, size);
            distanceMap.put(node, distance);
            heapInsert(node, size++);
        }
        // Ignore
    }

    public NodeRecord pop() {
        NodeRecord nodeRecord = new NodeRecord(nodes[0], distanceMap.get(nodes[0]));
        swap(0, size - 1);
        indexMap.put(nodes[size - 1], -1);
        distanceMap.remove(nodes[size - 1]);
        // free C++同学还要把原本堆顶节点析构，对java同学不必
        nodes[size - 1] = null;
        heapify(0, --size);
        return nodeRecord;
    }
}

class NodeRecord {
    public Node node;
    public int distance;

    public NodeRecord(Node node, int distance) {
        this.node = node;
        this.distance = distance;
    }
}

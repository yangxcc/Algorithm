package graph.structure;

public class createGraph {

    /**
     * 给一个图的邻接矩阵，矩阵的格式为 N * 3 ，如下
     * weight, from, to
     * [5,0,7]   表示 从节点0到节点7之间有一条边，weight为5
     * [3,0,1]   从节点0到节点1之间有一条边，weight为3
     * [4,3,2]   从节点3到结点2之间有一条边，weight为4
     */
    public createGraph(int[][] matrix) {
        Graph graph = new Graph();
        for (int i = 0; i < matrix[0].length; i++) {
            int weight = matrix[i][0];
            int from = matrix[i][1];
            int to = matrix[i][2];

            if (!graph.nodes.containsKey(from)) {  // 如果图中没有from这个节点
                graph.nodes.put(from, new Node(from));
            }
            if (!graph.nodes.containsKey(to)) {    // 如果图中没有to这个节点
                graph.nodes.put(to, new Node(to));
            }

            Node fromNode = graph.nodes.get(from);
            Node toNode = graph.nodes.get(to);
            Edge edge = new Edge(weight,fromNode,toNode);
            graph.edges.add(edge);
            fromNode.nexts.add(toNode);
            fromNode.edges.add(edge);
            fromNode.out++;
            toNode.in++;
        }
    }
}

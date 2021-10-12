package graph.structure;

import java.util.ArrayList;

// Node的结构
public class Node {
    public int value;
    public int in;  // 入度
    public int out; // 出度
    public ArrayList<Node> nexts;  // 与这个点相邻的点的集合
    public ArrayList<Edge> edges;  // 与这个点相邻的边的集合

    public Node (int val) {
        this.value = val;
        this.in = 0;
        this.out = 0;
        this.nexts = new ArrayList<>();
        edges = new ArrayList<>();
    }
}

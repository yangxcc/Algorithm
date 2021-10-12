package graph.structure;

// Edge的结构
public class Edge {
    public Node fromNode;
    public Node toNode;
    public int weight;

    public Edge (int wei, Node from, Node to) {
        this.weight = wei;
        this.fromNode = from;
        this.toNode = to;
    }
}

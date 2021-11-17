package tree;

import java.lang.String;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 二叉树的序列化和反序列化
 *
 * 空结点使用#代替，节点之间的分隔符使用，
 * */
public class serializeAndDeserialize {


    public String serialize_preOrder(Node root) {
        if (root == null) {
            return "";
        }
        StringBuilder sb = new StringBuilder();  // StringBuilder的效率要比String高，看一下为什么？
        serialize_preOrder_helper(root, sb);
        return sb.toString();
    }

    public void serialize_preOrder_helper(Node root, StringBuilder sb) {
        if (root == null) {
            sb.append("#,");
            return;
        }
        sb.append(root.value).append(",");
        serialize_preOrder_helper(root.left, sb);
        serialize_preOrder_helper(root.right, sb);
    }

    public Node deserialize_preOrder(String data) {
        if (data.equals("")) {
            return null;
        }
        LinkedList<String> nodes = new LinkedList<>();
        for (String val : data.split(",")) {
            nodes.add(val);
        }
        return deserialize_preOrder_helper(nodes);
    }

    public Node deserialize_preOrder_helper(LinkedList<String> nodes) {
        if (nodes.isEmpty()) {
            return null;
        }
        String first = nodes.removeFirst();
        if (first.equals("#")) {
            return null;
        }
        Node root = new Node(Integer.parseInt(first));
        root.left = deserialize_preOrder_helper(nodes);
        root.right = deserialize_preOrder_helper(nodes);
        return root;
    }


    public String serialize_postOrder(Node root) {
        if (root == null) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        serialize_postOrder_helper(root, sb);
        return sb.toString();
    }

    public void serialize_postOrder_helper(Node root, StringBuilder sb) {
        if (root == null) {
            sb.append("#,");
            return;
        }

        serialize_postOrder_helper(root.left, sb);
        serialize_postOrder_helper(root.right, sb);

        sb.append(root.value).append(",");
    }

    public Node deserialize_postOrder(String data) {
        if (data.equals("")) {
            return null;
        }
        LinkedList<String> nodes = new LinkedList<>();
        for (String val : data.split(",")) {
            nodes.add(val);
        }
        return deserialize_postOrder_helper(nodes);
    }

    public Node deserialize_postOrder_helper(LinkedList<String> nodes) {
        if (nodes.isEmpty()) {
            return null;
        }
        String last = nodes.removeLast();
        if (last.equals("#")) {
            return null;
        }
        Node root = new Node(Integer.parseInt(last));
        // 注意这里是先right，后left哦！！
        root.right = deserialize_postOrder_helper(nodes);
        root.left = deserialize_postOrder_helper(nodes);
        return root;
    }

    /**
     * 可以看出，前序遍历和后序遍历之所以能够进行序列化和反序列化，是因为
     * 通过前序遍历或者后序遍历我们能够知道根节点所在的位置
     * */

    // 除此之外，使用层序遍历也能够实现序列化和反序列化，但是它是根据数组中的下标得到节点应该在的位置
    public String serialize_levelOrder(Node root) {
        if (root == null) {
            return "";
        }
        Queue<Node> queue = new LinkedList<>();
        StringBuilder sb = new StringBuilder();
        queue.add(root);
        while (!queue.isEmpty()) {
            Node node = queue.poll();
            if (node == null) {
                sb.append("#,");
                continue;
            }
            sb.append(root.value).append(",");
            queue.add(node.left);
            queue.add(node.right);
        }
        return sb.toString();
    }

    public Node deserialize_levelOrder(String data) {
        if (data.equals("")) {
            return null;
        }
        String[] nodes = data.split(",");
        Node root = new Node(Integer.parseInt(nodes[0]));
        Queue<Node> q = new LinkedList<>();   // 用来存放父节点，保证顺序
        q.add(root);
        for (int i = 1; i < nodes.length;) {
            Node parent = q.poll();
            String left = nodes[i++];
            if (left.equals("#")) {
                parent.left = null;
            } else {
                parent.left = new Node(Integer.parseInt(left));
                q.add(parent.left);
            }

            String right = nodes[i++];
            if (right.equals("#")) {
                parent.right = null;
            } else {
                parent.right = new Node(Integer.parseInt(right));
                q.add(parent.right);
            }
        }
        return root;
    }

}

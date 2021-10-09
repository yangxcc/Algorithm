package binaryTree;


import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 二叉树的宽度
 * */
public class maxWidthOfBinaryTree {

    /**
     * 使用HashMap来求解二叉树的宽度
     *
     * 使用HashMap的思路
     * HashMap<Node,level> 中存放节点和它对应的层级
     * 使用队列来保证能够每一层每一层的按照顺序出队
     * max用来维护到目前为止最多的node节点数
     * curLevel 表示当前所在层数
     * curLevelNodes 表示当前所在层数中具有的node节点数
     * curNodeLevel  表示某个节点所在的层数
     *
     * */
    public static int widthHashMap (Node head) {
        if (head == null) {
            return -1;
        }
        HashMap<Node, Integer> hashMap = new HashMap<>();
        int max = -1;
        hashMap.put(head,1);  // 头节点在第一层
        Queue<Node> queue = new LinkedList<>();
        queue.add(head);
        int curLevel = 1;    // 记录当前所在层级
        int curLevelNodes = 0;     // 记录当前层级中node的个数
        while (!queue.isEmpty()) {
            Node cur = queue.poll();
            int curNodeLevel = hashMap.get(cur);   // 当前节点所在的层级
            if (cur.left != null) {
                hashMap.put(cur.left,curLevel + 1);
                queue.add(cur.left);
            }
            if (cur.right != null) {
                hashMap.put(cur.right,curLevel + 1);
                queue.add(cur.right);
            }
            if (curNodeLevel == curLevel) {
                curLevelNodes++;
            } else {
                max = Math.max(max,curLevelNodes);
                curLevel++;
                curLevelNodes = 1;
            }
        }
        // 通过上面的这种循环，是没有办法把最后一层的node节点个数被统计在了curLevelNodes中，所以还需要比较一下
        return Math.max(max,curLevelNodes);

    }

    /**
     * 不使用HashMap来求解二叉树的宽度
     * 当我们在遍历上一层的时候就已经知道了下一层的最后一个节点是什么
     * */
    public static int maxWidthNoHashMap (Node head) {
        if (head == null) {
            return -1;
        }
        Queue<Node> queue = new LinkedList<>();
        Node curEnd = head;    // 当前层最右侧的节点
        Node nextEnd = null;   // 下一层最右侧的节点
        int curLevelNode = 0;  // 当前层节点数
        int max = 0;

        queue.add(head);

        while (!queue.isEmpty()) {
            Node cur = queue.poll();
            if (cur.left != null) {
                queue.add(cur.left);
                nextEnd = cur.left;
            }
            if (cur.right != null) {
                queue.add(cur.right);
                nextEnd = cur.right;
            }
            curLevelNode++;
            if (cur == curEnd) {
                max = Math.max(max,curLevelNode);
                curLevelNode = 0;
                curEnd = nextEnd;
                nextEnd = null;
            }
        }
        return max;
    }

    public static Node createBT () {
        Node head = new Node(1);
        Node node1 = new Node(2);
        Node node2 = new Node(3);
        Node node3 = new Node(4);
        Node node4 = new Node(5);
        Node node5 = new Node(6);
        Node node6 = new Node(7);
        head.left = node1;
        head.right = node2;
        node1.left = node3;
        node1.right = node4;
        node2.left = node5;
        node2.right = node6;
        return head;
    }

    public static void main(String[] args) {
        Node head = createBT();
        System.out.println(widthHashMap(head));
        System.out.println("===================");
        System.out.println(maxWidthNoHashMap(head));
    }


}

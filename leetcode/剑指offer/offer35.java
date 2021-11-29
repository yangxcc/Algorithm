package 剑指offer;

/**
 * 复杂链表的复制
 **/
public class offer35 {

    class Node {
        int val;
        Node next;
        Node random;
        public Node(int v) {
            val = v;
        }
    }
    /**
     * 分成三步
     * 第一步：将复制节点放到源节点后面
     * 第二步：把复制节点的random指针制好
     * 第三步：把复制链表和原链表分开
     * */
    public Node copyRandomList(Node head) {
        if (head == null) {
            return null;
        }
        Node cur = head;

        // 1. 将复制节点放到源节点后面
        while (cur != null) {
            Node copyNode = new Node(cur.val);
            copyNode.next = cur.next;
            cur.next = copyNode;

            cur = cur.next.next;
        }

        // 2. 连接好复制节点的random指针
        cur = head;
        while (cur != null) {
            if (cur.random != null) {
                cur.next.random = cur.random.next;
            }
            cur = cur.next.next;
        }

        // 3. 分开copy和原链
        Node copyHead = head.next;
        Node helper = copyHead;
        cur = head;
        while (cur != null) {
            cur.next = helper.next;
            cur = helper.next;
            if (cur == null) {
                return copyHead;
            }
            helper.next = cur.next;
            helper = cur.next;
        }
        return copyHead;
    }
}

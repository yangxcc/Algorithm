package 剑指offer;

/**
 * 删除链表节点
 * <p>
 * 给定单向链表的头指针和一个要删除的节点的值，定义一个函数删除该节点。
 * <p>
 * 返回删除后的链表的头节点。
 */
public class offer18 {
    public ListNode deleteNode(ListNode head, int val) {
        if (head == null) return null;
        if (head.val == val) return head.next;

        ListNode pre = head;
        ListNode cur = head.next;
        while (cur.val != val) {
            pre = cur;
            cur = cur.next;
        }
        pre.next = cur.next;
        cur.next = null;

        return head;
    }
}

class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
        val = x;
    }
}

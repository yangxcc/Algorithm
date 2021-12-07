package 单链表的解题套路;

import linkedList.ListNode;

/**
 * 单链表倒数第k个节点
 *
 * 删除单链表倒数第k个节点
 * */
public class FindKNodeFromEnd {

    public ListNode findFromEnd(ListNode head, int k) {
        if (head == null || k < 0) {
            return null;
        }
        // 快慢指针，快指针先走k步，慢指针再走直到快指针到头
        ListNode slow = head;
        ListNode fast = head;
        for (int i = 0; i < k; i++) {
            fast = fast.next;
        }
        while (fast != null) {
            slow = slow.next;
            fast = fast.next;
        }
        return slow;
    }

    // 如果要删除倒数第k个节点，那么我们要找到倒数k+1个节点
    public ListNode deleteKNodeFromEnd(ListNode head, int k) {
        if (head == null) {
            return null;
        }
        ListNode res = head;
        ListNode slow = head;
        ListNode fast = head;
        for (int i = 0; i < k + 1; i++) {
            fast = fast.next;
        }
        while (fast != null) {
            fast = fast.next;
            slow = slow.next;
        }
        // 这时候slow指向的是倒数k+1个节点
        ListNode delete = slow.next;
        if (delete == null) {
            slow.next = null;
        } else {
            slow.next = delete.next;
            delete.next = null;
        }
        return res;
    }

    /**
     * 同时寻找单链表的中点，链表中是否有环，求出在环的起点，
     * 都是使用快慢指针来完成
     *
     * 两个链表是否相交是使用双指针来解决
     */
}

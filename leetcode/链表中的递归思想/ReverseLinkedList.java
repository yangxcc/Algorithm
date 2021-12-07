package 链表中的递归思想;

import linkedList.ListNode;

/**
 * 反转单链表
 * */
public class ReverseLinkedList {

    // 先使用循环来反转链表
    public ListNode reverse(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode pre = head;
        ListNode cur = head.next;
        ListNode helper = cur.next;
        pre.next = null;

        while (cur != null) {
            cur.next = pre;
            pre = cur;
            cur = helper;
            if (cur != null) {
                helper = cur.next;
            }
        }
        return pre;
    }

    // 使用递归来反转链表
    public ListNode reverse2(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        // 翻转以head.next为头的链表
        ListNode node = reverse2(head.next);
        head.next.next = head;
        head.next = null;
        return node;
    }

    // 反转前n个节点
    ListNode helper = null;  // 用来记录后驱节点
    // 这个helper必须是全局变量
    public ListNode reverse3(ListNode head, int n) {

        if (n == 1) {
            helper = head.next;
            return head;
        }
        // 以head.next为起点，翻转前n-1个节点
        ListNode node = reverse3(head.next, n - 1);
        head.next.next = head;
        head.next = helper;
        return node;
    }

    // 反转给定区间的节点
    public ListNode reverse4(ListNode head, int left, int right) {
        if (left == 1) {
            return reverse3(head, right);  // 相当于翻转前n个节点
        }
        head.next = reverse4(head.next, left - 1, right - 1);
        return head;
    }
}

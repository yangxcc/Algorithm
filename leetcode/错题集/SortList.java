package 错题集;

import linkedList.ListNode;

/**
 * 排序链表，给你链表的头结点 head ，请将其按 升序 排列并返回 排序后的链表 。
 */
public class SortList {
    // 说白了就是对链表进行归并排序
    public ListNode sortList(ListNode head) {
        return mergeSort(head);
    }

    public ListNode mergeSort(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        // 快慢指针找到中点
        ListNode slow = head;
        ListNode fast = head;
        ListNode pre = null;  // slow前面的节点
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            pre = slow;
            slow = slow.next;
        }
        pre.next = null;
        // 现在链已经断成两部分了，slow指向的是后一部分，head是前一部分
        ListNode before = mergeSort(head);
        ListNode after = mergeSort(slow);
        return merge(before, after);
    }

    public ListNode merge(ListNode before, ListNode after) {
        if (before == null) {
            return after;
        }
        if (after == null) {
            return before;
        }
        if (before.val < after.val) {
            before.next = merge(before.next, after);
            return before;
        } else {
            after.next = merge(before, after.next);
            return after;
        }
    }
}

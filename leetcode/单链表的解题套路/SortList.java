package 单链表的解题套路;

import linkedList.ListNode;

import java.util.PriorityQueue;

/**
 * 排序链表
 *
 * 给你链表的头结点 head ，请将其按 升序 排列并返回 排序后的链表 。
 */
public class SortList {
    // 如果不限制空间复杂度，那么使用一个最小堆，就能够将链表中的各个节点排好序
    public ListNode sortList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        // 升序排列
        PriorityQueue<ListNode> pq = new PriorityQueue<>((ListNode l1, ListNode l2) -> (l1.val - l2.val));
        ListNode res = new ListNode(-1);
        ListNode cur = res.next;
        while (head != null) {
            pq.add(head);
            head = head.next;
        }
        while (!pq.isEmpty()) {
            ListNode node = new ListNode(pq.poll().val);
            cur.next = node;
            cur = cur.next;
        }
        return res.next;
    }


    // 实际上这道题考察的是在链表上的归并排序，因为leetcode题目中要求的是空间复杂度为O(1)，时间复杂度为O(N*logN)
    public ListNode sortList2(ListNode head) {
        return mergeSort(head);
    }

    // 要一直去联想数组的归并排序，数组中需要left和right，这里就需要head和tail
    public ListNode mergeSort(ListNode head) {
        //
        if (head == null || head.next == null) {
            return head;
        }
        ListNode fast = head;
        ListNode slow = head;
        ListNode pre = null;
        // 使用快慢指针的时候，要注意链表的长度是奇数还是偶数

        while (fast != null && fast.next.next != null) {
            pre = slow;
            fast = fast.next.next;
            slow = slow.next;
        }

        ListNode l1 = mergeSort(slow);
        pre.next = null;
        ListNode l2 = mergeSort(head);
        return merge(l1, l2);
    }

    public ListNode merge(ListNode l1, ListNode l2) {
        if (l1 == null) {
            return l2;
        }
        if (l2 == null) {
            return l1;
        }
        if (l1.val <= l2.val) {
            l1.next = merge(l1.next, l2);
            return l1;
        } else {
            l2.next = merge(l1, l2.next);
            return l2;
        }
    }
}

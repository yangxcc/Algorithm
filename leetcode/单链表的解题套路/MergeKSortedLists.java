package 单链表的解题套路;

import linkedList.ListNode;

import java.util.PriorityQueue;

/**
 * 合并K个升序链表
 *
 * 通过一个小根堆，堆中存放K个元素，每次弹出的都是K个升序链表中最小的位置
 * */
public class MergeKSortedLists {

    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) {
            return null;
        }
        ListNode res = new ListNode(-1);
        PriorityQueue<ListNode> minVal = new PriorityQueue<>(
                (ListNode head1, ListNode head2) -> (head1.val - head2.val)
        );
        for (ListNode head : lists) {
            minVal.offer(head);
        }
        while (!minVal.isEmpty()) {
            ListNode node = minVal.poll();
            res.next = node;
            res = res.next;
            if (node.next != null) {
                minVal.offer(node.next);
            }
        }
        return res.next;
    }
}

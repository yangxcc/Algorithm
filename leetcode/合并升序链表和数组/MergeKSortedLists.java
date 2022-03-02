package 合并升序链表和数组;

import linkedList.ListNode;

import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

/**
 * 合并K个升序链表
 */
public class MergeKSortedLists {
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists.length == 0) {
            return null;
        }
        // 定义一个小根堆
        PriorityQueue<ListNode> pq = new PriorityQueue<>(new Comparator<ListNode>() {
            @Override
            public int compare(ListNode o1, ListNode o2) {
                return o1.val - o2.val;
            }
        });
//        PriorityQueue<ListNode> pq = new PriorityQueue<>((ListNode o1, ListNode o2) -> o1.val - o2.val);

        for (ListNode head : lists) {
            if (head != null) {
                pq.add(head);
            }
        }
        ListNode res = new ListNode(-1);
        ListNode cur = res;
        while (!pq.isEmpty()) {
            ListNode node = pq.poll();
            cur.next = node;
            cur = cur.next;
            if (node.next != null) {
                pq.add(node.next);
            }
        }
        return res.next;
    }
}

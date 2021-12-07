package 单链表的解题套路;

import linkedList.ListNode;

/**
 * 合并两个有序链表
 * */
public class MergeTwoSortedLists {
    // 使用双指针
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        if (list1 == null) {
            return list2;
        }
        if (list2 == null) {
            return list1;
        }
        ListNode head1 = list1;
        ListNode head2 = list2;
        // 这道题的技巧就在于使用了一个虚拟头节点，有了这个头节点降低了很多的代码复杂性
        ListNode res = new ListNode(-1);
        ListNode p = res;

        while (head1 != null && head2 != null) {
            if (head1.val <= head2.val) {
                p.next = head1;
                head1 = head1.next;
            } else {
                p.next = head2;
                head2 = head2.next;
            }
            p = p.next;
        }
        if (head1 != null) {
            p.next = head1;
        }
        if(head2 != null) {
            p.next = head2;
        }
        return res.next;
    }

    // 使用递归操作
    public ListNode mergeTwoLists2(ListNode list1, ListNode list2) {
        if (list1 == null) {
            return list2;
        }
        if (list2 == null) {
            return list1;
        }
        if (list1.val <= list2.val) {
            list1.next = mergeTwoLists2(list1.next, list2);
            return list1;
        } else {
            list2.next = mergeTwoLists2(list1, list2.next);
            return list2;
        }
    }

}

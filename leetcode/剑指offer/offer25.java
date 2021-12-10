package 剑指offer;

/**
 * 合并两个排序的链表
 * 输入两个递增排序的链表，合并这两个链表并使新链表中的节点仍然是递增排序的。
 * */
public class offer25 {
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        // 归并排序的思路
        if (l1 == null) {
            return l2;
        }
        if (l2 == null) {
            return l1;
        }
        if (l1.val <= l2.val) {
            l1.next = mergeTwoLists(l1.next, l2);
            return l1;
        } else {
            l2.next = mergeTwoLists(l1, l2.next);
            return l2;
        }
    }

    // 其实这道题更容易想到的做法还是通过创建一个新的链表
    public ListNode mergeTwoLists2(ListNode l1, ListNode l2) {
        if (l1 == null) {
            return l2;
        }
        if (l2 == null) {
            return l1;
        }
        ListNode res = new ListNode(-1);
        ListNode head1 = l1;
        ListNode head2 = l2;
        ListNode cur;
        while (head1 != null && head2 != null) {
            if (head1.val < head2.val) {
                cur = new ListNode(head1.val);
                res.next = cur;
                head1 = head1.next;
            } else {
                cur = new ListNode(head2.val);
                res.next = cur;
                head2 = head2.next;
            }
            res = res.next;
        }
        if (head1 != null) {
            res.next = head1;
        }
        if (head2 != null) {
            res.next = head2;
        }
        return res.next;
    }
}

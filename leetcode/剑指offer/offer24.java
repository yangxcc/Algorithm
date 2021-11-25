package 剑指offer;

/**
 * 反转链表
 * */
public class offer24 {
    // 先使用三个指针
    public ListNode reverseList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode pre = head;
        ListNode cur = pre.next;
        ListNode curNext = null;

        pre.next = null;
        ListNode res = null;
        while (cur != null) {
            curNext = cur.next;
            cur.next = pre;
            pre = cur;
            if (curNext == null) {
                res = cur;
            }
            cur = curNext;
        }
        return res;
    }

    // 递归
    public ListNode reverseList02(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode reverse = reverseList02(head.next);
        head.next.next = head;
        head.next = null;
        return reverse;
    }
}

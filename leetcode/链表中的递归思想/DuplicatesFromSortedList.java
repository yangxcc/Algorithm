package 链表中的递归思想;

import com.sun.security.auth.UnixNumericUserPrincipal;
import linkedList.ListNode;

/**
 * 给定一个已排序的链表的头 head ， 删除原始链表中所有重复数字的节点，只留下不同的数字 。返回 已排序的链表 。
 */
public class DuplicatesFromSortedList {
    public ListNode deleteDuplicates(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode nxt = head.next;
        if (head.val == nxt.val) {
            while (nxt != null && head.val == nxt.val) {
                nxt = nxt.next;
            }
            head = deleteDuplicates(nxt);
        } else {
            head.next = deleteDuplicates(nxt);
        }
        return head;
    }

    // 不使用递归
    public ListNode deleteDuplicates2(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode res = new ListNode(-1);
        res.next = head;

        ListNode pre = res;
        ListNode cur = head;
        while (cur != null && cur.next != null) {
            if (cur.val == cur.next.val) {
                while (cur.next != null && cur.val == cur.next.val) {
                    cur = cur.next;
                }
                pre.next = cur.next;
                cur = cur.next;
            } else {
                pre = cur;
                cur = cur.next;
            }
        }

        return res.next;
    }


    // 如果是保留一个重复的元素
    // https://leetcode-cn.com/problems/remove-duplicates-from-sorted-list/
    public ListNode keepDuplicates(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode nxt = head.next;
        while (nxt != null && head.val == nxt.val) {
            nxt = nxt.next;
        }
        head.next = keepDuplicates(nxt);
        return head;
    }

    // 不使用递归
    public ListNode keepDuplicates2(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode pre = head;
        ListNode nxt = head;
        while (nxt != null) {
            if (pre.val != nxt.val) {
                pre.next = nxt;
                pre = pre.next;
            }
            nxt = nxt.next;
        }
        // 不要忘了这一句，为的是吧最后面重复的去掉
        pre.next = null;
        return head;
    }
}

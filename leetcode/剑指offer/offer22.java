package 剑指offer;

import java.util.List;

/**
 * 链表中倒数第k个节点
 *
 * 快慢指针，不一定非要循环n-k次
 *
 * */
public class offer22 {
    private class ListNode {
        int val;
        ListNode next;
        public ListNode(int val) {
            this.val = val;
        }
    }
    public ListNode getKthFromEnd(ListNode head, int k) {
        if (head == null || k <= 0) {
            return null;
        }
        ListNode fast = head;
        ListNode slow = head;

        for (int i = 0; i < k; i++) {
            fast = fast.next;
        }
        while (fast != null) {
            fast = fast.next;
            slow = slow.next;
        }
        return slow;
    }

    // 更好的写法
    public ListNode getKthFromEnd2(ListNode head, int k) {
        ListNode fast = head;
        while(fast!=null) {
            fast = fast.next;
            if(k==0) {
                head = head.next;
            }else {
                k--;
            }
        }
        return head;
    }
}



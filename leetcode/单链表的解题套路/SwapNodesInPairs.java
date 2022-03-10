package 单链表的解题套路;

import linkedList.ListNode;

/**
 * 两两交换链表中的节点
 */
public class SwapNodesInPairs {
    public static ListNode swapPairs(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        // 先把这一个长链断成两个链
        // 然后后面的那个链为头
        ListNode odd = head;   // 奇数位的节点
        ListNode even = head.next;  // 偶数位的节点
        ListNode helper = even;
        while (helper != null && helper.next != null) {
            odd.next = helper.next;
            odd = odd.next;
            helper.next = odd.next;
            helper = helper.next;
        }
        odd.next = null;
        // 现在两条链分别是head和even
//        ListNode cur = head;
//        while (cur != null) {
//            System.out.println(cur.val);
//            cur = cur.next;
//        }
//        cur = even;
//        System.out.println("********");
//        while (cur != null) {
//            System.out.println(cur.val);
//            cur = cur.next;
//        }
//        System.out.println("********");
        return merge(even, head);
    }

    public static ListNode merge(ListNode l1, ListNode l2) {
        ListNode res = l1;
        while (l1 != null && l2 != null) {
            ListNode helper1 = l1.next;
            ListNode helper2 = l2.next;
            l1.next = l2;
            l1 = helper1;
            if (l1 == null) {
                break;
            }
            l2.next = l1;
            l2 = helper2;
        }
        return res;
    }

    // 递归解法
    public ListNode swapPairs2(ListNode head) {
        if (head == null || head.next == null) {
            return null;
        }
        ListNode nxt = head.next;
        head.next = swapPairs2(nxt.next);
        nxt.next = head;
        return nxt;
    }

    public static ListNode createList() {
        ListNode head = new ListNode(1);
        ListNode node1 = new ListNode(2);
        ListNode node2 = new ListNode(3);
        ListNode node3 = new ListNode(4);
        head.next = node1;
        node1.next = node2;
        node2.next = node3;
        return head;
    }

    public static void main(String[] args) {
        ListNode res = swapPairs(createList());
        while (res != null) {
            System.out.println(res.val);
            res = res.next;
        }
    }
}

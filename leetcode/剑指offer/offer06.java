package 剑指offer;

/**
 * 从头到尾打印链表
 *
 * 输入一个链表的头节点，从尾到头反过来返回每个节点的值（用数组返回）。
 *
 * 对于这道题，我最开始想复杂了，想先原地翻转单链表，然后再用list，最后转成数组
 * */
public class offer06 {


    public int[] reversePrint(ListNode head) {
        ListNode cur = head;
        int count = 0;
        while (cur != null) {
            count++;
            cur = cur.next;
        }
        int[] res = new int[count];
        for (int i = count - 1; i >= 0; i--) {
            res[i] = head.val;
            head = head.next;
        }
        return res;
    }


    private class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }
}

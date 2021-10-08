package linkedList;

/**
 * 打印两个单链表的公共部分
 * 时间复杂度 O(N)
 * 空间复杂度 O(1)
 * */
public class printPublicPart {
    public static void printPublicPartForTwoSingleLinkedList(ListNode head1, ListNode head2) {
        if (head1 == null && head2 == null) {
            return;
        }
        while (head1 != null && head2 != null) {
            if (head1.val < head2.val) {
                head1 = head1.next;
            } else if (head1.val > head2.val) {
                head2 = head2.next;
            } else {
                System.out.println(head1.val);
                head1 = head1.next;
                head2 = head2.next;
            }
        }
    }

    public static ListNode createLinkedList() {
        ListNode head = new ListNode(1);
        ListNode node1 = new ListNode(2);
        ListNode node2 = new ListNode(3);
        ListNode node3 = new ListNode(4);
        ListNode node4 = new ListNode(5);
        head.next = node1;
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        return head;
    }

    public static ListNode createLinkedList2() {
        ListNode head = new ListNode(3);
        ListNode node1 = new ListNode(2);
        ListNode node2 = new ListNode(4);
        ListNode node3 = new ListNode(7);
        ListNode node4 = new ListNode(5);
        ListNode node5 = new ListNode(9);
        head.next = node1;
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;
        return head;
    }

    public static void main(String[] args) {
        ListNode head1 = createLinkedList();
        ListNode head2 = createLinkedList2();
        printPublicPartForTwoSingleLinkedList(head1,head2);
    }
}

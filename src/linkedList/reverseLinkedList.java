package linkedList;

/**
 * 要求时间复杂度O(N)
 * 空间复杂度O(1)
 * */
public class reverseLinkedList {

    /**
     * 反转单链表
     * */
    public static ListNode reverseSingleLinkedList(ListNode head) {
        // 如果不要求空间复杂度是O(1)，那么直接使用一个栈就行，利用它先进后出的性质
        // 这里就不实现上面这种用栈的方法了

        // 这里直接使用两个指针就好了
        if (head == null || head.next == null) {
            return head;
        }
        ListNode f = head.next;    // forward  前一个指针
        ListNode b = head;         // backward 后一个指针
        b.next = null;

        ListNode temp1 = null;
        ListNode temp2 = null;
        while (f.next != null) {
            temp1 = f;
            temp2 = f.next;
            f.next = b;        // 必须要有temp1和temp2，分别用来记录next指针反向之前的状态
            f = temp2;
            b = temp1;
        }
        f.next = b;
        return f;
    }

    /**
     * 反转双链表
     * */
    public static DoubleListNode reverseDoubleLinkedList(DoubleListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        DoubleListNode temp = null;
        DoubleListNode cur = head;
        while (cur != null) {
            temp = cur.pre;
            cur.pre = cur.next;
            cur.next = temp;
            cur = cur.pre;   // 这里是pre，不能是next了，因为next已经被改变了
        }
        return temp.pre;
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

    public static DoubleListNode createDoubleLinkedList () {
        DoubleListNode head = new DoubleListNode(1);
        DoubleListNode node1 = new DoubleListNode(2);
        DoubleListNode node2 = new DoubleListNode(3);
        DoubleListNode node3 = new DoubleListNode(4);
        head.next = node1;
        node1.pre = head;
        node1.next = node2;
        node2.pre = node1;
        node2.next = node3;
        node3.pre = node2;
        return head;
    }
    public static void main(String[] args) {
        ListNode head = createLinkedList();
        ListNode res = reverseSingleLinkedList(head);
        while (res != null) {
            System.out.println(res.val);
            res = res.next;
        }

        System.out.println("===================================");
        DoubleListNode head1 = createDoubleLinkedList();
        DoubleListNode res1 = reverseDoubleLinkedList(head1);
        while (res1 != null) {
            System.out.println(res1.val);
            res1 = res1.next;
        }
    }

}

package linkedList;

/**
 * Node节点
 */
public class ListNode {
    Integer val;
    ListNode next;
    ListNode rand;

    ListNode() {
    }

    ListNode(Integer val) {
        this.val = val;
    }

    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }

    ListNode(int val, ListNode next, ListNode rand) {
        this.val = val;
        this.next = next;
        this.rand = rand;
    }

}

package linkedList;

/**
 * Node节点
 */
public class ListNode {
    public Integer val;
    public ListNode next;
    public ListNode rand;

    ListNode() {
    }

    public ListNode(Integer val) {
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

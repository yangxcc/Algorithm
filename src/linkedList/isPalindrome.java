package linkedList;

import java.util.LinkedList;
import java.util.Stack;

/**
 * 给定一个链表的 头节点 head ，请判断其是否为回文链表。
 * 如果一个链表是回文，那么链表节点序列从前往后看和从后往前看是相同的。
 *
 * 输入: head = [1,2,3,3,2,1]
 * 输出: true
 *
 * 输入: head = [1,2]
 * 输出: false
 * */
public class isPalindrome {

    // 暴力解法，时间复杂度是O(N)，空间复杂度是O(N)
    public static boolean violence(ListNode head) {
        if (head == null || head.next == null) {  // 只有1个或者2个数据一定不是回文链表
            return false;
        }
        Stack<Integer> stack = new Stack<>();
        ListNode cur = head;    // 这里一定要有这句话呀，因为如果没有这句话，那么head就会移动了
        while (cur != null) {
            stack.push(cur.val);
            cur = cur.next;
        }

//        while (head != null) {
//            stack.add(head.val);
//            head = head.next;
//        }

        // 如果没有cur = head 这句话，这里的head就是null了，自然会报空指针异常了
        while (head != null) {   // 也可以 !stack.isEmpty()
            System.out.println(stack.pop());
            System.out.println("head.val="+head.val);
            if (head.val != stack.pop()) {   // 疯了！！！为什么明明两个数是一样的，最后偏偏是不相同啊
                return false;
            }
            head = head.next;
        }

        return true;
    }

    /**
     * 不使用额外的空间，使得时间复杂度为O(N)
     * 空间复杂度降为O(1)，即仅使用几个变量来完成回文链表的判断
     *
     * 使用快慢指针，快指针一次走2个，慢指针一次走1个，当快指针指向最后的时候，慢指针指向了中间
     * */
    public static boolean betterWay(ListNode head) {
        if (head == null || head.next == null) {  // 只有1个或者2个数据一定不是回文链表
            return false;
        }

        ListNode fast = head;    // 快指针
        ListNode slow = head;    // 慢指针
        ListNode cur = head;

        while (fast.next != null && fast.next.next != null) {  // 快慢指针的移动条件，先记住这么写！！！
            fast = fast.next.next;
            slow = slow.next;       // 让慢指针指向中间的位置
        }
        fast = slow.next;
        slow.next = null;
        ListNode temp = null;
        // 进行后半部分的链表反转
        while (fast != null) {
            temp = fast.next;
            fast.next = slow;
            slow = fast;
            fast = temp;
        }
        // 在上面的循环结束之后，慢指针刚好指的是链表的最后一个数，fast和temp都是指向的是slow.next
        fast = head;  // 将fast弄到最前面，要开始进行比较了
        temp = slow;  // 让temp记住最后的位置，再后面恢复链表的时候会用到
        while (fast != null && slow != null) {
            if (fast.val != slow.val) {
                return false;
            }
            fast = fast.next;
            slow = slow.next;
        }

        // 比较完了之后不要忘记把翻转过来的链表再反转回去,此时slow指针又指向了中间位置
        slow = temp.next;
        temp.next = null;
        while (slow != null) {
            fast = slow.next;
            slow.next = temp;
            temp.next = slow;
            slow = fast;
        }
        return true;
    }


    public static ListNode createLinkedList() {
        ListNode head = new ListNode(1);
        ListNode node1 = new ListNode(2);
        ListNode node2 = new ListNode(3);
        ListNode node3 = new ListNode(2);
        ListNode node4 = new ListNode(1);
        head.next = node1;
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        return head;
    }
    public static void main(String[] args) {
        ListNode head = createLinkedList();
        System.out.println(violence(head));
        System.out.println(betterWay(head));
    }
}

package linkedList;

import java.util.HashMap;
import java.util.List;

/**
 *
 *
 * Node类中的value时节点值，next指针和正常单链表中next指针的意义一样，都指向下一个节点，
 * rand指针是Node类中新增的指针，这个指针可以指向链表的任意一个节点，也可以指向NULL。
 *
 * 给定一个由Node节点类型组成的无环单链表的头节点head
 * 请实现一个函数完成这个链表中所有结构的复制，并返回复制的新链表的头节点
 *
 * 比如    1 --> 2 --> 3 --> null
 *        |           ^
 *        |___________|
 */


public class copyListWithRand {


    /**
     * 暴力解法，使用一个HashMap，key是节点，value是节点指向的新拷贝的节点
     *
     * haspMap.get(cur).next = hashMap.get(cur.next);
     * hashMap.get(cur).rand = hashMap.get(cur.rand);
     *
     * 时间复杂度是O(N)，因为使用了HashMap，所以空间复杂度是O(N)
     * */

    public static ListNode violence (ListNode head) {
        if (head == null) {
            return head;
        }
        HashMap<ListNode, ListNode> hashMap = new HashMap<>();
        ListNode cur = head;
        while (cur != null) {
            hashMap.put(cur, new ListNode(cur.val));
            cur = cur.next;
        }
        cur = head;
        while (cur != null) {
            hashMap.get(cur).next = hashMap.get(cur.next);
            hashMap.get(cur).rand = hashMap.get(cur.rand);
            cur = cur.next;
        }
        return hashMap.get(head);
    }

    /***
     * 使用一个更好的方法，使得空间复杂度为O(1)，时间复杂度为O(N)
     * @param head 被拷贝的链表头部
     * @return 拷贝后的链表头部
     */
    public static ListNode better (ListNode head) {
        if (head == null) {
            return head;
        }
        // 首先，把每个节点复制出来的节点都放在自己后面
        ListNode node = null;
        ListNode cur = head;
        ListNode temp = null;
        while (cur != null) {
            node = new ListNode(cur.val);
            temp = node;
            temp.next = cur.next;
            cur.next = temp;
            cur = cur.next.next;
        }

        ListNode res = head.next;
        ListNode p1 = head;
        ListNode p2 = p1.next;
        while (p2.next != null) {
            p1.next = p2.next;
            if (p1.rand != null) {
                p2.rand = p1.rand.next;
            } else {
                p2.rand = null;
            }

            if (p2.next != null) {
                p2.next = p2.next.next;
            } else {
                break;
            }

            p1 = p1.next;    // 这里让p1指向p1.next就行了，因为上面p1.next已经动了
            p2 = p2.next;    // 同理
        }
        return res;
    }

    public static ListNode createLinkedList() {
        ListNode head = new ListNode(1);
        ListNode node1 = new ListNode(2);
        ListNode node2 = new ListNode(3);
        ListNode node3 = new ListNode(2);
        ListNode node4 = new ListNode(1);
        ListNode node5 = new ListNode(5);
        head.next = node1;
        head.rand = node3;
        node1.next = node2;
        node1.rand = node5;
        node2.next = node3;
        node2.rand = node4;
        node3.next = node4;
        node3.rand = node1;
        node4.next = node5;
        return head;
    }

    public static void printList (ListNode head) {
        if (head == null) {
            System.out.println("链表是空的");
        }
        while (head != null) {
            System.out.println(head.val);
            head = head.next;
        }
    }


    public static void main(String[] args) {
        ListNode head = createLinkedList();
//        ListNode res = violence(head);
//        while (res != null) {
//            System.out.println("我是" + res + ",我的值是" + res.val + "，我的随机节点是" + res.rand
//             + "，我的下一个节点是" + res.next);
//            res = res.next;
//        }
        ListNode res = better(head);
        printList(res);
    }
}

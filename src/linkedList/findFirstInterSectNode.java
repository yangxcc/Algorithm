package linkedList;

import java.util.List;

/**
 * 给了两个单链表，没有说明这两个单链表是否有环，如果两个单链表有公共部分，那么返回公共部分的第一个
 * 没有公共部分返回 null
 * 说明：这里的相交指的是某个节点在内存中的地址是相同的
 */
public class findFirstInterSectNode {

    /**
     * 先看看链表是否存在环
     * 返回入环节点，没有返回null
     *
     * 使用快慢指针
     */
    public static ListNode getLoopNode (ListNode head) {
        if (head == null || head.next == null || head.next.next == null) {
            return null;    // 链表中少于三个节点不可能成环
        }

        ListNode fast = head.next.next;
        ListNode slow = head.next;
        while (fast != slow) {
            if (fast.next == null || fast.next.next == null) {   // 没环，走到头了
                return null;
            }
            fast = fast.next.next;
            slow = slow.next;
        }
        fast = head;  // 通过上面的循环，fast和slow相遇了，说明有环，那么保持slow不动，fast回到head头节点位置处
        // 之后，fast和slow都走一步，他们一定会在环的入口处相遇，这是一个结论！！
        while (fast != slow) {
            fast = fast.next;
            slow = slow.next;
        }
        return fast;
    }

    // 第一种情况，两个链表都没有环，返回第一个相交的节点，如果没有返回null

    /**
     *
     *
     *         \
     *          \
     *           \      /
     *            \    /
     *             \  /
     *              \/         两个无环单链表相交的情况，在某一点相交之后一定会公用一段区域
     *              |          所以，两个链表如果相交的话，最后的一个节点地址一定是相同的
     *             |           因此，我们可以通过判断最后一个节点是否相同，如果不同，肯定是没有
     *            |            相交，如果相同，可以先走完长链表的差值部分，因为第一个公共节点肯定在
     *                         短链表和长连接相同长度之后的部分产生的，因为他们两个的终点是相同的
     * @param head1   第一个链表
     * @param head2   第二个链表
     * @return        第一个相交的节点或者null
     */
    public static ListNode noLoop (ListNode head1, ListNode head2) {
        if (head1 == null || head2 == null) {
            return null;
        }
        // 先找出长链表和短链表
        ListNode cur1 = head1;
        ListNode cur2 = head2;
        int n = 0;
        while (cur1.next != null) {    // 让cur1停留在最后一个节点上，好比较
            n++;
            cur1 = cur1.next;
        }
        while (cur2.next != null) {
            n--;
            cur2 = cur2.next;
        }
        if (cur1 != cur2) {         // 最后节点不相同，表明肯定不存在公共部分
            return null;
        }
        cur1 = n > 0 ? head1 : head2;          // 让cur1指向长的链表
        cur2 = cur1 == head1 ? head2 : head1;  // cur2指向短的链表
        n = Math.abs(n);
        while (n != 0) {
            cur1 = cur1.next;   // 走长短链表的差值，直到他们两个一样长了
            n--;
        }
        while (cur1 != cur2) {
            cur1 = cur1.next;
            cur2 = cur2.next;
        }
        return cur1;
    }


    /***
     *
     * 如果两个单链表都有环，那么他可能有三种情况
     *
     * \
     *  \ ___
     *   \  /
     *    \/             \         这种情况，两个单链表是分开的两个圈，没有公共部分
     *                    \ ___
     *                     \  /
     *                      \/
     *
     *
     *
     *
     *       第二种情况，两个单链表在同一个位置入环，见solve.md文件中
     *       第三种情况，两个单链表在不同位置入环，见solve.md文件
     *
     * @param head1  第一个链表头
     * @param loop1  第一个链表的入环位置
     * @param head2  第二个链表头
     * @param loop2  第二个链表的入环位置
     * @return       公共部分的第一个节点，没有返回null
     */
    public static ListNode bothLoop (ListNode head1, ListNode loop1, ListNode head2, ListNode loop2) {
        ListNode cur1 = null;
        ListNode cur2 = null;
        if (loop1 == loop2) {       // 情况二，两个单链表入环位置相同
            // 把入环节点看成单链表相交的最后一个节点
            cur1 = head1;
            cur2 = head2;
            int n = 0;
            while (cur1.next != loop1) {
                n++;
                cur1 = cur1.next;
            }
            while (cur2.next != loop2) {   // loop1 == loop2
                n--;
                cur2 = cur2.next;
            }

            cur1 = n > 0 ? head1 : head2;
            cur2 = cur1 == head1 ? head2 : head1;
            n = Math.abs(n);

            while (n != 0) {
                cur1 = cur1.next;
                n--;
            }

            while (cur1 != cur2) {
                cur1 = cur1.next;
                cur2 = cur2.next;
            }
            return cur1;

        } else {
            // 去遍历这个圈，一定能够找到最先开始相交的点
            cur1 = loop1.next;
            while (cur1 != loop1) {   // 一圈之内一定能够找到
                if (cur1 == loop2) {
                    return cur1;
                }
                cur1 = cur1.next;
            }
            return null;
        }
    }


    public static ListNode getIntersectNode (ListNode head1, ListNode head2) {
        if (head1 == null || head2 == null) {
            return null;
        }
        ListNode loop1 = getLoopNode(head1);
        ListNode loop2 = getLoopNode(head2);

        // 两个链表都没有环
        if (loop1 == null && loop2 == null) {
            return noLoop(head1,head2);
        }
        // 两个链表都有环
        if (loop1 != null && loop2 != null) {
            return bothLoop(head1,loop1,head2,loop2);
        }
        // 如果一个链表有环，一个链表没环，那么肯定不可能有相交的地方，因为是单链表，一个节点只能够有一个next指向
        return null;
    }


    public static void main(String[] args) {
        // 1->2->3->4->5->6->7->null
        ListNode head1 = new ListNode(1);
        head1.next = new ListNode(2);
        head1.next.next = new ListNode(3);
        head1.next.next.next = new ListNode(4);
        head1.next.next.next.next = new ListNode(5);
        head1.next.next.next.next.next = new ListNode(6);
        head1.next.next.next.next.next.next = new ListNode(7);

        // 0->9->8->6->7->null
        ListNode head2 = new ListNode(0);
        head2.next = new ListNode(9);
        head2.next.next = new ListNode(8);
        head2.next.next.next = head1.next.next.next.next.next; // 8->6
        System.out.println(getIntersectNode(head1, head2).val);

        // 1->2->3->4->5->6->7->4...
        head1 = new ListNode(1);
        head1.next = new ListNode(2);
        head1.next.next = new ListNode(3);
        head1.next.next.next = new ListNode(4);
        head1.next.next.next.next = new ListNode(5);
        head1.next.next.next.next.next = new ListNode(6);
        head1.next.next.next.next.next.next = new ListNode(7);
        head1.next.next.next.next.next.next = head1.next.next.next; // 7->4

        // 0->9->8->2...
        head2 = new ListNode(0);
        head2.next = new ListNode(9);
        head2.next.next = new ListNode(8);
        head2.next.next.next = head1.next; // 8->2
        System.out.println(getIntersectNode(head1, head2).val);

        // 0->9->8->6->4->5->6..
        head2 = new ListNode(0);
        head2.next = new ListNode(9);
        head2.next.next = new ListNode(8);
        head2.next.next.next = head1.next.next.next.next.next; // 8->6
        System.out.println(getIntersectNode(head1, head2).val);
    }
}

package linkedList;

import static sort.QuickSort.swap;

/**
 * 给定一个单向链表的头节点head，节点的值类型是整型，再给定一个整数pivot。实现一个调整链表的函数，
 * 将链表调整为左部分都是值小于pivot的节点，中间部分都是值等于pivot的节点，右部分都是值大于pivot的节点。
 * 除这个要求外，对调整后的节点顺序没有更多的要求。
 *
 * 进阶
 * 1. 要求调整后结点的顺序仍然能够保持调整之前节点的相对顺序
 * 2. 要求时间复杂度为O(N)，空间复杂度为O(1)
 *
 */
public class linkedListPartition {

    /***
     * 将ListNode中的节点转化成数组，从数组中进行Partition，空间复杂度O(N)，时间复杂度O(N)
     * @param head 初始链表
     * @param num  基准数据
     * @return 调整后的链表头节点
     */
    public static ListNode simple(ListNode head, int num) {
        if (head == null) {
            return head;
        }

        int length = 0;   // 记录数组的长度
        ListNode cur = head;
        while (cur != null) {
            length++;
            cur = cur.next;
        }

        int[] arr = new int[length];
        cur = head;   // 让cur恢复位置
        for (int i = 0; i < length && cur != null; i++) {
            arr[i] = cur.val;
            cur = cur.next;
        }

        partition (arr,0,arr.length - 1, num);

        ListNode res = new ListNode(arr[0]);   // 头节点
        ListNode temp = res;   // 这个指针用于指向反转后的链表
        for (int j = 1; j < arr.length; j++) {
            ListNode node = new ListNode(arr[j]);
            temp.next = node;
            temp = temp.next;
        }
        return res;
    }

    /**
     * partition之后让链表表示有序，而且不使用额外空间
     * 使用六个指针变量，分别表示大于区域的起点终点，小于区域的起点终点，等于区域的起点终点
     * */
    public static ListNode better(ListNode head, int num) {
        if (head == null) {
            return head;
        }
        ListNode Es = null;  // Equals start
        ListNode Ed = null;  // Equals end
        ListNode Bs = null;  // Bigger start
        ListNode Be = null;  // Bigger end
        ListNode Ls = null;  // Little start
        ListNode Le = null;  // Little end

        while (head != null) {
            if (head.val < num) {
                if (Ls == null) {
                    Ls = head;
                    Le = head;
                } else {
                    Le.next = head;
                    Le = head;
                }
            } else if (head.val == num) {
                if (Es == null) {
                    Es = head;
                    Ed = head;
                } else {
                    Ed.next = head;
                    Ed = head;
                }
            } else {
                if (Bs == null) {
                    Bs = head;
                    Be = head;
                } else {
                    Be.next = head;
                    Be = head;
                }
            }
            head = head.next;
        }

        // 经过上面的过程之后，大于小于等于的区域已经划分开了
        // 我们需要使用小于区域的尾巴连接等于区域的头，等于区域的尾巴连接大于区域的头
        // 但是需要注意的一点是，如果没有小于区域或者没有大于区域或者没有等于区域怎么办？
        if (Ls != null) {   // 如果有小于区域
            Le.next = Es;
            Ed = Ed == null ? Ls : Ed;   // 谁去连接大于区域头部谁就是这个Ed
            // 这里的Ed==null表示的就是是否有等于区域，
        }

        // 如果上面的代码没有执行，也就是没有小于区域
        // 但是有等于区域，那么就连接等于区域和大于区域
        if (Ed != null) {
            Ed.next = Bs;
        }

        return Ls != null ? Ls : (Es != null ? Es : Bs);
    }

    public static void partition(int[] arr, int L, int R, int num) {
        int left = L - 1;   // < 区域的右边界
        int right = R + 1;      // > 区域的左边界
        while (L < right) {       // L表示的是当前数据
            if (arr[L] < num) {
                // 当前数据和小于区域的下一个交换，当前数据和小于区域都移动下一位
                swap(arr,++left,L++);
            } else if (arr[L] > num) {
                // 当前数据和大于区域的前一个交换，当前数据不动，大于区域前移
                swap(arr,--right,L);
            } else {
                // 等于的时候直接跳下一个
                L++;
            }
        }
    }

    public static ListNode createLinkedList() {
        ListNode head = new ListNode(1);
        ListNode node1 = new ListNode(2);
        ListNode node2 = new ListNode(3);
        ListNode node3 = new ListNode(2);
        ListNode node4 = new ListNode(1);
        ListNode node5 = new ListNode(5);
        head.next = node1;
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;
        return head;
    }

    public static void main(String[] args) {
        ListNode head = createLinkedList();
        ListNode res = simple(head,2);
        while (res != null) {
            System.out.println(res.val);
            res = res.next;
        }
        System.out.println("========================");
        ListNode head1 = createLinkedList();
        ListNode res1 = better(head1,2);
        while (res1 != null) {
            System.out.println(res1.val);
            res1 = res1.next;
        }
    }
}

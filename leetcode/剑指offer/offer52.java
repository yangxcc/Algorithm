package 剑指offer;

import java.util.List;

/**
 * 两个链表的公共节点
 *
 * 这里面需要注意的一个点，不管是使用hashmap还是直接判断相等，
 * 可以直接使用nodeA == nodeB，这就是判断两个节点的地址是否相等
 * */
public class offer52 {
    // 第一种方法：将两个链表从等长的位置开始判断
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) {
            return null;
        }
        int ALength = 0;
        int BLength = 0;
        ListNode nodeA = headA;
        ListNode nodeB = headB;

        while (nodeA != null) {
            ALength++;
            nodeA = nodeA.next;
        }
        while (nodeB != null) {
            ALength++;
            nodeB = nodeB.next;
        }

        nodeA = headA;
        nodeB = headB;
        // 看看A链和B链谁更长
        if (ALength > BLength) {
            while (ALength != BLength) {
                nodeA = nodeA.next;
                ALength--;
            }
        }
        if (ALength < BLength) {
            while (ALength != BLength) {
                nodeB = nodeB.next;
                BLength--;
            }
        }
        // 经过上面的变化，nodeA和nodeB现在在同一个起跑线上了
        while (nodeA != null) {
            if (nodeA == nodeB) {
                return nodeA;
            }
            nodeA = nodeA.next;
            nodeB = nodeB.next;
        }

        return null;
    }

    public ListNode getIntersectionNode2(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) {
            return null;
        }
        // 假设A链和B链公共部分的长度为c
        // A链非公共部分的长度为a，B链非公共部分的长度为b
        // 如果A链和B链有公共部分的话，那么a + c + b == b + c + a
        // 上面的意思是A链走完自己，然后再走B链的非公共部分一定等于
        // B链走完自己，然后再走A链的非公共部分
        ListNode n1 = headA;
        ListNode n2 = headB;
        while (n1 != n2) {
            n1 = n1 == null ? headB : n1.next;
            n2 = n2 == null ? headA : n2.next;
        }
        return n1;
        // 如果两个链没有公共部分，那么也会走到都是null的时候因为相等而跳出循环，不会有死循环
    }

}

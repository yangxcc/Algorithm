package StructureAndTrick.morris;

import binaryTree.Node;

/**
 * Morris遍历的作用就是在时间复杂度为O(N)，空间复杂度为O(1)的情况下进行遍历二叉树
 * 很明显，Morris遍历的空间复杂度很低，因为如果使用递归的话，会使用递归栈
 * 如果不使用递归的话，那么就需要使用Stack这种额外空间，都不可能达到O(1)的空间复杂度
 * */
public class Morris {

    /**
     * Morris遍历的思想就是利用的是树的叶节点左右孩子为空（树的大量空闲指针），实现空间开销的极限缩减。
     *
     * Morris遍历的流程
     *  如果cur无左孩子，cur向右移动（cur=cur.right）
     *  如果cur有左孩子，找到cur左子树上最右的节点，记为mostright
     *        如果mostright的right指针指向空，让其指向cur，cur向左移动（cur=cur.left）
     *        如果mostright的right指针指向cur，让其指向空，cur向右移动（cur=cur.right）
     *
     * morris遍历的实质是建立起一种机制，对于没有左子树的节点只到达一次，对于有左子树的节点会到达两次
     *
     * */

    public static void morris(Node head) {
        if (head == null) {
            return;
        }
        Node cur = head;
        Node mostRight = null;   // 代表cur节点左子树上的最右节点
        while (cur != null) {
            mostRight = cur.left;
            if (mostRight != null) {   // cur有左子树
                while (mostRight.right != null && mostRight.right != cur) {
                    mostRight = mostRight.right;
                }
                if (mostRight.right == null) {
                    mostRight.right = cur;
                    cur = cur.left;
                    continue;
                } else {  // mostRight.right == cur
                    mostRight.right = null;  // 把mostRight的指向改回来
                }
            }
            cur = cur.right;
        }
    }


    // 使用morris遍历实现先序遍历
    /**
     * 如果一个节点只能到达两次，第一次打印
     * 如果一个节点只能到达一次，那么打一次打印*/
    public static void morrisPre(Node head) {
        if (head == null) {
            return;
        }
        Node cur = head;
        Node mostRight = null;
        while (cur != null) {
            mostRight = cur.left;
            if (mostRight != null) {  // 有左子树
                while (mostRight.right != null && mostRight.right != cur) {
                    mostRight = mostRight.right;
                }
                if (mostRight.right == null) {
                    System.out.println(cur.val);   // 第一次出现的时候打印
                    mostRight.right = cur;
                    cur = cur.left;
                    continue;
                } else {
                    mostRight.right = null;
                }
            } else {   // 没有左子树的情况，也就是说只能出现1次，直接打印
                System.out.println(cur.val);
            }
            cur = cur.right;
        }
    }


    // 使用morris遍历实现中序
    /**
     * 只出现一次的节点，直接打印
     * 能够出现两次的节点，第二次打印
     * */
    public static void morrisIn(Node root) {
        if (root == null) {
            return;
        }
        Node cur = root;
        Node mostRight = null;
        while (cur != null) {
            mostRight = cur.left;
            if (mostRight != null) {   // 有左子树
                while (mostRight.right != null && mostRight.right != cur) {
                    mostRight = mostRight.right;
                }
                if (mostRight.right == null) {   // 第一次
                    mostRight.right = cur;
                    cur = cur.left;
                    continue;
                } else {
                    System.out.println(cur.val);
                    mostRight.right = null;
                }
            } else {
                System.out.println(cur.val);
            }
            cur = cur.right;
        }
    }

    // 后序遍历
    /**
     * 只有当碰到能够二次返回自己的节点时，才逆序打印自己 左树的右边界
     * 最后，单独逆序打印整棵树 的右边界
     * */
    public static void morrisPost(Node root) {
        if (root == null) {
            return;
        }
        Node cur = root;
        Node mostRight = null;
        while (cur != null) {
            mostRight = cur.left;
            if (mostRight != null) {
                while (mostRight.right != null && mostRight.right != cur) {
                    mostRight = mostRight.right;
                }
                if (mostRight.right == null) {
                    mostRight.right = cur;
                    cur = cur.left;
                    continue;
                } else {
                    mostRight.right = null;
                    printRightEdge(cur.left);   // 打印第二次 左树的右边界
                }
            }
            cur = cur.right;
        }
        printRightEdge(root);    // 单独打印整棵树的右边界
    }

    // 以X为头的树，逆序打印它的右边界
    public static void printRightEdge(Node X) {
        Node tail = reverseRightEdge(X);  // 先把右边界逆序
        Node cur = tail;
        while (cur != null) {
            System.out.println(cur.val);
            cur = cur.right;
        }
        reverseRightEdge(tail);     // 再把右边界给逆序回来
    }

    public static Node reverseRightEdge(Node X) {
        Node pre = null;
        Node next = null;
        while (X != null) {
            next = X.right;
            X.right = pre;
            pre = X;
            X = next;
        }
        return pre;
    }


    public static Node createBT () {
        Node head = new Node(1);
        Node node1 = new Node(2);
        Node node2 = new Node(3);
        Node node3 = new Node(4);
        Node node4 = new Node(5);
        Node node5 = new Node(6);
        Node node6 = new Node(7);
        head.left = node1;
        head.right = node2;
        node1.left = node3;
        node1.right = node4;
        node2.left = node5;
        node2.right = node6;
        return head;
    }

    public static void main(String[] args) {
        Node root = createBT();
        morrisPre(root);
        System.out.println("======");
        morrisIn(root);
        System.out.println("=======");
        morrisPost(root);
    }
}

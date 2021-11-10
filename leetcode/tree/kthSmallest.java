package tree;

import java.util.Stack;

/**
 * leetcode 230. 二叉搜索树中第K小的元素 middle
 *
 * 从1开始计数
 *
 * 其实看到这个题，最笨的方法就是中序遍历，遍历到k就停止
 * */
public class kthSmallest {

    /**
     * 下面这种写法不行呀，因为k是一个局部变量，每一个在每一层的递归中都有一个k，都是k的初始值，k根本传递不过去
     */

//    public static int kthSmallestAlgorithm(Node root, int k) {
//        if (root == null) {
//            return -1;
//        }
//        int value = kthSmallestAlgorithm(root.left, k);
//        if (value != -1) {
//            // 左树里面找到了
//            return value;
//        }
//        k--;   // 看看根节点是不是
//        return k == 0 ? root.value : kthSmallestAlgorithm(root.right, k);
//    }


    static int kTH = 0;
    public static int kthSmallestAlgorithm2(Node root, int k) {
        if (root == null) {
            return -1;
        }
        kTH = k;
        return process(root);
    }

    public static int process(Node root) {
        if (root == null) {
            return -1;
        }
        int value = process(root.left);
        if (value != -1) {
            return value;
        }
        kTH--;
        return kTH == 0 ? root.value : process(root.right);
    }

    public static Node createBSTree() {
        Node root = new Node(5);
        Node node1 = new Node(3);
        Node node2 = new Node(6);
        Node node3 = new Node(2);
        Node node4 = new Node(4);
        Node node5 = new Node(1);

        root.left = node1;
        root.right = node2;
        node1.left = node3;
        node1.right = node4;
        node3.left = node5;

        return root;
    }



    public static int violence(Node root, int k) {
        if (root == null) {
            return -1;
        }
        Stack<Integer> stack = new Stack<>();
        inOrder(root, stack, k);
        return stack.peek();
    }

    public static void inOrder(Node root, Stack<Integer> stack, int k) {
        if (root == null) {
            return ;
        }
        inOrder(root.left, stack, k);

        if (stack.size() == k) {
            return;
        }
        stack.add(root.value);    // 一定要写在后面！！

        inOrder(root.right, stack, k);
    }
    public static void main(String[] args) {
        Node root = createBSTree();
        System.out.println(kthSmallestAlgorithm2(root, 3));
        System.out.println(violence(root, 3));
    }
}

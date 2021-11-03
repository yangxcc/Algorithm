package tree;


/**
 * leetcode 116. 填充每个节点的下一个右侧节点指针 middle
 *
 * 给定一个 完美二叉树 ，其所有叶子节点都在同一层，每个父节点都有两个子节点。二叉树定义如下：
 *
 * struct Node {
 *   int val;
 *   Node *left;
 *   Node *right;
 *   Node *next;
 * }
 * 填充它的每个 next 指针，让这个指针指向其下一个右侧节点。如果找不到下一个右侧节点，则将 next 指针设置为 NULL。
 *
 * 你只能使用常量级额外空间。
 * 使用递归解题也符合要求，本题中递归程序占用的栈空间不算做额外的空间复杂度。
 * */
public class paddingPointer {
    public Node connect(Node root) {
        if (root == null) {
            return null;
        }
        return process(root);
    }

    public Node process(Node node) {
        // 叶子节点，只有一个节点，其实node.left == null的时候，node.right一定也是null，因为题目中给的是完美二叉树
        if (node.left == null && node.right == null) {
            return node;
        }
        node.left.next = node.right;
        if (node.next != null) {
            node.right.next = node.next.left;
        }
        process(node.left);
        process(node.right);
        return node;
    }
}

class Node {
    Node left;
    Node right;
    Node next;
    int value;
    public Node(int val) {
        this.value = val;
    }
}

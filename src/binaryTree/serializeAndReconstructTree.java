package binaryTree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 二叉树的序列化   将二叉树转换成字符串
 * 遍历方式可以是前序、后序、按层等，不能是中序，因为中序遍历的两棵树的结果可能是一样的
 * * 比如如下两棵树
 * *         __2
 * *        /
 * *       1
 * *       和
 * *       1__
 * *          \
 * *           2
 * * 补足空位置的中序遍历结果都是{ null, 1, null, 2, null}
 * <p>
 * <p>
 * 二叉树的反序列化就是按照和序列化相同的遍历方式，将字符串转换成二叉树
 */
public class serializeAndReconstructTree {


    public static String preSerialize(Node head) {
        if (head == null) {
            return "#_";       // 空节点使用 # 来代替，_代表一个位置的结束
        }
        String str = head.val + "_";
        str += preSerialize(head.left);
        str += preSerialize(head.right);
        return str;
    }

    public static Node reSerialize (String str) {
        if (str == null) {
            return null;
        }
        String[] s = str.split("_");
        Queue<String> queue = new LinkedList<>();

        for (int i = 0; i < s.length; i++) {
            queue.add(s[i]);
        }
        return reSerializeProcess(queue);
    }

    public static Node reSerializeProcess(Queue<String> queue) {
        String value = queue.poll();
        if (value.equals("#")) {
            return null;
        }
        Node head = new Node(Integer.valueOf(value));
        head.left = reSerializeProcess(queue);
        head.right = reSerializeProcess(queue);
        return head;
    }


    public static Node createBT() {
        Node head = new Node(1);
        Node node1 = new Node(2);
        Node node2 = new Node(3);
        head.left = node1;
        node1.right = node2;
        return head;
    }

    public static void main(String[] args) {
        Node head = createBT();
        System.out.println(preSerialize(head));
        String str = preSerialize(head);
        Node node = reSerialize(str);
        System.out.println(node.left.val);
    }
}

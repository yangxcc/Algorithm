package tree;

import binaryTree.Node;

import java.util.*;


/**
 * 对于这道题的层序遍历，就是让层序遍历的结构倒着数出来，第一层最后输出，第二层倒数第二输出...
 *
 *
 * 其实最开始看到这道题我的想法很简单，就是在加个栈，利用栈先进后出
 * 但是这样又增加了O(N)的额外空间复杂度
 *
 * 其实可以发现，我最开始的代码是把res给实例化成了一个ArrayList，如果我把它给实例化成LinkedList，那么他就是一个双端队列
 * 我直接利用addFirst方法就搞定了，把每一层的结果都给加到队头就好了，就不用再用栈来倒换数据了...
 * */
public class levelOrder2 {
    public List<List<Integer>> levelOrderBottom(Node root) {
        if (root == null) {
            return new ArrayList<>();
        }
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        List<List<Integer>> res = new ArrayList<>();
//        LinkedList<List<Integer>> res1 = new LinkedList<>();
        // 这里一定要直接定义LinkedList，不然没有addFirst方法
        Stack<List<Integer>> stack = new Stack<>();
        while (!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> temp = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                Node node = queue.poll();
                temp.add(node.val);
                if (node.left != null) {
                    queue.add(node.left);
                }
                if (node.right != null) {
                    queue.add(node.right);
                }
            }
            stack.add(temp);
//            res1.addFirst(temp);

        }
        while (!stack.isEmpty()) {
            res.add(stack.pop());
        }
        return res;
        // return res1;
    }
}

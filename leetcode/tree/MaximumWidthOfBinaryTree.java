package tree;


import java.util.LinkedList;
import java.util.Queue;

/**
 * 给定一个二叉树，编写一个函数来获取这个树的最大宽度。树的宽度是所有层中的最大宽度。
 * 这个二叉树与满二叉树（full binary tree）结构相同，但一些节点为空。
 *
 * 每一层的宽度被定义为两个端点（该层最左和最右的非空节点，两端点间的null节点也计入长度）之间的长度
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/maximum-width-of-binary-tree
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */

// 层序遍历的升级版，因为同一层两个节点之间的空结点也算宽度，所以需要用到其他的结构来记录节点之间的距离
// 如果将满二叉树映射成数组结构，假如根节点的下标为1，那么对于任意一个节点i，其左子节点2*i，右子节点2*i+1
public class MaximumWidthOfBinaryTree {
    public int widthOfBinaryTree(Node root) {
        if (root == null) {
            return 0;
        }
        Queue<Node> queue = new LinkedList<>();
        // 用来记录索引,这里使用双向链表的原因是因为她能够方便的使用removeFirst,getLast等方法
        LinkedList<Integer> indexList = new LinkedList<>();
        queue.add(root);
        indexList.add(1);   // 根节点的下标记录成1

        int maxWidth = 1;

        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                Node node = queue.poll();
                // 从indexList中得到node的index
//                int index = indexList.getFirst();
                int index = indexList.removeFirst(); // 还要删除这个index，因为他是属于上一层的了
                if (node.left != null) {
                    queue.add(node.left);
                    indexList.add(2 * index);
                }
                if (node.right != null) {
                    queue.add(node.right);
                    indexList.add(2 * index + 1);
                }
            }
            if (indexList.size() >= 2) {
                // 链表最后的下标肯定是这一层最右的节点
                maxWidth = Math.max(maxWidth, indexList.getLast() - indexList.getFirst() + 1);
            }
        }
        return maxWidth;
    }
}

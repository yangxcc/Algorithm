package 错题集;


import java.util.LinkedList;
import java.util.Queue;

/**
 * 二叉树的最大宽度
 * <p>
 * 给定一个二叉树，编写一个函数来获取这个树的最大宽度。
 * 树的宽度是所有层中的最大宽度。这个二叉树与满二叉树（full binary tree）结构相同，但一些节点为空。
 * <p>
 * 每一层的宽度被定义为两个端点（该层最左和最右的非空节点，两端点间的null节点也计入长度）之间的长度。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/maximum-width-of-binary-tree
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class MaximumWidthOfBinaryTree {
    public int widthOfBinaryTree(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int maxWidth = 1;

        Queue<TreeNode> q = new LinkedList<>();
        // 这里需要使用一个链表来记录每一层的最前一个和最后一个的索引位置
        // 对于i位置的元素，它的左叶子的索引是2i+1，右叶子的索引是2i+2
        LinkedList<Integer> indexArr = new LinkedList<>();

        q.add(root);
        indexArr.addLast(1);

        while (!q.isEmpty()) {
            int size = q.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = q.poll();
                int index = indexArr.removeFirst();

                if (node.left != null) {
                    indexArr.addLast(2 * index);
                    q.add(node.left);
                }

                if (node.right != null) {
                    indexArr.add(2 * index + 1);
                    q.add(node.right);
                }
            }

            if (indexArr.size() >= 2) {
                maxWidth = Math.max(maxWidth, indexArr.getLast() - indexArr.getFirst());
            }
        }
        return maxWidth;
    }
}



class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    public TreeNode() {
    }

    TreeNode(int val) {
        this.val = val;
    }

    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}

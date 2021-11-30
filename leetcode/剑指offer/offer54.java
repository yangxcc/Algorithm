package 剑指offer;

/**
 * 搜索二叉树中第K大节点
 * */
public class offer54 {
    // 递归还是有点不太行，这些边界条件
    // 要不要用全局变量这种问题，还是看天
    int count = 0, ans = 0;
    // 这个题我就不清楚为什么不同count这个全局变量就不行
    public int kthLargest(TreeNode root, int k) {
        inOrder(root, k);
        return ans;
    }

    public void inOrder(TreeNode root, int k) {
        if (root == null) {
            return;
        }
        // 因为这个是第K大，而中序遍历BST得到的是升序的数组
        // 为了得到降序的，我们需要把顺序给调一下
        inOrder(root.right, k);
        count++;
        if (count == k) {
            ans = root.val;
        }
        inOrder(root.left, k);
    }
}

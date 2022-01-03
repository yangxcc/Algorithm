package dp.打家劫舍;


import java.util.HashMap;

/**
 * 在上次打劫完一条街道之后和一圈房屋后，小偷又发现了一个新的可行窃的地区。
 * 这个地区只有一个入口，我们称之为“根”。 除了“根”之外，每栋房子有且只有一个“父“房子与之相连。
 * 一番侦察之后，聪明的小偷意识到“这个地方的所有房屋的排列类似于一棵二叉树”。
 * 如果两个直接相连的房子在同一天晚上被打劫，房屋将自动报警。
 *
 * 输入: [3,2,3,null,3,null,1]
 *
 *      3
 *     / \
 *    2   3
 *     \   \
 *      3   1
 *
 * 输出: 7
 * 解释: 小偷一晚能够盗取的最高金额 = 3 + 3 + 1 = 7.
 *
 */
public class HouseRobber_III {
    public int rob(TreeNode root) {
        memo = new HashMap<>();
        return dfs(root);
    }

    HashMap<TreeNode, Integer> memo;

    public int dfs(TreeNode root) {
        if (root == null) {
            return 0;
        }
        // 只有一个节点，那么就偷这个节点
        if (root.left == null && root.right == null) {
            return root.val;
        }

        if (memo.containsKey(root)) {
            return memo.get(root);
        }
        // 在root这个节点上，小偷有两种选择，一种是偷他，另一种是不偷
        int rob = root.val;
        if (root.left != null) {
            rob += dfs(root.left.left) + dfs(root.left.right);
        }
        if (root.right != null) {
            rob += dfs(root.right.left) + dfs(root.right.right);
        }

        int not_rob = dfs(root.left) + dfs(root.right);

        memo.put(root, Math.max(rob, not_rob));

        return Math.max(rob, not_rob);
    }

    public int[] postOrder(TreeNode root) {
        if (root == null) {
            return new int[]{0, 0};
        }
        // 使用后序遍历能够更快
        // 因为使用后序遍历相当于自底向上，没有重叠子问题
        // postOrder返回一个数组，res[0]表示不抢这个房子能够获得的最大值,res[1]表示抢这个房子能够获得的最大值
        int[] robInLeft = postOrder(root.left);
        int[] robInRight = postOrder(root.right);

        // 我们要做的就是返回抢root和不抢root之间的最大值
        int rob = robInLeft[0] + robInRight[0] + root.val;
        int not_rob = Math.max(robInLeft[0],robInLeft[1]) + Math.max(robInRight[0], robInRight[1]);

        return new int[]{not_rob, rob};
    }
}

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    public TreeNode(int x) {
        this.val = x;
    }
}
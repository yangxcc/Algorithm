package 剑指offer;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 二叉树中和为某一值的路径
 * */
public class offer34 {
    public List<List<Integer>> pathSum(TreeNode root, int target) {
        if (root == null) return new ArrayList<>();

        List<List<Integer>> res = new ArrayList<>();
        List<Integer> temp = new ArrayList<>();

        process(root, target, temp, res);

        return res;
    }

    public void process(TreeNode root, int target, List<Integer> temp, List<List<Integer>> res) {
        if (root == null) {
            return ;
        }

        temp.add(root.val);
        target -= root.val;

        if (target == 0 && root.left == null && root.right == null) {
            res.add(temp);
//            return ;   // 这里不能有return，因为如果在这里return了，就没法执行后面的remove了
        }

        process(root.left, target, temp, res);
        process(root.right, target, temp, res);

        temp.remove(temp.size() - 1);  // 一定不要忘了把新加入的这个点给踢出去
    }
}

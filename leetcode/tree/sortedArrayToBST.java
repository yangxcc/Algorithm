package tree;

import binaryTree.Node;

/**
 * leetcode  108. 将有序数组转换为二叉搜索树 simple
 * <p>
 * 因为他是有序数组，所以直接把中间的数弄出来，做root就好了
 */
public class sortedArrayToBST {

    public Node sortedArrayToBSTAlgorithm(int[] nums) {
        if (nums.length < 1 || nums == null) {
            return null;
        }
        int left = 0;
        int right = nums.length - 1;
        // int mid = (left + right) >> 1;
        return process(nums, left, right);
    }

    public Node process(int[] nums, int left, int right) {
        if (left > right) {
            return null;
        }
        int mid = (left + right) >> 1;
        Node root = new Node(nums[mid]);
        root.left = process(nums, left, mid - 1);
        root.right = process(nums, mid + 1, right);
        return root;
    }
}

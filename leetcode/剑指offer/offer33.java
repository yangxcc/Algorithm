package 剑指offer;

/**
 * 二叉搜索树的后序遍历序列
 */
public class offer33 {
    public boolean verifyPostOrder(int[] postorder) {
        if (postorder.length == 0) return true;
        return process(postorder, 0, postorder.length - 1);
    }

    public boolean process(int[] postOrder, int start, int end) {
        if (start >= end) return true;
        int rootVal = postOrder[end];
        int index = start;
//        for (int i = start; i < end; i++) {
//            if (postOrder[i] > rootVal) {
//                index = i;
//                break;
//            }
//        }
        // 不清楚为什么上面这么写有测试用例过不去
        while (postOrder[index] < rootVal && index < end) {
            index++;
        }
//        while (index < end) {
//            if (postOrder[index] < rootVal) {
//                return false;
//            }
//            index++;   // 不能这么写，这么写下面的index就变了
//        }

        for (int i = index; i < end; i++) {
            if (postOrder[i] < rootVal) {
                return false;
            }
        }

        return process(postOrder, start, index - 1) &&
                process(postOrder, index, end - 1);
    }
}

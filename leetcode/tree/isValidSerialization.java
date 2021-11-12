package tree;

/**
 * leetcode 331 验证二叉树的前序序列化  middle
 *
 * 序列化二叉树的一种方法是使用前序遍历。当我们遇到一个非空节点时，我们可以记录下这个节点的值。
 * 如果它是一个空节点，我们可以使用一个标记值记录，例如 #。
 *
 *      _9_
 *     /   \
 *    3     2
 *   / \   / \
 *  4   1  #  6
 * / \ / \   / \
 * # # # #   # #
 *
 *
 * 验证一个字符串是不是前序遍历的顺序
 * */
public class isValidSerialization {

    /**
     * 可以发现，在上面所示的一棵二叉树中，可以把"#" 看成是一个叶子节点
     * 对于根节点来说，入度为0，出度为2
     * “#”的入度为1，出度为0
     * 数字节点的入度为1，出度为2
     * 所以我们可以根据出度入度来判断是否为前序遍历
     * 因为前序遍历中，非叶子节点出现的一定比叶子节点出现的早，也就是说在这个过程中
     * 除非遍历完了，否则出度不可能为0
     * */
    public boolean isValidSerializationAlgorithm(String preOrder) {
        if (preOrder.equals("") || preOrder.length() == 0) {
            return false;
        }
        String[] strs = preOrder.split(",");
        int degree = 1;     // 这里一定要是degree = 1
        for (String str : strs) {
            if (degree == 0) {
                return false;
            }
            if (str.equals("#")) {
                degree -= 1;
            } else {
                degree += 1;
            }
        }
        return degree == 0;
    }
}

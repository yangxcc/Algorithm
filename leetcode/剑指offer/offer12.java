package 剑指offer;

/**
 * 矩阵中的路径
 * <p>
 * 给定一个 m x n 二维字符网格 board 和一个字符串单词 word 。如果 word 存在于网格中，返回 true ；否则，返回 false 。
 * <p>
 * 单词必须按照字母顺序，通过相邻的单元格内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。同一个单元格内的字母不允许被重复使用。
 * <p>
 * 链接：https://leetcode-cn.com/problems/ju-zhen-zhong-de-lu-jing-lcof
 */
public class offer12 {

    public boolean exist(char[][] board, String word) {
        int rowNum = board.length;
        int colNum = board[0].length;
        if (rowNum * colNum < word.length()) {
            return false;
        }
        /**
         * 为什么要有visited呢？
         *    [[aa]]
         *    aaa
         *    上面的用例的意思是board数组是aa，在里面找aaa
         *    如果没有visited标记已经访问过的点，那么当到了第三个字符的时候，process(col-1)会判断成true
         *    这样显然是不正确的，他不能够再往回走了
         *
         * */
        boolean[][] visited = new boolean[rowNum][colNum];

        for (int i = 0; i < rowNum; i++) {
            for (int j = 0; j < colNum; j++) {
                if (process(board, word, 0, i, j, visited)) {
                    return true;
                }
            }
        }
        return false;
    }

    // index之前的字符都已经匹配上了
    public boolean process(char[][] board, String word, int index, int row, int col, boolean[][] visited) {
        if (index == word.length()) {
            return true;
        }
        // base case中一定不要忘了 <0这一边！！
        // 也不要忘了 word.charAt(index) != board[row][col])，下面就不需要使用if判断了
        if (row < 0 || col < 0 || row >= board.length || col >= board[0].length || visited[row][col]
                || word.charAt(index) != board[row][col]) {
            return false;
        }

        visited[row][col] = true;   // 标记上这个点已经访问过了
        boolean res = process(board, word, index + 1, row - 1, col, visited) ||
                process(board, word, index + 1, row + 1, col, visited) ||
                process(board, word, index + 1, row, col - 1, visited) ||
                process(board, word, index + 1, row, col + 1, visited);

        visited[row][col] = false;   // 前往不要忘了再把它标记回来
        /**
         *         A B C D
         *         E F G H
         *         I J K L
         *         M N O P
         *   比如我想要找有没有 KG，当我遍历到G点时，会先把它的上下左右都标记上遍历过了，
         *   很明显这样不行，因为我还需要访问K的上下左右，而且到了K的时候，还需要往上看，
         *   所以我们要在它的上下左右都访问完了之后再把它标记回来
         *   如果G标记成以访问，那么我们就直接返回false了，就不能够找到KG了
         * */
        return res;
    }
}

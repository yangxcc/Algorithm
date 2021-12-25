package BFS;

import java.util.*;

/**
 * 滑动谜题  leetcode 773
 * <p>
 * 在一个 2 x 3 的板上（board）有 5 块砖瓦，用数字 1~5 来表示, 以及一块空缺用 0 来表示.
 * <p>
 * 一次移动定义为选择 0 与一个相邻的数字（上下左右）进行交换.
 * <p>
 * 最终当板 board 的结果是 [[1,2,3],[4,5,0]] 谜板被解开。
 * <p>
 * 给出一个谜板的初始状态，返回最少可以通过多少次移动解开谜板，如果不能解开谜板，则返回 -1
 */
public class SlidingPuzzle {
    /**
     * @param board start
     * @return 最少滑动多少次能够解开密板
     * target = [[1,2,3],[4,5,0]]
     * 我们要解决两个问题：
     * 第一个问题是 如果使用bfs它的起点和终点分别是什么
     * 第二个问题是 怎么移动，即怎么交换0和其他元素
     */
    public int slidingPuzzle(int[][] board) {
        // 对于第二个问题，我们可以看到题目中规定了棋盘就是2×3的，所以我们可以通过这个条件来进行交换
        // 即如果将这个二维数组变成一维的，如下
        //  0 1 2
        //  3 4 5
        // 0 1 2 3 4 5 那么对于二维数组中的0，它的相邻元素就是1号和3号，而且不管元素怎么变化，他周围元素的索引是不会变的，变得
        // 只是索引上的值，以此类推...
        List<List<Integer>> neighbor = new ArrayList<>();
        // 不知道在Java中有没有更好的方法
        List<Integer> t1 = new ArrayList<>();
        t1.add(1);
        t1.add(3);
        List<Integer> t2 = new ArrayList<>();
        t2.add(0);
        t2.add(2);
        t2.add(4);
        List<Integer> t3 = new ArrayList<>();
        t3.add(1);
        t3.add(5);
        List<Integer> t4 = new ArrayList<>();
        t4.add(0);
        t4.add(4);
        List<Integer> t5 = new ArrayList<>();
        t5.add(1);
        t5.add(3);
        t5.add(5);
        List<Integer> t6 = new ArrayList<>();
        t6.add(2);
        t6.add(4);

        neighbor.add(t1);
        neighbor.add(t2);
        neighbor.add(t3);
        neighbor.add(t4);
        neighbor.add(t5);
        neighbor.add(t6);
        // {{1,3},{0,2,4}, {1,5}, {0,4}, {1,3,5},{2,4}}

        // 下面就可以开始进行bfs的过程了
        // 解决了第二个问题，第一个问题很简单，就是把当前棋盘给一维化作为start
        String start = "";
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 3; j++) {
                start += board[i][j];
            }
        }
        String target = "123450";
        Queue<String> q = new ArrayDeque<>();
        Set<String> visited = new HashSet<>();
        int step = 0;
        q.add(start);
        visited.add(start);

        while(!q.isEmpty()) {
            int sz = q.size();
            for (int i = 0; i < sz; i++) {
                String cur = q.poll();
                if (cur.equals(target)) {
                    return step;
                }

                // 接下来我需要找到0所在的位置，然后和他周围的元素交换
                int index = -1;
                for (int j = 0; j < cur.length(); j++) {
                    if (cur.charAt(j) == '0') {
                        index = j;
                        break;
                    }
                }

                for (int k = 0; k < neighbor.get(index).size(); k++) {
                    String s = swap(cur, neighbor.get(index).get(k), index);
                    if (!visited.contains(s)) {
                        q.add(s);
                        visited.add(s);
                    }
                }
            }
            step++;
        }
        return -1;
    }

    public String swap(String s, int i, int j) {
        char[] chs = s.toCharArray();
        char temp = chs[i];
        chs[i] = chs[j];
        chs[j] = temp;
        return new String(chs);
    }

}

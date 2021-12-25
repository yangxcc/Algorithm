package BFS;

import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.Queue;

/**
 * 打开转盘锁
 * leetcode 752
 * 你有一个带有四个圆形拨轮的转盘锁。每个拨轮都有10个数字：
 * '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' 。每个拨轮可以自由旋转：
 * 例如把 '9' 变为 '0'，'0' 变为 '9' 。每次旋转都只能旋转一个拨轮的一位数字。
 *
 * 锁的初始数字为 '0000' ，一个代表四个拨轮的数字的字符串。
 *
 * 列表 deadends 包含了一组死亡数字，一旦拨轮的数字和列表里的任何一个元素相同，这个锁将会被永久锁定，无法再被旋转。
 *
 * 字符串 target 代表可以解锁的数字，你需要给出解锁需要的最小旋转次数，如果无论如何不能解锁，返回 -1 。
 *
 */
public class OpenTheLock {
    // 从题意可以看出，每转动一个位置，那么它的周围将有8个点，也就是说这是一个有向图，我们可以使用bfs的方法来解题
    public int openLock(String[] deadends, String target) {
        String start = "0000";
        Queue<String> q = new ArrayDeque<>();
        HashSet<String> visited = new HashSet<>();   // 避免走回头路
        q.add(start);
        visited.add(start);
        int step = 0;

        HashSet<String> dead = new HashSet<>();
        for (String d : deadends) {
            dead.add(d);
        }

        while (!q.isEmpty()) {
            int sz = q.size();
            for (int i = 0; i < sz; i++) {
                String cur = q.poll();
                if (dead.contains(cur)) {
                    continue;
                }
                if (cur.equals(target)) {
                    return step;
                }

                for (int j = 0; j < 4; j++) {
                    String toUp = up(cur, j);  // 向上转动
                    if (!visited.contains(toUp)) {
                        q.add(toUp);
                        visited.add(toUp);
                    }

                    String toDown = down(cur, j);
                    if (!visited.contains(toDown)) {
                        q.add(toDown);
                        visited.add(toDown);
                    }
                }
            }
            step++;
        }
        return -1;
    }

    public String up(String s, int k) {
        // j位置的转盘向上转
        char[] chs = s.toCharArray();
        if (chs[k] == '0') {
            chs[k] = '9';
        } else {
            chs[k] -= 1;
        }
        return new String(chs);
    }

    public String down(String s, int k) {
        char[] chs = s.toCharArray();
        if (chs[k] == '0') {
            chs[k] = 9;
        } else {
            chs[k] += 1;
        }
        return new String(chs);
    }
}

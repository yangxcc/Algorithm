package 图;

import java.io.File;
import java.util.LinkedList;

/**
 * 名流问题：
 * 假设你是一个专业的狗仔，参加了一个 n 人派对，其中每个人被从 0 到 n - 1 标号。
 * 在这个派对人群当中可能存在一位 “名人”。所谓 “名人” 的定义是：其他所有 n - 1 个人都认识他/她，而他/她并不认识其他任何人。
 * 现在你想要确认这个 “名人” 是谁，或者确定这里没有 “名人”。而你唯一能做的就是问诸如 “A 你好呀，
 * 请问你认不认识 B呀？” 的问题，以确定 A 是否认识 B。你需要在（渐近意义上）尽可能少的问题内来确定这位 “名人” 是谁
 * （或者确定这里没有 “名人”）。
 * 在本题中，你可以使用辅助函数 bool knows(a, b) 获取到 A 是否认识 B。请你来实现一个函数 int findCelebrity(n)。
 *
 */
public class FindCelebrity {
    private int[][] neighbors;
    public FindCelebrity(int[][] neighbors) {
        this.neighbors = neighbors;
    }

    // 使用暴力解法的时间复杂度是O(n^2)
    public int findCelebrity(int n) {
        // 如果使用暴力解法，那就是对于每个人都去循环遍历一下，假设candidate是名人
        for (int candidate = 0; candidate < n; candidate++) {
            int other;
            for (other = 0; other < n; other++) {
                if (candidate == other) {
                    // 是同一个人
                    continue;
                }
                // 因为假设candidate是名人，如果有人不认识candidate，或者candidate认识某个人
                // 都能够说明candidate不是名人
                if (knows(candidate, other) || !knows(other, candidate)) {
                    break;
                }
            }
            if (other == n) {
                return candidate;
            }
        }
        return -1;
    }

    // 对于两个人，他们的关系可能是
    // 两者互相认识，或者两者互相不认识  —— 情况1 2
    // A认识B，但是B不认识A，或者A不认识B，但是B认识A —— 情况3 4
    // 对于情况1 2，这两个人都不可能是名人，任意丢弃一个就可以了
    // 对于情况3，4，分别是B可能是名人，A可能是名人，丢弃不是名人的那一个
    // 时间复杂度是O(n)，空间复杂度是O(n)
    public int findCelebrity2(int n) {
        if (n == 1) {
            return 0;
        }
        LinkedList<Integer> q = new LinkedList<>();
        // 把所有人都加入到队列中
        for (int i = 0; i < n; i++) {
            q.add(i);
        }
        // 当只剩下一个候选人的时候停下
        while (q.size() > 1) {
            int person1 = q.pollFirst();    // 双端队列弹出前后的数时间复杂度都是O(1)
            int person2 = q.pollFirst();
            if (knows(person1, person2) || !knows(person2, person1)) {
                // person不可能是名人，丢弃person1，让person2在归队
                q.addFirst(person2);
            } else {
                q.addFirst(person1);
            }
        }

        int candidate = q.pollFirst();
        for (int i = 0; i < n; i++) {
            if (i == candidate) {
                continue;
            }
            if (!knows(i, candidate) || knows(candidate, i)) {
                return -1;
            }
        }
        return candidate;
    }


    // 进一步压缩空间，使得空间复杂度为O(1)
    public int findCelebrity3(int n) {
        // 假设0号是名人
        int candidate = 0;
        for (int other = 1; other < n; other++) {
            if (knows(candidate, other) || !knows(other, candidate)) {
                // 说明candidate不是名人
                candidate = other;
            }
        }

        // 最后对这个candidate在验证一下
        for (int other = 0; other < n; other++) {
            if (other == candidate) continue;
            if (knows(candidate, other) || !knows(other, candidate)) {
                return -1;
            }
        }
        return candidate;
    }

    // i是否认识j，时间复杂度是O(1)，就是对邻接矩阵的查找
    public boolean knows(int i, int j) {
        return neighbors[i][j] == 1;
    }
}

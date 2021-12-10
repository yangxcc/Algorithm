package 剑指offer;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;

/**
 * 滑动窗口的最大值
 * <p>
 * https://leetcode-cn.com/problems/hua-dong-chuang-kou-de-zui-da-zhi-lcof/
 */
public class offer59_1 {
    // k表示的是窗口大小
    public int[] maxSlidingWindow(int[] nums, int k) {
        List<Integer> res = new ArrayList<>();
        MonotonicQueue window = new MonotonicQueue();

        for (int i = 0; i < nums.length; i++) {
            if (i < k - 1) {
                // 先把窗口的k-1填满
                window.push(nums[i]);
            } else {
                window.push(nums[i]);
                res.add(window.max());
                window.pop(i - k + 1);
            }
        }

        int[] ans = new int[res.size()];
        for (int i = 0; i < res.size(); i++) {
            ans[i] = res.get(i);
        }
        return ans;
    }

    // 单调队列
    class MonotonicQueue {
        // 双向链表
        LinkedList<Integer> q = new LinkedList<>();

        public void push(int val) {
            while (!q.isEmpty() && q.peekLast() < val) {
                q.pollLast();
            }
            q.addLast(val);
        }

        public int max() {
            return q.getFirst();
        }

        public void pop(int val) {
            if (q.getFirst() == val) {
                q.pollFirst();
            }
        }
    }
}

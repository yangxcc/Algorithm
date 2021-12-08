package 单调队列;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * 输入：nums = [1,3,-1,-3,5,3,6,7], k = 3
 * 输出：[3,3,5,5,6,7]
 * 解释：
 * 滑动窗口的位置                最大值
 * ---------------               -----
 * [1  3  -1] -3  5  3  6  7       3
 *  1 [3  -1  -3] 5  3  6  7       3
 *  1  3 [-1  -3  5] 3  6  7       5
 *  1  3  -1 [-3  5  3] 6  7       5
 *  1  3  -1  -3 [5  3  6] 7       6
 *  1  3  -1  -3  5 [3  6  7]      7
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/sliding-window-maximum
 */
public class slidingWindowMaximum {
    public int[] maxSlidingWindow(int[] nums, int k){
        if (nums.length == 0) {
            return new int[0];
        }

        // 滑动窗口，单调队列
        MonotonicQueue window = new MonotonicQueue();
        ArrayList<Integer> helper = new ArrayList<>();

        for (int i = 0; i < nums.length; i++) {
            if (i < k - 1) {
                // 先把window的前k-1个位置填充起来
                window.push(nums[i]);
            } else {
                // 加入新元素
                window.push(nums[i]);
                // 取出window里面的最大元素
                helper.add(window.max());
                // 把窗口最左边的数删除
                window.pop(nums[i - k + 1]);
            }
        }
        int[] ans = new int[helper.size()];
        for (int i = 0; i < helper.size(); i++) {
            ans[i] = helper.get(i);
        }
        return ans;
    }
}

class MonotonicQueue {
    LinkedList<Integer> q = new LinkedList<>();

    public void push(int n) {
        while (!q.isEmpty() && q.peekLast() < n) {
            q.pollLast();
        }
        q.addLast(n);
    }

    public void pop(int x) {
        if (x == q.getFirst()) {
            q.pollFirst();
        }
    }

    public int max() {
        return q.getFirst();
    }
}

package StructureAndTrick.滑动窗口;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.WeakHashMap;

public class SlidingWindowMaxArray {

    // 暴力解法，时间复杂度也是O(N)？
    public static int[] getMaxWindow01(int[] arr, int w) {
        if (arr.length < w || arr == null || w <= 0) {
            return null;
        }
        int[] res = new int[arr.length - w + 1];
        int index = 0;
        int L = 0;
        int R = w - 1;
        while (R < arr.length) {
            int max = arr[L];
            for (int i = L; i <= R; i++) {
                max = Math.max(max, arr[i]);
            }
            res[index++] = max;
            L++;
            R++;
        }
        return res;
    }

    // w 是窗口的大小
    public static int[] getMaxWindow(int[] arr, int w) {
        if (arr.length < w || arr == null || w < 0) {
            return null;
        }
        // 窗口最大值更新结构，维护一个双端队列
        LinkedList<Integer> queue = new LinkedList<>();
        // 队列中元素都是从大 -> 小的，里面放下标，因为下标能够存储更多的信息
        int[] res = new int[arr.length -w + 1];
        int index = 0;

        // 每个元素最多入队一次、出队一次，所以时间复杂度是O(N)
        for (int R = 0; R < arr.length; R++) {
            while (!queue.isEmpty() && arr[queue.peekLast()] <= arr[R]) {
                queue.pollLast();
            }
            queue.addLast(R);
            if (queue.peekFirst() == R - w) {   // 窗口滑动，保证窗口中元素个数始终是w个
            }
            if (R >= w - 1) {   // 等于/超过窗口大小了，就可以直接使用队头的元素了，因为队头的下标所表示的元素就是最大的
                res[index++] = arr[queue.peekFirst()];
            }
        }
        return res;
    }


    public static void main(String[] args) {
        int[] arr = new int[]{1,2,3,4,5,3,1,7,4,6};
        int[] maxWindow = getMaxWindow(arr, 3);
        System.out.println(Arrays.toString(maxWindow));
        System.out.println(Arrays.toString(getMaxWindow01(arr,3)));
    }
}

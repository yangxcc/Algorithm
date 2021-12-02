package 剑指offer;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * 和为s的连续正数序列
 *
 * 输入一个正整数 target ，输出所有和为 target 的连续正整数序列（至少含有两个数）。
 * 序列内的数字由小到大排列，不同序列按照首个数字从小到大排列
 * */
public class offer57_2 {
    // 滑动窗口
    public int[][] findContinuousSequence(int target) {
        int left = 1;
        int right = 1;
        List<int[]> helper = new ArrayList<>();
        int window = 0;  // sum

        while (right < target) {
            window += right;
            right++;

            while (window > target) {
                window -= left;
                left++;
            }

            if (window == target) {
                int[] temp = new int[right - left];   // 因为是左闭右开的区间
                for (int i = 0; i < temp.length; i++) {
                    temp[i] = left + i;
                    // 注意这里不能写left++，因为left是窗口，不能再这里变
                }
                helper.add(temp);
            }
        }
        int[][] res = new int[helper.size()][];
        for (int i = 0; i < res.length; i++) {
            res[i] = helper.get(i);
        }
        return res;
    }
}

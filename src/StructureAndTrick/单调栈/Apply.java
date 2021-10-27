package StructureAndTrick.单调栈;

import java.util.LinkedList;
import java.util.Stack;

/**
 * 定义：正数数组中累加和与最小值的乘积，乘坐指标A
 * 给定一个数组，请返回子数组中，指标A最大的值
 * <p>
 * <p>
 * 整体思路
 * - 我们给定一个指针index，用来指向当前数
 * - 我们假设当前指针index指向数是x，那么我们去寻找以x为最小值的子数组
 * - 找到这个子数组后，让x乘以子数组的累加和，什么情况下指标A最大，只有当子数组的长度最大的时候，因为是正数数组
 * - 去寻找这个最长子数组，这个时候我们就可以使用单调栈了
 * - 比如对于一个这样的数组  [5,3,2,1,6,7,8,4]
 * - 当index指向5的时候，往左、往右分别去看可以看比5小的且离他最近的数，找到了就是它的边界，因为他保证5是最小的数
 * - 所以当index指向5的时候，只需要计算 5 * 5就可以了
 * - 当index指向3的时候，往左可以找到5，往右就不能扩了，因为如果把2包含进来，2就是最小的了，此时子数组为[5,3],[3]
 * - 以此类推....
 */

public class Apply {


    /**
     * 单调栈结构的实现，数组中没有重复值
     * */
    // 给我数组中的一个下标，返回这个下边能往两边扩的下标
    // 求 数组中的每一个数 左右两边的 离它最近 比它小的
    public static int[][] MonotonicStack(int[] arr) {
        if (arr == null || arr.length < 1) {
            return null;
        }
        int[][] res = new int[arr.length][2];

        Stack<Integer> stack = new Stack<>();
        // 遍历整个数组
        for (int i = 0; i < arr.length; i++) {
            // 栈中元素从小到大
            while (!stack.isEmpty() && arr[stack.peek()] > arr[i]) {
                // 弹出元素，生成信息
                int popIndex = stack.pop();
                // popIndex右侧比他小且离他最近的数是i
                // popIndex左侧比他小且离他最近的数是栈中它下面的那一个
                int leftIndex = stack.isEmpty() ? -1 : stack.peek();
                res[popIndex][0] = leftIndex;
                res[popIndex][1] = i;
            }
            stack.push(i);
        }
        // 数组遍历完了之后，如果栈中还有元素，那么将进入清算阶段
        while (!stack.isEmpty()) {
            int index = stack.pop();
            int leftIndex = stack.isEmpty() ? -1 : stack.peek();
            res[index][0] = leftIndex;
            res[index][1] = -1;
        }
        return res;
    }

    public static int apply(int[] arr) {
        if (arr == null || arr.length < 1) {
            return -1;
        }
        int max = Integer.MIN_VALUE;
        int[][] monotonicStack = MonotonicStack(arr);
        for (int i = 0; i < arr.length; i++) {
            int left = monotonicStack[i][0];
            int right = monotonicStack[i][1];
            int sum = 0;
            for (int j = left + 1; j < right; j++) {
                sum += arr[j];
            }
            max = Math.max(max, sum * arr[i]);
        }
        return max;
    }

    public static void main(String[] args) {
        int[] arr = {5,3,2,6,4};
        int res = apply(arr);
        System.out.println(res);
    }
}

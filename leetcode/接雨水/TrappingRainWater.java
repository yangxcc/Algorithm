package 接雨水;

/**
 * 给定 n 个非负整数表示每个宽度为 1 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水。
 *
 * 注意这个是有宽度的
 */
public class TrappingRainWater {

    // 首先暴力解法，这个解法的时间复杂度为O(n^2)
    public int trap(int[] height) {
        if (height == null || height.length == 0) {
            return -1;
        }
        int res = 0;
//        int maxInLeft = 0, maxInRight = 0;
        // 第一个柱子和最后一个柱子肯定是没有存水的
        for (int i = 1; i < height.length - 1; i++) {
            // 一定要注意，在这种解法中，maxInLeft和maxInRight要在for循环里面，因为他们不能够是全局的最大值
            int maxInLeft = 0, maxInRight = 0;
            for (int l = 0; l < i; l++) {
                maxInLeft = Math.max(maxInLeft, height[l]);
            }

            for (int r = i + 1; r < height.length; r++) {
                maxInRight = Math.max(maxInRight, height[r]);
            }

            // 第i跟柱子能够存入的水的容量取决于其左右两边最高的柱子两根里面的短的那一根
            int temp = Math.min(maxInLeft, maxInRight) - height[i];
            res += temp < 0 ? 0 : temp;
        }
        return res;
    }

    // 第二种方法，使用备忘录的方式，将每个位置左右两边的最小值用两个数组保存起来，本质上就是用空间换时间
    // 此时，时间复杂度为O(n)
    public int trap2(int[] height) {
        if (height == null || height.length == 0) {
            return -1;
        }
        int n = height.length;
        int[] maxInLeft = new int[n];
        int[] maxInRight = new int[n];
        int max = 0;   // 辅助变量
        int res = 0;

        // 节点左边的最大值
        for (int i = 0; i < n; i++) {
            max = Math.max(max, height[i]);
            maxInLeft[i] = max;
        }

        max = 0;
        for (int i = n - 1; i >= 0; i--) {
            max = Math.max(max, height[i]);
            maxInRight[i] = max;
        }

        for (int i = 1; i < n - 1; i++) {
            int temp = Math.min(maxInLeft[i], maxInRight[i]) - height[i];
            res += Math.max(temp, 0);
        }
        return res;
    }

    // 第三种方法，使用双指针，将空间复杂度降成常数阶
    public int trap3(int[] height) {
        if (height == null || height.length == 0) {
            return -1;
        }
        int maxInLeft = 0, maxInRight = 0;
        int left = 0, right = height.length - 1;
        int res = 0;

        while (left < right) {
            maxInLeft = Math.max(maxInLeft, height[left]);
            // maxInLeft是[0,left]上的最大值
            maxInRight = Math.max(maxInRight, height[right]);
            // maxInRight是[right, n - 1]上的最大值

            if (maxInLeft < maxInRight) {
                // 即使maxInRight不是右边最高的也无所谓，因为他比左边最高的还要高，所以能存多少水是由左边这个低的决定的
                res += maxInLeft - height[left];
                left++;
            } else {
                res += maxInRight - height[right];
                right--;
            }
        }
        return res;
    }
}

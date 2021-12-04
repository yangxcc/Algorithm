package 二分;
/**
 * 珂珂喜欢吃香蕉。这里有 N 堆香蕉，第 i 堆中有 piles[i] 根香蕉。
 * 警卫已经离开了，将在 H 小时后回来。
 *
 * 珂珂可以决定她吃香蕉的速度 K （单位：根/小时）。
 * 每个小时，她将会选择一堆香蕉，从中吃掉 K 根。
 * 如果这堆香蕉少于 K 根，她将吃掉这堆的所有香蕉，然后这一小时内不会再吃更多的香蕉。  
 *
 * 珂珂喜欢慢慢吃，但仍然想在警卫回来前吃掉所有的香蕉。
 *
 * 返回她可以在 H 小时内吃掉所有香蕉的最小速度 K（K 为整数）
 *
 * 链接：https://leetcode-cn.com/problems/koko-eating-bananas
 *
 * 1 <= piles[i] <= 10^9
 * */

// 这道题符合使用二分的条件，因为我们可以把珂珂吃香蕉的速度看成自变量x，
// 那么珂珂吃完这堆香蕉所用的时间就是因变量，
// 吃香蕉的速度越大，所用时间就会越少，也就是说，时间关于速度是一个单调递减函数
// 所以我们可以使用二分来做
public class kokoEatingBananas {

    /**
     * 我们首先用自变量把因变量表示出来
     *
     * @param nums 香蕉堆
     * @param x    吃香蕉的速度
     * @return     吃完全部香蕉的时间
     */
    public int f(int[] nums, int x) {
        int hours = 0;
        for (int num : nums) {
            hours += num / x;
            if (num % x != 0) {  // 如果num > x的话，也就是一次吃不了或者num < x，一次能吃完
                hours++;
            }
        }
        return hours;
    }

    public int minEatingSpeed(int[] piles, int h){
        int left = 1;     // 珂珂最少要吃1跟香蕉
        int right = 1000000000 + 1;  // 根据题意，1 <= piles[i] <= 10^9，最多能吃10^9个香蕉
        // 这里其实也可以把right定义成piles中最大值
        while (left < right) {
            int mid = left + (right - left) / 2;
            // 一定要注意，这是单调递减函数，不要框架中的升序
            if (f(piles, mid) <= h) {
                right = mid;      // 因为是最小速度，所以找左边界
            } else {
                left = mid + 1;
            }
        }
        return left;
    }


}

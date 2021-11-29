package 剑指offer;

import com.sun.org.apache.bcel.internal.generic.IF_ACMPEQ;

/**
 * 数组中出现次数超过一半的数字
 * */
public class offer39 {
    /**
     * 很明显，这道题可以使用hashmap把每个数出现的次数都保存起来，这样的时间复杂度和空间复杂度都是O(N)
     *
     * 解决这类问题，更好的方法是摩尔投票法
     *
     * 所谓摩尔投票法，其实就是极限一换一
     * 如果两个数相同，那么计数+1，如果两个数不同，那么两个数都不管了
     * 也就是说让两个数不相同的时候，就相当于让他们互相杀掉
     * 因为题目中说了肯定有一个数是大于一半的，所以最后剩下的那个数肯定是大于一半的那个数
     * */
    public int majorityElement(int[] nums) {
        if (nums.length == 0) {
            return Integer.MIN_VALUE;
        }
        int res = Integer.MIN_VALUE;
        int count = 0;
        for (int i = 1; i < nums.length; i++) {
            if (count == 0) {
                // 说明i前面的数都已经抵消了
                res = nums[i];
                count++;
            } else {
                if (res == nums[i]) {
                    count++;
                } else {
                    count--;
                }
            }
        }
        return res;
    }
}

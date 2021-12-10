package 剑指offer;

import java.util.Arrays;

/**
 * 扑克牌中的顺子
 *
 * 从若干副扑克牌中随机抽 5 张牌，判断是不是一个顺子，即这5张牌是不是连续的。
 * 2～10为数字本身，A为1，J为11，Q为12，K为13，而大、小王为 0 ，可以看成任意数字。A 不能视为 14。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/bu-ke-pai-zhong-de-shun-zi-lcof
 */
public class offer61 {
    public boolean isStraight(int[] nums){
        // 最大值和最小值不能超过5，最小值指的是除了0之外的最小值
        Arrays.sort(nums);
        int min = 13;
        int max = 0;
        // nums中也不能够有重复值
        int help = -1;
        for (int num : nums) {
            if (num == 0) {
                continue;
            }
            if (help == num) {
                return false;
            } else {
                help = num;
            }
            max = Math.max(max, num);
            min = Math.min(min, num);
        }
        return max - min < 5 ;
    }
}

package 剑指offer;

/**
 * 0～n-1中缺失的数字
 * 一个长度为n-1的递增排序数组中的所有数字都是唯一的，
 * 并且每个数字都在范围0～n-1之内。在范围0～n-1内的n个数字中有且只有一个数字不在该数组中，
 * 请找出这个数字。
 *
 * 链接：https://leetcode-cn.com/problems/que-shi-de-shu-zi-lcof
 **/
public class offer53_2 {

    // 这道题一看就是使用二分，但是和其他题目不同的是需要绕过
    // 这个弯来，当nums[mid] != mid的时候，说明缺失的数在左边
    public int missingNumber(int[] nums) {
        if (nums.length == 0) {
            return -1;
        }
        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            // 二分中的mid要这么写，避免有的题目中 left + right溢出
            // 还有一个特别奇怪的地方，我写left + (right - left) >> 1死循环？？
            // 这个暂时没有想通
            int mid = left + (right - left) / 2;
            if (nums[mid] == mid) {
                left = mid + 1;   // 说明mid左边的所有数据都是符合题意得
            } else {
                right = mid - 1;
            }
        }
        return left;
    }
}

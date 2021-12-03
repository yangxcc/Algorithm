package 剑指offer;

/**
 * 旋转数组的最小数字
 *
 * 把一个数组最开始的若干个元素搬到数组的末尾，我们称之为数组的旋转。
 * 输入一个递增排序的数组的一个旋转，输出旋转数组的最小元素。
 * 例如，数组 [3,4,5,1,2] 为 [1,2,3,4,5] 的一个旋转，该数组的最小值为1。 
 *
 * 链接：https://leetcode-cn.com/problems/xuan-zhuan-shu-zu-de-zui-xiao-shu-zi-lcof
 *
 * */
public class offer11 {

    // 看到有序，要最先想到二分
    public int minArray(int[] numbers) {
        if (numbers.length == 0) {
            return Integer.MIN_VALUE;
        }
        int left = 0;
        int right = numbers.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (numbers[mid] < numbers[right]) {
                // 这种情况下说明右边是有序的
                right = mid;
            } else if (numbers[mid] > numbers[right]) {
                left = mid + 1;
            } else {
                right--;
            }
        }
        return numbers[left];
    }
}

package 剑指offer;

/**
 * 在排序数组中查找数字
 *
 * 统计一个数字在排序数组中出现的次数
 * */
public class offer53_1 {
    // 看到排序数组就要想到二分查找，
    // 很明显这个就是二分查找里面找左边界或者右边界
    public int search(int[] nums, int target) {
        if (nums.length == 0) {
            return 0;
        }
        int res = 0;
        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                // 因为想找左边界，所以去收缩右边界
                right = mid - 1;
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        // 在return left之前要先判断一下left是否越界，因为left == right + 1的时候才会跳出循环
        if (left == nums.length || nums[left] != target) {
            return 0;
        }
//        return left;
        // 因为是要统计个数，所以不能就从这里返回左边界就完事了
        for (int i = left; i < nums.length; i++) {
            if (nums[i] != target) {
                break;
            }
            res++;
        }
        return res;
    }
}

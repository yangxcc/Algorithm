package 错题集;

/**
 * 给定两个大小分别为 m 和 n 的正序（从小到大）数组 nums1 和 nums2。请你找出并返回这两个正序数组的 中位数 。
 *
 * 算法的时间复杂度应该为 O(log (m+n)) 。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/median-of-two-sorted-arrays
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class MedianOfTwoSortedArrays {
    // 这里一个技巧：因为我们去找中位数的时候需要看看数组是奇数还是偶数，
    // 如果是奇数，那么返回中间的那一个数就好了
    // 如果是偶数，那么返回中间的两个数的平均值
    // 所以技巧是在数组中查找两个数，下标分别是
    // (len + 1) / 2 和 (len + 2) / 2
    // 当len为奇数的时候，两个值是相等的
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int m = nums1.length;
        int n = nums2.length;
        int index1 = (m + n + 1) / 2;
        int index2 = (m + n + 2) / 2;

        return (find(nums1, 0, nums2, 0, index1) + find(nums1, 0, nums2, 0, index2)) / 2;
    }

    /**
     *
     * @param nums1  第一个数组
     * @param start1 nums1的起始位置
     * @param nums2  第二个数组
     * @param start2 nums2的起始位置
     * @param k      target
     * @return       num1和nums2中的第k个数字
     */
    public double find(int[] nums1, int start1, int[] nums2, int start2, int k) {
        if (start1 >= nums1.length) {
            // 这种情况表明nums1中的数据都是不合适的，相当于nums1是空的
            // 所以就是直接从nums2中找第k个元素
            return nums2[start2 + k - 1];
        }

        if (start2 >= nums2.length) {
            return nums1[start1 + k - 1];
        }

        if (k == 1) {
            // 如果是找第一个元素，就返回两个数组中最小的那个就可以了
            return Math.min(nums1[start1], nums2[start2]);
        }

        int val1 = start1 + k / 2 - 1 < nums1.length ? nums1[start1 + k / 2 - 1] : Integer.MAX_VALUE;
        int val2 = start2 + k / 2 - 1 < nums2.length ? nums2[start2 + k / 2 - 1] : Integer.MAX_VALUE;

        if (val1 < val2) {
            // 这种情况下就能够把nums1中的一半给跳过去了
            return find(nums1, start1 + k / 2, nums2, start2, k - k / 2);
        } else {
            return find(nums1, start1, nums2, start2 + k / 2, k - k / 2);
        }
    }
}

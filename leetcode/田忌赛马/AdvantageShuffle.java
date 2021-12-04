package 田忌赛马;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * 给定两个大小相等的数组 A 和 B，A 相对于 B 的优势可以用满足 A[i] > B[i] 的索引 i 的数目来描述。
 * <p>
 * 返回 A 的任意排列，使其相对于 B 的优势最大化。
 * 数组B不能够改变
 * <p>
 * 链接：https://leetcode-cn.com/problems/advantage-shuffle
 */

// 这道题就是一个典型的田忌赛马问题
// 田忌赛马问题的核心就是如果田忌的马能够比得过齐王的马就和他比，
// 比不过就用田忌这边的垃圾去和齐王的精英比
public class AdvantageShuffle {
    public int[] advantageCount(int[] nums1, int[] nums2) {
        // 因为nums2不能变，所以我们需要一个辅助结构来对他进行排序
        // 使用大根堆，nums2相当于齐王的马
        PriorityQueue<int[]> maxQ = new PriorityQueue<>(
                (int[] pair1, int[] pair2) -> (pair2[1] - pair1[1]));

        for (int i = 0; i < nums2.length; i++) {
            maxQ.add(new int[]{i, nums2[i]});
        }

        int[] res = new int[nums1.length];

        Arrays.sort(nums1);
        // 使用双指针，来替换马
        int left = 0, right = nums1.length - 1;
        while (!maxQ.isEmpty()) {
            int[] maxVal = maxQ.poll();  // 目前nums2中最大的数
            int index = maxVal[0];
            int value = maxVal[1];
            if (nums1[right] > value) {
                // nums[1]中的数大于nums[2]中的数
                // 这种情况下就比了
                res[index] = nums1[right];
                right--;
            } else {
                // nums1中的数比不过nums2
                // 找到nums1中的一个垃圾来
                res[index] = nums1[left];
                left++;
            }
        }
        return res;
    }
}

// 这道题里面用到了很多技巧，不仅用到了田忌赛马，还用到了双指针

package 错题集;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 有序矩阵中第k小的元素
 *
 * 给你一个 n x n 矩阵 matrix ，其中每行和每列元素均按升序排序，找到矩阵中第 k 小的元素。
 * 请注意，它是 排序后 的第 k 小元素，而不是第 k 个 不同 的元素。
 *
 * 你必须找到一个内存复杂度优于 O(n^2) 的解决方案。
 *
 * 输入：matrix = [[1,5,9],[10,11,13],[12,13,15]], k = 8
 * 输出：13
 * 解释：矩阵中的元素为 [1,5,9,10,11,12,13,13,15]，第 8 小元素是 13
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/kth-smallest-element-in-a-sorted-matrix
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class KthSmallestElementInSortedMatrix {
    // 最容易想到的方法就是把这个矩阵转化成一维的
    // 然后取出nums[k-1]来，但是这样显然不合题意空间复杂度的要求
    // 而且没能用到每行和每列元素均按升序排序这个性质

    // 方法一：使用归并排序的思想
    // 空间复杂度为O(n)，时间复杂度为O(klogn)，归并k次，堆的大小为n
    public int kthSmallest(int[][] matrix, int k) {
        int n = matrix.length;

        // 小根堆里面是一个数组，matrix的每行都是一个有序数组，因此可以通过归并排序的思想来归并这n个数组
        // 数组的第一个元素是matrix[i][j]
        // 第二个元素是i，代表是哪个数组
        // 第三个元素是j，代表是数组中的哪个元素
        PriorityQueue<int[]> p = new PriorityQueue<int[]>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] - o2[0];
            }
        });

        for (int i = 0; i < n; i++) {
            p.add(new int[]{matrix[i][0], i, 0});
        }

        while (k > 1) {
            int[] ele = p.poll();
            if (ele[2] + 1 < n) {
                p.add(new int[]{matrix[ele[1]][ele[2] + 1], ele[1], ele[2] + 1});
            }
            k--;
        }
        return p.poll()[0];
    }

    // 方法二：使用归并排序的思想也仅仅是用到了行有序这一条性质
    // 并没有用到列有序这一性质，因此可以进一步优化
    // 使用二分的思想，我们知道矩阵中的最小值和最大值分别是
    // matrix[0][0]和matrix[n - 1][n - 1]
    // 我们在matrix[0][0]和matrix[n - 1][n - 1]之间任意挑选出一个数来
    // 能够发现，比这个数小的都在矩阵的左上角
    // 那么我们就能够统计比这个数小的数的个数，通过个数和k对比
    // 然后来压缩区间

    // 时间复杂度是O(nlog(r - l))
    // 二分查找进行次数为 O(log(r-l))，每次操作时间复杂度为 O(n)
    // 空间复杂度O(1)
    public int kthSmallest2(int[][] matrix, int k){
        int n = matrix.length;
        int left = matrix[0][0];
        int right = matrix[n - 1][n - 1];

        while (left < right) {
            int mid = left + (right - left) / 2;

            if (helper(matrix, mid) < k) {
                // 比mid小的个数小于k，说明第k小的元素在右边
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return left;
    }

    // 从matrix中找到比mid小的元素的个数
    public int helper(int[][] matrix, int mid) {
        int n = matrix.length;
        // 从左下角开始遍历
        int i = n - 1;
        int j = 0;
        int count = 0;
        while (i >= 0 && j < n) {
            if (matrix[i][j] <= mid) {
                count += i + 1;
                j++;
            } else {
                i--;
            }
        }
        return count;
    }
}

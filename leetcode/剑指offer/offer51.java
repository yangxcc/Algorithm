package 剑指offer;

/**
 * 数组中的逆序对
 *
 * 题目链接：https://leetcode-cn.com/problems/shu-zu-zhong-de-ni-xu-dui-lcof/
 *
 * 这道题是归并排序的一个经典应用
 * 类似的题目还有315（和本题一样）,327,493,
 * */
public class offer51 {

    int ans = 0;
    int[] temp;

    public int reversePairs(int[] nums) {
        int left = 0;
        int right = nums.length - 1;
        temp = new int[nums.length];

        mergeSort(nums, left, right);

        return ans;
    }

    public void mergeSort(int[] nums, int left, int right) {
        if (left >= right) {
            return ;
        }
        int mid = left + (right - left) / 2;
        mergeSort(nums, left, mid);
        mergeSort(nums, mid + 1, right);
        merge(nums, left, mid, right);
    }

    public void merge(int[] nums, int left, int mid, int right) {
        int i = left;
        int j = mid + 1;
        int index = left;

        while (i <= mid && j <= right) {
            if (nums[i] <= nums[j]) {
                temp[index++] = nums[i++];
            } else {
                ans += mid - i + 1;   // mid - i + 1这一部分的数都是比nums[j]大的
                temp[index++] = nums[j++];
            }
        }
        while (i <= mid) {
            temp[index++] = nums[i++];
        }
        while (j <= right) {
            temp[index++] = nums[j++];
        }

        for (int k = left; k <= right; k++) {
            nums[k] = temp[k];
        }
    }
}

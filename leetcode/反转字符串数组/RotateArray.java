package 反转字符串数组;

/**
 * 给你一个数组，将数组中的元素向右轮转 k 个位置，其中 k 是非负数
 */
public class RotateArray {
    // 最坏的方法，时间复杂度和空间复杂度都是O(n)
    public void rotate(int[] nums, int k) {
        int n = nums.length;
        int[] res = new int[n];
        for (int i = 0; i < n; i++) {
            res[(i + k) % n] = nums[i];
        }
        for (int j = 0; j < n; j++) {
            nums[j] = res[j];
        }
    }

    // 这种做法是错误的，不能通过全部的测试用例，比如[-1, 100, 3, 99]
    // 上面这个代码对于上面这个测试用例就是在idx=0和idx=2之间来回换
    public void rotate2(int[] nums, int k) {
        int temp_idx = 0;
        int temp_val = nums[0];
        int val = 0;
        int index = 0;
        int n = nums.length;
        int count = 0;
        // 需要动n次
        while (count < n) {
            val = temp_val;           // 记录将要移动的数值
            temp_idx = (temp_idx + k) % n;
            temp_val = nums[temp_idx];     // 移动之前记录一下即将被覆盖的数值
            nums[temp_idx] = val;
            count++;
        }
    }

    /**
     * 对于数组旋转这种问题，很多情况下都能分成三步：
     *   - 反转整个字符串
     *   - 反转[0, k]这个区间
     *   - 反转k到数组末尾这个区间
     * @param nums   数组
     * @param k     向右移动k个位置
     *
     *              [1,2,3,4,5,6,7] --> [5,6,7,1,2,3,4]
     *              这一个是属于右旋转
     */
    public void rotate3(int[] nums, int k) {
        // 避免k > nums.length
        k = k % nums.length;
        reverse(nums, 0, nums.length - 1);
        reverse(nums, 0, k - 1);
        reverse(nums, k, nums.length - 1);
    }

    public void reverse(int[] nums, int begin, int end) {
        for (int i = begin, j = end; i < j; i++, j--) {
            int temp = nums[i];
            nums[i] = nums[j];
            nums[j] = temp;
        }
    }
}

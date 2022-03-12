package 二分;

/**
 * 二分搜索的框架
 * */
public class frame {

    // nums是一个升序的正数数组
    public int binarySearch(int[] nums, int target) {
        if (nums.length == 0) {
            return -1;
        }
        int left = 0;
        int right = nums.length - 1;
        // 如果left和right这么规定的话，那么他就是一个左闭右闭的区间
        while (left <= right) {  // 当left=right + 1的时候，能够跳出循环，此时[right + 1,right】区间内没有数字
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else if (nums[mid] > target) {
                right = mid - 1;
            }
        }
        return -1;
    }

    // 寻找左边界，比如数组是[1,2,3,3,3,3,4,5,6]
    public int leftBound(int[] nums, int target) {
        if (nums.length == 0) {
            return -1;
        }
        int left = 0;
        int right = nums.length - 1;
        // 这样初始化left和right，他们表示的区间是左闭右开的
        while (left <= right) {
            // 所以这里就是<，而不是<=，因为当left=right的时候，[right,right)区间内没有数据了
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                // 因为是找左边界，所以这里就不能直接返回了，而是收缩右边界
                // 因为是闭区间了，所以right = mid - 1
                right = mid - 1;
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else if (nums[mid] > target) {
                right = mid - 1;
            }
        }

        // 如果target比所有元素都大，left会越界
        // 如果数组中没有target这个数
        if (left >= nums.length || nums[left] != target) {
            return -1;
        }
        return left;   // 如果将区间规定成闭区间，那么后面需要对left进行判断，因为他有可能越界
    }


    public int rightBound(int[] nums, int target) {
        if (nums.length == 0) {
            return -1;
        }
        int left = 0;
        int right = nums.length - 1;
        while(left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                // 因为是找右边界，所以这里就不能直接返回了，而是收缩左边界
                left = mid + 1;
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else if (nums[mid] > target) {
                right = mid - 1;
            }
        }
        // 如果target比所有值都小，那么right会越界
        // 如果target在数组中不存在
        if (right < 0 || nums[right] != target) {
            return -1;
        }
        return right;
        // 实例 [1,2,2,4] target = 2
    }
}

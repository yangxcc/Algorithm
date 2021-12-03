package 剑指offer;

/**
 * 二分搜索思想很简单，但是细节处很容易出现错误，下面整理出二分搜索的模板
 *
 * 二分能够被使用的前提是数组已经是排好序的了
 * */
public class binarySearchSummary {

    // 一定要时刻注意二分搜索的左右区间，又叫搜索区间
    public int binarySearch(int[] nums, int target) {
        if (nums.length == 0) {
            return -1;
        }
        int left = 0;
        int right = nums.length - 1;
        // 如果左右是定义了上面这两种，就说明是[left, right]，两侧都是闭区间
        // 那么下面的while就要是小于等于，因为当left=right+1的时候，循环会跳出，
        // 此时，[right+1, right]这个区间才是个空区间；如果while中的条件是left<right
        // 那么跳出循环的时候就是left==right,[right, right]这个区间内还有一个数
        while (left <= right) {
            int mid = left + (right - left) / 2;   // 防止right+left溢出
            if (nums[mid] == target) {
                // do something
                return mid;
            } else if (nums[mid] < target) {
                // 去右边查找
                left = mid + 1;
                // 此时的区间变成了[mid+1, right]
            } else if (nums[mid] > target) {
                right = mid - 1;
                // 此时的区间变成了[left, mid-1]
            }
        }
        return -1;
    }


    // 很明显，我们上面的这个算法中必须要保证数组中的数不重复
    // 比如数组如下int[] nums = {1,1,2,2,2,2,3,4,5}, target = 2
    // 用上面的算法找到的2肯定是第三个2，那如果我想要找最左侧/最右侧的2呢？
    // 很简单，我只需要修改上面nums[mid] == target情况下的处理逻辑就可以了

    public int leftBound(int[] nums, int target) {
        if (nums.length == 0) {
            return -1;
        }

        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                // do Something
                // 不要直接返回了，因为是去找左边界，所以缩小右边界
                right = mid - 1;
                // 因为最后跳出while循环的时候是left = right + 1
                // 所以不用担心这种情况{1,1,1,1,2,3,3,3,3}，只有一个2，
                // 虽然最开始越过去了，但是left最后会指向他的
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else if (nums[mid] > target) {
                right = mid - 1;
            }
        }
        // 同样是因为left = right + 1，所有有可能越界，所以这里需要判断一下
        if (left == nums.length || nums[left] != target) {
            return -1;
        }
        return left;
    }


    public int rightBound(int[] nums, int target) {
        if (nums.length == 0) {
            return -1;
        }
        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                // 同样是不直接返回了
                left = mid + 1;
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else if (nums[mid] > target) {
                right = mid - 1;
            }
        }
        if (right < 0 || nums[right] != target) {
            return -1;
        }
        return right;
    }

}

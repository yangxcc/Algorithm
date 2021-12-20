package 回溯;

/**
 *
 * 划分k个相等的子集
 *
 * 给定一个整数数组  nums 和一个正整数 k，找出是否有可能把这个数组分成 k 个非空子集，其总和都相等。
 *
 * 输入： nums = [4, 3, 2, 3, 5, 2, 1], k = 4
 * 输出： True
 * 说明： 有可能将其分成 4 个子集（5），（1,4），（2,3），（2,3）等于总和。
 *
 */
public class partitionToKEqualSumSubsets {
    // 从数组中元素的角度
    public boolean canPartitionKSubsets(int[] nums, int k){
        if (nums.length == 0 || k <= 0 || k > nums.length) {
            return false;
        }

        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        if (sum % k != 0) {
            return false;
        }
        int target = sum / k;
        // 假设有k个桶代表分成的k组，那么每个桶中的元素之和要为target
        int[] buckets = new int[k];
        // 从nums中每个数的角度来看，每个元素都要经历这样的一个过程：
        // 判断自己是否要进入相应的桶
        return backtrack(nums, buckets, 0, target);
    }

    public boolean backtrack(int[] nums, int[] buckets, int index, int target) {
        // index表示的是nums中的第index个数，我们是从数的角度来看的，所以我们要看看这个数能进入那个桶
        if (index == nums.length) {
            for (int i = 0; i < buckets.length; i++) {
                if (buckets[i] != target) {  // 每个桶的和都要为target
                    return false;
                }
            }
            return true;
        }

        for (int i = 0; i < buckets.length; i++) {
            // 先判断一下，需不需要进入下面的步骤
            /**
             * 事实上，针对这个判断，我们可以做一点优化
             * 如果我们能够让程序进入下面这个if的概率变大，那么我们就能够节省时间
             * 所以我们可以对数据进行排序，然后再逆序，让数组是一个降序的顺序
             * 因为这样我们可以让比较大的数先进入桶
             * Arrays.sort(nums);
             * swap(nums, i, j);  // 两头交换
             */
            if (buckets[i] + nums[index] > target) {
                continue;
            }
            // 做选择，进入i号桶
            buckets[i] += nums[index];
            if (backtrack(nums, buckets, index + 1, target)) {
                return true;
            }
            // 撤销选择
            buckets[i] -= nums[index];
        }
        return false;
    }

    // 从桶的角度
    public boolean canPartitionKSubsets2(int[] nums, int k){
        if (nums.length == 0 || k <= 0 || k > nums.length) {
            return false;
        }

        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        if (sum % k != 0) {
            return false;
        }
        int target = sum / k;
//        // 假设有k个桶代表分成的k组，那么每个桶中的元素之和要为target
//        int[] buckets = new int[k];
        // 从桶的角度来看：
        // 对于每个桶，都需要遍历一遍数组，判断某个元素是否要放入自己的桶中
        // k表示的是第k号桶，后面的一个参数表示的是这个桶的数值之和
        boolean[] visited = new boolean[nums.length];
        return backtrack2(nums, k,0, target, visited);
    }

    public boolean backtrack2(int[] nums, int k, int bucket, int target, boolean[] visited) {
        if (k == 0) {
            return true;
        }
        if (bucket == target) {
            k--; // 换个桶
//            return backtrack2(nums, k, bucket, target, visited);
            return backtrack2(nums, k, 0, target, visited);  // 换了一个桶之后，这个桶中还没有元素了
        }

        for (int i = 0; i < nums.length; i++) {
            if (visited[i]) continue;
            if (bucket + nums[i] > target) {
                return false;
            }
            // 做选择
            visited[i] = true;
            bucket += nums[i];

            if (backtrack2(nums, k, bucket, target, visited)) {
                return true;
            }

            // 撤销选择
            visited[i] = false;
            bucket -= nums[i];
        }

        return false;
    }
}

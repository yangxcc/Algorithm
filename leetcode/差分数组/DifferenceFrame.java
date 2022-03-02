package 差分数组;

/**
 * 差分数组，通常用于对某个区间频繁的进行修改
 *
 *
 */
public class DifferenceFrame {
    int[] diff;

    public DifferenceFrame(int[] nums) {
        int n = nums.length;
        diff = new int[n];
        diff[0] = nums[0];
        for (int i = 1; i < n; i++) {
            diff[i] = nums[i] - nums[i - 1];
        }
    }

    // 根据diff数组还原nums
    // 就按照diff怎么来的，就怎么还原就行
    public int[] result() {
        int[] res = new int[diff.length];
        res[0] = diff[0];
        for (int i = 1; i < diff.length; i++) {
            res[i] = res[i - 1] + diff[i];
        }
        return res;
    }

    // 更新区间nums[i,j]之间的值
    // diff[i] + val相当于对nums[i,...]加上val
    // 所以我们还需要对diff[j + 1] - val，对于j之后的都减去val
    public void update(int i, int j, int val) {
        diff[i] += val;
        if (j + 1 < diff.length) {
            diff[j + 1] -= val;
        }
    }
}

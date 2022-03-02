package 差分数组;

/**
 * 这里有 n 个航班，它们分别从 1 到 n 进行编号。
 *
 * 有一份航班预订表 bookings ，表中第 i 条预订记录 bookings[i] = [firsti, lasti, seatsi] 
 * 意味着在从 firsti 到 lasti （包含 firsti 和 lasti ）的 每个航班 上预订了 seatsi 个座位。
 *
 * 请你返回一个长度为 n 的数组 answer，里面的元素是每个航班预定的座位总数。
 *
 * 其实这道题翻译一下，就是给一个长度为n，初始化为0的数组，对这个数组进行区间操作，所以也是属于差分数组的应用
 *
 */
public class CorpFlightBookings {
    public int[] corpFlightBookings(int[][] bookings, int n) {
        // 相当于在一个长度为n，初始值都是0的数组上，在某个区间内增加数据
        int[] res = new int[n];
        Difference d = new Difference(res);
        for (int[] book : bookings) {
            int left = book[0] - 1;
            int right = book[1] - 1;
            int val = book[2];
            d.update(left, right, val);
        }

        return d.recover();
    }
}

class Difference {
    int[] diff;

    public Difference(int[] nums) {
        int n = nums.length;
        diff = new int[n];
        diff[0] = nums[0];
        for (int i = 1; i < n; i++) {
            diff[i] = nums[i] - nums[i - 1];
        }
    }

    public int[] recover() {
        int[] nums = new int[diff.length];
        nums[0] = diff[0];
        for (int i = 1; i < diff.length; i++) {
            nums[i] = nums[i - 1] + diff[i];
        }
        return nums;
    }

    // 更新[left, right]区间中的diff数组
    public void update(int left, int right, int val) {
        diff[left] += val;
        if (right + 1 < diff.length) {
            diff[right + 1] -= val;
        }
    }
}

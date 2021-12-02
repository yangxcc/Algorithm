package 剑指offer;

/**
 * 数组中数字出现的次数
 * */
public class offer56 {

    /**
     * 数组中的其他元素都出现了偶数次，只有一个元素出现了1次，请问这个元素是哪个？
     * 这道题使用异或运算
     *
     * a ^ a = 0
     * a ^ 0 = a
     * */
    public int singleNumber(int[] nums) {
        int res = 0;
        for (int num : nums) {
            res ^= num;
        }
        return res;
    }

    /**
     * 一个整型数组 nums 里除两个数字之外，其他数字都出现了两次。
     * 请写程序找出这两个只出现一次的数字。要求时间复杂度是O(n)，空间复杂度是O(1)
     * */
    public int[] singleNumbers(int[] nums) {
        int res = 0;
        for (int num : nums) {
            res ^= num;
        }
        // 此时的res等于的是两个出现一次数的异或值
        // res = a ^ b
        int mostRightOne = res & (~res + 1);
        // res & (~res + 1) --> res & (-res)，找到res最右边的1
        // 我们知道在最右边这个1的这个位置，a和b一定是一个是0一个是1
        // 对于其他的值无所谓，因为他们相差之后都变成0了
        int onlyOne = 0;
        for (int num : nums) {
            if ((num & mostRightOne) == 0) {   // 就是找到mostRightOne对应位置上为0的元素
                onlyOne ^= num;
            }
        }
        return new int[]{onlyOne, res ^ onlyOne};
    }

    /**
     * 在一个数组 nums 中除一个数字只出现一次之外，
     * 其他数字都出现了三次。请找出那个只出现一次的数字
     * */
    public int singleNum(int[] nums) {
        // 我看着评论中也有使用异或能够做出来的，但是我没咋看明白

        // 使用一个比较容易想的方法
        // 把每个数字的二进制位都加起来，出现3次的数字在对应的二进制位上一定能够被3整除
        int[] helper = new int[32];
        for (int num : nums) {
            for (int i = 31; i >= 0; i--) {
                helper[i] += num & 1;
                num = num >> 1;
            }
        }
        int res = 0;
        for (int j = 31; j >= 0; j--) {
            if (helper[j] % 3 != 0) {
                res += 1 << (31 - j);
            }
        }
        return res;
    }
}

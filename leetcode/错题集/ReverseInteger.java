package 错题集;

/**
 * 整数反转
 *
 * 给你一个 32 位的有符号整数 x ，返回将 x 中的数字部分反转后的结果。
 *
 * 如果反转后整数超过 32 位的有符号整数的范围 [−231,  231 − 1] ，就返回 0。
 *
 * 假设环境不允许存储 64 位整数（有符号或无符号）。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/reverse-integer
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class ReverseInteger {
    // 这道题里有要求，说了不允许存储64位正数，不然的话可以使用long，如下
    public int reverse(int x) {
        long res = 0;
        while (x != 0) {
            res = res * 10 + x % 10;
            x /= 10;
        }
        return (int)res == res ? (int)res : 0;
    }

    // 我最开始的思路
    public int reverse2(int x) {
        boolean flag = x < 0 ? true : false;
        x = Math.abs(x);
        int res = 0;
        while (x > 0) {
            if (res > Integer.MAX_VALUE / 10) {   // 因为下面要乘10，所以这里需要这么比较
                return 0;
            }
            res = res * 10 + x % 10;
            x /= 10;
        }
        return flag ? -res : res;
    }

    // 另一种方式来判断越界：通过前后两次的数值
    public int reverse3(int x) {
        boolean flag = x < 0 ? true : false;
        x = Math.abs(x);
        int res = 0;
        while (x > 0) {
            int temp = res;          // 记录上一次计算的结果
            res = res * 10 + x % 10;
            if (temp != res / 10) {   // 如果发生溢出，那么res和它的前一次计算*10肯定是不相等
                return 0;
            }
            x /= 10;
        }
        return flag ? -res : res;
    }

    // 使用异常处理
    public int reverse4(int x) {
        int flag = x < 0 ? -1 : 1;
        StringBuilder sb = new StringBuilder(String.valueOf(x * flag));  // 这里要把它变成整数，不然反转之后会变成321-
        String s = sb.reverse().toString();
        try {
            int res = Integer.valueOf(s) * flag;
            return res;
        } catch (Exception e) {
            return 0;
        }
    }
}

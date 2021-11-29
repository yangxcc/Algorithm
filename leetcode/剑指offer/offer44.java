package 剑指offer;

/**
 * 数字序列中某一位的数字
 * 数字以0123456789101112131415…的格式序列化到一个字符序列中。在这个序列中，
 * 第5位（从下标0开始计数）是5，第13位是1，第19位是4，等等。
 *
 * 链接：https://leetcode-cn.com/problems/shu-zi-xu-lie-zhong-mou-yi-wei-de-shu-zi-lcof
 * */
public class offer44 {
    /**
     * 位数   数字个数     能表示的范围
     *  1       9             9      （没有算上0）
     *  2      90            180
     *  3      900           2700
     *  4      9000          36000
     *  ...
     *  */
    public int findNthDigit(int n) {  // 为了算上0，后面n-1就行了
        int digit = 1;
        int start = 1;         // 开始的地方
        int count = 9;         // 表示多少位A
        while (n > count) {  // 先找到n对应的那个数
            n -= count;
            digit++;
            start *= 10;
            count = start * 9 * digit;
        }
        long num = start + (n - 1) / digit;   // 找到那个数
        return String.valueOf(num).charAt((n - 1) % digit) - '0';
    }
}

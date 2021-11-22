package 剑指offer;

/**
 * 数值的整数次方，也就是实现Math.pow(x, n)函数
 *
 * 题目链接：https://leetcode-cn.com/problems/shu-zhi-de-zheng-shu-ci-fang-lcof/
 * */
public class offer16 {

    // 这道题在leetcode中是middle难度，这种解法太妙了！！
    // 直接使用for循环，超时，这道题的本意也不是让用for循环
    public double myPow(double x, int n) {
        if (n == 0) return 1;
        if (n == 1) return x;
        if (n == -1) return 1 / x;

        double half = myPow(x, n / 2);
        double oddAndEven = myPow(x, n % 2);
        return half * half * oddAndEven;
    }
}

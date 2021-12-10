package 剑指offer;

/**
 * 求 1+2+...+n
 * 求 1+2+...+n ，要求不能使用乘除法、for、while、if、else、switch、case等关键字及条件判断语句（A?B:C）。
 */
public class offer64 {
    // 不能使用乘除，也不能使用for循环，只能够使用递归
    // 但是递归的base case不能使用if
    // 所以使用逻辑运算符 && ，它具有短路特性
    int res = 0;
    public int sumNums(int n) {
        boolean x = n > 1 && sumNums(n - 1) > 0;
        res += n;
        return res;
    }
}

package basic;

/**
 * 异或的用法
 *
 * 异或的运算：相同为0，不同为1，因此异或运算可以看成是无进位相加
 *
 * 异或的性质
 * 1. N ^ 0 = N
 * 2. N ^ N = 0
 *
 * 位运算是比加减乘除这种运算快的*/

// 异或运算的一个用处就是可以根据性质用于数字交换
/**
 * 但是需要注意的是，当使用异或运算交换数据顺序的时候，必须要保证两个数字指向的不是同一块内存，
 * 因为如果是同一块内存的话，在进行交换的时候会把这个位置上的值变成0，因为 N ^ N = 0
 * */

public class eor {

    public static void main(String[] args) {
        int a = 2;
        int b = 4;
        a = a ^ b;
        b = a ^ b;
        a = a ^ b;
        System.out.println("a = " + a);
        System.out.println("b = " + b);
    }
}

package basic;

import java.util.Arrays;

/**
 * 问题一：在一个整型数组中只有一个数出现了奇数次，其他的数都出现了偶数次，怎么找到这个出现了奇数次的数
 * 问题二：有两个数出现了奇数次，其他数出现了偶数次，怎么找到这两个数
 * */
public class EorCase {

    public static int case01(int[] arr) {
        // 根据异或的性质，偶数次的数异或之后都变成了0
        // 只剩下了奇数次的数
        int result = 0;  // 这里一定要是0，因为 0 ^ N = N
        for (int i = 0; i < arr.length; i++) {
            result = result ^ arr[i];
        }
        return result;
    }

    public static  int[] case02(int[] arr) {
        int eor = 0;
        for (int i = 0; i < arr.length; i++) {
            eor = eor ^ arr[i];
        }
        // 此时这个result是两个奇数次数的异或值，即result = a ^ b
        // 因为a != b，所以result != 0
        // 这也就说明了 a和b换成二进制后一定有一位上的值是不一样的，即一个是1，一个是0
        // 我就假设第 i 位置上 a是1，b是0
        // 这里使用的方法是把最右边的1给找出来
        int rightOne = eor & (~eor + 1); // 这是一种固定写法，可以举例证明
        // 这个rightOne得出来的结果只有一个位置是1，其余的地方都是0，出现1的那个位置就是eor最右边的那个1
        // 这个1就表示a和b在这个位置上一个1一个0
        int onlyOne = 0;
        for (int cur : arr) {
            if ((cur & rightOne) == 0) {
                onlyOne = onlyOne ^ cur;
            }
        }
        int onlyTwo = onlyOne ^ eor;   // b = a ^ b ^ a
        return new int[]{onlyOne,onlyTwo};
    }


    public static void main(String[] args) {
        int[] arr = {1,1,1,2,2,2,2,2,2};
        int case01 = case01(arr);
        System.out.println(case01);

        int[] arr02 = {1,2,3,3,3,3};
        int[] res = case02(arr02);
        System.out.println(Arrays.toString(res));
    }
}

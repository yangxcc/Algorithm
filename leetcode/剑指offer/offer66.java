package 剑指offer;

/**
 * 构建乘积数组
 *
 * 给定一个数组 A[0,1,…,n-1]，请构建一个数组 B[0,1,…,n-1]，
 * 其中 B[i] 的值是数组 A 中除了下标 i 以外的元素的积, 即 B[i]=A[0]×A[1]×…×A[i-1]×A[i+1]×…×A[n-1]。不能使用除法。
 *
 * 输入: [1,2,3,4,5]
 * 输出: [120,60,40,30,24]
 */
public class offer66 {
    public int[] constructArr(int[] a) {
        int[] res = new int[a.length];
        // 先从左开始乘一遍
        int cur = 1;
        for (int i = 0; i < a.length; i++) {
            res[i] = cur;
            cur *= a[i];
        }

        // 再从右边开始乘一遍
        cur = 1;
        for (int i = a.length - 1; i >= 0; i--) {
            res[i] *= cur;
            cur *= a[i];
        }
        return res;
    }
}

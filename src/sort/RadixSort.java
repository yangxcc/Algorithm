package sort;

import java.util.Arrays;

/**
 * 桶排序的思想是划分多个范围相同的区间，每个子区间自排序，最后合并。
 * 桶排序是计数排序的扩展版本，计数排序可以看成每个桶只存储相同元素，而桶排序每个桶存储一定范围的元素，
 * 通过映射函数，将待排序数组中的元素映射到各个对应的桶中，对每个桶中的元素进行排序，最后将非空桶中的元素逐个放入原序列中
 *
 * 桶排序需要尽量保证元素分散均匀，否则当所有数据集中在同一个桶中时，桶排序失效
 */
public class RadixSort {


    // 其实这个方法叫基数排序更加适合
    public static void main(String[] args) {
        int[] arr = new int[]{51,23,16,75,43,29,100,23};
        radixSort(arr,0,arr.length-1,maxbits(arr));
        System.out.println(Arrays.toString(arr));
    }



    /**
     * 排序arr中start到end这段距离的数字
     * @param arr   需要排序的数组
     * @param start 起始位置
     * @param end   终点
     * @param digit 位，通过上面的maxbits求出来
     */
    public static void radixSort(int[] arr, int start, int end, int digit) {
        final int radix = 10;    // 基数
        int i = 0, j = 0;
        // 辅助空间
        int[] buckets = new int[end - start + 1];
        // 有多少个十进制位，就要发生多少次的入桶出桶
        for (int d = 1; d <= digit; d++) {
            // 创建10个空间
            // count[0]表示当前位（d位）是0的数字有多少个
            // count[1]表示当前位（d位）是0和1的数字有多少个
            // count[2]表示当前位（d位）是0.1.2的数字有多少个
            // count[i]表示当前位（d位）是0~i的数字有多少个
            int[] count = new int[radix];
            for (i = start; i <= end; i++) {
                j = getDigit(arr[i],d);
                count[j]++;      // 先计数
            }
            for (i = 1; i < radix; i++) {
                count[i] = count[i] + count[i-1];    // 做成前缀和，至此count就有了上面注释的意思
            }
            // 从右往左进行遍历，这个就相当于出桶操作
            // 为什么要从右往左！
            // 因为最右边的数是最后出桶的，所以应该先去把这个数给放到count[j]-1的这个位置，因为这个位置是保证它
            // 是最后入桶的，比如一个数组[...,52,...,62,...]，count[2] = 5，所以应该先遍历62，把62给放到buckets数组
            // 的4位置，到了52的时候，把它放到3位置，依然是52在62的前面，如果是从左往右，没办法根据count[j]来保持这个顺序
            // 要保证每次桶排序的顺序被保存下来
            for (i = end; i >= start; i--) {
                j = getDigit(arr[i],d);
                buckets[count[j] - 1] = arr[i];
                count[j]--;
            }
            // 保留这次入桶出桶做出来的顺序，进行下一次的入桶出桶操作
            for (i = start, j = 0; i <= end; i++,j++) {
                arr[i] = buckets[j];
            }
        }

    }

    // d = 1，取个位数
    // d = 2，取十位数
    // ...
    public static int getDigit(int num, int d) {
        return ((num / ((int)Math.pow(10,d-1))) % 10);
    }

    public static int maxbits(int[] arr) {    // 找到这个数组中最大有多少位？比如[1,34,100]，最大就是3位
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < arr.length; i++) {
            max = max > arr[i] ? max : arr[i];
        }
        int res = 0;
        while (max != 0) {
            res++;
            max /= 10;
        }
        return res;
    }

}

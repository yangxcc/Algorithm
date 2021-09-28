package sort.example;

import java.util.MissingFormatArgumentException;

public class ExampleForMergeSort {
    /**
     * case1：小和问题
     *      在一个数组中，每一个数左边比当前数小的数累加起来，叫做这个数的小和，求一个数组的小和
     * 例子：[1,3,4,2,5]  1的左边没有比1小的数，所以小和为0，3的左边比3小的数有一个1，4的左边比4小的数有2个，1+3=4
     * ...
     * 所以上述数组的和为 1+1+3+1+1+3+4+2=16
     *
     * 解法 1：这道题当然可以使用暴力解法，使用两重循环，时间复杂度是O(N^2)
     *
     * 解法2：这道题其实就是可以改写 归并排序
     * 以[1,3,4,2,5]为例
     *              [1,3,4,2,5]
     *                    / \
     *                  /    \
     *              [1,3,4]  [2,5]
     *                /\        /\
     *               /  \      /  \
     *           [1,3]  [4]   [2] [5]
     *             /\
     *            /  \
     *          [1]  [3]
     *   （1）  1和 3 merge，会产生一个 1
     *   （2） merge之后变成了[1,3]，这个小数组会和4进行merge，产生一个1和一个3
     *   （3） merge之后变成了[1,3,4]
     *    (4)  2和 5 merge，会产生一个2
     *   （5） merge之后变成了[2,5]，然后[1,3,4]会和[2,5]进行merge
     *   （6） merge的过程中，因为2比1大，所以会产生两个1，这里无需遍历右侧的全部数据，可以通过下标来进行计算
     *   （7） 左侧数组的指针移动到了3那个位置，右侧只有一个5比他大，所以会产生一个3
     *   （8） 左侧数组的指针继续移动到了4那个位置，右侧只有一个5比他大，所以会产生一个4
     *   所以，小和 = 1+1+3+2+1+1+3+4=16
     *
     *   这种方式的时间复杂度就是O(N*logN)
     * */

    public static int smallSum(int[] arr) {
        if (arr == null || arr.length < 2) {
            return Integer.MIN_VALUE;
        }
        return process(arr,0,arr.length-1);
    }

    public static int process(int[] arr, int L, int R) {
        if (L == R) {
            return 0;
        }
        int Mid = L + ((R - L) >> 1);
        return process(arr, L, Mid) +      // 左边的小和
                process(arr, Mid + 1, R) +  // 右边的小和
                merge(arr, L, Mid, R);         // 合并两个数组产生的小和
    }

    public static int merge(int[] arr, int L, int M, int R) {
        int i = 0;
        int tag1 = L;
        int tag2 = M + 1;
        int[] helper = new int[R - L + 1];
        int smallSum = 0;
        while (tag1 <= M && tag2 <= R) {
            smallSum += arr[tag1] <= arr[tag2] ? arr[tag1] * (R - tag2 + 1): 0;
            helper[i++] = arr[tag1] <= arr[tag2] ? arr[tag1++] : arr[tag2++];
        }
        while (tag1 <= M) {
            helper[i++] = arr[tag1++];
        }
        while (tag2 <= R) {
            helper[i++] = arr[tag2++];
        }
        return smallSum;
    }

    public static void main(String[] args) {
        int[] arr = {1,3,4,2,5};
        int res = smallSum(arr);
        System.out.println(res);
        int res02 = smallSumWorse(arr);
        System.out.println(res02);
    }


    /**
     * 暴力解法
     * */
    public static int smallSumWorse(int[] arr) {
        if (arr.length < 2 || arr == null) {
            return Integer.MIN_VALUE;
        }
        int smallSum = 0;
        for (int i = 0; i < arr.length; i++) {
            for (int j = i+1; j < arr.length; j++) {
                if (arr[i] < arr[j]) {
                    smallSum += arr[i];
                }
            }
        }
        return smallSum;
    }
}

package sort;

import java.util.Arrays;

/**
 * 归并排序的思想是合并两个有序的数组
 * */
public class MergeSort {

    public static void process(int[] arr, int L, int R) {
        if (arr.length == 0 || arr == null) {
            return;
        }
        if (L == R) {   // 递归出口这里必须是L == R，不能够是arr.length == 0 || arr == null 因为在递归过程中数组不会变成空的
            return;
        }
        int mid = L + ((R - L) >> 1); // 位运算一定是比加减乘除快的
        process(arr,L,mid);      // 给左侧排序
        process(arr,mid+1,R); // 给右侧排序
        merge(arr,L,mid,R);      // 归并左右两个有序数组
    }

    public static void merge(int[] arr, int Left, int Mid, int Right) {
        int[] helper = new int[Right-Left+1];
        int i = 0;
        int tag1 = Left;
        int tag2 = Mid + 1;
        while (tag1 <= Mid && tag2 <= Right) {
            helper[i++] = arr[tag1] <= arr[tag2] ? arr[tag1++] : arr[tag2++];
        }
        while (tag1 <= Mid) {
            helper[i++] = arr[tag1++];
        }
        while (tag2 <= Right) {
            helper[i++] = arr[tag2++];
        }
        // 现在这个helper中的数组就是排好序的了，我们再把他放回到arr中，因为我们的类型是void
        for (int j = 0; j < helper.length; j++) {
            arr[Left + j] = helper[j];
        }
    }

    public static void main(String[] args) {
        int[] arr = {1,2,4,5,23,7,3,10};
        process(arr,0,arr.length-1);
        System.out.println(Arrays.toString(arr));
    }
}


/**
 * 归并排序的时间复杂度
 * 可以使用Master公式来进行计算
 * T(N) = 2*T(N/2) + O(N)
 * a = 2, b = 2, d = 1
 * log(b,a) = 1 = d
 * 所以归并排序的时间复杂度是O(N*logN)
 * 为什么他会比冒泡排序和选择排序时间复杂度上好
 * 因为他没有浪费比较的过程，无论是冒泡还是选择排序，它比较N次只能够拍好一个数的位置
 * 而在归并排序中，每一次的比较都能够merge出一个大的数组，没有浪费掉比较的过程
 *
 * */

package sort;

import java.util.Arrays;

/**
 * 选择排序的思想就是通过遍历数据找到最大/最小的数字，把这个数字放到开头/结尾。一直重复这个过程，
 * 第二大/小的数字放到第二位/倒数第二位
 * 以此类推 ...
 * */
public class SelectSort {

    public static void selectSort(int[] arr) {
        if (arr.length < 2 || arr == null) {
            return ;
        }

        for (int i = 0; i < arr.length; i++) {
            // 第一层循环去找相应长度数组中的最小值的下标，根本不需要知道最小的数是啥，知道他的位置就行了
            int minIndex = i;
            for (int j = i + 1; j < arr.length; j++) {
                minIndex = arr[j] > arr[minIndex] ? minIndex : j;
            }
            swap(arr,i,minIndex);
        }
    }

    public static void swap(int[] arr, int index, int minIndex) {
        int temp = 0;
        temp = arr[index];
        arr[index] = arr[minIndex];
        arr[minIndex] = temp;
    }


    public static void main(String[] args) {
        int[] arr = {1,3,2,5,4,7,10,8};
        selectSort(arr);
        System.out.println(Arrays.toString(arr));
    }
}

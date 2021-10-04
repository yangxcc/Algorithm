package sort;

import java.util.Arrays;

/**
 * 插入排序的思想是假设我想知道第i位置的数应该放在什么地方，那么我就假设从0到i-1位置
 * 这一段的数组是有序的
 * */

// 往前看
public class InsertSort {

    public static void insertSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        for (int i = 1; i < arr.length; i++) {
//            for (int j = i - 1; j >= 0 && arr[j+1] < arr[j]; j--) {  // 0到i的数组是有序的了
//                swap(arr,j+1,j);   // 注意交换的数字,在插入排序一定要注意是j+1和j比较，相邻的两个数据比较
//            }
            for (int j = i - 1; j >= 0; j--) {
                if (arr[i] < arr[j]) {
                    swap(arr,i,j);
                }
            }
        }
    }

    public static void swap(int[] arr, int index01, int index02) {
        int temp = 0;
        temp = arr[index01];
        arr[index01] = arr[index02];
        arr[index02] = temp;
    }

    public static void main(String[] args) {
        int[] arr = {1,3,2,5,4,7,10,8};
        insertSort(arr);
        System.out.println(Arrays.toString(arr));
    }
}

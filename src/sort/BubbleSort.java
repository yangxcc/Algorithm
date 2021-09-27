package sort;

import java.util.Arrays;

/**
 * 冒泡排序的思想是把大数下沉或者小数上浮，也是每次循环只能够确定一个数的顺序
 * */
public class BubbleSort {

    public static void bubbleSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        for (int i = arr.length - 1; i > 0 ; i--) { // 控制数组的大小
            for (int j = 0; j < i; j++) {
                if (arr[j] > arr[j+1]) {
                    swap(arr,j,j+1);
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
        bubbleSort(arr);
        System.out.println(Arrays.toString(arr));
    }
}

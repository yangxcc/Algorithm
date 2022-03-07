package 错题集;

import static sort.QuickSort.swap;

/**
 * 在无序数组中找中位数
 *
 */
public class MedianOfNotSortedArray {
    public static int findMedian(int[] arr) {
        int index = -1;
        int left = 0;
        int right = arr.length - 1;

        int mid = left + (right - left) / 2;

        while (index != mid) {
            index = partition(arr, left, right)[0];
            if (index < mid) {
                // 去右边搜索
                left = index + 1;
            } else {
                right = index - 1;
            }
        }
        return arr.length % 2 == 0 ? (arr[index] + arr[index + 1]) / 2 : arr[index];
    }

    public static int[] partition(int[] arr, int L, int R) {
        int left = L - 1;
        int right = R;

        while (L < right) {   // L表示的是当前数的位置
            if (arr[L] < arr[R]) {
                swap(arr,++left,L++);
            } else if (arr[L] > arr[R]) {
                swap(arr,--right,L);
            } else {
                L++;
            }
        }
        swap(arr,right,R);   // 因为是以最后的这个数为基准，所以要将它给换到中间的等于区域
        return new int[]{left + 1,right};
    }

//    public static int partition(int[] arr, int left, int right) {
//        if (left > right) {
//            return -1;
//        }
//
//        int key = arr[right];
//
//        while (left < right) {
//
//            while (left < right && arr[left] < key) {
//                left++;
//            }
//            while (left < right && arr[right] > key) {
//                right--;
//            }
//            if (left < right) {
//                swap(arr, left, right);
//            }
//        }
//        swap(arr, right, arr.length - 1);
//        return left;
//    }

    public static void main(String[] args) {
        int[] arr = new int[]{1,4,2,100,101, 3, 102, 9};
        System.out.println(findMedian(arr));
    }
}

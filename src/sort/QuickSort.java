package sort;

import java.util.Arrays;

public class QuickSort {


    /**
     * 荷兰国旗问题 v1
     * 给定一个数组arr，和一个数num，请把小于等于num的数放在左边，大于num的数放在右边，要求额外的空间复杂度O(1)
     * 时间复杂度O(N)
     *
     *
     * 解决方法
     * 弄一个变量 i 来表示小于等于区域的右边界，最开始指向 -1 位置
     * 1.如果 j 指向的位置的数 ≤ num，那么有边界往右移，即i++，这个数不动
     * 2.如果 j 指向的位置的数 > num，j++，右边界不动，直到碰到比num小的数，然后将这个数和小于等于区域的下一个数做交换，i++
     * 
     * */
    public static void quickSortV1(int[] arr, int num) {
        if (arr.length < 2 || arr == null) {
            return;
        }
        int zone = -1;
        for (int j = 0; j < arr.length; j++) {
            if (arr[j] <= num) {
                swap(arr, zone+1, j);
                zone++;
            }
        }
    }


    /**
     * 荷兰国旗 V2
     * V1中 ≤ 区域 没有分开，在V2中想要严格划分三块区域，小于，等于，大于
     *
     * 解决方法
     * i 表示当前指向的数组中的数，leftZone表示小于区域的边界，rightZone表示大于区域的边界
     * （1） 如果 i < num, i 指向的数和leftZone区域指向的下一个数做交换，然后i++，leftZone++
     * （2） 如果 i == num，i++
     * （3） 如果 i > num，i 指向的数和rightZone的前一个数进行交换，i不动，rightZone--
     * */
    public static void quickSortV2(int[] arr, int num) {
        if (arr.length < 2) {
            return;
        }
        int leftZone = -1;
        int rightZone = arr.length;
        for (int i = 0; i < arr.length; ) {
            if (i == rightZone) {
                break;          // 一定要有这个跳出循环的条件，不然当排好序后会打乱这个顺序
            }
            if (arr[i] < num) {
                swap(arr,i,++leftZone);
                i++;
            } else if (arr[i] == num) {
                i++;
            } else {
                swap(arr,i,--rightZone);
            }
        }
    }

    /**
     * 真正的快排
     *
     * 真正的快排是随机的选出一个数来作为基准，也就是荷兰国旗中的num，而不能去选定一个数，因为选定一个数可以人为地找出最差的情况
     *
     * 比如[1,2,3,4,5,6,7,8]，我选了8做基准，那么此时，时间复杂度为O(N^2)
     * 所以要随机选择一个数，这样的话快排的时间复杂度变成了O(N*logN)
     *
     * 同理，快排的空间复杂度最差也是O(N)，随机选择数的时候就变成了O(log N)
     * */
    public static void quickSortV3(int[] arr) {
        if (arr.length < 2 || arr == null) {
            return;
        }
        quickSort(arr,0,arr.length-1);
    }

    public static void quickSort(int[] arr, int L, int R) {
        // 一定要随机选择一个数，才能够保证快排的时间复杂度
        if (L < R) {
            swap(arr, L + ((R - L) >> 1), R);  // 随机选出一个位置和最后一个位置的数做交换，那么后面就用最后的这个位置当成基准num
            int[] p = partition(arr, L, R);    // partition 函数返回的是 <区域的右边界和 >区域的左边界
            quickSort(arr, L, p[0] - 1);
            quickSort(arr, p[1] + 1, R);
        }
    }

    // 荷兰国旗问题
    public static int[] partition(int[] arr, int L, int R) {
        int left = L - 1;
        int right = R;
        for (int i = 0; i < arr.length;) {
            if (i >= right) {
                break;
            }
            if (arr[i] < arr[R]) {
                swap(arr, i, ++left);
                i++;
            } else if (arr[i] == arr[R]){
                i++;
            } else {
                swap(arr, i, --right);
            }
        }
        swap(arr,right,R);   // 因为是以最后的这个数为基准，所以要将它给换到中间的等于区域
        return new int[]{left + 1,right};
    }


    public static void swap (int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void main(String[] args) {
        int[] arr = {1,4,6,9,5,2,3};
        int num = 5;
        quickSortV1(arr,num);
        System.out.println("quickSortV1: " + Arrays.toString(arr));
        int[] arr2 = {1,4,6,9,5,2,3,5};
        quickSortV2(arr2,num);
        System.out.println("quickSortV2: " + Arrays.toString(arr2));
        int[] arr3 = {1,4,6,9,5,2,3,5};
        quickSortV3(arr3);
        System.out.println(Arrays.toString(arr3));
    }
}

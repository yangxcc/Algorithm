package sort;

import java.util.Arrays;

/**
 * 堆排序
 * 时间复杂度O(N*log N)
 * 空间复杂度O(1)
 * 他是这些排序算法中，唯一一个只需要常数阶额外空间的算法
 */
public class HeapSort {

    public static void main(String[] args) {
        int[] arr = new int[]{1, 3, 5, 2, 9, 7, 4};
        heapSort(arr);
        System.out.println(Arrays.toString(arr));
    }


    /***
     * heapInsert 是堆中的一个重要方法
     * 它的作用是将一个数组给排列成一个大顶堆/小顶堆
     * 大顶堆：首先是一颗完整二叉树，而且每一颗子树的父节点都是大于子节点的
     * 小顶堆：首先是一颗完整二叉树，而且每一颗子树的父节点都是小于子节点的
     * 数组中某个位置 i 的左子节点 是 2*i+1，右子节点是2*i+2，父节点是 （i-1）/2
     *
     * @param arr 想要化成大顶堆的数组
     * @param index 数目前处在index位置上
     *
     * heapInsert往上移动
     */
    public static void heapInsert(int[] arr, int index) {
        if (arr.length < 2 || arr == null) {
            return;
        }
        while (arr[index] > arr[(index - 1) / 2]) {  // 来一个数就跟他的父节点比较一次
            swap(arr, index, (index - 1) / 2);
            index = (index - 1) / 2;
            // System.out.println("index=" + index);
        }
        /**
         * 这个算法的过程是
         * 当把数组中的一个数往大根堆里面送时，要比较他和父节点的大小，如果父节点的数小于它了，那么将交换数据
         * 数据被交换之后，仍然是需要继续向上面的数据进行比较的...
         * */
    }

    /***
     * heapify是堆中另一个重要的方法
     * 这个方法是向下走的，比如我把大根堆中的最大数（也就是堆顶）给移出去，之后我还需要对这个堆重新进行变化，然他在成为另一个大根堆
     * @param arr  数组
     * @param index  当前所在位置
     * @param heapSize 堆的范围
     */
    public static void heapify(int[] arr, int index, int heapSize) {
        if (arr.length < 2 || arr == null) {
            return;
        }
        int left = 2 * index + 1; // 左孩子的下标
        while (left < heapSize) {
            // index的左右孩子谁大选谁
            // left + 1 < heapSize的作用就是看看还有没有右孩子
            int largest = left + 1 < heapSize && arr[left + 1] > arr[left] ? left + 1 : left;
            // 父节点和孩子节点谁大
            largest = arr[largest] > arr[index] ? largest : index;
            if (largest == index) {
                break;
            }
            swap(arr, largest, index);
            index = largest;
            left = 2 * index + 1;
        }
    }

    public static void heapSort(int[] arr) {
        if (arr.length < 2 || arr == null) {
            return;
        }
        for (int i = 0; i < arr.length; i++) { // O(log N)
            heapInsert(arr, i);             // 给arr变成一个大顶堆，O(N)
        }
        // 这里使用的是heapInsert变成大顶堆，其实是用heapify转换成大顶堆是更快的
//        for (int i = arr.length - 1; i >= 0; i--) {
//            heapify(arr,i,arr.length);
//        }
        int heapSize = arr.length;
        swap(arr, 0, --heapSize);             // 对于大顶堆而言，0位置的数就是最大的
        while (heapSize > 0) {                  // O(N)
            heapify(arr, 0, heapSize);   // O(log N)
            swap(arr, 0, --heapSize);
        }
    }

    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}

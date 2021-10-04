package sort.example;

import java.util.PriorityQueue;

/**
 * 已知一个几乎有序的数组，几乎有序是指，如果把数组排好顺序的话，每个元素移动的距离可以不超过k
 * 并且k相对数组来说是比较小的，请选择一个合适的算法对这个数组进行排序
 *
 * 如果使用堆排序那么时间复杂度是 O(N*log k)
 * */
public class ExampleForHeapSort {
    public static void main(String[] args) {
        int[] arr = new int[]{2,3,1,};
    }

    public static void sortedArrDistanceLessK(int[] arr, int k) {
        PriorityQueue<Integer> heap = new PriorityQueue<>();   // 在Java中优先级队列默认就是小根堆
        int index = 0;
        for (; index <= Math.min(arr.length,k); index++) {   // 把0~k范围内的数加入到小根堆中，让他们组成一个小根堆
            heap.add(arr[index]);
        }
        int i = 0;
        for (; index < arr.length; i++, index++) {
            heap.add(arr[index]);
            arr[i] = heap.poll();
        }
        while (!heap.isEmpty()) {
            arr[i++] = heap.poll();
        }

    }
}

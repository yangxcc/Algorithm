package 合并升序链表和数组;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 合并K个升序数组
 *
 * 一共有三种思路
 *   1. 将这K个升序数组依次遍历，放入到一个大数组中，然后对这个数组进行sort，时间复杂度为NlogN
 *   2. 对着K个数组两两归并，时间复杂度为Nlogk
 *   3. 使用小根堆，采用和合并K个升序链表一样的思路，时间复杂度为 nlogn
 */
public class MergeKSortedNums {
    /**
     * 使用小根堆的具体思路
     *      创建一个大小为n*k的数组保存最后的结果
     *      创建一个大小为k的最小堆，堆中元素为k个数组中的每个数组的第一个元素
     *      重复下列步骤n*k次：
     *          每次从堆中取出最小元素（堆顶元素），并将其存入输出数组中
     *          用堆顶元素所在数组的下一元素将堆顶元素替换掉，
     *          如果数组中元素被取光了，将堆顶元素替换为无穷大。每次替换堆顶元素后，重新调整堆
     */
    public int[] mergeKSortedArrays(int[][] arrays) {
        PriorityQueue<Node> pq = new PriorityQueue<Node>(new Comparator<Node>(){
            @Override
            public int compare(Node o1, Node o2){
                return o1.val - o2.val;
            }
        });

        // 初始化pq，同时求出arrays中元素的个数
        int total = 0;
        for (int i = 0; i < arrays.length; i++) {
            Node node = new Node(arrays[i][0], i, 0);
            pq.add(node);
            total += arrays[i].length;
        }

        int[] res = new int[total];
        int index = 0;

        while (!pq.isEmpty()) {
            Node ele = pq.poll();
            res[index++] = ele.val;
            if (ele.idx + 1 < arrays[ele.arrIndex].length) {
                ele.idx += 1;
                pq.add(new Node(arrays[ele.arrIndex][ele.idx], ele.arrIndex, ele.idx));
            }
        }
        return res;
    }

    // 使用两两归并处理，时间复杂度为n*logk
    public int[] mergeKSortedArrays2(int[][] arrays) {
        return merge(arrays, 0, arrays.length - 1);
    }

    public int[] merge(int[][] arrays, int start, int end) {
        if (start == end) {
            return arrays[start];
        }
        int mid = start + (end - start) / 2;
        int[] left = merge(arrays, start, mid);
        int[] right = merge(arrays, mid + 1, end);

        int[] res = new int[left.length + right.length];
        int l = 0, r = 0, index = 0;
        while (l < left.length && r < right.length) {
            if (left[l] < right[r]) {
                res[index++] = left[l++];
            } else {
                res[index++] = right[r++];
            }
        }

        while (l < left.length) {
            res[index++] = left[l++];
        }

        while (r < right.length) {
            res[index++] = right[r++];
        }
        return res;
    }
}

// 与合并多个升序链表不同的是，因为链表中的节点本身就存在next指针，能够指向下一个节点
// 而数组中却没办法自动跳到数组的下一个索引，所以我们需要创建一个节点类，放入到小根堆中
class Node {
    int val;
    int arrIndex;   // 在arrays中的位置，也就是哪个数组，二维数组中的索引
    int idx;        // 用于定位在特定数组中的特定位置，一维数组中的索引

    public Node(int val, int arrIndex, int idx) {
        this.val = val;
        this.arrIndex = arrIndex;
        this.idx = idx;
    }
}

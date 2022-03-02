package 贪心算法.区间;

import java.util.Arrays;
import java.util.Comparator;

/**
 * 无重叠区间，给定一个区间的集合 intervals ，
 *
 * 其中 intervals[i] = [starti, endi] 。返回 需要移除区间的最小数量，使剩余区间互不重叠 。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/non-overlapping-intervals
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class NonOverlappingIntervals {
    public int eraseOverlapIntervals(int[][] intervals) {
        // 根据区间的end进行排序，从小到大，因为end越小，表示留给右边的范围越大
        // 越不容易产生交叉区间，尽可能多的选出非交叉区间，总区间个数减去非交叉区间的个数就是答案
        Arrays.sort(intervals, new Comparator<int[]>(){
            @Override
            public int compare(int[] o1, int[] o2) {
                if (o1[1] == o2[1]) {
                    return o1[0] - o2[0];
                } else {
                    return o1[1] - o2[1];
                }
            }
        });

        int edge = intervals[0][1];
        int count = 1;  // 非交叉区间的个数
        for (int i = 1; i < intervals.length; i++) {
            if (edge <= intervals[i][0]) {
                count++;
                edge = intervals[i][1];
            }
        }
        return intervals.length - count;
    }
}

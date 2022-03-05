package 滑动窗口;

import java.util.*;

/**
 * 你有 k 个 非递减排列 的整数列表。找到一个 最小 区间，使得 k 个列表中的每个列表至少有一个数包含在其中。
 *
 * 我们定义如果 b-a < d-c 或者在 b-a == d-c 时 a < c，则区间 [a,b] 比 [c,d] 小。
 *
 * 输入：nums = [[4,10,15,24,26], [0,9,12,20], [5,18,22,30]]
 * 输出：[20,24]
 * 解释：
 * 列表 1：[4, 10, 15, 24, 26]，24 在区间 [20,24] 中。
 * 列表 2：[0, 9, 12, 20]，20 在区间 [20,24] 中。
 * 列表 3：[5, 18, 22, 30]，22 在区间 [20,24] 中。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/smallest-range-covering-elements-from-k-lists
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class SmallestRangeCoveringElementsFromKLists {
    // 看的评论区的思路
    // 先把k个列表合并到一个列表或者数组中，
    // 并记录每个数是属于哪个集合
    // 然后在用滑动窗口，即问题reduce到了leetcode 76
    public static int[] smallestRange(List<List<Integer>> nums) {
        int k = nums.size();
        int count = 0;
        for (List<Integer> num : nums) {
            count += num.size();
        }
        Pair[] merge = new Pair[count];
        int index = 0;
        for (int i = 0; i < nums.size(); i++) {
            for (int j = 0; j < nums.get(i).size(); j++) {
                merge[index++] = new Pair(nums.get(i).get(j), i);
            }
        }
        Arrays.sort(merge, new Comparator<Pair>(){
            @Override
            public int compare(Pair o1, Pair o2) {
                return o1.num - o2.num;
            }
        });

//        for (int i = 0; i < merge.length; i++) {
//            System.out.printf("value=" + merge[i].num + "," + "idx=" + merge[i].idx);
//            System.out.println();
//        }

        // 使用滑动窗口了
        int left = 0;
        int right = 0;
        // key是列表索引，value是列表中的数
        HashMap<Integer, List<Pair>> window = new HashMap<>();
        int[] res = new int[2];
        res[0] = 0;
        res[1] = Integer.MAX_VALUE;

        while (right < merge.length) {
            Pair c = merge[right];
            right++;

            // 加入窗口
            if (window.get(c.idx) == null) {
                List<Pair> temp = new ArrayList<>();
                temp.add(c);
                window.put(c.idx, temp);
            } else {
                List<Pair> temp = window.get(c.idx);
                temp.add(c);
                window.put(c.idx, temp);
            }

            while (window.size() == k) {
                // 更新条件的位置写错了，一共就k个列表，按照我对hashmap的定义window的size最大也就是k了
                // 当window.size()等于k的时候，就说明已经满足要求了，window里面覆盖了每个列表至少一个元素
                // 所以更新区间，应该在这个里面进行，而不是外面，外面的都是不符合要求的

                // 更新区间
                if (res[1] - res[0] > merge[right - 1].num - merge[left].num) {
                    res[1] = merge[right - 1].num;
                    res[0] = merge[left].num;
                } else if (res[1] - res[0] == merge[right - 1].num - merge[left].num) {
                    if (left < res[0]) {
                        res[1] = merge[right - 1].num;
                        res[0] = merge[left].num;
                    }
                }
                Pair d = merge[left];
                left++;

                List<Pair> temp = window.get(d.idx);
                temp.remove(d);
                if (temp.size() == 0) {
                    window.remove(d.idx);
                }
            }

//            // 更新区间
//            if (res[1] - res[0] > right - left) {    // 而且这里不是right-left，而是他们分别指向的数！
//                res[1] = merge[right - 1].num;
//                res[0] = merge[left].num;
//            } else if (res[1] - res[0] == right - left) {
//                if (left < res[0]) {
//                    res[1] = merge[right - 1].num;
//                    res[0] = merge[left].num;
//                }
//            }
        }
        return res;
    }

    public static void main(String[] args) {
        List<List<Integer>> nums = new ArrayList<>();
        List<Integer> t1 = new ArrayList<>();
        List<Integer> t2 = new ArrayList<>();
        List<Integer> t3 = new ArrayList<>();
        // 4,10,15,24,26
        int[] num1 = {4,10,15,24,26};
        for (int num : num1) {
            t1.add(num);
        }
        int[] num2 = {0,9,12,20};
        for (int num : num2) {
            t2.add(num);
        }
        int[] num3 = {5,18,22,30};
        for (int num : num3) {
            t3.add(num);
        }

        nums.add(t1);
        nums.add(t2);
        nums.add(t3);

        smallestRange(nums);
    }
}

class Pair {
    int num;
    int idx;
    public Pair(int num, int index) {
        this.num = num;
        this.idx = index;
    }
}

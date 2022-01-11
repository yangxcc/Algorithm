package dp.加权有向图中的最短路径;

import java.util.*;

/**
 * 现在给定所有的城市和航班，以及出发城市 src 和目的地 dst，
 * 你的任务是找到出一条最多经过 k 站中转的路线，使得从 src 到 dst 的 价格最便宜 ，并返回该价格。 如果不存在这样的路线，则输出 -1。
 *
 */
public class CheapestFlightsWithinKStops {
    /**
     *
     * @param n  城市的个数
     * @param flights 二维数组，[[0,1,100],[1,2,100],[0,2,500]]，[0,1,100]表示从0出发到1的路径是100
     * @param src  出发地
     * @param dst  目的地
     * @param k    中转站的最多个数
     * @return     最小值
     *
     * 先使用bfs的思路
     */
    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
        // 这个题和bfs框架不一样的是，因为有带权图，所以需要使用优先级队列
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        // 看看是不是已经访问过了，避免走回头路
        HashSet<Integer> visited = new HashSet<>();
        queue.add(src);
        visited.add(src);

        int res = Integer.MAX_VALUE;

        while(!queue.isEmpty()) {
            int sz = queue.size();
            for (int i = 0; i < sz; i++) {

            }
        }

        if (!visited.contains(dst)) {
            return -1;
        } else {
            return res;
        }
    }


    /**
     * 使用动态规划的思路求解
     * */
    int begin;  // 起始点
    int end;    // 终点
    HashMap<Integer, List<int[]>> record;   // 用来记录每个点由哪些点能够到达，以及到达的费用
    int[][] memo;

    public int findCheapestPrice2(int n, int[][] flights, int src, int dst, int k) {
        this.begin = src;
        this.end = dst;
        this.record = new HashMap<>();
        for (int[] flight : flights) {
            int from = flight[0];
            int to = flight[1];
            int price = flight[2];
            if (!record.containsKey(to)) {
                record.put(to, new LinkedList<>());
            }
            record.get(to).add(new int[]{from, price});
        }
        k++;   // 题目中的k给的是能够中转的地点个数，而dp中的k是步数，所以需要加上1
        memo = new int[n][k + 1];
        for (int[] row : memo) {
            Arrays.fill(row, -2);
        }
        return dp(dst, k);
    }

    // 表示的是从起始点到dst，在有限步骤k以内，能够得到的最小费用
    public int dp(int dst, int k) {
        if (dst == begin) {
            return 0;
        }
        if (k == 0) {
            return -1;
        }
        if (memo[dst][k] != -2) {
            return memo[dst][k];
        }
        int fee = Integer.MAX_VALUE;
        if (record.containsKey(dst)) {
            // 看看dst周围有哪些元素
            for (int[] ele : record.get(dst)) {
                int from = ele[0];
                int price = ele[1];
                int subProblem = dp(from, k - 1);
                if (subProblem != 1) {
                    fee = Math.min(fee, subProblem + price);
                }
            }
        }
        memo[dst][k] = fee == Integer.MAX_VALUE ? -1 : fee;
        return memo[dst][k];
    }
}

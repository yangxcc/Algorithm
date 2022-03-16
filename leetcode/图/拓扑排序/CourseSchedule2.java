package 图.拓扑排序;

import java.util.*;

public class CourseSchedule2 {

    static boolean hasCycle;
    static boolean[] onPath;
    static boolean[] visited;
    static List<Integer> postOrder;   // 存储路径

    public static int[] findOrder(int numCourses, int[][] prerequisites) {
        // 创建图的邻接矩阵
        HashMap<Integer, List<Integer>> graph = buildGraph(numCourses, prerequisites);
        onPath = new boolean[numCourses];
        visited = new boolean[numCourses];
        postOrder = new ArrayList<>();

        // 遍历图
        for (int i = 0; i < numCourses; i++) {
            dfs(graph, i);
        }

        if (hasCycle) {
            return new int[]{};
        }

        // 反转后序遍历序列就是拓扑排序
        int[] res = new int[numCourses];
        Collections.reverse(postOrder);
        for (int i = 0; i < numCourses; i++) {
            res[i] = postOrder.get(i);
        }
        return res;
    }

    public static HashMap<Integer, List<Integer>> buildGraph(int numCourses, int[][] prerequisites) {
        HashMap<Integer, List<Integer>> graph = new HashMap<>();

        for (int i = 0; i < numCourses; i++) {
            graph.put(i, new ArrayList<>());    // 要这么写，确保每个节点后面都有一个链，这样才不会出现空结点的现象
        }

        for (int[] edge : prerequisites) {
            int before = edge[1];
            int after = edge[0];
            List<Integer> temp = graph.get(before);
            temp.add(after);
            graph.put(before, temp);
        }

        return graph;
    }

    public static void dfs(HashMap<Integer, List<Integer>> graph, int i) {
        if (onPath[i]) {
            hasCycle = true;
        }
        if (hasCycle || visited[i]) {
            return;
        }

//        List<Integer> neighbor = graph.get(i);
//        if (neighbor == null) {
////            postOrder.add(i);
//            return;
//        }

        onPath[i] = true;
        visited[i] = true;

        for (int nei : graph.get(i)) {
            dfs(graph, nei);
        }

        // 处于后序遍历的位置
        /**
         * 为什么要使用后序遍历
         *   可以通过二叉树的思路来类比：对于一棵二叉树，如果是后序遍历，那么就是先遍历出左子树，在遍历出右子树，最后才知道根
         *   类比到图中，只有当以这个节点为起始点的所有邻居都被访问过了之后，才能够直到这条路是否为通路，如果是通路的话，才能够继续往下走
         *   才会被加入到onPath中
         *
         * 而且加入的顺序是从终点到起点的，所以后面还需要对postOrder进行反转
         */
        postOrder.add(i);
        onPath[i] = false;
    }



    // 在BFS中节点的遍历顺序就是拓扑排序的结果
    public int[] findOrderUsingBFS(int numCourses, int[][] prerequisites) {
        HashMap<Integer, List<Integer>> graph = buildGraph(numCourses, prerequisites);
        // 入度
        int[] indgree = new int[numCourses];
        for (int[] edge : prerequisites) {
            int from = edge[1];
            int to = edge[0];
            indgree[to]++;
        }

        // 遍历入度数组，找到入度为0的节点
        Queue<Integer> q = new LinkedList<>();
        for (int i = 0; i < numCourses; i++) {
            if (indgree[i] == 0) {
                q.add(i);
            }
        }

        // 记录拓扑排序的结果
        int[] res = new int[numCourses];
        int count = 0;
        while (!q.isEmpty()) {
            int node = q.poll();
            res[count] = node;
            count++;
            for (int neighbor : graph.get(node)) {
                indgree[neighbor]--;
                if (indgree[neighbor] == 0) {
                    q.add(neighbor);
                }
            }
        }

        if (count == numCourses) {
            return res;
        } else {
            return new int[]{};
        }
    }


    public static void main(String[] args) {
        int numCourses = 2;
        int[][] prerequisites = {{1,0}};

        findOrder(numCourses, prerequisites);
    }
}

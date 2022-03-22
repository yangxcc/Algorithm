package 图.环检测;

import java.util.*;

public class CourseSchedule {
    public static boolean canFinish(int numCourses, int[][] prerequisites) {
        // 统计每门课程的入度
        int[] indegree = new int[numCourses];
        // 可以通过HashMap做邻接表
        // key -- 课程编号   value -- 该课程的相关课程，有边相连的点的集合
        HashMap<Integer, List<Integer>> graph = new HashMap<>();
        for (int i = 0; i < prerequisites.length; i++) {
            int after = prerequisites[i][0];
            int before = prerequisites[i][1];
            // after课程的入度+1
            indegree[after]++;

            if (graph.get(before) == null) {
                List<Integer> temp = new ArrayList<>();
                temp.add(after);
                graph.put(before, temp);
            } else {
                List<Integer> temp = graph.get(before);
                temp.add(after);
                graph.put(before, temp);
            }
        }
        // 遍历graph，找到入度为0的课程，加入到队列中，进行广度优先遍历bfs
        Queue<Integer> q = new LinkedList<>();
        for (int i = 0; i < indegree.length; i++) {
            if (indegree[i] == 0) {
                q.add(i);
            }
        }

        while (!q.isEmpty()) {
            int preCourse = q.poll();
            numCourses--;
            List<Integer> neighbors = graph.get(preCourse);
            // 一定要注意有这句话，通过debug发现的
            if (neighbors == null) continue;
            for (int i = 0; i < neighbors.size(); i++) {
                if (--indegree[neighbors.get(i)] == 0) {
                    q.add(neighbors.get(i));
                }
            }
        }
        return numCourses == 0;
    }


    public HashMap<Integer, List<Integer>> buildGraph(int numCourses, int[][] prerequisites) {
        HashMap<Integer, List<Integer>> graph = new HashMap<>();
        for (int i = 0; i < numCourses; i++) {
            graph.put(i, new ArrayList<>());
        }
        for (int[] edge : prerequisites) {
            int from = edge[1];
            int to = edge[0];
            // indegree[to]++;
            graph.get(from).add(to);
        }
        return graph;
    }

    boolean[] visited;   // 防止重复遍历同一个节点
    boolean[] onPath;    // 记录每一次的dfs经过的节点
    boolean hasCycle;
    int[] indegree;

    public void dfs(HashMap<Integer, List<Integer>> graph, int start) {
        if (onPath[start]) {
            hasCycle = true;
        }
        if (visited[start] || hasCycle) {
            // 已经遍历过这个节点或者有环
            return;
        }

        if (graph.get(start) == null) {
            return;
        }

        visited[start] = true;
        onPath[start] = true;

        for (int neighbor : graph.get(start)) {
            dfs(graph, neighbor);
        }

//        visited[start] = false;
        onPath[start] = false;
    }

    public boolean canFinishUsingDFS(int numCourses, int[][] prerequisites) {
        HashMap<Integer, List<Integer>> graph = buildGraph(numCourses, prerequisites);
        visited = new boolean[numCourses];
        onPath = new boolean[numCourses];
        // 图中并不是所有节点都相连，所以要用⼀个 for 循环将所有节点都作为起点调用⼀次 DFS 搜索算法
        for (int i = 0; i < numCourses; i++) {
            dfs(graph, i);
        }
        return !hasCycle;
    }

    // 不管是DFS，还是BFS，时间复杂度都分成了两个部分：访问每个节点花费的事件以及找这个节点的邻居花费的事件
    // 访问每个节点的时间是O(1)，一共n个节点，所以这部分的时间复杂度是O(n)
    // 在邻接表中找每个节点的邻居其实就是遍历这个list，因此这一部分的时间复杂度是O(e)
    // 所以总的时间复杂度为O(n + e)
    // 由于在BFS中还用到了队列，因此BFS要比DFS慢一些

    public boolean canFinishUsingBFS(int numCourses, int[][] prerequisites) {
        indegree = new int[numCourses];
        HashMap<Integer, List<Integer>> graph = buildGraph(numCourses, prerequisites);
        // 找到入度为0的课程
        Deque<Integer> q = new ArrayDeque<>();
        for (int i = 0; i < numCourses; i++) {
            if (indegree[i] == 0) {
                q.addLast(i);
            }
        }
        int count = 0; // 操作次数
        while (!q.isEmpty()) {
            int curCourse = q.pollLast();
            for (int neighbor : graph.get(curCourse)) {
                indegree[neighbor]--;
                if (indegree[neighbor] == 0) {
                    q.addLast(neighbor);
                }
            }
            count++;
        }
        return count == numCourses;
    }

    public static void main(String[] args) {
        int[][] prerequisites = new int[][]{{1, 0}};
        int numCourses = 2;
        System.out.println(canFinish(numCourses, prerequisites));
    }
}

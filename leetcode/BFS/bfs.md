BFS框架模板

BFS源于二叉树的层序遍历，其核心是利用队列这种数据结构

BFS问题的本质就是让你在一幅图中找到从起点到终点的**最短距离/最小步数**

```java
// 计算从起点start到终点target的最短距离
int bfs(Node start, Node target){
    Queue<Node> q;    		// 核心数据结构
    Set<Node> visited;  	// 避免走回头路，大部分情况下都是需要的，向二叉树这种结构中就是不需要的，因为没有从子节点到父节点的路径
    
    q.offer(start);			// 把start加入到队列中
    visited.add(start);
    
    int step = 0;
    
    while (!q.isEmpty()) {
        int sz = q.size();
        // 将当前队列中的所有节点向四周扩散
        for (int i = 0; i < sz; i++) {
            Node cur = q.poll();
            // 这里一定要判断一些是否达到了终点
            if (cur is target) {
                return step;
            }
            for (Node x : cur.neighber) {   // cur的相邻节点
                if (x not in visited) {
                    q.offer(x);
                    visited.add(x);
                }
            }
        }
        // 在这里更新步数
        step++;
    }
}
```


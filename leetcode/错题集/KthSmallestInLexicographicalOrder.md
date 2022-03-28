<img src="../../image/image-20220328103636308.png" alt="image-20220328103636308" style="zoom:67%;" />

[参考链接](https://zhuanlan.zhihu.com/p/61661191)



字典序排数可以看作是第一层节点为`0-9`的十叉树，然后我们在树上找到第k小的数字即可，因此，我们需要分别统计以`1-9`为根节点的每个树的节点个数，**如果k小于当前树的节点个数，那么第k小的数字即在当前树中，我们进入子树继续查找；如果k大于当前树的节点个数，那么我们需要查找后面树中第（k-当前树的节点个数）小的数字**

因此我们可以写出下面的伪代码

```java
public int findKthNumber(int n, int k) {
    int steps;  // 当前树的节点个数
    int cur = 1;
    k--;
    while (k > 0) {
        steps = caculateSteps(cur, cur + 1);     // 计算当前树的节点个数
        if (steps <= k) {
        	// 去下一颗树中查找
        	cur++;
            k -= steps;
    	} else {
            cur *= 10;
            k--;
        }
    }
    return cur;
}
```



可以看到，重点就是求当前树的节点个数

![img](../../image/v2-4af05d4805b7384eee3e7ab496940f75_720w.jpg)

- 第一层以1为根节点的个数`right - left`
- 第二层以2为根节点的个数`right * 10 - left * 10`
- 第三层以3为根节点的个数`right - left + 1`，但是此时**由于最大值的限制，right就不是right * 10了，而是n**

```java
// 最大值为n，计算n1到n2之间的距离，其实就是统计以n1为根节点的树中有多少个节点
public int caculateSteps(int n, int n1, int n2) {
    int steps = 0;
    while (n1 <= n) {
        // 按层分别统计
        steps += Math.min(n2, n + 1) - n1;
        n1 *= 10;
        n2 *= 10;
    }
    return steps;
}
```





> 但是还需要注意的是，在leetcode中，因为规定了``1 <= k <= n <= 10^9`，因此`n1`有可能等于`10^9*10>Integer.MAX_VALUE`，所以这里需要使用long来保存，具体`KthSmallestInLexicographicalOrder.java`

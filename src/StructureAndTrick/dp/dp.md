动态规划其实是由暴力递归一步步的演变而来的，下面我们来看一道题

给你`k`种面值的硬币，面值分别为`c1, c2 ... ck`，每种硬币的数量无限，再给一个总金额`amount`，问你**最少**需要几枚硬币凑出这个金额，如果不可能凑出，算法返回 -1 。算法的函数签名如下：

```java
int coinChange(int[] coins, int target)
```



### 暴力递归

首先，看到这个题，我最先能够想到的就是使用暴力递归来穷举所有情况，如下：

```java
public int minCoinsAlgorithm(int[] coins, int target) {
    if (coins.length == 0 || coins == null || target < 0) {
        return -1;
    }
    return process(coins, target, 0);
}

// index 表示的是第index个硬币
public int process(int[] coins, int target, int index) {
    // base case
    if (target < 0) {
        return -1;       // 不能够满足条件
    }
    if (target == 0) {
        return 0;        // 当target == 0的时候，还需要0个硬币
    }
    if (index >= coins.length) {       // 都遍历完了都没有找到能够让target为0的硬币
        return -1;
    }
    
    // 对于每个硬币只有两种选择，一种是我选择他，另一种就是不选择他
    int p1 = process(coins, target, index + 1);  // 不选
    int p2 = process(coins, target - coins[index], index + 1); // 选
    
    if (p1 == -1 && p2 == -1) {
        return -1;
    } else if (p2 == -1) {
        return p1;
    } else if (p1 == -1) {
        return p2 + 1;           // 因为p2是选择了这个硬币，所以硬币个数 + 1
    } else {
        return Math.min(p1, p2 + 1);
    }
}
```



上面这样写，能够满足题意得到结果，但是它的时间复杂度太高了，因为其中存在很多的**重叠子问题**，暴力递归的时间复杂度是指数级别的，**递归算法的时间复杂度怎么计算？子问题个数乘以解决一个子问题需要的时间。**

子问题个数，即递归树中节点的总数。显然二叉树节点总数为指数级别，所以子问题个数为 O(2^n)。所以暴力递归的时间复杂度是指数级别，很差

比如给定了数组`[2,3,4,5,6,9]，target = 10`，主方法中的调用可以化简成`minCoinsAlgorithm(0,10)`，表示硬币从0开始，寻找target为10的硬币总数

![image-20211030155539828](../../../image\image-20211030155539828.png)

可以看到，上图中表示出的一种选择方案中就存在多个重复计算，如果我们能够记住已经算过的值，那么再次遇到的时候就可以直接得出结果，也就是我们下面的为递归算法添加一个“备忘录”



### 添加一个“备忘录”的递归

其实这种方式和上面的暴力递归如出一辙，就是在程序计算的过程中添加一个备忘录，来记录已经判断过的节点

```java
public int minCoinsAlgorithm(int[] coins, int target) {
    if (coins.length == 0 || coins == null || target < 0) {
        return -1;
    }
    int[][] dp = new int[coins.length + 1][target + 1];
    for (int i = 0; i <= coins.length; i++) {
        for (int j = 0; j <= target; j++) {
            dp[i][j] = -2;      // 表示未被访问过
        }
    }
    return process(coins, target, 0, dp);
}

// index 表示的是第index个硬币
public int process(int[] coins, int target, int index, int[][] dp) {
    
    // 这个硬币已经被访问过了，直接放回他的值
    if (dp[index][target] != -2) {
        return dp[index][target];
    }
    // base case
    if (target < 0) {
        dp[index][target] = -1;
        // return -1;       // 不能够满足条件
    }
    if (target == 0) {
        dp[index][target] = 0;
        // return 0;        // 当target == 0的时候，还需要0个硬币
    }
    if (index >= coins.length) {       // 都遍历完了都没有找到能够让target为0的硬币
        dp[index][target] = -1;
        // return -1;
    }
    
    // 对于每个硬币只有两种选择，一种是我选择他，另一种就是不选择他
    int p1 = process(coins, target, index + 1);  // 不选
    int p2 = process(coins, target - coins[index], index + 1); // 选
    
    if (p1 == -1 && p2 == -1) {
        dp[index][target] = -1;
        // return -1;
    } else if (p2 == -1) {
        dp[index][target] = p1;
        // return p1;
    } else if (p1 == -1) {
        dp[index][target] = p2 + 1;
        // return p2 + 1;           // 因为p2是选择了这个硬币，所以硬币个数 + 1
    } else {
        dp[index][target] = Math.min(p1, p2 + 1);
        // return Math.min(p1, p2 + 1);
    }
    return dp[index][target];
}
```



这样的话，就能够记录下计算过的硬币了，所以时间复杂度变成了`O(n * k)`

因为，这种情况下，不存在冗余计算了，所以子问题的个数就是输入样本的数量，也就是`O(N)`，其实，这种方式下时间复杂度就是`O(N)`级别的了



### 动态规划

动态规划一般情况下脱离了递归，由循环迭代来完成

假设我们的数组是`int[] coins = {3,5,6,2,7}, target = 10`，我们首先会开辟一个`dp`数组，大小为`dp[coins.length + 1][target + 1]`

也就是一个行为`coins.length + 1`，列为`target + 1`的网格

![image-20211030161920026](../../../image\image-20211030161920026.png)

因为`target = 0`的时候，`return 0`，所以`dp[i][0] = 0`，如下：

![image-20211030162037251](../../../image\image-20211030162037251.png)

因为，`index >= coins.length`时`return -1`，所以`dp[coins.length][i] = -1`，如下：

![image-20211030162212975](../../../image\image-20211030162212975.png)

至此，对于`dp`数组的初始化就可以了，后续我们只需要根据已经存在的值把不存在的值给补充上就行了

因为在主程序中的调用是`process(..,0,10,..)`，这也就意味着我们后面要返回的是`dp[0][10]`，如下图

![image-20211030162415077](../../../image\image-20211030162415077.png)

那么，我们要从程序要在右下角开始，我们可以看一下递归的写法

```java
    int p1 = process(coins, target, index + 1);  // 不选
    int p2 = process(coins, target - coins[index], index + 1); // 选
```

对于任何一个普通的点，它的值取决于p1和p2，可以看到下图中，p1的位置就在当前点的下面，因为target没变，index + 1，p2的位置就是一个范围了，因为需要减去当前点的钱数，

![image-20211030162918333](../../../image\image-20211030162918333.png)

```java
public int minCoinsAlgorithm(int[] coins, int target) {
    if (coins.length == 0 || coins == null || target < 0) {
        return -1;
    }
    int[][] dp = new int[coins.length + 1][target + 1];
    for (int i = 0; i <= coins.length; i++) {
        dp[i][0] = 0;
    }
    for (int j = 1; j <= target; j++) {
        dp[coins.length][j] = -1;
    }
    for (int index = 0; index < coins.length; index++) {
        for (int aim = 1; aim <= target; aim++) {
            int p1 = dp[index + 1][aim];
            int p2 = -1;
            if (aim - coins[index]  >= 0) {
                p2 = dp[index + 1][aim - coins[index]];
            }
            if (p1 < 0 && p2 < 0) {
                dp[index][aim] = -1;
            } else if (p1 < 0) {
                dp[index][aim] = p2 + 1;
            } else if (p2 > 0) {
                dp[index][aim] = p1;
            } else {
                dp[index][aim] = Math.min(p1, p2 + 1);
            }
        }
    }
    return dp[0][target];
}
```

时间复杂度和"备忘录"递归一样都是`O(n * k)`


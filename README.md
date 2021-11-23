1. 数组和链表代表着计算机最基本的两种存储形式：顺序存储和链式存储，所以他们两个可以算是最基本的数据结构了。

   数组和链表的主要算法技巧是双指针，双指针又分为中间向两端扩散的双指针，两端向中间收缩的双指针，以及快慢指针，除了双指针之外，前缀和和差分数组也是常用技巧

2. 前缀和技巧**适用于原始数组不会被修改的情况下**，快速、频繁地计算/查询一个索引区间内的元素之和

   ```java
   // 前缀和的核心代码
   class prefixSum {
       private int[] prefix;
       
       public prefixSum(int[] nums) {
           prefix = new int[nums.length + 1];
           // 计算nums的累加和
           for (int i = 1; i < prefix.length; i++) {
               prefix[i] = prefix[i - 1] + nums[i - 1];
           }
       }
       
       // 查询区间[i,j]的累加和
       public int query(int i, int j) {
           return prefix[j + 1] - prefix[i];
       }
   }
   ```

   

3. 差分数组是和前缀和思想很相似的一个算法技巧，**差分数组的主要适用场景是频繁对原始数组某个区间的元素进行增减**

   差分数组`diff`表示的是一个数和他前一个数的差值

   <img src="image/image-20211123112958208.png" style="zoom:80%;" />

   ```java
   // 差分数组的核心代码
   class Difference {
       // 差分数组表示第i个数和第i-1个数之和
       private int[] diff;
       
       public Difference(int[] nums) {
           diff = new int[nums.length];
           diff[0] = nums[0];
           // 根据初始数组构造差分数组
           for (int i = 1; i < nums.length - 1; i++) {
               diff[i] = nums[i] - nums[i - 1];
           }
       }
       
       // 给区间[i,j]增加val（val可以是负数）
       public void increment(int i, int j, int val) {
           diff[i] += val;
           if (j + 1 < diff.length) {
               diff[j + 1] -= val;
           }
       }
       
       // 根据差分数组，还原原数组
       public int[] result() {
           int[] res = new int[diff.length];
           res[0] = diff[0];
           for (int i = 1; i < diff.length; i++) {
               res[i] = res[i - 1] + diff[i];
           }
           return res;
       }
   }
   ```

   

   

4. 对于子串、子数组的问题，很有可能使用滑动窗口能够解决，滑动窗口的框架

   ```java
   public void slidingWindow(String s, String t) {   // t是子数组
       HashMap<Character, Integer> need = new HashMap<>();
       HashMap<Character, Integer> window = new HashMap<>();
       int left = 0, right = 0;
       int valid = 0;
       
       while (right < s.length()) {
           // c是移入窗口的字符
           char c = s.charAt(right);
           // 右移窗口
           right++;    
           // 从上面两行代码可以看出区间是左开右闭的[left,right)
           
           // 对窗口内的数据进行更新
           ...
               
           while (window needs shrink) {   // 窗口能够收缩的条件
               // d是移出窗口的字符
               char d = s.charAt(left);
               // 收缩窗口
               left++;
               // 对窗口内数据进行更新
               ...
           }    
       }
   }
   ```



5. 有一个小点需要注意，在Java中，Integer等包装类的类型判断相等不能够直接使用`==`，应该和String一样，也使用`equals()`


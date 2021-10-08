在学习链表之前向简单地学一下hash表的用法...

其中涉及到的一些原理性的知识等到后面补上

### Hash表的简单介绍
1. hash表在使用层面上可以理解为是一种结构
2. 如果只有key，没有伴随value，可以使用HashSet结构
3. 如果既有key，又有伴随value，可以使用HashMap结构
4. 有无伴随数据是HashSet和HashMap的唯一区别，他们两个的底层结构其实是一样的
5. 使用hash表进行增删改查操作，可以认为时间复杂度是常数级别的，即O(1)，但是这个常数是比较大的
6. 放入Hash表的东西（key，value），如果是基础数据类型，内部按值传递，内存占用就是这个东西的大小
7. 放入Hash表的东西（key，value），如果不是基础数据类型，内部按引用传递，内存占用是这个东西内存地址的大小,一律8字节
```java
    public static void example01() {
        HashMap<Node,Integer> hashMap = new HashMap<>();
        Node node1 = new Node(1);
        Node node2 = new Node(1);
        hashMap.put(node1,1);    // 这里的key就是node1在内存中的地址
        hashMap.put(node2,2);
        System.out.println(hashMap.size());
        
        HashMap<String,Integer> hashMap1 = new HashMap<>();
        hashMap1.put("asdefsdfsdffgs",1);
        // 这里的key是什么，就会拷贝一份到哈希表中，因为这里是按照值传递的
        // 但是我有一个疑问，就是String并不是基础数据类型啊！为什么它这里还是使用值传递呢？
    }
```

哈希表的原理以后再说...

### 有序表的简单介绍
1. 有序表在使用层面上可以看作是一个集合结构
2. 如果只有key，没有伴随数据value，可以使用TreeSet结构
3. 如果既有key，又有伴随数据value，可以使用TreeMap结构
4. 有无伴随数据是TreeSet和TreeMap的唯一区别，他们两个的底层结构是一样的
5.有序表和哈希表的区别是，有序表把key按照顺序组织起来，而哈希表是不组织的
6. 红黑树、AVL树、size-balance-tree和跳表等都属于有序表结构，只是底层的具体实现不同
7. 放入有序表的东西（key，value），如果是基础数据类型，内部按值传递，内存占用就是这个东西的大小
8. 放入有序表的东西（key，value），如果不是基础数据类型，内部按引用传递，内存占用是这个东西内存地址的大小 
9. 不管是什么底层具体实现，**只要是有序表，都有以下固定的基本功能和固定的时间复杂度**
```java
    public static void example02() {
        TreeMap<String, Integer> treeMap = new TreeMap<>();

        // put()函数，将一个（key，value）记录加入到表中或者将key的记录更新成value
        treeMap.put("a",1);
        treeMap.put("a",2);  // 将key为“a”的记录更新成2
        treeMap.put("b",3);  // 添加一条新的记录
        treeMap.put("c",3);
        treeMap.put("d",3);
        treeMap.put("e",3);

        // get()函数，根据给定的key查询对应的value，并返回
        System.out.println(treeMap.get("a"));   // 2
        System.out.println(treeMap.get("b"));   // 3

        // remove()函数，移除key的记录
        treeMap.remove("a");

        // containsKey()函数，查询是否有key的记录
        System.out.println(treeMap.containsKey("a"));  // false

        // firstKey()函数，返回所有键值的排序结果中，最左侧的那一个
        System.out.println(treeMap.firstKey());       // b，因为a已经删除了

        // lastKey() 函数，返回所有键值的排序结果中，最右侧的那一个
        System.out.println(treeMap.lastKey());

        // floorKey(K key)函数，如果表中有key的记录，那么返回key，如果没有存入key，那么返回所有键值的排序结果中key的前一个
        System.out.println(treeMap.floorKey("g"));        // e
        System.out.println(treeMap.floorKey("b"));        // b
        treeMap.remove("c");
        System.out.println(treeMap.floorKey("c"));        // b

        // ceilingKey(K key)函数，如果表中有key的记录，那么返回key，如果没有key的记录，那么就会返回所有键值排序后结果中key的后一个
        System.out.println(treeMap.ceilingKey("b"));      // b
        System.out.println(treeMap.ceilingKey("c"));      // d
```

有序表的原理后面补充...




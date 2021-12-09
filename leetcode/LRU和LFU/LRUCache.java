package LRU和LFU;

import java.util.HashMap;
import java.util.LinkedHashMap;

/**
 * 先不用LinkedHashMap
 * 先自己原生写一个LRU
 */
public class LRUCache {
    // 通过hashmap直接找到节点在双向链表中的位置
    private HashMap<Integer, Node> hm;
    private DoubleQueue cache;
    private int capacity;   // 缓存的最大容量

    public LRUCache(int cap) {
        this.capacity = cap;
        hm = new HashMap<>();
        cache = new DoubleQueue();
    }

    /**
     * 让某个节点称为最近使用的cache
     * @param key 节点的key
     */
    private void makeCacheRecently(int key) {
        Node x = hm.get(key);   // 根据key找到这个节点
        cache.remove(x);           // 先把这个节点从原有位置删除
        cache.addLast(x);          // 然后再双向链表的再末尾加上这个节点
    }

    /**
     * 添加最近新使用的Node
     * @param key Node的key
     * @param val Node的value
     */
    private void addRecently(int key, int val) {
        Node x = new Node(key, val);
        cache.addLast(x);
        hm.put(key, x);  // 一定不要忘了把x放入到哈希表中，记录好它的key
    }

    /**
     * 删除某个节点
     * @param key 该节点的key
     */
    public void deleteKey(int key) {
        Node x = hm.get(key);
        cache.remove(x);
        hm.remove(key);   // 从哈希表中也删除
    }

    /**
     * 删除最近最久未使用的节点，即双向链表的头节点
     */
    public void removeLeastRecently() {
        Node x = cache.removeFirst();
        int key = x.key;
        hm.remove(key);   // 别忘了从哈希表中删除
    }

    /**
     *
     * @param key key作为查询条件
     * @return  某个节点
     */
    public int get(int key) {
        if (!hm.containsKey(key)) {
            return -1;
        }
        // 一定不要忘了把这个node给设置成最近使用的
        makeCacheRecently(key);
        return hm.get(key).value;
    }

    /**
     * 如果key在cache中已经存在了，那么需要修改value，然后设置为最新
     * 如果key在cache中不存在，
     *      如果cache没有满，那么将这个新的Node节点插到缓存的最后面就行了
     *      如果cache满了，那么先删除头部的Node，然后再把这个新的Node加入到尾部
     * @param key Node的key
     * @param val Node的value
     */
    public void put(int key, int val) {
        Node x = new Node(key, val);
        if (hm.containsKey(key)) {
            deleteKey(key);  // 删除旧的数据
            addRecently(key, val);  // 新插入的数据查到尾部
            return;
        }
        if (cache.getSize() == capacity) {
            removeLeastRecently();
        }
        addRecently(key, val);
    }
}

class Node {
    int key, value;
    Node pre, next;
    public Node(int k, int v) {
        this.key = k;
        this.value = v;
        this.pre = null;
        this.next = null;
    }
}

class DoubleQueue {
    private int size;
    private Node head, tail;

    public DoubleQueue() {
        size = 0;
        // head和tail是两个虚拟节点
        head = new Node(0, 0);
        tail = new Node(0, 0);
        head.next = tail;
        tail.pre = head;
    }

    public int getSize() {
        return this.size;
    }

    /**
     * 在链表的末尾添加一个节点，因此尾部表示最近使用的
     * @param x 节点x
     */
    public void addLast(Node x) {
        x.pre = tail.pre;
        x.next = tail;
        tail.pre.next = x;
        tail.pre = x;
        size++;
    }

    /**
     * 删除链表中的某个节点
     * 之所以要是要使用双向链表，就是为了让删除时候的时间复杂度是O(1)，不用再去寻找x的前驱和后继了
     * @param x 节点x
     */
    public void remove(Node x) {
        // x一定存在，假设现在已经找到了它的位置
        x.pre.next = x.next;
        x.next.pre = x.pre;
        x.next = null;
        x.pre = null;
        size--;
    }

    /**
     * 删除链表中的第一个节点
     * @return 返回这个节点
     */
    public Node removeFirst() {
        if (head.next == tail) {
            return null;
        }
        // 最旧的缓存
        Node first = head.next;
        remove(first);
        return first;
    }
}

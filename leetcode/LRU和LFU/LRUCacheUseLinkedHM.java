package LRU和LFU;

import java.util.LinkedHashMap;

/**
 * 使用LinkedHashMap来实现LRU，相当于双向链表 + 哈希表
 */
public class LRUCacheUseLinkedHM {
    int capacity;
    // 尾部就是最新的，头部就是将要被淘汰的
    LinkedHashMap<Integer, Integer> cache;

    public LRUCacheUseLinkedHM(int cap) {
        this.capacity = cap;
        cache = new LinkedHashMap<>();
    }

    public void put(int key, int value) {
        // 如果缓存中已经存在这个key了，那么直接修改他的value值即可
        if (cache.containsKey(key)) {
            cache.put(key, value);
            makeCacheRecently(key);
            return;
        }
        // 如果缓存中不存在，那么还需要判断一下cache是否满了
        if (cache.size() >= capacity) {
            // 现需要将头部给淘汰了
            int headKey = cache.keySet().iterator().next();
            cache.remove(headKey);
//            // 再把新的节点加进来
//            cache.put(key, value);
        }
        cache.put(key, value);
    }

    public int get(int key) {
        if (!cache.containsKey(key)) {
            return -1;
        }
        makeCacheRecently(key);
        return cache.get(key);
    }

    public void makeCacheRecently(int key) {
        int val = cache.get(key);
        cache.remove(key);
        cache.put(key, val);  // addLast
    }
}

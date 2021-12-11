package LRU和LFU;

import java.util.HashMap;
import java.util.LinkedHashSet;

/**
 * 需要三个哈希表，分别映射KEY-VALUE,KEY-frequency， frequency-Keys
 *
 * 同时需要一个minFeq，来记录缓存中访问频次最小的key
 *
 * 最后还需要一个capacity，表示缓存的容量
 */
public class LFUCache {

    // 缓存的容量
    int capacity;
    // 缓存中最小的访问频次
    int minFeq;
    // Key-Value
    HashMap<Integer, Integer> keyToVal;
    // Key-Frequency
    HashMap<Integer, Integer> keyToFreq;
    // Frequency-Keys
    HashMap<Integer, LinkedHashSet<Integer>> freqToKeys;

    public LFUCache(int capacity) {
        this.capacity = capacity;
        this.minFeq = 0;
        keyToVal = new HashMap<>();
        keyToFreq = new HashMap<>();
        freqToKeys = new HashMap<>();
    }

    public int get(int key) {
        if (!keyToVal.containsKey(key)) {
            return -1;
        }
        // 增加key的频次
        increaseFreq(key);
        return keyToVal.get(key);
    }

    public void put(int key, int value) {
        if (this.capacity <= 0) {
            return;  // 容量为0
        }
        // 先来看看缓存中有没有这个key
        if (keyToVal.containsKey(key)) {
            keyToVal.put(key, value);
            increaseFreq(key);
            return;
        }

        // 如果缓存中没有这个key，我们还需要看看缓存是否满了
        if (this.capacity <= keyToVal.size()) {
            // 缓存满了，就需要删除freq最小的链表中的头节点，也就是freq最小，而且key最久没有被访问
            removeLeastFreqKey();
        }

        // 添加新的数据
        // 更新KV表
        keyToVal.put(key, value);
        // 更新KF表
        keyToFreq.put(key, 1);
        // 更新FK表
        // 这句话是为了防止FK表中没有频次为1的链表
        freqToKeys.putIfAbsent(1, new LinkedHashSet<>());
        freqToKeys.get(1).add(key);

        this.minFeq = 1;
    }

    // 增加某个key的频次
    public void increaseFreq(int key) {
        // 只需要更新KF和FK表
        int freq = keyToFreq.get(key);  // 获取当前key的频次
        // 更新KF表
        keyToFreq.put(key, freq + 1);
        // 现在当前FK表中对应的链表删除这个key，然后将这个key加到FK中freq+1对应的链表中
        freqToKeys.get(freq).remove(key);
        // 在freq+1链表中加上这个key
        freqToKeys.putIfAbsent(freq + 1, new LinkedHashSet<>());
        freqToKeys.get(freq + 1).add(key);

        // 如果freq对应的链表中只有一个key，经过上面删除之后为空了
        if (freqToKeys.get(freq).isEmpty()) {
            freqToKeys.remove(freq);
            // 如果这个freq是最小频次，那么频次也需要更新
            if (freq == minFeq) {
                minFeq++;
            }
        }
    }

    // 缓存满了，就需要删除freq最小的链表中的头节点，也就是freq最小，而且key最久没有被访问
    public void removeLeastFreqKey() {
        // 最小频次对应的链表
        LinkedHashSet<Integer> keyList = freqToKeys.get(minFeq);
        // 要删除keyList的第一个节点
        Integer deleteKey = keyList.iterator().next();
        keyList.remove(deleteKey);
        // 同理，删除了这个key之后，keyList可能为空
        if (keyList.isEmpty()) {
            freqToKeys.remove(minFeq);
        }

        // 接下来就要更新KF表
        keyToFreq.remove(deleteKey);
        // 更新KV表
        keyToVal.remove(deleteKey);
    }
}

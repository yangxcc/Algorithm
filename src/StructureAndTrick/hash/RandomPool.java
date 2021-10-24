package StructureAndTrick.hash;

import java.util.HashMap;
import java.util.HashSet;

/**
 * 设计一个结构，该结构中有如下三种功能，
 * insert(key)：将某个key加入到该结构中，做到不重复加入
 * delete(key)：将原本在结构中的某个key移除
 * getRandom()：等概率随机返回结构中的任何一个key
 *
 * 要求上面这三个方法的时间复杂度为O(1)
 * */
public class RandomPool {

    private HashMap<Integer, Integer> hashMap1;   // value是索引
    private HashMap<Integer, Integer> hashMap2;   // key 是索引
    int size;

    public RandomPool() {
        this.hashMap1 = new HashMap<>();
        this.hashMap2 = new HashMap<>();
        this.size = 0;
    }

    public void insert(int key) {
        if (!hashMap1.containsKey(key)) {
            hashMap1.put(key, size);
            hashMap2.put(size++,key);
        }
    }

    public void delete(int key) {
        // 删除了这个节点之后，需要用最后的一个节点补上这个地方，
        // 因为如果没有不上的话，后面随机选取节点，可能会遇到这个已经被删除的地方
        if (hashMap1.containsKey(key)) {
            int deleteIndex = hashMap1.get(key);
            int lastIndex = --size;
            int lastKey = hashMap2.get(lastIndex);
            hashMap1.put(key, deleteIndex);
            hashMap2.put(deleteIndex, lastKey);
            hashMap1.remove(key);
            hashMap2.remove(lastIndex);
        }
    }

    public int getRandom() {
        if (size == 0) {
            return Integer.MIN_VALUE;
        }
        int randomIndex = (int) (Math.random() * size);
        return hashMap2.get(randomIndex);
    }
}

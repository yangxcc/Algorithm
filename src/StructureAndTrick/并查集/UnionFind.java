package StructureAndTrick.并查集;

import java.util.HashMap;
import java.util.List;
import java.util.Stack;

/**
 * 并查集结构的代码实现
 */
public class UnionFind {

    // 样本进来会包一层，叫做元素
    // 功能就类似于用户给了我ABCDE...，经过Element之后就变成Ⓐ...等
    public static class Element<V> {
        public V value;

        public Element(V value) {
            this.value = value;
        }
    }

    public static class UnionFindSet<V> {
        /**
         * a
         * / \
         * b   d
         * \
         * c
         * 对于上面这个结构
         * elementMap中存放的是 A和Ⓐ，B和圈B...
         * fatherMap中存放的是 A和A，B和A，C和B，D和A，也就是某个节点的父节点
         * sizeMap中存放的是 a,4，这个sizeMap表示的是某个集合的代表元素以及这个集合中节点的个数
         */
        public HashMap<V, Element<V>> elementMap;
        public HashMap<Element<V>, Element<V>> fatherMap;
        public HashMap<Element<V>, Integer> sizeMap;

        public UnionFindSet(List<V> List) {
            elementMap = new HashMap<>();
            fatherMap = new HashMap<>();
            sizeMap = new HashMap<>();
            for (V list : List) {
                Element<V> element = new Element<>(list);
                elementMap.put(list, element);
                fatherMap.put(element, element);
                sizeMap.put(element, 1);
            }
        }

        public Element<V> findHead(Element<V> ele) {
            if (elementMap.containsValue(ele)) {
                Stack<Element<V>> path = new Stack<>();
                while (ele != fatherMap.get(ele)) {
                    path.push(ele);
                    ele = fatherMap.get(ele);
                }
                while (!path.isEmpty()) {   // 将并查集扁平化
                    fatherMap.put(path.pop(), ele);
                }
                return ele;
            } else {
                return null;
            }
        }


        public boolean isSameSet(V a, V b) {
            if (elementMap.containsKey(a) && elementMap.containsKey(b)) {
                return findHead(elementMap.get(a)) == findHead(elementMap.get(b));
            }
            return false;
        }


        public void union(V a, V b) {
            if (elementMap.containsKey(a) && elementMap.containsKey(b)) {
                Element<V> aF = findHead(elementMap.get(a)); // a的father
                Element<V> bF = findHead(elementMap.get(b));
                if (aF != bF) {   // 如果这两个一样就表示在同一个集合中了，没必要处理了
                    // 将小的集合挂到大的集合上面
                    Element<V> big = sizeMap.get(aF) > sizeMap.get(bF) ? aF : bF;
                    Element<V> small = big == aF ? bF : aF;
                    fatherMap.put(small, big);
                    sizeMap.put(big, sizeMap.get(aF) + sizeMap.get(bF));
                    sizeMap.remove(small);
                }
            }
        }
    }
}

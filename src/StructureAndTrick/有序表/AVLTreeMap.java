package StructureAndTrick.有序表;

/**
 * AVL树和BST增删改查节点没有任何不同
 * 但是需要注意AVL树在增加和删除节点时的检查时机
 * <p>
 * 四种平衡性被破坏的情况：LL RR, LR, RL
 */
public class AVLTreeMap {

    public static class AVLNode<K extends Comparable<K>, V> {
        public K k;
        public V v;
        public AVLNode<K, V> l;
        public AVLNode<K, V> r;
        public int h;

        public AVLNode(K key, V value) {
            k = key;
            v = value;
            h = 1;
        }
    }

    public static class AVLTree<K extends Comparable<K>, V> {
        private AVLNode<K, V> root;
        private int size;

        public AVLTree() {
            root = null;
            size = 0;
        }

        // 左旋
        private AVLNode<K, V> leftRotate(AVLNode<K, V> cur) {
            AVLNode<K, V> right = cur.r;
            cur.r = right.l;
            right.l = cur;
            cur.h = Math.max((cur.l != null ? cur.l.h : 0), (cur.r != null ? cur.r.h : 0)) + 1;
            right.h = Math.max((right.l != null ? right.l.h : 0), (right.r != null ? right.r.h : 0)) + 1;
            return right;
        }


        // 右旋
        private AVLNode<K, V> rightRotate(AVLNode<K, V> cur) {
            AVLNode<K, V> left = cur.l;
            cur.l = left.r;
            left.r = cur;
            cur.h = Math.max((cur.l != null ? cur.l.h : 0), (cur.r != null ? cur.r.h : 0)) + 1;
            left.h = Math.max((left.l != null ? left.l.h : 0), (left.r != null ? left.r.h : 0)) + 1;
            return left;
        }

        private AVLNode<K, V> maintain(AVLNode<K, V> cur) {
            if (cur == null) {
                return null;
            }

            int leftHeight = cur.l != null ? cur.l.h : 0;
            int rightHeight = cur.r != null ? cur.r.h : 0;
            if (Math.abs(leftHeight - rightHeight) > 1) {   // 不平衡了
                if (leftHeight > rightHeight) {
                    // 左树高，有可能是LL，也有可能是LR
                    int leftLeftHeight = cur.l != null && cur.l.l != null ? cur.l.l.h : 0;
                    int leftRightHeight = cur.l != null && cur.l.r != null ? cur.l.r.h : 0;
                    if (leftLeftHeight >= leftRightHeight) {  // LL型
                        cur = rightRotate(cur);   // 做一次右旋就好了
                    } else {    // LR型
                        cur.l = leftRotate(cur.l);
                        cur = rightRotate(cur);
                    }
                } else {
                    // 右树高，有可能是RR，也有可能是RL
                    int rightRightHeight = cur.r != null && cur.r.r != null ? cur.r.r.h : 0;
                    int rightLeftHeight = cur.r != null && cur.r.l != null ? cur.r.l.h : 0;
                    if (rightRightHeight >= rightLeftHeight) {
                        cur = leftRotate(cur);
                    } else {
                        cur.r = rightRotate(cur.r);
                        cur = leftRotate(cur);
                    }
                }
            }
            return cur;
        }

        // 找给定key值的AVLNode节点
        private AVLNode<K, V> findLastIndex(K key) {
            AVLNode<K, V> pre = root;
            AVLNode<K, V> cur = root;
            while (cur != null) {
                pre = cur;
                if (key.compareTo(cur.k) == 0) {
                    break;
                } else if (key.compareTo(cur.k) < 0) {
                    cur = cur.l;
                } else {
                    cur = cur.r;
                }
            }
            return pre;
        }


//        private AVLNode<K, V> findLastNoSmallIndex(K key) {
//            AVLNode<K, V> ans = null;
//            AVLNode<K, V> cur = root;
//            while (cur != null) {
//                if (key.compareTo(cur.k) == 0) {
//                    ans = cur;
//                    break;
//                } else if (key.compareTo(cur.k) < 0) {
//                    ans = cur;
//                    cur = cur.l;
//                } else {
//                    cur = cur.r;
//                }
//            }
//            return ans;
//        }
//
//
//        private AVLNode<K, V> findLastNoBigIndex(K key) {
//            AVLNode<K, V> ans = null;
//            AVLNode<K, V> cur = root;
//            while (cur != null) {
//                if (key.compareTo(cur.k) == 0) {
//                    ans = cur;
//                    break;
//                } else if (key.compareTo(cur.k) < 0) {
//                    cur = cur.l;
//                } else {
//                    ans = cur;
//                    cur = cur.r;
//                }
//            }
//            return ans;
//        }


        // 在cur这棵树上，加上一个AVLNode(key, value)
        // 返回添加后的这颗新树
        private AVLNode<K, V> add(AVLNode<K, V> cur, K key, V value) {
            if (cur == null) {
                return new AVLNode<>(key, value);
            } else {
                if (key.compareTo(cur.k) < 0) {
                    cur.l = add(cur.l, key, value);
                } else {
                    cur.r = add(cur.r, key, value);
                }
                // 每加一个节点就需要维护一下，时刻让他保持是AVL树
                // 而且还需要更新以下目前树的高度
                cur.h = Math.max((cur.l != null ? cur.l.h : 0), (cur.r != null ? cur.r.h : 0)) + 1;
                return maintain(cur);
            }
        }


        // 在cur这棵树上，删除key这个AVLNode
        // 返回删除后的这颗新树
        private AVLNode<K, V> delete(AVLNode<K, V> cur, K key) {
            if (key.compareTo(cur.k) > 0) {
                cur.r = delete(cur.r, key);   // 在右子树上删除
            } else if (key.compareTo(cur.k) < 0) {   // 在左子树上删除
                cur.l = delete(cur.l, key);
            } else {    // 删除的是根节点
                if (cur.l == null && cur.r == null) {
                    cur = null;     // 就cur一个节点，直接把他置为空，就算删除了
                } else if (cur.l == null && cur.r != null) {
                    cur = cur.r;
                } else if (cur.l != null && cur.r == null) {
                    cur = cur.l;
                } else {
                    //这种方式下，我们以选择左子树上的最右节点或者右子树上的最左节点
                    AVLNode<K, V> node = cur.r;
                    while (node.l != null) {
                        node = node.l;
                    }
                    // 找到右子树上的最左节点之后，用这个节点代替根节点
                    cur.r = delete(cur.r, node.k);  // 先从右子树上把这个节点删了
                    // 让node去代替cur
                    node.l = cur.l;
                    node.r = cur.r;
                    cur = node;
                    // 没有管node节点的右子树？？？
                }
            }
            if (cur != null) {
                cur.h = Math.max(cur.l != null ? cur.l.h : 0, cur.r != null ? cur.r.h : 0) + 1;
            }
            return maintain(cur);
        }

        public int size() {
            return size;
        }

        public boolean containsKey(K key) {
            if (key == null) {
                return false;
            }
            AVLNode<K, V> lastNode = findLastIndex(key);
            return lastNode != null && key.compareTo(lastNode.k) == 0 ? true : false;
        }

        public void put(K key, V value) {
            if (key == null) {
                return;
            }
            AVLNode<K, V> lastNode = findLastIndex(key);
            if (lastNode != null && key.compareTo(lastNode.k) == 0) {
                lastNode.v = value;     // key相同但是value不同的节点，更新value值
            } else {
                size++;
                root = add(root, key, value);
            }
        }

        public void remove(K key) {
            if (key == null) {
                return;
            }
            if (containsKey(key)) {
                size--;
                root = delete(root, key);
            }
        }

        public V get(K key) {
            if (key == null) {
                return null;
            }
            AVLNode<K, V> lastNode = findLastIndex(key);
            if (lastNode != null && key.compareTo(lastNode.k) == 0) {
                return lastNode.v;
            }
            return null;
        }
    }
}

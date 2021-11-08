package StructureAndTrick.有序表;

/**
 * SBTree 平衡性的判断
 * 每棵子树的大小，不小于其兄弟的子树大小，也就是每颗叔叔树的大小不小于任何侄子子树的大小
 *
 * SBTree的增删改查和AVLTree是一样的，不同的是在增删之后的maintain策略不同
 *
 * */
public class SizeBalancedTree {

    public static class SBNode<K extends Comparable<K>> {
        public K key;
        public SBNode<K> l;
        public SBNode<K> r;
        public int size;

        public SBNode(K k) {
            this.key = k;
            this.size = 1;
        }
    }

    public static class SBTree<K extends Comparable<K>>{
        private SBNode<K> root;

        // 左旋和右旋操作都是一样的
        public SBNode<K> leftRotate(SBNode<K> cur) {
            SBNode<K> right = cur.r;
            cur.r = right.l;
            right.l = cur;
            right.size = cur.size;
            cur.size = (cur.l != null ? cur.l.size : 0) + (cur.r != null ? cur.r.size : 0) + 1;
            return right;
        }

        public SBNode<K> rightRotate(SBNode<K> cur) {
            SBNode<K> left = cur.l;
            cur.l = left.r;
            left.r = cur;
            left.size = cur.size;
            cur.size = (cur.l != null ? cur.l.size : 0) + (cur.r != null ? cur.r.size : 0) + 1;
            return left;
        }


        // 不同的就是maintain，其实在SBTree中需要调整的也有四种，分别是LL,LR,RR,RL
        // 但是调整的时机是不同的，在AVL树中调整的时机是看高度，这里是看size，树的大小
        // 而且调整完了之后，还需要看看有哪些节点发生了变化，继续调整这些发生了变化的节点
        public SBNode<K> maintain(SBNode<K> cur) {
            if (cur == null) {
                return null;
            }
            int leftSize = cur.l != null ? cur.l.size : 0;
            int rightSize = cur.r != null ? cur.r.size : 0;
            // 要跟侄子去比，必须是亲侄子！
            int leftLeftSize = cur.l != null && cur.l.l != null ? cur.l.l.size : 0;
            int leftRightSize = cur.l != null && cur.l.r != null ? cur.l.r.size : 0;
            int rightLeftSize = cur.r != null && cur.r.l != null ? cur.r.l.size : 0;
            int rightRightSize = cur.r != null && cur.r.r != null ? cur.r.r.size : 0;

            if (leftLeftSize > rightSize) {   // LL型
                cur = rightRotate(cur);
                cur.r = maintain(cur.r);
                cur = maintain(cur);
            } else if (leftRightSize > rightLeftSize) {   // LR型
                cur.l = leftRotate(cur.l);
                cur = rightRotate(cur);
                cur.l = maintain(cur.l);
                cur.r = maintain(cur.r);
                cur = maintain(cur);
            } else if (rightRightSize > leftSize) {   // RR型
                cur = leftRotate(cur);
                cur.l = maintain(cur.l);
                cur = maintain(cur);
            } else if (rightLeftSize > leftSize){   // RL型
                cur.r = rightRotate(cur.r);
                cur = leftRotate(cur);
                cur.l = maintain(cur.l);
                cur.r = maintain(cur.r);
                cur = maintain(cur);
            }
            return cur;
        }
    }
}

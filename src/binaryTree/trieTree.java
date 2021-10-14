package binaryTree;

import java.util.HashMap;

/**
 * 什么是前缀树
 * 给定一个字符串数组 ["abc","bck","abd","ace"]
 * 就可以变成如下的前缀树
 * O
 * a/ \b
 * O   O
 * b/ \c  \c
 * O   O   O
 * c/ \d  \e  \k
 * O  O    O   O
 * 最开始有一个空结点O，开始遍历第一个字符串，发现O下面没有a，所以就在O下面创建一条路径表示a
 * 接着a下面也没有b，所以继续创建一条路径表示b，b下面没有c，创建一条路径表示c
 * ...(如果存在节点就可以复用，比如abc和abd)
 * <p>
 * 但是我们可以发现这里面的路径表示字符，但是节点的信息为空，所以我们可以给节点添加一些属性，使其意义更加丰富一些
 * <p>
 * ["abc","bck","abd","ace"]
 * <p>
 * O   (p=4,e=0)
 * a/ \b
 * (p=3,e=0) O   O  (p=1,e=0)
 * b/ \c  \c
 * (p=2,e=0)O   O   O (p=1,e=0)   , 中间那个节点c下面的也是(p=1,e=0)
 * c/ \d  \e  \k
 * (p=1,e=1)O  O    O   O (p=1,e=0)
 * (p=1,e=1) (p=1,e=0)
 * <p>
 * 其中p代表的意思是有多个条路径经过这个点
 * e代表的意思是当前这个节点是不是终止节点
 */
public class trieTree {
    public NodeForTrie root;

    public trieTree() {
        root = new NodeForTrie();
    }

    // 将word插入到前缀树中
    public void insert(String word) {
        if (word.equals("") || word == null) {
            return;
        }
        char[] chs = word.toCharArray();
        NodeForTrie node = root;
        node.pass++;
        int path = 0;
        for (char ch : chs) {
            path = ch - 'a';
            if (node.nexts[path] == null) {
                node.nexts[path] = new NodeForTrie();
            }
            node = node.nexts[path];
            node.pass++;
        }
        node.end++;
    }

    // 删除某个单词
    public void delete(String word) {
        if (searchNumForWord(word) != 0) {
            char[] chs = word.toCharArray();
            NodeForTrie node = root;
            node.pass--;
            int path = 0;
            for (int i = 0; i < chs.length; i++) {
                path = chs[i] - 'a';
                if (--node.nexts[path].pass == 0) {
                    node.nexts[path] = null;
                    return;
                }
                node = node.nexts[path];
            }
            node.end--;
        }
    }

    // word这个单词出现过几次
    // 最后一个字母的end是多少，他就出现了多少次
    public int searchNumForWord(String word) {
        if (word.equals("") || word == null) {
            return 0;    // 返回0，不要反悔-1等乱七八糟的东西！！
        }
        NodeForTrie node = root;
        char[] chs = word.toCharArray();
        // 遍历到最后一个节点就行了
        for (int i = 0; i < chs.length; i++) {
            node = node.nexts[chs[i] - 'a'];
            if (node == null) {
                return 0;    // 比如前缀树中只有abc，但是我找的是abcd，所以当到达d的时候，node==null
            }
        }
        return node.end;
    }

    // 有几个单词是以pre为前缀的
    public int prefixNumber(String pre) {
        if (pre.equals("") || pre == null) {
            return 0;
        }
        // 直到pre的最后一个字符所在节点的pass
        char[] chs = pre.toCharArray();
        NodeForTrie node = root;
        int index = 0;
        for (char ch : chs) {
            index = ch - 'a';
            if (node.nexts[index] == null) {
                return 0;    // 返回0，不要反悔-1等乱七八糟的东西
            }
            node = node.nexts[index];
        }
        return node.pass;
    }
}

class NodeForTrie {
    public int pass;
    public int end;
    public NodeForTrie[] nexts;

    public NodeForTrie() {
        pass = 0;
        end = 0;
        nexts = new NodeForTrie[26];
        // nexts表示当前节点下面的节点
        // nexts[0] != null 表示 当前节点下面有`a`
        // nexts[1] != null 表示 当前节点下面有`b`
        // ...
        // nexts[25] != null 表示 当前节点下面有`z`
    }
}

// 正确的前缀树方法
class Right {

    private HashMap<String, Integer> box;

    public Right() {
        box = new HashMap<>();
    }

    public void insert(String word) {
        if (!box.containsKey(word)) {
            box.put(word, 1);
        } else {
            box.put(word, box.get(word) + 1);
        }
    }

    public void delete(String word) {
        if (box.containsKey(word)) {
            if (box.get(word) == 1) {
                box.remove(word);
            } else {
                box.put(word, box.get(word) - 1);
            }
        }
    }

    public int search(String word) {
        if (!box.containsKey(word)) {
            return 0;
        } else {
            return box.get(word);
        }
    }

    public int prefixNumber(String pre) {
        int count = 0;
        for (String cur : box.keySet()) {
            if (cur.startsWith(pre)) {
                count += box.get(cur);
            }
        }
        return count;
    }
}

class test {
    /**
     * 对数器的使用
     */

    // for test   生成随机数据集 --- 生成随机字符串
    public static String generateRandomString(int strLen) {
        char[] ans = new char[(int) (Math.random() * strLen) + 1];
        for (int i = 0; i < ans.length; i++) {
            int value = (int) (Math.random() * 6);
            ans[i] = (char) (97 + value);
        }
        return String.valueOf(ans);
    }

    // for test  生成随机数据集 --- 生成随机字符数组
    public static String[] generateRandomStringArray(int arrLen, int strLen) {
        String[] ans = new String[(int) (Math.random() * arrLen) + 1];
        for (int i = 0; i < ans.length; i++) {
            ans[i] = generateRandomString(strLen);
        }
        return ans;
    }


    public static void main(String[] args) {
        int arrLen = 100;
        int strLen = 20;
        int testTimes = 100000;
        for (int i = 0; i < testTimes; i++) {
            String[] arr = generateRandomStringArray(arrLen, strLen);
            trieTree trie1 = new trieTree();
            Right right = new Right();
            for (int j = 0; j < arr.length; j++) {
                double decide = Math.random();
                if (decide < 0.25) {
                    trie1.insert(arr[j]);
                    right.insert(arr[j]);
                } else if (decide < 0.5) {
                    trie1.delete(arr[j]);
                    right.delete(arr[j]);
                } else if (decide < 0.75) {
                    int ans1 = trie1.searchNumForWord(arr[j]);
                    int ans3 = right.search(arr[j]);
                    if (ans1 != ans3) {
                        System.out.println("1Oops!");
                    }
                } else {
                    int ans1 = trie1.prefixNumber(arr[j]);
                    int ans3 = right.prefixNumber(arr[j]);
                    if (ans1 != ans3) {
                        System.out.println("2Oops!");
                    }
                }
            }
        }
        System.out.println("finish!");

    }
}


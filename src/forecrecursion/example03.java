package forecrecursion;

import java.util.ArrayList;
import java.util.List;

// 打印一个字符串的全部序列，不能够包含重复的
public class example03 {

    public static void printAllSequence(String word) {
        if (word == null) {
            return;
        }
        char[] chs = word.toCharArray();
        List<String> res = new ArrayList<>();
        process(chs, 0, res);
        for (String str : res) {
            System.out.println(str);
        }
    }

    // 对于一个字符串 abcd
    // 如果第一个位置上是 a，那么后面(bcd)是一堆

    public static void process(char[] chs, int index, List<String> res) {
        if (index == chs.length) {
            res.add(String.valueOf(chs));
            return;
        }
        boolean[] isVisited = new boolean[26];  // isVisited[0]表示`a`这个字符有没有被试过放到j位置
        for (int j = index; j < chs.length; j++) {
            if (!isVisited[chs[j] - 'a']) {
                // 这三句话从整体上看
                // 第一个swap，相当于交换index位置的数，比如index=0，那么a,b,c,d都可以在
                // 第一个位置上，相当于对第一个位置进行变化
                swap(chs, index, j);
                // 对chs[index+1,...]之后的区域进行处理，比如第一个位置选出来的是a，
                // 那么这句话就是对(b,c,d)进行process
                process(chs, index + 1, res);
                // 我还要把顺序给还原，比如我最开始是abcd，我第一个swap让b当成了开头，成了bacd，
                // 我应该先把顺序给恢复成abcd，在进行下一次的交换，即使用c,d交换
                swap(chs, index, j);
                isVisited[chs[j] - 'a'] = true;
            }
        }
    }

    public static void swap(char[] chs, int i, int j) {
        char temp = 0;
        temp = chs[i];
        chs[i] = chs[j];
        chs[j] = temp;
    }


    public static void printList(List<Character> res) {
        StringBuffer str = new StringBuffer();
        for (char ch : res) {
            str.append(ch);
        }
        System.out.println(str);
    }

    public static void main(String[] args) {
        printAllSequence("aabc");
    }
}

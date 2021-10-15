package forecrecursion;

import java.util.ArrayList;
import java.util.List;

/**
 * 打印一个字符串的全部子序列，包括空字符串
 */
public class example02 {

    public static void allSubSequence(String word) {
        char[] chs = word.toCharArray();

        int res = process(chs, 0, new ArrayList<>());
        System.out.println(res);    // 直接就都包含了空字符串
    }

    // i 往前的不管了，我就看i位置上的字符要不要
    // res 表示的是之前的i所形成的字符列表
//    public static void process(char[] chs, int i, List<Character> res) {
//        if (i == chs.length) {
//            printList(res);
//            return;
//        }
//        // 要i位置上的字符
//        List<Character> resKeepCopy = copyList(res);
//        resKeepCopy.add(chs[i]);
//        process(chs, i + 1, resKeepCopy);
//        // 不要i位置上的字符
//        List<Character> resNoKeepCopy = copyList(res);
//        process(chs,i+1,resNoKeepCopy);
//    }

    // 返回子字符串的数量
    public static int process(char[] chs, int i, List<Character> res) {
        int result = 0;
        if (i == chs.length) {
            // printList(res);
            return 1;
        }
        // 要i位置上的字符
        List<Character> resKeepCopy = copyList(res);
        resKeepCopy.add(chs[i]);
        result += process(chs, i + 1, resKeepCopy);
        // 不要i位置上的字符
        List<Character> resNoKeepCopy = copyList(res);
        result += process(chs,i+1,resNoKeepCopy);
        return result;
    }

    public static List<Character> copyList(List<Character> res) {
        List<Character> resCopy = new ArrayList<>();
        for (char ch : res) {
            resCopy.add(ch);
        }
        return resCopy;
    }

    public static void printList(List<Character> res) {
        StringBuffer str = new StringBuffer();
        for (char ch : res) {
            str.append(ch);
        }
        System.out.println(str);
    }

    public static void main(String[] args) {
        allSubSequence("abc");
    }
}

package 滑动窗口;

import java.util.HashSet;

/**
 *      给定m个不重复的字符 [a, b, c, d]，以及一个长度为n的字符串tbcacbdata，
 *      能否在这个字符串中找到一个长度为m的连续子串，使得这个子串刚好由上面m个字符组成.
 *      顺序无所谓，返回任意满足条件的一个子串的起始位置，未找到返回-1。比如上面这个例子，acbd，3.
 */
public class HasSameString {
    public static int findIndex(char[] ch, String s) {
        int targetSize = ch.length;
        int totalSize = s.length();

        if (targetSize > totalSize) {
            return -1;
        }

        // 实际上，相当于创建了一个长度为targetSize的窗口，不断比较窗口内的字符和给定的字符数组是否相同
        for (int i = 0; i < totalSize - targetSize; i++) {
            if (check(ch, s.substring(i, i + targetSize))) {
                return i;
            }
        }
        return -1;
    }

    public static boolean check(char[] ch, String s) {
        HashSet<Character> memo = new HashSet<>();
        for (char c : ch) {
            memo.add(c);
        }
        for (char c : s.toCharArray()) {
            if (memo.contains(c)) {
                memo.remove(c);
            }
        }
        return memo.size() == 0;
    }

//    private static boolean check(char[] ch, String s) {
//        for (int i = 0; i < ch.length; i++) {
//            if (s.indexOf(ch[i]) == -1) {
//                return false;
//            }
//        }
//        return true;
//    }


    public static void main(String[] args) {
        char[] ch = {'a', 'b', 'c', 'd'};
        String s = "tbcacbdata";
        System.out.println(findIndex(ch, s));
    }
}

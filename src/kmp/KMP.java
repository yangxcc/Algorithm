package kmp;

import java.util.Arrays;

/**
 * kmp算法是一种匹配字符串的算法
 * 比如给定两个字符串 str1 = abcdefafsfsdvcdsc， str2 = fsfsd，那么通过kmp算法之后
 * 会返回str1中是否包含str2，如果包含，则返回最先出现的位置，比如返回7，如果不包含，则返回-1
 * <p>
 * <p>
 * 通过上面的场景可以看出，暴力算法很容易想出来，两层循环，但是这种会有很大的时间浪费，因为如果对于两个字符串
 * str1 = aaaaacc  str2=aac，我会先从str1的0位置开始，一个位置一个位置的试，如果第i个位置没有匹配上，再从第i+1个
 * 位置开始，进行匹配
 * <p>
 * 因为上面这种效率太低了，所以就有了kmp算法
 */
public class KMP {

    public static int KMPAlgorithm(String s, String m) {
        if (s == null || m == null || s.length() < 1 || m.length() < 1) {
            return -1;
        }
        char[] str1 = s.toCharArray();
        char[] str2 = m.toCharArray();
        int i1 = 0;
        int i2 = 0;
        int[] next = getNexts(str2);
//        System.out.println(Arrays.toString(next));
        while (i1 < str1.length && i2 < str2.length) {
            if (str1[i1] == str2[i2]) {
                i1++;
                i2++;
//                System.out.println("--1---");
            } else if (next[i2] == -1) {  // 这个时候表示str2已经来到了0字符了，str2不能再往前推了，str1往前走一个换一个字符吧
                i1++;
//                System.out.println("2");
            } else {
                i2 = next[i2];
//                System.out.println("3");
            }
        }
        return i2 == str2.length ? i1 - i2 : -1;
        // 只有一种情况下i2会越界，那就是在str1中找到了str2
        // 如果i2越界了，那就返回str1中str2的开头位置，i1-i2
        // 如果i1越界了，说明str1走完了也没能找到str2
    }

    // 得到str2的最大前缀和数组
    public static int[] getNexts(char[] ms) {
        if (ms.length == 1) {
            return new int[]{-1};
        }
        int[] next = new int[ms.length];
        next[0] = -1;
        next[1] = 0;
        int i = 2; // next数组开始的位置
        int cn = 0;
        // cn 在下面的代码中有两个功能：第一个功能是表示i位置的最大前缀，第二个意思还能够定位到前缀的后一个
        while (i < next.length) {
            if (ms[cn] == ms[i - 1]) {
                next[i++] = ++cn;
                // 相当于 cn++  next[i] = cn  i++
            } else if (cn > 0) {
                cn = next[cn];
            } else {
                next[i++] = 0;
            }
        }
        return next;
    }


    public static void main(String[] args) {
        int res = KMPAlgorithm("ABCABCEREFDFRGFD", "EFDFR");
        System.out.println(res);
    }
}

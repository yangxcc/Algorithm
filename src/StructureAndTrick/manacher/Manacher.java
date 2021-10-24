package StructureAndTrick.manacher;

/**
 * 字符串str中，最长回文子串的长度，如何做到时间复杂度O(N)完成
 * 回文就是正着读和反着读是一样的
 */
public class Manacher {
    // 与处理字符串，加上特殊字符
    public static char[] manacherString(String str) {
        char[] charArr = str.toCharArray();
        char[] res = new char[charArr.length * 2 + 1];
        int index = 0;
        for (int i = 0; i < res.length; i++) {
            res[i] = (i & 1) == 0 ? '#' : charArr[index++];
        }
        return res;
    }

    public static int maxLcpsLength(String s) {
        if (s == null || s.length() == 0) {
            return -1;
        }
        char[] str = manacherString(s);  // 将s变成处理串
        int[] pArr = new int[str.length]; // 回文半径数组
        int R = -1;   // 在流程分析时R表示的是右边界的位置，这里表示的是右边界的下一个位置
        int C = -1;   // 中心点
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < str.length; i++) {

            pArr[i] = R > i ? Math.min(pArr[2 * C - i], R - i) : 1;

            // 上面这一句话包含了我们分析中的四种情况
            // 本质上，pArr就代表了不用再去验证的区域
            // R > i，表示这i在右边界外面，所以这时不用验证的区域就是i本身，所以给他一个值1
            // 如果i在右边界里面，那么就有三种情况了，pArr[2 * C - i]代表i’的回文半径区域， R-i同样是代表回文半径区域
            // 当i'的回文区域完全在C的回文半径区域里面的时候，i的回文半径就等于i’的回文半径
            // 当i'的回文区域有一部分超出在C的回文半径区域的时候，i的回文半径就是R-i
            // 当i'的回文区域与在C的回文半径区域的左边界压线的时候，这两个数相等，这段区域可以不用验证了，对于后面则需要暴力扩展
            while (i + pArr[i] < str.length && i - pArr[i] > -1) {
                if (str[i + pArr[i]] == str[i - pArr[i]]) {
                    pArr[i]++;   // 至少不用验的区域跳过去了，之后如果相等再去扩
                } else {
                    break;   // 那两种不用扩的情况在这里
                }
            }
            if (i + pArr[i] > R) {   // 更新右边界和中心点
                R = i + pArr[i];
                C = i;
            }
            max = Math.max(max, pArr[i]);
        }
        return max - 1;
    }


    public static void main(String[] args) {
        String str = "abcdedcbakabcdedcft";
        System.out.println(maxLcpsLength(str));
    }
}

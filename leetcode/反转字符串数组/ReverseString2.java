package 反转字符串数组;

/**
 * 给定一个字符串 s 和一个整数 k，从字符串开头算起，每计数至 2k 个字符，就反转这 2k 字符中的前 k 个字符。
 *
 * 如果剩余字符少于 k 个，则将剩余字符全部反转。
 * 如果剩余字符小于 2k 但大于或等于 k 个，则反转前 k 个字符，其余字符保持原样。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/reverse-string-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * 时间复杂度应该都是O(n*logk)吧？
 */
public class ReverseString2 {
    public String reverseStr(String s, int k) {
        char[] chs = s.toCharArray();
        // 这里i的条件是i += 2 * k！
        for (int i = 0; i < chs.length; i += 2 * k) {
            // 剩下字符串的长度大于等于k个，那么就能反转这k个
            if (i + k <= chs.length) {
                reverse(chs, i, i + k);
                continue;
            }
            // 最后再反转，不足k个的字符串，能够写到这里是因为前面有continue，如果是比k大，根本走不到这里
            reverse(chs, i, chs.length - 1);
        }
        return new String(chs);
    }


    public String reverseStr2(String s, int k) {
        char[] chs = s.toCharArray();
        for (int i = 0; i < chs.length; i += 2 * k) {
            int start = i;
            int end = Math.min(start + k - 1, chs.length - 1);
            while (start < end) {
                char temp = chs[start];
                chs[start] = chs[end];
                chs[end] = temp;
                start++;
                end--;
            }
        }
        return new String(chs);
    }

    public void reverse(char[] chs, int begin, int end) {
        char temp = chs[begin];
        chs[begin] = chs[end];
        chs[end] = temp;
    }
}

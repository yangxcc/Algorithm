package 反转字符串数组;

/**
 * 左旋转字符串
 *
 * 字符串的左旋转操作是把字符串前面的若干个字符转移到字符串的尾部。
 *
 * 输入: s = "abcdefg", k = 2
 * 输出: "cdefgab"
 */
public class RotateString {
    // 其实如果是不考虑空间复杂度的的话，直接s*2，就能够得到了
    public String reverseLeftWords(String s, int n) {
        String res = s + s;
        return res.substring(n, n + s.length());
    }

    // 如果想要原地旋转
    // 那么就需要分成三个步骤了
    // 先反转前k个，再反转k到末尾，再反转整个的字符串
    public String reverseLeftWords2(String s, int k) {
        // sb指向的是字符串常量池中的s，所以并没有在堆空间中创建新的对象，所以空间复杂度是O(1)
        StringBuilder sb = new StringBuilder(s);
        reverse(sb, 0, k - 1);
        reverse(sb, k, s.length() - 1);
        reverse(sb, 0, s.length() - 1);
        return sb.toString();
    }

    public void reverse(StringBuilder s, int begin, int end) {
        while (begin < end) {
            char temp = s.charAt(begin);
            s.setCharAt(begin, s.charAt(end));
            s.setCharAt(end, temp);
            begin++;
            end--;
        }
    }
}

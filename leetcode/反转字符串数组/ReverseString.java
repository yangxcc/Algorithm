package 反转字符串数组;

/**
 *
 */
public class ReverseString {
    // 这个没什么好说的，直接双指针，在O(1)的空间复杂度下反转字符串就好了
    public void reverseString(char[] s) {
        for (int i = 0, j = s.length - 1; i < j; i++, j--) {
            char temp = s[i];
            s[i] = s[j];
            s[j] = temp;
        }
    }
}

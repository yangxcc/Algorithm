package 剑指offer;

import java.util.HashMap;

/**
 * 最长不包含重复字符的子字符串
 *
 * 题目链接；https://leetcode-cn.com/problems/zui-chang-bu-han-zhong-fu-zi-fu-de-zi-zi-fu-chuan-lcof/*/
public class offer48 {
    // 这道题是简单的滑动窗口题
    public int lengthOfLongestSubstring(String s) {

        if (s.equals("") || s.length() == 0) {
            return 0;
        }
        int left = 0, right = 0;
        HashMap<Character, Integer> window = new HashMap<>();
        int max = 0;
        while (right < s.length()) {
            char c = s.charAt(right);
            right++;
            window.put(c, window.getOrDefault(c, 0) + 1);

            while (window.get(c) > 1) {
                char d = s.charAt(left);
                left++;
                window.put(d, window.get(d) - 1);
            }
            max = Math.max(right - left, max);
        }
        return max;
    }
}

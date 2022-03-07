package 错题集;

/**
 * 给你一个字符串 s 和一个整数 k ，请你找出 s 中的最长子串， 要求该子串中的每一字符出现次数都不少于 k 。返回这一子串的长度。
 */
public class LongestSubstringWithAtLeastKRepeatingCharacters {
    public int longestSubstring(String s, int k) {
        return dfs(s, k);
    }

    public int dfs(String s, int k) {
        // 统计每个字符出现的次数
        int[] charCount = new int[26];
        for (int i = 0; i < s.length(); i++) {
            charCount[s.charAt(i) - 'a']++;
        }

        // 找出<k的字符
        String split = "";
        for (int i = 0; i < charCount.length; i++) {
            int count = charCount[i];
            if (count > 0 && count < k) {
                // 这里必须这么写！
                split = String.valueOf((char)(i + 'a'));
                break;
            }
        }

        // 如果字符全部都>=k，这时候返回这个s就可以了
        if (split.equals("")) {
            return s.length();
        }

        // 通过这个<k的字符来分段，因为要求的是最长的连续部分，所以肯定是通过<k的字符来分割
        // 分段是把大的字符串按照出现次数小于k的字符给分开的
        String[] strs = s.split(split);
        int max = 0;
        for (String str : strs) {
            int len = dfs(str, k);
            max = Math.max(max, len);
        }
        return max;
    }
}

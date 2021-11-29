package 剑指offer;

/**
 * 第一个只出现一次的字符
 *
 * 题目链接：https://leetcode-cn.com/problems/di-yi-ge-zhi-chu-xian-yi-ci-de-zi-fu-lcof/
 * */
public class offer50 {
    // 这道题当然可以使用hashmap，但是肯定有比hashmap更好的方法
    // 通过这个题建立起一个意识来，那就是为每个字符构建一个数组
    public char firstUniqChar(String s) {
        if (s.equals("") || s.length() == 0) {
            return ' ';
        }
        char[] chs = s.toCharArray();
        int[] target = new int[26];
        for (char ch : chs) {
            target[ch]++;
        }
        for (int i = 0; i < s.length(); i++) {
            if (target[s.charAt(i) - 'a'] == 1) {
                // char和int一样，需要注意使用char的时候会有-'a'和-'0'
                return s.charAt(i);
            }
        }
        return ' ';
    }
}

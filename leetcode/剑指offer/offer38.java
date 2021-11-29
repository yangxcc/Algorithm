package 剑指offer;

import java.util.HashSet;
import java.util.Set;

/**
 * 字符串的排列
 * 输入一个字符串，打印出该字符串中字符的所有排列。
 * 你可以以任意顺序返回这个字符串数组，但里面不能有重复元素。
 *
 * 题目链接 https://leetcode-cn.com/problems/zi-fu-chuan-de-pai-lie-lcof/
 * */
public class offer38 {
    public String[] permutation(String s) {
        if (s.equals("") || s.length() == 0) {
            return new String[0];
        }
        Set<String> res = new HashSet<>();
        StringBuilder sb = new StringBuilder();
        boolean[] isVisited = new boolean[s.length()];
        process(s, sb, isVisited, res);
        return res.toArray(new String[0]);
    }

    public void process(String s, StringBuilder sb, boolean[] isVisited, Set<String> res) {
        if (sb.length() == s.length()) {
            res.add(sb.toString());
            return;
        }
        for (int i = 0; i < s.length(); i++) {
            if (isVisited[i]) continue;
            isVisited[i] = true;
            sb.append(s.charAt(i));
            process(s, sb, isVisited, res);
            isVisited[i] = false;
        }
    }
}

package 回溯;

import java.util.HashSet;

/**
 * 字符串全排列
 * 和数字全排列一摸一样
 *
 * 输入：s = "abc"
 * 输出：["abc","acb","bac","bca","cab","cba"]
 *
 * 输入一个字符串，打印出该字符串中字符的所有排列。
 * 你可以以任意顺序返回这个字符串数组，但里面不能有重复元素。
 */
public class strPermutations {
    // 因为不能够含有重复的元素，所以需要用到Set
    HashSet<String> hs = new HashSet<>();

    public String[] permutation(String s){
        if (s.equals("") || s.length() == 0) {
            return new String[0];
        }
        boolean[] visited = new boolean[s.length()];
        char[] chs = s.toCharArray();
        backtrack(chs, visited, "");
        return hs.toArray(new String[0]);
    }

    public void backtrack(char[] chs, boolean[] visited, String path) {
        // index表示是第index个字符
        if (path.length() == chs.length) {
            hs.add(path);
            return;
        }

        for (int i = 0; i < chs.length; i++) {
            if (visited[i]) continue;
            // 做选择
            visited[i] = true;

            backtrack(chs, visited, path + chs[i]);
            // 撤销选择
            visited[i] = false;
        }
    }
}

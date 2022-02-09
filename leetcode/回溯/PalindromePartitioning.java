package 回溯;

import java.util.ArrayList;
import java.util.List;

/**
 * 分割回文字符串
 * 输入：s = "aab"
 * 输出：[["a","a","b"],["aa","b"]]
 *
 * 这道题的思路就是回溯，也就是穷举每种可能，在穷举的过程中剪枝
 */
public class PalindromePartitioning {

    List<List<String>> res = new ArrayList<>();

    public List<List<String>> partition(String s) {
        if (s.equals("") || s.length() == 0) {
            return new ArrayList<>();
        }
        backtrack(s, 0, new ArrayList<>());
        return res;
    }

    // index表示当前遍历到什么位置了
    public void backtrack(String s, int index, List<String> path) {
        if (index == s.length()) {
            res.add(new ArrayList<>(path));
            return;
        }

        for (int i = index; i < s.length(); i++) {
            if (isValid(s, index, i)) {
                // 做选择
                path.add(s.substring(index, i + 1));   // substring是左闭右开的区间

                // 回溯
                backtrack(s, i + 1, path);

                // 撤销选择
                path.remove(path.size() - 1);
            } else {
                continue;   // 这里是continue，而不是break，因为ab不是回文串，但是aba就是回文串了，所以是跳过这一次循环，不是跳过整个循环
            }
        }
    }

    public boolean isValid(String s, int startIndex, int endIndex) {
        while (startIndex < endIndex) {
            if (s.charAt(startIndex) != s.charAt(endIndex)) {
                return false;
            }
            startIndex++;
            endIndex--;
        }
        return true;
    }
}

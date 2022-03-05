package dp.背包问题._01背包;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * 给你一个二进制字符串数组 strs 和两个整数 m 和 n 。
 * <p>
 * 请你找出并返回 strs 的最大子集的长度，该子集中 最多 有 m 个 0 和 n 个 1 。
 * <p>
 * 如果 x 的所有元素也是 y 的元素，集合 x 是集合 y 的 子集 。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/ones-and-zeroes
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class OnesAndZeros {
    // 方法1：回溯得到所有的子集，然后对子集进行校验选择
    // 这种方法在leetcode种内存爆了
    List<List<String>> res = new ArrayList<>();

    public int findMaxForm(String[] strs, int m, int n) {
        List<String> path = new ArrayList<>();
        backtrack(strs, 0, path);
        // 现在res中都是strs的子集
        int maxLen = 0;
        Iterator<List<String>> it = res.iterator();
        while (it.hasNext()) {
            ArrayList<String> p = (ArrayList<String>) it.next();
            if (valid(p, m, n)) {
                maxLen = Math.max(maxLen, p.size());
            }
        }
        return maxLen;
    }

    public void backtrack(String[] strs, int index, List<String> path) {
        res.add(new ArrayList<>(path));

        if (index == strs.length) {
            return;
        }

        for (int i = index; i < strs.length; i++) {
            path.add(strs[i]);
            backtrack(strs, i + 1, path);
            path.remove(path.size() - 1);
        }
    }

    public boolean valid(ArrayList<String> path, int m, int n) {
        for (int i = 0; i < path.size(); i++) {
            for (int j = 0; j < path.get(i).length(); j++) {
                if (path.get(i).charAt(j) == '1') {
                    n--;
                } else {
                    m--;
                }
            }
        }
        return m >= 0 && n >= 0;
    }


    // 方法2：因为子集问题属于组合问题，所以有可能能够使用动态规划解决
    public int findMaxForm2(String[] strs, int m, int n) {
        // dp[i][j]表示最多i个0,j个1的最大子集的长度，对状态进行压缩了
        // 对于每一个字符串strs[k]都可以选择或者不选
        // 这个其实本质上也是一个0-1背包，只不过物品有两个属性，因此背包也需要有两个属性
        int[][] dp = new int[m + 1][n + 1];
        dp[0][0] = 0;
        for (String str : strs) {
            // 先遍历物品
            int zeroNum = 0, oneNum = 0;
            for (int k = 0; k < str.length(); k++) {
                if (str.charAt(k) == '1') {
                    oneNum++;
                } else {
                    zeroNum++;
                }
            }

            // 再倒序遍历背包
            for (int i = m; i >= zeroNum; i--) {
                for (int j = n; j >= oneNum; j--) {
                    dp[i][j] = Math.max(dp[i][j], dp[i - zeroNum][j - oneNum] + 1);
                    // 选择这个字符串或者不选
                }
            }
        }

        return dp[m][n];
    }
}

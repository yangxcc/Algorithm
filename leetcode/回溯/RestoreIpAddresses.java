package 回溯;

import java.util.ArrayList;
import java.util.List;

/**
 * 有效 IP 地址 正好由四个整数（每个整数位于 0 到 255 之间组成，且不能含有前导 0），整数之间用 '.' 分隔。
 *
 * 例如："0.1.2.201" 和 "192.168.1.1" 是 有效 IP 地址，但是 "0.011.255.245"、"192.168.1.312" 和 "192.168@1.1"
 * 是 无效 IP 地址。
 *
 */
public class RestoreIpAddresses {
    List<String> res = new ArrayList<>();

    public List<String> restoreIpAddresses(String s) {
        if (s.equals("") || s.length() == 0) {
            return new ArrayList<>();
        }
        backtrack(s, 0, 0);
        return res;
    }

    // 这里的难点就是要想到这个pointNum，当前字符串中点的个数
    // 因为要设置结束条件,只有一个index不够，走到头了，怎么样才能加入到res中呢？
    // 二叉树路径遍历中有一个当前路径和变量，所以这里还需要一个点的个数
    public void backtrack(String s, int index, int pointNum) {
        if (pointNum == 3) {
            // 有三个点了，从index到字符串最后如果合法，那么才能够加入到res中
            if (isValid(s, index, s.length())) {
                res.add(s);
                return;
            }
        }

        for (int i = index; i < s.length(); i++) {
            if (isValid(s, index, i)) {
                // 做选择，在i位置后面加上.
                s = s.substring(0, i + 1) + "." + s.substring(i + 1);
                pointNum++;

                // 回溯，这里要越过上面加上的那个点
                backtrack(s, i + 2, pointNum);

                // 撤销选择
                s = s.substring(0, i + 1) + s.substring(i + 2); // 要越过那个.
                pointNum--;
            } else {
                break;  // 这里就是break了，因为一旦有一个位置是不合法的，那么这条路径就没有继续下去的必要了
            }
        }
    }

    public boolean isValid(String s, int startIndex, int endIndex) {
        if (startIndex > endIndex) {
            return false;   // 因为在回溯的时候，是i+2，所以有可能出现这种情况
        }
        if (endIndex > startIndex && s.charAt(startIndex) == '0') {
            return false;   // 023这种也是不符合规范的
        }

        int sum = 0;
        for (int i = startIndex; i <= endIndex; i++) {
            // 因为有一个测试用例是里面有@符号
            if (!Character.isDigit(s.charAt(i))) {
                return false;
            }
            sum = sum * 10 + s.charAt(i) - '0';
            if (sum > 255 || sum < 0) {
                return false;
            }
        }
        return true;
    }
}

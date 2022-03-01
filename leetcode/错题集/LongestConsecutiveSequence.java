package 错题集;

import java.util.HashSet;

/**
 * 最长连续数序列
 */
public class LongestConsecutiveSequence {
    public int LongestConsecutive(int[] nums) {
        HashSet<Integer> memo = new HashSet<>();
        int maxLen = 1;
        for (int num : nums) {
            memo.add(num);
        }

        // 在hashset中找和nums[i]相邻的数
        for (int i = 0; i < nums.length; i++) {
            int curLen = 1;
            int num = nums[i];
            while (!memo.isEmpty() && memo.contains(--num)) {
                curLen++;
                memo.remove(num);
            }

            num = nums[i];  // 一定不要忘了再把num给弄回来，再去看看有没有num右边的数
            while (!memo.isEmpty() && memo.contains(++num)) {
                curLen++;
                memo.remove(num);
            }

            maxLen = Math.max(maxLen, curLen);
        }
        return maxLen;
    }
}

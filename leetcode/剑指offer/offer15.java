package 剑指offer;

/**
 * 二进制中1的个数
 *
 * 题目链接：https://leetcode-cn.com/problems/er-jin-zhi-zhong-1de-ge-shu-lcof/
 * */
public class offer15 {

    public int hammingWeight(int n) {
        int res = 0;
        while (n != 0) {
            res++;
            n -= getMostRightOne(n);
        }
        return res;
    }

    // 这里复习一下在看左神视频的时候的一个小知识点
    // 找到一个二进制数中的最右侧的1
    public int getMostRightOne(int n) {
        return n & (~n + 1);
    }
}

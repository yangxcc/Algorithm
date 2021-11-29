package 剑指offer;

import java.util.ArrayList;
import java.util.List;

/**
 * 把数组排成最小的数
 * 输入一个非负整数数组，把数组里所有数字拼接起来排成一个数，打印能拼接出的所有数字中最小的一个。
 *
 * 输入: [3,30,34,5,9]
 * 输出: "3033459"
 *
 * 题目链接：https://leetcode-cn.com/problems/ba-shu-zu-pai-cheng-zui-xiao-de-shu-lcof/
 *
 * */
public class offer45 {
    /**
     * 我认为这个问题最难想的地方在于对于两个数怎么样排列最小呢？
     * 可以通过比较O1+O2和O2+O1，例如[10,2]
     * O1+O2 --> 102
     * O2+O1 --> 210
     *
     * 想到了这里，应该就能够想到使用比较器了，
     * 想到比较器，就应该学会使用lambda表达式了，有时间学一下lambda表达式
     *
     * */
    public String minNumber(int[] nums) {
        List<String> res = new ArrayList<>();
        for (int num : nums) {
            res.add(String.valueOf(num));
        }
        res.sort((o1, o2) -> (o1 + o2).compareTo(o2 + o1));
        return String.join("", res);
    }
}

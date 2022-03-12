package dp;

/**
 * 有 buckets 桶液体，其中 正好有一桶 含有毒药，其余装的都是水。
 * 它们从外观看起来都一样。为了弄清楚哪只水桶含有毒药，你可以喂一些猪喝，
 * 通过观察猪是否会死进行判断。不幸的是，你只有 minutesToTest 分钟时间来确定哪桶液体是有毒的。
 *
 * 喂猪的规则如下：
 *
 *      选择若干活猪进行喂养
 *      可以允许小猪同时饮用任意数量的桶中的水，并且该过程不需要时间。
 *      小猪喝完水后，必须有 minutesToDie 分钟的冷却时间。在这段时间里，你只能观察，而不允许继续喂猪。
 *      过了 minutesToDie 分钟后，所有喝到毒药的猪都会死去，其他所有猪都会活下来。
 *      重复这一过程，直到时间用完。
 *
 * 给你桶的数目 buckets ，minutesToDie 和 minutesToTest ，返回 在规定时间内判断哪个桶有毒所需的 最小 猪数 。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/poor-pigs
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class PoorPigs {
    public int poorPigs(int buckets, int minutesToDie, int minutesToTest) {
        if (buckets == 1) {
            return 0;  // 只有一桶水，不用猪就知道谁是有毒的
        }
        // 首先根据题意，我们可以知道通过minutesToDie和minutesToTest能够确定下可以测试的轮数
        int iterator = minutesToTest / minutesToDie;
        // dp[i][j]表示一共需要验证i桶，能够测试j轮，最少需要的猪🐖的数量
        int[][] dp = new int[buckets][iterator + 1];
        // 初始化
        for (int i = 0; i < buckets; i++) {
            dp[i][0] = 1;   // 当测试轮数为0的时候，有一只猪就够了
        }
        for (int j = 0; j <= iterator; j++) {
            dp[0][j] = 1;   // 桶子的数量为0的时候，有一只猪就够了
        }

        // combinations[i][j]其实表示的是从i只猪中选出j只来，死掉，也就是喝到了有毒的水
        int[][] combinations = new int[buckets + 1][buckets + 1];
        combinations[0][0] = 1;
//        combinations[buckets][buckets] = 1;
        for (int i = 1; i < buckets; i++) {
            combinations[i][0] = 1;
            combinations[i][i] = 1;
            for (int j = 1; j < i; j++) {
                // 这是一个公式，要记住
                combinations[i][j] = combinations[i - 1][j - 1] + combinations[i - 1][j];
            }

            for (int j = 1; j <= iterator; j++) {
                for (int k = 0; k <= i; k++) {
                    // 第j轮，选出k个猪死掉，也就是毒药肯定在死掉的这些桶子里面了
                    // 这个k其实表示的是一次性排除了多少个桶子
                    dp[i][j] += dp[i - k][j - 1] * combinations[i][k];
                }
            }
            if (dp[i][iterator] >= buckets) {
                return i;
            }
        }
        return 0;
    }
}

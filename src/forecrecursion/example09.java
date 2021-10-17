package forecrecursion;

/**
 * 对 n皇后问题进行优化
 * n 皇后问题的时间复杂度是 O(N^N)，下面的方式知识加速运算，减少常数时间
 * 时间复杂度虽然还是这个指标，但是速度还是提升了不少
 *
 * */
public class example09 {

    public static int queen(int n) {
        if (n < 1 || n > 32) {
            return -1;
        }
        // 如果是8皇后问题，那么后8位都是1，前24位都是0
        // 如果是9皇后问题，那么后9位都是1，前23位都是0
        int limit = n == 32 ? -1 : (1 << n) - 1;
        return process(limit, 0, 0 , 0);
    }

    /**
     * columnLimit 表示的是列限制，如果是8皇后问题，那么columnLimit最开始就是 00000000，
     * leftLimit 表示的是左斜线限制，如果是8皇后问题，那么leftLimit最开始就是 00000000
     * rightLimit 表示的是右斜线限制，如果是8皇后问题，那么rightLimit最开始就是 00000000
     *
     * 比如说，我把第一个皇后放在了第2列(编号从0开始)
     * 那么columnLimit就变成了 00100000，
     *     leftLimit  变成了  01000000，
     *     rightLimit 变成了  00010000
     * 所以，上面三个limit的并集就是第二个皇后不能在的位置
     *
     * */
    public static int process(int limit, int columnLimit, int leftLimit, int rightLim) {
        if (limit == columnLimit) {
            return 1;
        }
        int pos = limit & (~(columnLimit | leftLimit | rightLim));  // 这个式子能够得到下一个皇后还能再的位置
        int res = 0;
        while (pos != 0) {
            int mostRightOne = pos & (~pos + 1);    // 取到最右侧的1，把皇后放在这个位置
            pos = pos - mostRightOne;
            res += process(limit, columnLimit | mostRightOne,
                    (leftLimit | mostRightOne) << 1, (rightLim | mostRightOne) >>> 1);
        }
        /**
         * 在这里我要解释一下为什么 left | mostright之后要左移一位
         *
         *   0 1 2 3 4 5 6 7
         * 0     *                   假设第一个皇后放到了2位置
         * 1   # # #     *           那么123这三个位置都不能够在放皇后了，假如第二个皇后被放到了6位置
         * 2 #       # # # #         那么567位置不能够在放皇后了 ，此外，0和4位置同样也不能够放皇后，因为
         * 3                         他们是在左右斜线上的点，如果第三个皇后放在了0位置，那么他就会和第一个皇后
         * 4                          共斜线！！，所以左斜线再 | mostRightOne之后，需要整体左移一位
         * 5                          同理，右斜线限制再 | mostRightOne之后，需要整体右移一位
         * 6
         * 7
         * */
        return res;
    }

    public static void main(String[] args) {
        System.out.println(queen(8));
    }
}

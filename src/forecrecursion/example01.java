package forecrecursion;

/**
 * 动态规划都是由暴力递归改出来的
 */

/**
 * 汉诺塔问题
 * 打印 n层汉诺塔从最左边到最右边的全部过程
 * */
public class example01 {

    /***
     * n表示的是圆盘的个数，只有三根柱子，from， to， other
     * 主要分成三个步骤
     *    1. 将 1 ~ n-1 个圆盘从from移动到other
     *    2. 将 第n个圆盘 从from移动到 to
     *    3. 将 1 ~ n-1 个圆盘从other移动回to
     *  通过上面这三步就能够将这n个圆盘从from移动到to了
     */

    public static void hantio(int n, String from, String to, String other) {
        if (n == 0) {
            // System.out.println("没有圆盘或者输入不合法");
            return;
        }
        hantio(n - 1, from, other, to);
        System.out.println("将圆盘" + n +"从"+ from + "移动到" + to);
        hantio(n-1,other,to,from);
    }

    public static void main(String[] args) {
        hantio(3,"左","中","右");
    }
}

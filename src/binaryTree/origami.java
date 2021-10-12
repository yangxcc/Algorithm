package binaryTree;

/**
 * 请把一张纸条竖着放在桌子上，然后从纸条的下面向上方对折一次，压出折痕后展开
 * 这时折痕是凹下去的，即折痕凸起的方向指向纸条的背面
 * 如果从纸条的下面向上方连续对折两次，压出折痕后展开，从上到下依次是下折痕、下折痕、上折痕
 *
 * 给定一个输入参数N，代表纸条从下方向上方连续对折N次，请从上到下打印出所有折痕的方向
 *
 * 例如 N=1,打印down
 * N=2时，打印down，down，up
 * */
public class origami {


    /**
     * 通过自己折纸可以发现
     * 第一次折纸会出现一个 1down
     * 第二次折纸会在 1down的上下 出现 2down（上）和2up（下）
     * 第三次折纸会在 2down的上下出现3down和3up，2up的上下出现3down和3up
     *
     *
     *              _1down_
     *            /        \
     *        _2down_     _2up_
     *       /       \   /     \
     *    3down     3up  3down  3up
     *                ......
     *
     *  所以看以看出，如果使用中序遍历，那么就能够从上到下按照要求输出了
     *
     * */
    // i 表示当前节点的层数
    public static void origami(int N, int i, boolean down) {
        if (i > N) {
            return;
        }
        origami(N, i + 1, true);   // 左子树为down
        System.out.println(down == true ? "down" : "up");
        origami(N, i + 1, false);  // 右子树为up
    }

    public static void printFolds (int N) {
        if (N <= 0) {
            return;
        }
        origami(N,1,true);
    }

    public static void main(String[] args) {
        printFolds(3);
    }
}

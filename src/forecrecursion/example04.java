package forecrecursion;

/**
 * 问题描述
 * 给定一个整型数组arr，代表数值不同的纸牌排成一条线，
 * 玩家A和玩家B依次拿走每张纸牌，规定玩家A先拿，玩家B后拿
 * 但是每个玩家每次只能拿走最左或者最右的纸牌，
 * 玩家A和玩家B都绝顶聪明，请返回最后获胜者的分数
 * <p>
 * 举例：arr=[1,2,100,4]
 * 开始时，玩家A只能拿走1或者4，如果开始时玩家A拿走1，那么排列变为[2,100,4]，接下来玩家B可以拿2或者4，然后继续轮到玩家A
 * 如果开始时玩家A拿走4，那么排列变为[1,2,100]，接下来玩家B一定会拿100，因为他们很聪明，都会使自己利益最大化，然后继续轮到玩家A
 * <p>
 * 所以，玩家A作为绝顶聪明的人绝对不会先拿4，所以他先拿1，让序列变成[2,100,4]，接下来不管玩家B怎么选，100都会被玩家A拿走，玩家A获胜，
 * 返回 101
 */
public class example04 {

    public static int first(int[] arr, int L, int R) {
        if (L == R) {
            return arr[L];
        }
        // 如果我先拿，我一定要让后面一个人的价值最少，我自己的价值最大
        return Math.max((arr[L] + second(arr, L + 1, R)), (arr[R] + second(arr, L, R - 1)));
    }

    public static int second(int[] arr, int L, int R) {
        if (L == R) {
            return 0;    // 注意这里是返回0，可以想一个极端情况，当只有一个数的时候，那个数被先手拿走了，后面就没有数了，只能返回0了
        }
        // 最开始先拿R   first(arr, L + 1, R),   最开始先拿L   first(arr, L, R - 1)
        return Math.min(first(arr, L + 1, R), first(arr, L, R - 1));
    }


    public static void main(String[] args) {
        int[] arr = {1,2,100,4};
        int res = first(arr,0,arr.length - 1);
        int res2 = second(arr,0,arr.length - 1);
        System.out.println(res);
        System.out.println(res2);
    }
}
